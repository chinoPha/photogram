package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController  // 데이터 응답
@ControllerAdvice //모든 Exception 다 낚아챔
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class) // RuntimeException이 발동하는 모든 Exception을 validationException함수가 가로챔
	public Map<String, String> validationException(CustomValidationException e) {
		return e.getErroMap();
	}
}
