package cn.action.modules.equip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.common.utils.UserUtils;
import cn.action.modules.equip.dao.EquipFaultReportDao;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.entity.Equipment;

@Service
@Transactional(readOnly=true)
public class EquipFaultReportService extends CrudService<EquipFaultReportDao, EquipFaultReport>{
	@Autowired
	private EquipmentService equipmentService;
	/**
	 * �����豸�����ϱ���¼
	 * @param equipFaultReport
	 * @return
	 */
	public boolean saveFaultReport(EquipFaultReport equipFaultReport) {
		//�����豸��Ų�ѯ�Ƿ�������豸�����û�У����ܱ��棬����������Ӧ������Ϣ������
		Equipment equipment=new Equipment();
		equipment.setQrCode(equipFaultReport.getEquipNo());
		Equipment equip=equipmentService.getByQRCode(equipment);
		if(equip==null) {
			return false;
		}
		//�����������
		equipFaultReport.setEquipId(equip.getId());//�����豸Id
		equipFaultReport.setReportPerson(UserUtils.getUser().getName());//�����ϱ���
		equipFaultReport.setStatus("0001");//����״̬Ϊ���ϱ���
		//����
		this.save(equipFaultReport);
		return true;
	}
	/**
	 * �Ƿ��Ѿ��ɹ�
	 * @param equipFaultReport
	 * @return
	 */
	public boolean isAssign(EquipFaultReport equipFaultReport) {
		EquipFaultReport report=this.get(equipFaultReport);
		if(!report.getStatus().equals("0001")) {
			return false;
		}
		return true;
	}
		/**
	 * ����
	 */
	public String saveStartRepair(EquipFaultReport equipFaultReport)
	{
		String message=null;
		EquipFaultReport report =this.get(equipFaultReport);
		if(report.getStatus().equals("0001"))
		{
			message="�豸�Ѿ����ޣ����ڵȴ��ɹ�";
		}else if(report.getStatus().equals("0003")){
			message="�豸��ʼά�ޣ������ظ�����";
		}else if(report.getStatus().equals("0004")){
			message="�豸ά���Ѿ��깤";
		}else{
			report.setStatus("0003");
			this.save(report);
			message="���Ͽ�ʼά��";
		}
		return message;
	}
}
