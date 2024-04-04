package com.koreait.kod.biz.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IMemberDAO {
	
	public List<MemberDTO> selectAllByGrade(Map<String,String> map);
	
	// 회원 전체 검색 메서드
	public List<MemberDTO> selectAllMember(MemberDTO memberDTO);
	
	
	// 로그인 메서드
	public MemberDTO selectOneLogin(Map<String,String> map);  // map  낮은 결합도를 유지하게해줌
	// 아이디 중복검사 메서드
	public MemberDTO selectOneIdChecK(Map<String,String> map);  

	
	
	public MemberDTO selectOneMemberCount(MemberDTO memberDTO);  
	
	public MemberDTO selectOneNewMemberCount(MemberDTO memberDTO);  
	
	
	
	// 회원 추가 메서드
	public boolean insert(Map<String,String> map);
	
	// 회원 정보 업데이트 메서드
	public boolean update(Map<String,String> map);
	
	// 이름 업데이트 메서드
	public boolean updateName(Map<String,String> map);
	
	// 비밀번호 업데이트 메서드
	public boolean updatePassword(Map<String,String> map);
	
	// 이메일 업데이트 메서드
	public boolean updateEmail(Map<String,String> map);
	
	// 회원 삭제 메서드
	public boolean delete(Map<String,String> map);
	
	
	// 탈퇴 신청 상태
	public boolean updateRoleUnregister(Map<String,String> map);
	
	// 복구 신청 상태
	public boolean updateRoleRecoveryPending(Map<String,String> map);
	
	// 복구 완료
	public boolean updateRoleUser(Map<String,String> map);
	
	// 복구신청 상태회원 전체출력
	public List<MemberDTO> selectAllRecoveryPending(Map<String,String> map);

}
