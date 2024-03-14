package com.koreait.kod.biz.review;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {
	private int reviewID; // PK
	private int reviewScore; // 상품 리뷰점수
	private double reviewAvgScore; // 상품 리뷰평점
	private String reviewImg; // 상품 리뷰이미지
	private Date reviewDate; // 리뷰 작성일
	private String reviewTitle; // 리뷰 제목
	private String reviewContent; // 리뷰 내용
	private String reviewReply; // 리뷰 답글(관리자) ex)구매해주셔서 감사합니다.
	private String hasWrittenReview; // 리뷰작성여부
	private String memberID; // 작성자ID
	private String memberName; // 작성자 이름
	private int productID; // 상품ID
	private int productName; // 상품명
	private int odContentID; // 주문내역ID - 주문내역별로 리뷰를 남기기 위함 - 상품 재구매 시 리뷰작성 가능하게 함
	private String searchCondition; // DAO실행조건
	
}
