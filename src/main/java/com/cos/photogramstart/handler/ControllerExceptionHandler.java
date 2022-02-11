package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.utill.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController  // 데이터 응답
@ControllerAdvice //모든 Exception 다 낚아챔
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class) // RuntimeException이 발동하는 모든 Exception을 validationException함수가 가로챔
	public String validationException(CustomValidationException e) { //<>제네릭 사용시 <?>를 사용하면 알아서 찾아줌.
		/*public CMRespDto<?>*/	
		// CMRespDto, Script 비교
		// 1. 클라이언트에게 응답할 때는 Script 좋음. - 통신을 '브라우저'로 받는거(클라이언트)
		// 2. Ajax통신 - CMRespDto 가 좋음 - 개발자가 자바스크립트 코드로 서버쪽으로 던져서 응답받는거(개발자)
		// 3. Android통신 - CMRespDto 가 좋음 - 응답을 안드로이드앱에서 개발자가 받는거(개발자)
		return Script.back(e.getErroMap().toString());
				/*return new CMRespDto<Map<String,String>>(-1, e.getMessage(),e.getErroMap());*/
	}
}
