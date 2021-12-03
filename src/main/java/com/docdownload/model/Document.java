package com.docdownload.model;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Document implements Serializable {

	private Long id;

	private String customerId;

	private String docCorrespondingNumber;

	private String rejectReasons;

	private String name;

	private long size;

	private String fileType;

	private String status;

	private Date uploadTime;

	private Date updatedOn;

	@JsonIgnore
	private byte[] content;

	public Document() {

	}

	public Document(Long id, String customerId, String docCorrespondingNumber, String status, Date uploadTime, String fileType) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.docCorrespondingNumber = docCorrespondingNumber;
		this.status = status;
		this.uploadTime = uploadTime;
		this.fileType=fileType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDocCorrespondingNumber() {
		return docCorrespondingNumber;
	}

	public void setDocCorrespondingNumber(String docCorrespondingNumber) {
		this.docCorrespondingNumber = docCorrespondingNumber;
	}

	public String getRejectReasons() {
		return rejectReasons;
	}

	public void setRejectReasons(String rejectReasons) {
		this.rejectReasons = rejectReasons;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
