package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //1.IoC에 등록 2. 트랙잭션 관리 
public class AuthService {
	// db에 인서트 하기위해
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional // Write(Insert, Update, Delete)
	public User 회원가입(User user) {
		// 회원가입 진행
	String rawPassword = user.getPassword();
	String encPassword = bCryptPasswordEncoder.encode(rawPassword); 
	user.setPassword(encPassword); //암호화가 됨
	user.setRole("ROLE_USER"); //ROLE_USER 회원가입시 권한은 넣어줌./ 관리자는 ROLE_ADMIN
	User userEntity = userRepository.save(user); //DB에 잘 들어가고난 후에 Entity 응답
	return userEntity;
	}
}
