package com.cos.photogramstart.web.dto.auth;

import lombok.Data;

//회원가입 정보를 요청하는 DTO
@Data //Getter, Setter를 만들어주는 어노테이션
public class SignupDto {
	private String username;
	private String password;
	private String email;
	private String name;
}
