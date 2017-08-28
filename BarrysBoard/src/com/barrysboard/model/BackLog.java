package com.barrysboard.model;

import java.time.LocalDate;

public class BackLog {

	private int logId;
	private String csrID;
	private String companyID;
	private LocalDate date;
	private int actualBackLog;
	private int priorBackLog;

	public BackLog(int logId, String csrID, String companyID, LocalDate date, int actualBackLog, int priorBackLog) {
		this.logId = logId;
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.actualBackLog = actualBackLog;
		this.priorBackLog = priorBackLog;
	}
	
	public BackLog(String csrID, String companyID, LocalDate date, int actualBackLog, int priorBackLog) {
		this.csrID = csrID;
		this.companyID = companyID;
		this.date = date;
		this.actualBackLog = actualBackLog;
		this.priorBackLog = priorBackLog;
	}
	
	
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getCsrID() {
		return csrID;
	}

	public void setCsrID(String csrID) {
		this.csrID = csrID;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getActualBackLog() {
		return actualBackLog;
	}

	public void setActualBackLog(int actualBackLog) {
		this.actualBackLog = actualBackLog;
	}

	public int getPriorBackLog() {
		return priorBackLog;
	}

	public void setPriorBackLog(int priorBackLog) {
		this.priorBackLog = priorBackLog;
	}
}
