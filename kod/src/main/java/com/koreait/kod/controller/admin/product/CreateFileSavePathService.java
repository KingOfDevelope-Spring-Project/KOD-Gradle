package com.koreait.kod.controller.admin.product;

import java.io.File;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CreateFileSavePathService {

	public String[] getUploadFilePath(HttpServletRequest request) {
		
		// 업로드된 파일을 저장할 경로 설정
		String uploadFilePath = request.getServletContext().getRealPath("uploads");
		
		// 서로다른 작업 환경(PC)에서도 별도의 작업없이 경로를 공유할 수 있는 복사 경로 만들기
        int num = uploadFilePath.indexOf("."); // 첫번째 만나는 .의 인덱스 값 (.metadata)
        String forwardUrlParts = uploadFilePath.substring(0, num);
        System.out.println("[로그:정현진] num : "+num); // 45
        System.out.println("[로그:정현진] forwardUrlParts : "+forwardUrlParts);
        
        String middleUrlParts = request.getContextPath();
        System.out.println("[로그:정현진] middleUrlParts : "+middleUrlParts);
        
        String lastUrlParts = "/src/main/webapp/uploads";
        String copyFilePathForKOD = forwardUrlParts+middleUrlParts+lastUrlParts+File.separator;
        System.out.println("[로그:정현진] copyFilePathForKOD : "+copyFilePathForKOD);
        // C:\Users\Springonward\Desktop\KOIT\KODsounds\/kod/src/main/webapp/uploads\
		
		String[] UploadFilePath = {uploadFilePath,copyFilePathForKOD};
		
		return UploadFilePath;
	}
	
}
