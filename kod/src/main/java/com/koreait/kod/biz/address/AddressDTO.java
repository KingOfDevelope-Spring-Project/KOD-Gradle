package com.koreait.kod.biz.address;

import lombok.Data;

@Data
public class AddressDTO {
	private int addressID; //PK
	private String addressName;  // 주소 저장할 이름 (회사 , 집) 등
	private String addressStreet; //도로명주소
	private String addressLand; // 지번주소
	private String addressDetail; //상세주소
	private String addressZipCode; //우편번호
	private String memberID; // MID
	private String searchCondition; 
	
	private String memberName;
	private String memberPhoneNumber;
}
