package com.koreait.kod.model.member;

import lombok.Data;

@Data
public class MemberDTO {
	private String memberID;
	private String memberPW;
	private String memberPWCheck ;
	private String memberName;
	private String memberPhoneNumber;
	private String memberEmail;
	private String memberGrade;
	private String memberGender;
	private String memberBirth;
	private String searchCondition;
}
