package cn.action.modules.qc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.qc.dao.ViolactionDao;
import cn.action.modules.qc.entity.Violaction;
import cn.action.modules.tec.entity.ProcessStation;
import cn.action.modules.tec.service.ProcessStationService;

@Service
@Transactional(readOnly=true)
public class ViolactionService extends CrudService<ViolactionDao, Violaction>{
	
	@Autowired
	private ProcessStationService processStationService;
	/**
	 * ����
	 * @param violaction
	 * @return
	 */
	public boolean saveViolaction(Violaction violaction) {
		//��Υ���¼�еĹ�վ��Ӧ�Ĺ��������ӵ��ö�����
		ProcessStation processStation=processStationService.findProcessStationByStation(violaction.getWorkStationInfos());
		//�������Ϊ�գ�˵��û��ƥ��Ĺ��򣬾Ͳ������Υ���¼
		if(processStation==null) {
			return false;
		}
		violaction.setProcess(processStation.getProcess());
		this.save(violaction);
		return true;
	}
}
