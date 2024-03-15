package com.koreait.kod.biz.productAndWishList;

import java.sql.Time;

import lombok.Data;

@Data
public class WishListDTO {
	private int wishListID; // PK
	private int isWished; // 찜여부
	private int wishListCnt; // 위시리스트 합계갯수(회원별 위시리스트에 담긴 상품갯수)
	private int wishTotalCnt; // 상품의 찜 합계수량(상품에 찜이 되어있는 갯수)
	private Time wishListAddTime; // 찜을 추가한 시간
	private String memberID; // 회원ID
	private String memberGender; // 회원성별
	private int memberAge; // 회원나이
	private int memberMinAge; // 회원최소 나이값(연령별 추천상품 로직에서 사용됨)
	private int memberMaxAge; // 회원최대 나이값
	private int productID; // 상품ID
	private String productBrand; // 상품브랜드
	private String productName; // 상품이름
	private String productCategory; // 상품카테고리
	private String productInfo; // 상품정보
	private String productImg; // 상품이미지
	private int productPrice; // 상품가격
	private int productStock; // 상품재고
	private String searchCondition; // DAO 실행조건
	private String SearchKeyword; // 검색어
}
