package com.koreait.kod.controller.user.review;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.kod.model.member.MemberDTO;
import com.koreait.kod.model.review.ReviewDTO;
import com.koreait.kod.model.review.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewWriteController {
	
	@Autowired
	ReviewService reviewService;
	@Autowired
	FileUploadAndCopy fileUploadAndCopy;
    @Value("${upload.file.path}")
    private String uploadFilePath;
	
    @RequestMapping(value = "/reviewWrite", method = RequestMethod.POST)
    public String reviewWrite(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("score") int reviewScore,
                              @RequestParam("productID") int productID,
                              @RequestParam("orderContentID") int orderContentID,
                              @RequestParam("reviewImageName") String reviewImageName,
                              @RequestParam("uploadReviewImages") List<MultipartFile> uploadReviewImages,
                              ReviewDTO reviewDTO,
                              HttpSession session) throws IOException {

        // 현재 세션에서 회원 아이디 가져오기
        String memberID = ((MemberDTO) session.getAttribute("memberDTO")).getMemberID();
        
        // ReviewDTO 설정
        reviewDTO.setReviewTitle(title);
        reviewDTO.setReviewContent(content);
        reviewDTO.setReviewScore(reviewScore);
        reviewDTO.setMemberID(memberID);
        reviewDTO.setProductID(productID);
        reviewDTO.setOdContentID(orderContentID);
        
        // ReviewDTO를 데이터베이스에 삽입
        reviewService.insert(reviewDTO);
        
        // 파일을 업로드하고 복사하는 메서드 호출하여 UUID 목록 가져오기
        List<String> uuids = fileUploadAndCopy.uploadAndCopy(uploadFilePath, uploadReviewImages);
        
        // 파일이 업로드되었을 경우에만 파일 처리
        if (!uuids.isEmpty()) {
            // 첫 번째 파일에 대한 경로 생성
            String filePath = uploadFilePath + File.separator + uuids.get(0) + "_" + reviewImageName;
            File copyFile = new File(filePath);
            
            // 복사 파일이 존재하지 않을 경우 생성
            if (!copyFile.exists()) {
                if (copyFile.createNewFile()) {
                    reviewDTO.setReviewImg(copyFile.getAbsolutePath());
                } else {
                    // 파일 생성 실패 시 예외 처리
                    throw new IOException("Failed to create file: " + copyFile);
                }
            } 
            else {// 파일이 이미 존재할 경우 처리 == 덮어쓰기
                // 새로운 파일명 생성: 원본 파일명 + 현재 시간(밀리초 단위)을 이용하여 중복을 피함
                String newFileName = uuids.get(0) + "_" + System.currentTimeMillis() + "_" + reviewImageName;
                File newCopyFile = new File(uploadFilePath + File.separator + newFileName);
                
                // 새로운 파일이 존재하지 않을 경우 생성
                if (!newCopyFile.exists()) {
                    if (newCopyFile.createNewFile()) {
                        // 파일 생성 성공 시 ReviewDTO의 이미지 경로를 새로운 파일의 절대 경로로 설정
                        reviewDTO.setReviewImg(newCopyFile.getAbsolutePath());
                    } 
                    else {// 파일 생성 실패 시 예외 처리
                        throw new IOException("Failed to create file: " + newCopyFile);
                    }
                } 
                else {// 파일이 이미 존재할 경우 에러 메시지 반환 또는 적절한 처리
                    throw new IOException("File already exists: " + newCopyFile);
                }
            }
        } 
        else {
            reviewDTO.setReviewImg(null); // 파일이 업로드되지 않았을 경우 ReviewDTO의 이미지 경로를 null로 설정
        }

        // 파일을 클라이언트에게 전송하지 않고 myOrderList 페이지로 리다이렉트
        return "redirect:/myOrderList";
    }
}