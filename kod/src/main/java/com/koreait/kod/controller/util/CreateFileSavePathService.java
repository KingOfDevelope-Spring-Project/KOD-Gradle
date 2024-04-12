package com.koreait.kod.controller.util;

import java.io.File;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CreateFileSavePathService {

	public String[] getUploadFilePath(HttpServletRequest request) {
		
		// 업로드할 파일 저장경로 생성(원본파일이 저장됨)
		String uploadFilePath = request.getServletContext().getRealPath("uploads/orijinDir")+File.separator;
		// uploadFilePath : C:\Users\Springonward\Desktop\KOIT\KODsoundsFinal\kod\src\main\webapp\*uploads\orijinDir\
		System.out.println("[로그:정현진] uploadFilePath : "+uploadFilePath);
		
		// 서로 다른 작업 환경(PC)에서도 별도의 작업없이 경로를 공유할 수 있는 복사 경로 만들기
        int num = uploadFilePath.lastIndexOf("orijinDir"); // 마지막으로 나오는 파일 구분자(.metadata)
        String forwardUrlParts = uploadFilePath.substring(0, num);
        System.out.println("[로그:정현진] num : "+num); // 78
        // forwardUrlParts : C:\Users\Springonward\Desktop\KOIT\KODsoundsFinal\kod\src\main\webapp\*uploads\
        System.out.println("[로그:정현진] forwardUrlParts : "+forwardUrlParts);
        
        // 복사파일이 저장될 경로 설정
        String addUrlParts = "thumnailDir";
        String copyFilePath = forwardUrlParts + addUrlParts + File.separator;
        // copyFilePathForKOD : C:\Users\Springonward\Desktop\KOIT\KODsoundsFinal\kod\src\main\webapp\*uploads\thumnailDir\
        System.out.println("[로그:정현진] copyFilePathForKOD : "+copyFilePath);
		
		String[] uploadFilePaths = {uploadFilePath, copyFilePath};
		
		return uploadFilePaths;
	}
}
