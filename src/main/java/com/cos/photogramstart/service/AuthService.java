package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //1.IoC에 등록 2. 트랙잭션 관리 
public class AuthService {
	// db에 인서트 하기위해
	private final UserRepository userRepository;
	
	public User 회원가입(User user) {
		// 회원가입 진행
	User userEntity = userRepository.save(user); //DB에 잘 들어가고난 후에 Entity 응답
	return userEntity;
	}
}
