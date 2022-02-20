package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	private boolean pageOwnerState;		//이 페이지의 주인인지 아닌지 여부 데이터(해당 페이지 오너의 상태)
	private int imageCount;
	private boolean subscribeState; //구독을 하는 상태인지
	private int subscribeCount;
	private User user;
}
