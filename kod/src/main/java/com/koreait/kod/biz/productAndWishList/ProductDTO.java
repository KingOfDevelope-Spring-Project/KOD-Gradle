package com.koreait.kod.biz.productAndWishList;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDTO {
	private int productID; // 상품번호 PK
	private int productCnt; // 구매 수량
	private int productStock; // 상품 재고
	private int productPrice; // 상품 가격
	private int productMaxPrice; // 최대 가격
	private int productMinPrice; // 최소 가격
	private int categoryID; // 카테고리 FK
	private String productCategory; // 상품 카테고리
	private String[] productCategoryList; // 필터검색을 위한 가격 및 카테고리 배열
	private List<MultipartFile> productImageList; // 여러 이미지 파일을 저장할 배열
	private String productBrand; // 상품 브랜드
	private String productName; // 상품 이름
	private String productInfo; // 상품 정보 
	private String productImg; // 상품이미지
	private String searchCondition;
	// 찜 여부를 확인하기 위한 멤버변수
	private String memberID; // 위시리스트 여부 저장할 멤버 변수
	private int isWished; // 위시리스트 여부 확인
	// 아래부터 통계에 사용되는 멤버변수
	private int year; // 년
	private int quarter; // 분기
	private int month; // 월
	private int day; // 일
	private int productSalesQuantity; // 상품 판매수량
	private int totalProductSalesQuantityForQuarter; // 분기동안 상품 판매수량 합계
	private int productSalesRevenue; // 상품 매출
	private int anualRevenue; // 연 매출
	private int quarterlyRevenue; // 분기 매출
	private int monthlyRevenue; // 월간 매출
	private int dailyRevenue; // 일간 매출
	private int monthlyOrderCnt; // 월 주문수
	private int yearlyOrderCnt; // 연 주문수
	
	// 기존 위시리스트DTO에서 옮겨온 멤버변수
	private int wishListID;
	private int wishListCnt;
	private int wishTotalCnt;
	private String memberGender;
	private String searchKeyword;
	
	// 결제 시 필요한 멤버변수
	private int purchaseType; // 구매유형 - 선택구매 0 , 즉시구매 1
	
	
	
	
}


