package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//네이티브 쿼리 만들기 
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{

	@Modifying //INSERT,DELETE,UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션이 필요!!
	@Query(value = "INSERT INTO subscribe( fromUserid, toUserid, createDate) VALUES(:fromUserid, :toUserid, now())", nativeQuery=true)
	void mSubscribe(int fromUserid, int toUserid); 
	
	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserid=:fromUserid AND toUserid=:toUserid", nativeQuery=true)
	void mUnSubscribe(int fromUserid, int toUserid); 
	
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalld AND toUserId = :pageUserId", nativeQuery = true)
	int mSubscribeState(int principalld, int pageUserId);
	
	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
	int mSubscribeCount(int pageUserId);
}
