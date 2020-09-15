package cn.action.modules.equip.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.sys.entity.User;
/**
 * �豸�����ƻ�ʵ����
 * @author Administrator
 *
 */
public class MaintenancePlan extends DataEntity<MaintenancePlan>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String equipType;//�豸����
	private String maintenance;//��������
	private String cycle;//��������
	private int warnTime;//Ԥ��ʱ�䣬����
	private User user;//�������������
	
	public MaintenancePlan() {
		super();
		this.user=new User();
	}
	
	public String getEquipType() {
		return equipType;
	}
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	public String getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public int getWarnTime() {
		return warnTime;
	}
	public void setWarnTime(int warnTime) {
		this.warnTime = warnTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
