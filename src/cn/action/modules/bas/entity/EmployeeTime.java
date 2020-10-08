package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class EmployeeTime extends DataEntity<EmployeeTime>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int week;
    private int month;
    private int year;
    private MountGuard MountGuard;
    private String clockIn;
    private String clockOff;
    private String employeeName;
	public MountGuard getMountGuard() {
		return MountGuard;
	}
	public String getClockIn() {
		return clockIn;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	public void setMountGuard(MountGuard mountGuard) {
		MountGuard = mountGuard;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	}
