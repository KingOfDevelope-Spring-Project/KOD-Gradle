package com.koreait.kod.biz.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired 
	MemberDAO memberDAO;

	@Override
	public List<MemberDTO> selectAll(MemberDTO memberDTO) {
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
		return memberDAO.update(memberDTO);
	}

	@Override
	public boolean delete(MemberDTO memberDTO) {
		return memberDAO.delete(memberDTO);
	}
}
