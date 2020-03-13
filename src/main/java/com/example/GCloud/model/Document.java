package com.example.GCloud.model;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="documents")
public class Document {
	@Id
	@GeneratedValue
	@Column(name="FILE_ID")
	private Integer id;
	
	@Column(name="FILE_NAME")
	private String fileName;
	
	@Column(name="FILE_DATA")
	private byte[] data;
	
	@Column(name="USER_ID")
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String name) {
		this.fileName = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
		
}
