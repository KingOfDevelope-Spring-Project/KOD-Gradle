package com.koreait.kod.biz.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	IMemberDAO imemberDAO;
	Map<String, String> map = new HashMap<String, String>();  

	@Override
	public List<MemberDTO> selectAll(MemberDTO memberDTO) {
		if (memberDTO.getSearchCondition().equals("memberSelectAll")) {			
		System.out.println("회원 전체출력 진입");
		System.out.println("전체 회원 "+memberDTO);
		return imemberDAO.selectAllMember(memberDTO);
		}
		else if(memberDTO.getSearchCondition().equals("selectAllByGrade")) {
			System.out.println("회원 등급 출력 진입");
			map.put("memberGrade", memberDTO.getMemberGrade());
			System.out.println(map);
			return imemberDAO.selectAllByGrade(map);
		}
		else if(memberDTO.getSearchCondition().equals("selectAllRecoveryPending")) {
			System.out.println(" 복구 신청 회원 전체 출력 진입");
			return imemberDAO.selectAllRecoveryPending(map);
		}
		else {
			return null;
		}
	}

	@Override
	public MemberDTO selectOne(MemberDTO memberDTO) {
		System.out.println("서비스 단일 조회메서드 진입");
		if (memberDTO.getSearchCondition().equals("memberLogin")) {
			System.out.println("로그인 조건 진입");
			map.put("memberID", memberDTO.getMemberID());
			map.put("memberPW", memberDTO.getMemberPW());
			System.out.println(map);
			return imemberDAO.selectOneLogin(map);
			
		} else if (memberDTO.getSearchCondition().equals("memberIDCK")) {
			System.out.println("아이디 중복검사 조건 진입");
			map.put("memberID", memberDTO.getMemberID());
			System.out.println("아이디 입력 확인 " + map);
			return	imemberDAO.selectOneIdChecK(map);
		}
		else if(memberDTO.getSearchCondition().equals("memberCount")) {
			System.out.println("회원 카운트 진입");
			System.out.println("회원 카운트"+memberDTO.getMemberCnt());
			return	imemberDAO.selectOneMemberCount(memberDTO);
			
		}
		else if(memberDTO.getSearchCondition().equals("newMemberCount")) {
			System.out.println(" 신규 회원 카운트 진입");
			System.out.println("신규 회원 카운트"+memberDTO.getNewMemberCnt());
			return	imemberDAO.selectOneNewMemberCount(memberDTO);
			
		}
		return null;
	}

	@Override
	public boolean insert(MemberDTO memberDTO) {
		System.err.println("회원정보 추가 진입");
		map.put("memberID", memberDTO.getMemberID());
		map.put("memberPW", memberDTO.getMemberPW());
		map.put("memberName", memberDTO.getMemberName());
		map.put("memberPhoneNumber", memberDTO.getMemberPhoneNumber());
		map.put("memberEmail", memberDTO.getMemberEmail());
		map.put("memberGender", memberDTO.getMemberGender());
		map.put("memberBirth", memberDTO.getMemberBirth());
		System.out.println("map"+map);
		return imemberDAO.insert(map);

	}

	@Override 
	public boolean update(MemberDTO memberDTO) {
	  if(memberDTO.getSearchCondition().equals("memberUpdatePw")) { 
		  System.out.println("비밀번호 변경 조건 진입");
		   map.put("memberID",  memberDTO.getMemberID()); 
		   map.put("memberPW", memberDTO.getMemberPW());
		   System.out.println("map"+map);
		   return imemberDAO.updatePassword(map); 
		  }
	  else if(memberDTO.getSearchCondition().equals("memberUpdateName")) {
		  System.out.println("이름 변경 조건 진입");
		  map.put("memberID",memberDTO.getMemberID()); 
		  map.put("memberName", memberDTO.getMemberName());
		  System.out.println("map"+map);
		  return imemberDAO.updateName(map); 
	  }
	  else if(memberDTO.getSearchCondition().equals("memberUpdateEmail")) {
		  System.out.println("이메일 변경 조건 진입");
		  map.put("memberID", memberDTO.getMemberID()); 
		  map.put("memberEmail",memberDTO.getMemberEmail());
		  System.out.println("map"+map);
		  return imemberDAO.updateEmail(map); 
		  } 
	  else if(memberDTO.getSearchCondition().equals("memberUpdateRoleUnregister")) {
		  System.out.println(" 탈퇴 신청 상태 조건 진입 ");
		  map.put("memberID", memberDTO.getMemberID()); 
		  System.out.println("map"+map);
		  return imemberDAO.updateRoleUnregister(map); 
	  } 
	  else if(memberDTO.getSearchCondition().equals("memberUpdateRoleRecoveryPending")) {
		  System.out.println(" 복구 신청 상태 조건 진입 " );
		  map.put("memberID", memberDTO.getMemberID()); 
		  System.out.println("map"+map);
		  return imemberDAO.updateRoleRecoveryPending(map); 
	  } 
	  else if(memberDTO.getSearchCondition().equals("memberUpdateRoleUser")) {
		  System.out.println(" 회원 복구 조건 진입 ");
		  map.put("memberID", memberDTO.getMemberID()); 
		  System.out.println("map"+map);
		  return imemberDAO.updateRoleUser(map); 
	  } 
	  return false; 
		  }
	
	  @Override public boolean delete(MemberDTO memberDTO) { 
		  System.out.println("회원 정보삭제 진입");
		  map.put("memberID",memberDTO.getMemberID()); 
		  System.out.println("map"+map);
		  return imemberDAO.delete(map); 
		  }
	 
}
