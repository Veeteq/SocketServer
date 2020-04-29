package com.wojnarowicz.socket.data;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private boolean isSuccess;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
