package com.cos.photogramstart.handler.ex;

import java.util.Map;

//에러딴에 데이터만이 아니라 에러문구도 확인하고자 만듦
public class CustomException extends RuntimeException{
	// 시리얼 번호 - 객체를 구분할 때 !! JVM한테 중요함.
	private static final long serialVersionUID = 1L;
	
	public CustomException(String message) {
		super(message); 
	}
	
}
