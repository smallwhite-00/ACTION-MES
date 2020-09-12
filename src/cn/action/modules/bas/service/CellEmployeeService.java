package cn.action.modules.bas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.CellEmployeeDao;
import cn.action.modules.bas.entity.CellEmployee;

@Service
@Transactional(readOnly=true)
public class CellEmployeeService extends CrudService<CellEmployeeDao, CellEmployee>{
	@Autowired
	private CellEmployeeDao cellEmployeeDao;
	/**
	 * ����
	 * @param cellEmployee
	 * @return
	 */
	public CellEmployee saveRelation(CellEmployee cellEmployee) {
		//�ж��Ƿ�����ӣ�����Ӿ���Ҫ�ж�Ա���Ƿ��Ѿ��󶨹�λ�ˣ���������
		if("".equals(cellEmployee.getId())) {
			//����Ա��id�ж��Ƿ�󶨹�λ��������ˣ��ͷ��ع�ϵ����
			CellEmployee relation=cellEmployeeDao.findByEmployeeId(cellEmployee);
			if(relation!=null) {
				//˵����Ա���Ѿ��󶨹�λ
				return relation;
			}
		}
		this.save(cellEmployee);
		return null;
	}
}
