package com.koreait.kod.controller.user.productAndWishList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.kod.model.member.MemberDTO;
import com.koreait.kod.model.productAndWishlist.WishListDTO;
import com.koreait.kod.model.productAndWishlist.WishListService;
import com.koreait.kod.model.review.ReviewDTO;
import com.koreait.kod.model.review.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetProductDetailPage {
	
	@Autowired
	WishListService wishListService;
	@Autowired
	ReviewService reviewService;
//	@Autowired
//	List<WishListDTO> productIsWishedDatas;

	@RequestMapping(value = "/productDetailPage", method = RequestMethod.GET)
	public String productDetailPage(/*@PathVariable(name = "productID") int productID,*/@RequestParam("page") String page,WishListDTO wishListDTO,ReviewDTO reviewDTO,Model model,HttpSession session) {
		
		// 회원정보 가져오기
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		
		
		
		if(memberID==null) { // 로그아웃 상태
			wishListDTO.setSearchCondition("상품상세페이지LOGOUT");
			model.addAttribute("productWishDetailData", wishListService.selectOne(wishListDTO));
			wishListDTO.setSearchCondition("연령별상품추천LOGOUT스텝1"); // 사이트에 가입한 회원들의 연령별 회원수가 가장 많은 나이대 조회
			int age = wishListService.selectOne(wishListDTO).getMemberAge();
			wishListDTO.setMemberAge(age);
			wishListDTO.setSearchCondition("연령별상품추천LOGOUT스텝2"); // 연령별 인기상품 조회
			model.addAttribute("productWishDatas", wishListService.selectAll(wishListDTO));
		}
		else { // 로그인 상태
			wishListDTO.setSearchCondition("상품상세페이지LOGIN");
			model.addAttribute("productWishDetailData", wishListService.selectOne(wishListDTO));
			wishListDTO.setSearchCondition("위시리스트합계갯수");
			wishListDTO.setMemberID(memberID);
			model.addAttribute("wishListCnt", wishListService.selectOne(wishListDTO));
			wishListDTO.setSearchCondition("나이");
			WishListDTO memberMinMaxAge = wishListService.selectOne(wishListDTO);
			memberMinMaxAge.setMemberMinAge(memberMinMaxAge.getMemberMinAge());
			memberMinMaxAge.setMemberMaxAge(memberMinMaxAge.getMemberMaxAge());
			List<WishListDTO> productWishDatas = wishListService.selectAll(wishListDTO);
			model.addAttribute("productWishDatas", productWishDatas);
			
			List<WishListDTO> productIsWishedDatas=new ArrayList<WishListDTO>();
			for (WishListDTO data : productWishDatas) {
				data.setSearchCondition("찜여부");
				data.setMemberID(memberID);
				productIsWishedDatas.add(wishListService.selectOne(data));
			}
			model.addAttribute("productIsWishedDatas", productIsWishedDatas);
		}
		
		wishListDTO.setSearchCondition("상품찜합계");
		model.addAttribute("wishTotalCnt", wishListService.selectOne(wishListDTO).getWishTotalCnt());
		
		ArrayList<ReviewDTO> productReviewDatas = new ArrayList<ReviewDTO>();
		reviewDTO.setSearchCondition("상품리뷰전체조회");
		model.addAttribute("productReviewDatas", reviewService.selectAll(reviewDTO));
		double totalScore = 0.0;
		double reviewAvgScore = 0.0;
		int totalReviewCount = productReviewDatas.size(); // 리뷰 총개수
		
		// 리뷰데이터 유효성 검사
		if (productReviewDatas.isEmpty() || productReviewDatas==null) { // 리뷰데이터가 없다면
		    System.out.println("리뷰 데이터 없음");
		} 
		else { // 리뷰데이터가 존재한다면
			for (ReviewDTO data : productReviewDatas) {
				totalScore += data.getReviewScore();
				
				if (data.getReviewImg() != null && !data.getReviewImg().isEmpty()) {
					Path path = null;
					try { // DB의 이미지 경로가 알수없는 경로일 경우 페이지에러발생을 막기위함
						path = Paths.get(data.getReviewImg());
						System.out.println("[로그:정현진] 리뷰 이미지 경로: " + path);
						
						// 상대 경로에서 파일명만 추출
						String relativePath = path.getFileName().toString();
						System.out.println("[로그:정현진] 파싱된 relativePath: " + relativePath);
						
						// 추출한 파일명을 리뷰 데이터의 이미지 경로로 설정
						data.setReviewImg(relativePath);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					// 이미지를 업로드하지 않은 경우의 처리
			    	System.out.println("[로그:정현진] 리뷰작성 시 이미지업로드 하지않음");
//			        data.setReviewImg("defaultImage.jpg"); // 기본 이미지 파일명으로 설정 (프로젝트에 실제로 존재하는 파일명으로 변경)
			        System.out.println("[로그:정현진] " + data.getMemberName() + "회원 " + data.getReviewScore() + "점 (이미지 없음)");
				}
			}
			reviewAvgScore = Math.round((totalScore / totalReviewCount) * 100.0) / 100.0;
			model.addAttribute("productReviewDatas", productReviewDatas);
			model.addAttribute("reviewAvgScore", reviewAvgScore);
			
			// 리뷰데이터가 존재할 때만 필요한 변수들이라 else문 안에 선언
			int oneScoreCount = 0;
			int twoScoreCount = 0;
			int threeScoreCount = 0;
			int fourScoreCount = 0;
			int fiveScoreCount = 0;

			for (ReviewDTO data : productReviewDatas) {
			    // 각 별점 개수 카운트
			    if (data.getReviewScore() == 1) {
			        oneScoreCount++;
			    } else if (data.getReviewScore() == 2) {
			        twoScoreCount++;
			    } else if (data.getReviewScore() == 3) {
			        threeScoreCount++;
			    } else if (data.getReviewScore() == 4) {
			        fourScoreCount++;
			    } else if (data.getReviewScore() == 5) {
			        fiveScoreCount++;
			    }
			}
			
			// 각 별점 비율 계산
			double oneScoreRatio = (double) oneScoreCount / totalReviewCount * 100;
			double twoScoreRatio = (double) twoScoreCount / totalReviewCount * 100;
			double threeScoreRatio = (double) threeScoreCount / totalReviewCount * 100;
			double fourScoreRatio = (double) fourScoreCount / totalReviewCount * 100;
			double fiveScoreRatio = (double) fiveScoreCount / totalReviewCount * 100;

			model.addAttribute("oneScoreCount", oneScoreCount);
			model.addAttribute("twoScoreCount", twoScoreCount);
			model.addAttribute("threeScoreCount", threeScoreCount);
			model.addAttribute("fourScoreCount", fourScoreCount);
			model.addAttribute("fiveScoreCount", fiveScoreCount);
			model.addAttribute("oneScoreRatio", oneScoreRatio);
			model.addAttribute("twoScoreRatio", twoScoreRatio);
			model.addAttribute("threeScoreRatio", threeScoreRatio);
			model.addAttribute("fourScoreRatio", fourScoreRatio);
			model.addAttribute("fiveScoreRatio", fiveScoreRatio);
			
		}
		
		//===페이징 처리===
		int productPerPage = 3; // 페이지 당 3개만 로드
		int currentPage = (page != null && !page.isEmpty())
                ? Integer.parseInt(page)
                : 1;
		int totalPages = (int) Math.ceil((double) productReviewDatas.size() / productPerPage);
		
		// 페이지 그룹 크기 설정
		int pagesPerGroup = 5; 
		int currentGroup = (int) Math.ceil((double) currentPage / pagesPerGroup);
		int startPageOfGroup = (currentGroup - 1) * pagesPerGroup + 1;
		int endPageOfGroup = Math.min(startPageOfGroup + pagesPerGroup - 1, totalPages);
		int startIndex = (currentPage - 1) * productPerPage;
		int endIndex = Math.min(startIndex + productPerPage, productReviewDatas.size());

		List<ReviewDTO> currentPageProducts = productReviewDatas.subList(startIndex, endIndex);
		List<ReviewDTO> newArrayList = new ArrayList<ReviewDTO>(currentPageProducts);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPageProducts", newArrayList);
		model.addAttribute("startPageOfGroup", startPageOfGroup);
		model.addAttribute("endPageOfGroup", endPageOfGroup);
		
		/* V -> C -> V로 구현한 이유
		 * 1. 유지보수 팀에 불릴확률이 높아짐
		 * 2. 내부적으로 모듈화 이후 모듈화를 용이하게 하는 주석을 달아줌
		 * 3. 요청을 한번만하기 때문에 가볍게 느껴질수있음
		 * 
		 * 추후 유지보수 시 V -> C -> C -> V 로 변경가능 (모듈화)
		 */
		
		return "productDetail";
	}
	
	
}
