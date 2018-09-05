package org.santanubrains.hystrix.response;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorResponse {

	private Integer errorCode;
	private String errorMessage;
	private String documentation;
	private Date date;

	public ErrorResponse() {

	}

	public ErrorResponse(Integer errorCode, String errorMessage, Date date) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.date = date;
	}

	public ErrorResponse(Integer errorCode, String errorMessage, String documentation) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.documentation = documentation;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
