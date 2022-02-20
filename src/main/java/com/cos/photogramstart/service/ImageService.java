package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	//db에 저장하기위해 
	private final ImageRepository imageRepository;
	
	@Transactional(readOnly = true) //영속성 컨텍스트 변경 감지를 해서, 더티체킹, flush(반영) X /인설트하지않고 읽기만 하겠다.
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		Page<Image> images = imageRepository.mStory(principalId, pageable);
		return images;
	}
	
	@Value("${file.path}")		//org.springframework, ${"application.yml에 등록된 이름"}
	private String uploadFolder; //
	
	//사진 업로드를 하는 함수 생성
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		//사진 파일의 이름 중복방지
		UUID uuid = UUID.randomUUID();// uuid
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); // 실제 파일 네임이 들어감. 1.jpg
		System.out.println("이미지 파일 이름: "+imageFileName);
		
		//실제 저장 경로 - 저장용도
		Path imageFilPath = Paths.get(uploadFolder+imageFileName); // 경로와 파일명
		
		//통신, I/O(하드디스크에 기록을 하거나 읽을때) - > 예외가 발생할 수 있다.
		try {
			Files.write(imageFilPath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); // 이미지 파일 이름: cde1326a-edf7-4d33-ba38-a6337eb92320_are.jpg
		imageRepository.save(image);
		
		//System.out.println(imageEntity.toString());
	}
}
