package com.koreait.kod.biz.member;

import java.util.List;

public interface MemberService {
	public List<MemberDTO> selectAll(MemberDTO memberDTO);
	public MemberDTO selectOne(MemberDTO memberDTO);
	public boolean insert(MemberDTO memberDTO);
	public boolean update(MemberDTO memberDTO);
	public boolean delete(MemberDTO memberDTO);

}
