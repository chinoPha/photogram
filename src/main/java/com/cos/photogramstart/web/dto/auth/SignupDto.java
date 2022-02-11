package com.cos.photogramstart.web.dto.auth;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

//회원가입 정보를 요청하는 DTO
@Data //Getter, Setter를 만들어주는 어노테이션
public class SignupDto {
	@Size(min = 2,max= 20) // 문자열, 배열등의 크기를 만족하는가?
	@NotBlank
	private String username;
	@NotBlank // Null, 빈 문자열, 스페이스만 있는 문자열 불가
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
