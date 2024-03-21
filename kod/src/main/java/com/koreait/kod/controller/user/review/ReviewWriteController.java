package com.koreait.kod.controller.user.review;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.kod.biz.productAndWishList.ImageDTO;
import com.koreait.kod.biz.productAndWishList.ImageService;
import com.koreait.kod.biz.review.ReviewDTO;
import com.koreait.kod.biz.review.ReviewService;
import com.koreait.kod.controller.admin.product.CreateFileSavePathService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewWriteController {
	
    @Autowired
    ReviewService reviewService;
    @Autowired
    ImageService imageService;
    @Autowired
    FileUploadAndCopyService fileUploadAndCopy;
    @Autowired
    CreateFileSavePathService fileSavePathService;
    
    @PostMapping("/reviewWrite")
    public String reviewWrite(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("score") int reviewScore,
                              @RequestParam("productID") int productID,
                              @RequestParam("orderContentID") int orderContentID,
                              @RequestParam("reviewImageName") String reviewImageName,
                              @RequestParam("reviewImageList") List<MultipartFile> reviewImageList,
                              ReviewDTO reviewDTO,
                              ImageDTO imageDTO,
                              HttpSession session,
                              HttpServletRequest request) throws IOException   {

        // 현재 세션에서 회원 아이디 가져오기
        String memberID = (String)session.getAttribute("memberID");
        
        // 이미지 파일이 업로드되었을 경우에만 업로드 및 파일복사 실행
        if(reviewImageList != null && !reviewImageList.isEmpty()) {
        	String[] filePaths = fileSavePathService.getUploadFilePath(request);
            List<String> uuids = fileUploadAndCopy.uploadAndCopy(filePaths, reviewImageList);
            for (String uuid : uuids) {
            	imageDTO.setImageUrl(uuid);
            	imageService.insert(imageDTO);
			}
        }
        
        // 리뷰 등록
        reviewDTO.setReviewTitle(title);
        reviewDTO.setReviewContent(content);
        reviewDTO.setReviewScore(reviewScore);
        reviewDTO.setMemberID(memberID);
        reviewDTO.setProductID(productID);
        reviewDTO.setOdContentID(orderContentID);
        reviewService.insert(reviewDTO);

        return "user/mypage/myOrderList";
    }
}