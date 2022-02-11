package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI 할때 사용-final이 걸려있는 모든 객체들의 생성자를 만들어줌.
@Controller // 1. IoC에 등록됨. 2. 파일을 리턴하는 컨트롤러
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	private final AuthService authService; // 밑에 방법을 사용하지 않고 final 을 사용해주며, 이때 final 특성으로 authService의 초기화를 무조건 해주어야 하는데 
																// @RequiredArgsConstructor을 어노테이션해주어 오류를 발생시키지 않게 한다.

	/*
	 * public AuthController(AuthService authService) { 
	 *    this.authService = authService; } // @Controller를 사용하면 스프링에서 컨테이너를 관리하는 메모리로 AuthController 로드를 하여 메모리 객체를 생성하여 
	 * 								관리를 하게 되는데 객체를 생성하기위해서는 첫번째 조건, 생성자를 만들어 실행을 시켜야 한다. 지금 생성된 AuthController를 사용할 경우
	 * 								IoC등록된 애들중에서 authService에 해당하는 Type이 있으면 this.authService 넣어준다(의존성 주입)
	 * 								없으면 오류가 발생. 현재 오류가 발생하지 않는 이유는 AuthService에 @Service를 어노테이션 해주어 Ioc 등록시켜 주었기 때문이다.
	 */
	@GetMapping("/auth/signin")
	public String signinForm() {
		return"auth/signin";	// 로그인 페이지
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return"auth/signup";	//가입하기 페이지
	}
	
	//회원가입버튼->/auth/signup -> /auth/signin
	//회원가입버튼-> X // CSRF 토큰이 실행돼 있어서 
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key= value(x-www-form-urlencoded)
				//@Valid SignupDto signupDto 서 오류가 발생하면 bindingResult에 getFieldErrors()라는 컬렉션에 모아줌
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("====================");
				System.out.println(error.getDefaultMessage());
				System.out.println("====================");
			}
		}
		
		log.info(signupDto.toString());
		//User <- SignupDto
		User user = signupDto.toEntity();
		User userEntity = authService.회원가입(user); // 회원가입을 하고 userEntity에 받아짐
		System.out.println(userEntity);
		//log.info(user.toString());
		return"auth/signin";	//회원가입이 성공하면 로그인 페이지로 
	}
	
}


