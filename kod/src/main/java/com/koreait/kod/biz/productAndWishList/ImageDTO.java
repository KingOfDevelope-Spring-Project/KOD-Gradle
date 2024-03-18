package com.koreait.kod.biz.productAndWishList;

import lombok.Data;

@Data
public class ImageDTO {
	private int imageID; // 이미지 PK
	private int productID; // 상품번호 PK
	private int reviewID; // 리뷰 PK
	private String imageUrl; // 이미지 경로
	private String imageCategory; // 이미지 분류

}
