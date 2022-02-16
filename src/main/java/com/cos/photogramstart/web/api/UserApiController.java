package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;

	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
			@PathVariable int id,
			@Valid UserUpdateDto userUpdateDto,
			BindingResult bindingResult, // 꼭 @Valid가 적혀있는 다음 파라메터에 적어야됨.
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) { // for문을 돌면서 컬렉션의 오류를 error에 담아줌
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성 검사 실패함.",errorMap);
		}
		
		User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
		principalDetails.setUser(userEntity);//세션 정보 변경
		return new CMRespDto<>(1,"회원수정완료",userEntity);
	}
}
