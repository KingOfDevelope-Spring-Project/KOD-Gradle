package com.koreait.kod.biz.productAndWishList;

import lombok.Data;

@Data
public class ProductDTO {
	private int productID; // 상품번호 PK
	private int productCnt; // 구매 수량
	private int productStock; // 상품 재고
	private int productPrice; // 상품 가격
	private int isWished; // 위시리스트 여부 확인
	private String memberID; // 위시리스트 여부 저장할 멤버 변수
	private String[] productCategoryList; // 필터검색을 위한 가격 및 카테고리 배열
	private int productMaxPrice; // 최대 가격
	private int productMinPrice; // 최소 가격
	private String productName; // 상품 이름
	private String productBrand; // 상품 브랜드
	private String categoryID; // 카테고리
	private String productInfo; // 상품 정보 
//	private String productImg; 
	private String searchCondition;
}
