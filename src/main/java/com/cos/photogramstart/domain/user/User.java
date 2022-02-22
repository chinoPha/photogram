package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//JPA -Java Persistence API(자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

@Builder
@AllArgsConstructor
@NoArgsConstructor //빈 생성자
@Data
@Entity //디비에 테이블을 생성
public class User {
	@Id // pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private int id;
	
	@Column(length = 100, unique=true) //제약조건을 걸어줌, OAuth2 로그인을 위해 컬럼 늘리기
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	private String website;
	private String bio; //자기소개
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;  // 나중에 작성자(user) 사진
	private String role; // 권한
	
	// 나는 연관관계의 주인이 아니다. 그러므로 테이블에 칼럼을 만들지마.
	// User 를 Select할 때 해당 User id로 등록된 image들을 다 가져와
	// Lazy = User를 Select할 때 해당 User id로 등록된 image들을 가져오지마. - 대신 getImages() 함수의 image들이 호출될 때 가져와!!
	// Eager = User를 Select할 때 해당 User id로 등록된 image들을 전부 Join해서 가져와!!
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"})
	private List<Image> images; // 양방향 매핑
	
	//데이터베이스는 항상 데이터가 언제들어왔는지 확인해주는 LocalDateTime createDate; 필요하다.
	private LocalDateTime createDate; 
	
	//위의 데이터를 직접 집어넣을게 아니라 자동으로 넣을것이기에
	@PrePersist // 디비에 INSERT 되기 직전에 실행 - id pw....만 입력하면 createDate 값은 자동으로 넣어주는 어노테이션
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role +", createDate="
				+ createDate + "]";
	}

	
}
