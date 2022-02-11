package com.cos.photogramstart.web.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T>{ // 전역으로 사용하고 싶을땐 제네릭
	private int code; //1(성공), -1(실패)		// 응답을 할때 필요한 코드
	private String message;
	private T data;
}
