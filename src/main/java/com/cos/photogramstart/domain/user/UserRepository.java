package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 JpaRepository를 상속하면 IoC에 자동으로 등록된다.
public interface UserRepository extends JpaRepository<User, Integer>{

}
