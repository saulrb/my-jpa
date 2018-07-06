package com.codegeeks.jpa.entity;

import java.util.Date;

import javax.persistence.*;

import com.codegeeks.jpa.util.BooleanConverter;


@Entity
@Table(name = "T_ID_CARD")
public class IdCard {

	private Long id;
	private String idNumber;
	private Date issueDate;
	private boolean valid;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ID_NUMBER")
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Column(name = "ISSUE_DATE")
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	@Column(name = "VALID")
	@Convert(converter = BooleanConverter.class)
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
}
