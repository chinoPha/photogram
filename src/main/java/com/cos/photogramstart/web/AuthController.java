package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.web.dto.auth.SignupDto;

@Controller // 1. IoC에 등록됨. 2. 파일을 리턴하는 컨트롤러
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
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
	public String signup(SignupDto signupDto) { // key= value(x-www-form-urlencoded)
		log.info(signupDto.toString());
		return"auth/signin";	//회원가입이 성공하면 로그인 페이지로 
	}
	
}


