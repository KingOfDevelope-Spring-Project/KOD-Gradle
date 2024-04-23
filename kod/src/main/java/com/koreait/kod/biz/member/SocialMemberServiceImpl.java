package com.koreait.kod.biz.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialMemberServiceImpl implements SocialMemberService {
	
    @Autowired
    SocialMemberDAO socialMemberDAO;

	@Override
	public List<MemberDTO> selectAll(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override // 이미 카카오로 가입된 회원인지 확인하는 메소드
	public MemberDTO selectOne(MemberDTO memberDTO) {
		System.out.println("[로그:정현진] selectOne 들어옴");
		System.out.println("[로그:정현진] 카카오ID : "+memberDTO.getMemberID());
		System.out.println("[로그:정현진] 회원이름 : "+memberDTO.getMemberName());
		Map<String,String> map = new HashMap<String,String>();
		map.put("memberID", memberDTO.getMemberID());
		map.put("memberName", memberDTO.getMemberName());
		System.out.println("[로그:정현진] map.memberID : "+map.get("memberID"));
		System.out.println("[로그:정현진] map.memberName : "+map.get("memberName"));
		return socialMemberDAO.selectOne(map);
	}

	@Override // 카카오로 회원가입
	public boolean insert(MemberDTO memberDTO) {
		System.out.println("[로그:정현진] insert 들어옴");
		System.out.println("[로그:정현진] 카카오ID : "+memberDTO.getMemberID());
		System.out.println("[로그:정현진] 회원이름 : "+memberDTO.getMemberName());
		Map<String,String> map = new HashMap<String,String>();
		map.put("memberID", memberDTO.getMemberID());
		map.put("memberName", memberDTO.getMemberName());
		return socialMemberDAO.insert(map);
	}

	@Override
	public boolean update(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
