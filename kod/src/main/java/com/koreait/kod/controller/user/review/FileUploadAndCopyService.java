package com.koreait.kod.controller.user.review;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;


@Service // @Service 을 사용하면 AOP를 지원함 => 트랜잭션 처리 + 모듈화
public class FileUploadAndCopyService {
	
	public List<String> uploadAndCopy(String uploadFilePath, List<MultipartFile> uploadReviewImages)
			throws IOException {
		// UUID 목록을 저장할 리스트 생성
		List<String> uuids = new ArrayList<String>();

		// 업로드할 디렉토리 생성
		File uploadDir = new File(uploadFilePath);
		if (!uploadDir.exists()) {
			// 디렉토리가 존재하지 않을 경우 생성
			if (!uploadDir.mkdirs()) {
				// 디렉토리 생성에 실패한 경우 IOException 발생
				throw new IOException("Failed to create directory: " + uploadDir);
			}
		}

		// 업로드된 각 이미지에 대해 처리
		for (MultipartFile uploadReviewImage : uploadReviewImages) {
			// UUID 생성
			String uuid = UUID.randomUUID().toString();
			// 업로드된 파일의 원래 이름 가져오기
			String originalFilename = uploadReviewImage.getOriginalFilename();
			// 파일 이름이 존재하고 비어 있지 않은 경우에만 처리
			if (originalFilename != null && !originalFilename.isEmpty()) {
				// UUID를 목록에 추가
				uuids.add(uuid);
				// 새 파일 생성
				File file = new File(uploadDir, uuid + "_" + originalFilename);
				try {
					// 업로드된 파일을 새 파일로 복사
					uploadReviewImage.transferTo(file); // 파일 복사

					// 이미지인 경우에만 썸네일 생성
					if (uploadReviewImage.getContentType() != null
							&& uploadReviewImage.getContentType().startsWith("image")) {
						// 썸네일 파일 생성
						File thumbnailFile = new File(uploadDir, "t_" + uuid + "_" + originalFilename);
						try (InputStream inputStream = uploadReviewImage.getInputStream();
								OutputStream outputStream = new FileOutputStream(thumbnailFile)) {
							// 이미지를 읽어와 썸네일 생성
							Thumbnails.of(file).size(100, 100).toOutputStream(outputStream);
						}
					}
				} catch (IOException e) {
					e.printStackTrace(); // 적절한 로깅 또는 예외 처리 추가
					// 파일 복사 실패 시 예외 처리
				}
			}
		}

		// UUID 목록 반환
		return uuids;
	}
}
