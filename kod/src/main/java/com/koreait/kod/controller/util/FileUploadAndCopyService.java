package com.koreait.kod.controller.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class FileUploadAndCopyService {
	
    public List<String> uploadAndCopy(String[] filePaths, List<MultipartFile> uploadReviewImages)
            throws IOException {
    	
        String originFilePath = filePaths[0]; // 원본파일 저장경로
        String thumnaulFilePathForDB = filePaths[1]; // 썸네일파일 저장경로
    	
        List<String> imageUrlList = new ArrayList<String>(); // UUID 목록을 저장할 리스트 생성

        File originFileUploadDir = new File(originFilePath+getPath()); // 원본파일 저장 폴더
        File thumnailFileUploadDir = new File(thumnaulFilePathForDB+getPath()); // 복사파일 저장폴더
        
        // 원본폴더가 존재하지 않을 경우 생성
        if (!(originFileUploadDir.exists() || originFileUploadDir.mkdirs())) {
            throw new IOException("원본파일 저장폴더 생성 실패 : " + originFileUploadDir);
        }

        // 썸네일폴더가 존재하지 않을 경우 생성
        if (!(thumnailFileUploadDir.exists() || thumnailFileUploadDir.mkdirs())) {
            throw new IOException("복사파일 저장폴더 생성 실패 : " + thumnailFileUploadDir);
        }

        // 업로드된 각 이미지에 대해 처리
        for (MultipartFile uploadReviewImage : uploadReviewImages) {
            // UUID 생성
            String uuid = UUID.randomUUID().toString();
            // 업로드된 파일의 원래 이름 가져오기
            String originalFilename = uploadReviewImage.getOriginalFilename();
            // 만약 파일이름이 존재한다면
            if (!(originalFilename == null || originalFilename.isEmpty())) {
                // 원본폴더 경로에 복사할 파일 메모리 할당
                File file = new File(originFileUploadDir, uuid + "_" + originalFilename);
                try {
                    // 원본파일 파일복사 실행, 단순 파일 복사
                    uploadReviewImage.transferTo(file); // 파일 복사
                    System.out.println("[로그:정현진] 원본file : "+file);

                 // 이미지인 경우에만 썸네일 생성
                    if (uploadReviewImage != null && uploadReviewImage.getContentType().startsWith("image")) {
                        System.out.println("[로그:정현진] 썸네일 파일 생성 들어옴 ");
                        // 썸네일 파일 생성, 이미지 조작(사이즈조절)을 위해 원본파일의 복사방법과는 다른 방식으로 진행
                        File thumbnailFile = new File(thumnailFileUploadDir, "thumbnail_" + uuid + "_" + originalFilename);
                        System.out.println("[로그:정현진] thumnailFileUploadDir : " + thumnailFileUploadDir);
                        System.out.println("[로그:정현진] thumbnailFile : " + thumbnailFile);
                        try (InputStream inputStream = new FileInputStream(file); // 파일의 InputStream 생성
                             OutputStream outputStream = new FileOutputStream(thumbnailFile)) {
                            System.out.println("[로그:정현진] 썸네일 파일복사 실행 들어옴");
                            // 이미지를 읽어와 썸네일 생성
                            Thumbnails.of(inputStream).size(100, 100).toOutputStream(outputStream);
                        }
                        
                        // imageUrl를 imageUrlList에 추가 (썸네일 파일의 UUID)
                        System.out.println("[로그:정현진] thumnailFilePathForKOD : "+thumnaulFilePathForDB);
                        System.out.println("[로그:정현진] thumnailFilePath : "+thumnailFileUploadDir+File.separator+"thumbnail_" + uuid + "_" + originalFilename);
//                        Path targetPath = Paths.get(thumnailFileUploadDir+getPath()); // 절대 경로
//                        System.out.println("[로그:정현진] targetPath : "+targetPath);
                        int num = thumnaulFilePathForDB.lastIndexOf("uploads"); // 마지막으로 나오는 파일 구분자(.metadata)
                        String relativePath = thumnaulFilePathForDB.substring(0, num);
                        System.out.println("[로그:정현진] relativePath : "+relativePath);
                        relativePath = thumnaulFilePathForDB.replace(relativePath, "");
                        System.out.println("[로그:정현진] relativePath : "+File.separator+relativePath+File.separator+getPath()+"thumbnail_" + uuid + "_" + originalFilename);
                        imageUrlList.add(File.separator+relativePath+getPath()+"thumbnail_" + uuid + "_" + originalFilename);
                    } else {
                        // 썸네일생성 실패 시 원본 파일의 imageUrl을 imageUrlList에 추가
                    	imageUrlList.add(originFilePath+File.separator+uuid + "_" + originalFilename);
                    } 
                } catch (IOException e) {
                    e.printStackTrace(); // 적절한 로깅 또는 예외 처리 추가
                    // 파일 복사 실패 시 예외 처리
                }
            }
        }
        // imageUrlList 목록 반환
        return imageUrlList;
    }
    
    public String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}




