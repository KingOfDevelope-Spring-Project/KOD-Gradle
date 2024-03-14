package com.koreait.kod.biz.member;

import java.util.List;

public interface MemberService {
	List<MemberDTO> selectAll(MemberDTO memberDTO);
	MemberDTO selectOne(MemberDTO memberDTO);
	
	boolean insert(MemberDTO memberDTO);
	boolean update(MemberDTO memberDTO);
	boolean delete(MemberDTO memberDTO);

}
