package cn.action.modules.kpi.entity;

import cn.action.common.persistence.DataEntity;
/**
 * ��Ƭ��Чʵ����
 * @author Administrator
 *
 */
public class CutPiece extends DataEntity<CutPiece>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String time;//����
	private String employeeName;//��Ա����
	private String pieces;//��Ƭ��
	private String money;//н��
	private String startTime;//��ʼʱ��
	private String endTime;//����ʱ��
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPieces() {
		return pieces;
	}
	public void setPieces(String pieces) {
		this.pieces = pieces;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
