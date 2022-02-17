package com.cos.photogramstart.handler.ex;

import java.util.Map;


public class CustomApiException extends RuntimeException{
	
	// 시리얼 번호 - 객체를 구분할 때 !! JVM한테 중요함.
	private static final long serialVersionUID = 1L;
	
	public CustomApiException(String message) {
		super(message); 
	}
}
