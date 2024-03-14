package com.koreait.kod.model.product;

import lombok.Data;

@Data
public class ProductDTO {
	private int productID;
	private int productCnt;
	private int productStock;
	private int productPrice;
	private int productMaxPrice;
	private int productMinPrice;
	private int isWished;
	private String[] productCategoryList;
	private String productName;
	private String productBrand;
	private String productCategory;
	private String productInfo;
	private String productImg;
	private String memberID;
	private String searchCondition;
}
