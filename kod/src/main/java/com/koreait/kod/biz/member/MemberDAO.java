package com.koreait.kod.biz.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAO {
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL_MEMBERS_BY_GRADE=
			"SELECT MEMBER_ID,MEMBER_GRADE,MEMBER_NAME FROM MEMBER WHERE MEMBER_GRADE=?";
	private static final String SELECTONE_LOGIN=
			"SELECT MEMBER_ID,MEMBER_ROLE  FROM MEMBER  WHERE MEMBER_ID=? AND MEMBER_PW=? ";
	private static final String SELECT_MEMBER_COUNT = "SELECT COUNT(MEMBER_ID) AS CNT_MEMBER FROM MEMBER";
	private static final String SELECT_NEW_MEMBER_COUNT="SELECT COUNT(MEMBER_ID) AS CNT_NEW_MEMBER FROM MEMBER WHERE MEMBER_REGDATE >= DATE_SUB(CURDATE(), INTERVAL 14 DAY)";
	private static final String INSERT="";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<MemberDTO> selectAll(MemberDTO memberDTO) {
		try {
	    if (memberDTO.getSearchCondition().equals("getMembersByGrade")) {
	    	System.out.println("[로그:정현진] memberDAO 들어옴");
	    	System.out.println("[로그:정현진] 회원등급"+memberDTO.getMemberGrade());
	        Object[] args = {memberDTO.getMemberGrade().toUpperCase()};
	        return jdbcTemplate.query(SELECTALL_MEMBERS_BY_GRADE,args, new MemberRowMapper2());
	    } else {
	        return null; // 다른 조건을 처리하는 코드를 여기에 추가해야 합니다.
	    }
		}catch(Exception e) {
			return null;
		}
	}

	public MemberDTO selectOne(MemberDTO memberDTO) {
		try {
		if(memberDTO.getSearchCondition().equals("login")) {
		Object[] args = {memberDTO.getMemberID(),memberDTO.getMemberPW()};
		return jdbcTemplate.queryForObject(SELECTONE_LOGIN, args, new MemberRowMapper());
		}else if(memberDTO.getSearchCondition().equals("memberCount")) {
			return jdbcTemplate.queryForObject(SELECT_MEMBER_COUNT, new MemberRowMapper1());
		}else if(memberDTO.getSearchCondition().equals("newMemberCount")) {
			return jdbcTemplate.queryForObject(SELECT_NEW_MEMBER_COUNT, new MemberRowMapper3());
		}else {
			return null;
		}
		}catch(Exception e) {
			return null;
		}
	}
	
	public boolean insert(MemberDTO memberDTO) {
		return false;
	}

	public boolean update(MemberDTO memberDTO) {
		return false;
	}

	public boolean delete(MemberDTO memberDTO) {
		return false;
	}
}


//개발자의 편의를 위해 RowMapper인터페이스 사용
class MemberRowMapper implements org.springframework.jdbc.core.RowMapper<MemberDTO> {
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] MemberRowMapper 들어옴");
		MemberDTO data = new MemberDTO();
		System.out.println("[로그:정현진] memberID = "+rs.getString("MEMBER_ID"));
		data.setMemberID(rs.getString("MEMBER_ID"));
		data.setMemberRole(rs.getString("MEMBER_ROLE"));
		return data;
	}
}
class MemberRowMapper1 implements RowMapper<MemberDTO>{

	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO data = new MemberDTO();
		data.setMemberCount(rs.getInt("CNT_MEMBER"));
		return data;
	}
	
}
class MemberRowMapper2 implements RowMapper<MemberDTO> {
    
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO data=new MemberDTO();
		data.setMemberID(rs.getString("MEMBER_ID"));
		data.setMemberName(rs.getString("MEMBER_NAME"));
		data.setMemberGrade(rs.getString("MEMBER_GRADE"));
		return data;
	}
}
class MemberRowMapper3 implements RowMapper<MemberDTO>{

	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO data=new MemberDTO();
		data.setNewMemberCount(rs.getInt("CNT_NEW_MEMBER"));
		return data;
	}
	
}

