package com.cos.photogramstart.handler.ex;

import java.util.Map;

//에러딴에 데이터만이 아니라 에러문구도 확인하고자 만듦
public class CustomValidationException extends RuntimeException{
	// 시리얼 번호 - 객체를 구분할 때 !! JVM한테 중요함.
	private static final long serialVersionUID = 1L;

	/* private String message; */ //부모한테 던질거라 필요없음
	private Map<String, String>errorMap;
	
	public CustomValidationException(String message,Map<String, String>errorMap) {
		super(message); // 부모한테 던질때는 super
		/* this.message = message; */  //부모한테 던질거라 필요없음
		this.errorMap = errorMap;
	}
	
	public Map<String, String>getErroMap(){
		return errorMap;
	}
}
