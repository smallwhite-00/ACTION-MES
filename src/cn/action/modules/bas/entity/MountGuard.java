package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;
/**
 * Ա��������¼��
 * @author Administrator
 *
 */
import cn.action.modules.sys.entity.Office;
public class MountGuard extends DataEntity<MountGuard>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MountGuard() {
		super();
		this.employee=new Employee();
		this.office=new Office();
		this.line=new Line();
		this.workStationInfos=new WorkStationInfos();
		this.workCell=new WorkCell();
	}
	private Employee employee;	
	private Office office;
	private Line line;	
	private WorkStationInfos workStationInfos;
	private WorkCell workCell;
	private String clockIn;//�ϰ�ʱ��
	private String clockOff;//�°�ʱ��
	private String workStatus;//����״̬

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public WorkStationInfos getWorkStationInfos() {
		return workStationInfos;
	}
	public void setWorkStationInfos(WorkStationInfos workStationInfos) {
		this.workStationInfos = workStationInfos;
	}
	public WorkCell getWorkCell() {
		return workCell;
	}
	public void setWorkCell(WorkCell workCell) {
		this.workCell = workCell;
	}
	public String getClockIn() {
		return clockIn;
	}
	public void setClockIn(String clockIn) {
		this.clockIn = clockIn;
	}
	public String getClockOff() {
		return clockOff;
	}
	public void setClockOff(String clockOff) {
		this.clockOff = clockOff;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}


}
