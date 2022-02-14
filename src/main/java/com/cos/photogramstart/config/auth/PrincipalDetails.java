package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;

	public PrincipalDetails(User user) {
		this.user = user;
	}

	// 권한 : 한개가 아닐 수 있음.(경우의 따라 3개이상의 권한)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // 권한을 가져오는 함수

		Collection<GrantedAuthority> collector = new ArrayList<>();
		collector.add(() -> {return user.getRole();}); //람다식: add안에 함수를 넣고싶음. 인터페이스를 넣어줌.
		return collector;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true; // false 떨어지면 로그인이 안됨.
	}

	@Override
	public boolean isAccountNonLocked() {

		return true; // false 떨어지면 로그인이 안됨.
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true; // false 떨어지면 로그인이 안됨.
	}

	@Override
	public boolean isEnabled() {

		return true; // false 떨어지면 로그인이 안됨.
	}

}
