package com.simple.training.domain.common.base64;

import java.io.Serializable;

public class Base64 implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String type;
	private byte[] src;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getSrc() {
		return src;
	}
	public void setSrc(byte[] src) {
		this.src = src;
	}
	
}
