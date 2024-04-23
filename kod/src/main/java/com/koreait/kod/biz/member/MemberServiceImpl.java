package com.koreait.kod.biz.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired 
	MemberDAO memberDAO;
	
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
			else if(memberDTO.getSearchCondition().equals("requestMemberRecoveryList")) {
				System.out.println(" 복구 신청 회원 전체 출력 진입");
				return imemberDAO.selectAllRecoveryPending(map);
			}
		return memberDAO.selectAll(memberDTO);
	}

	@Override
	public MemberDTO selectOne(MemberDTO memberDTO) {
		return memberDAO.selectOne(memberDTO);
	}

	@Override
	public boolean insert(MemberDTO memberDTO) {
		return memberDAO.insert(memberDTO); 
	} 

	@Override
	public boolean update(MemberDTO memberDTO) {
		 if(memberDTO.getSearchCondition().equals("memberUpdateRoleUnregister")) {
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
		  else if(memberDTO.getSearchCondition().equals("updateMemberRole")) {
			  System.out.println(" 회원 복구 조건 진입 ");
			  map.put("memberID", memberDTO.getMemberID()); 
			  System.out.println("map"+map);
			  return imemberDAO.updateRoleUser(map); 
		  } 
		return memberDAO.update(memberDTO);
	}

	@Override
	public boolean delete(MemberDTO memberDTO) {
		return memberDAO.delete(memberDTO);
	}


}
