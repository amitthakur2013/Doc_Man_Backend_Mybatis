package com.docdownload.utility;

import org.springframework.stereotype.Component;

@Component
public class SearchFilterRequest {
	
	private String status;
	
	private String customerId;
	
	private String docType;
	
	private String fromDate;
	
	private String toDate;

	public SearchFilterRequest() {
		
	}

	public SearchFilterRequest(String status, String customerId, String docType, String fromDate, String toDate) {
		this.status = status;
		this.customerId = customerId;
		this.docType = docType;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	

}
