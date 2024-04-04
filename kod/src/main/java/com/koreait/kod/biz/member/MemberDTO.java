package com.koreait.kod.biz.member;

import lombok.Data;

@Data
public class MemberDTO {
	private String memberID; // 회원 ID PK
	private String memberPW; // 회원 비밀번호
	private String memberPWCheck ; // 비밀번호 확인
	private String memberName; // 이름
	private String memberPhoneNumber; // 핸드폰번호
	private String memberEmail; //이메일
	private String memberGrade; // 등급
	private String memberGender; // 성별
	private String memberBirth; // 생년월일
	private String memberRole; // 상태 (유저,관리자,탈퇴)
	
	//컬럼에 없는 멤버변수
	private int memberCnt; // 기존회원 수
	private int newMemberCnt; // 신규회원 수
	private String searchCondition; 
}
