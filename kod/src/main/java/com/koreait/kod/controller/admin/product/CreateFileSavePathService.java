package com.koreait.kod.controller.admin.product;

import java.io.File;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CreateFileSavePathService {

	public String[] getUploadFilePath(HttpServletRequest request) {
		
		// 업로드할 파일 저장경로 생성(원본파일이 저장됨)
		String uploadFilePath = request.getServletContext().getRealPath("uploads/orijinDir");
		// C:\Users\Springonward\Desktop\KOIT\KODsoundsFinal\kod\src\main\webapp/uploads
		System.out.println("[로그:정현진] uploadFilePath : "+uploadFilePath);
		
		// 서로 다른 작업 환경(PC)에서도 별도의 작업없이 경로를 공유할 수 있는 복사 경로 만들기
        int num = uploadFilePath.lastIndexOf(File.separator); // 마지막으로 나오는 파일 구분자(.metadata)
        String forwardUrlParts = uploadFilePath.substring(0, num);
        System.out.println("[로그:정현진] num : "+num); // 77
        // C:\Users\Springonward\Desktop\KOIT\KODsoundsFinal\kod\src\main\webapp
        System.out.println("[로그:정현진] forwardUrlParts : "+forwardUrlParts);
        
        // 복사파일이 저장될 경로 설정
        String addUrlParts = "/thumnailDir";
        String copyFilePath = forwardUrlParts + addUrlParts + File.separator;
        System.out.println("[로그:정현진] copyFilePathForKOD : "+copyFilePath);
        // C:\Users\Springonward\Desktop\KOIT\KODsoundsFinal\kod\src\main\webapp/thumnailImg\
		
		String[] uploadFilePaths = {uploadFilePath, copyFilePath};
		
		return uploadFilePaths;
	}
}
