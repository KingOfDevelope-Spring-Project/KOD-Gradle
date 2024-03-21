package com.koreait.kod.biz.member;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private static final String SELECTONE_MEMBER_COUNTS=
			"SELECT COUNT(MEMBER_ID) AS MEMBER_COUNTS FROM MEMBER;";
	// 테이블에 특정 회원 정보조회를 하기위한 쿼리문
	private static final String SELECTONE_CHECK = "SELECT"
			// 회원 아이디, 비밀번호, 이름, 핸드폰 번호, 이메일, 등급, 성별, 생년월일 정보를 선택
			+ "MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_PHONE, "
			+ "MEMBER_EMAIL, MEMBER_GRADE, MEMBER_GENDER, MEMBER_BIRTH, MEMBER_ROLE"
			// 회원 테이블에서 데이터를 가져옴
			+ "FROM MEMBER "
			// WHERE절을 사용하여 조회할 아이디(MEMBER_ID)지정
			+ "WHERE MEMBER_ID=?";
	
	private static final String SELECT_MEMBER_COUNT = "SELECT COUNT(MEMBER_ID) AS CNT_MEMBER FROM MEMBER";
	
	private static final String SELECT_NEW_MEMBER_COUNT="SELECT COUNT(MEMBER_ID) AS CNT_NEW_MEMBER FROM MEMBER WHERE MEMBER_REGDATE >= DATE_SUB(CURDATE(), INTERVAL 14 DAY)";
	
//	private static final String INSERT = "INSERT INTO "
//			+ "(MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_PHONE, MEMBER_EMAIL, "
//			+ "MEMBER_GRADE, MEMBER_GENDER, MEMBER_BIRTH,MEMBER_ROLE) "
//			+ "MEMBER VALUES(?,?,?,?,?,'BRONZE',?,?,USER,CURRENT_TIMESTAMP)";
	
	
	
	
	private static final String INSERT = "INSERT INTO MEMBER "
			+ "(MEMBER_ID, "
			+ "MEMBER_PW, "
			+ "MEMBER_NAME, "
			+ "MEMBER_PHONENUMBER, "
			+ "MEMBER_EMAIL, "
			+ "MEMBER_GRADE, "
			+ "MEMBER_GENDER, "
			+ "MEMBER_BIRTH, "
			+ "MEMBER_ROLE,) "
			+ "VALUES(?,?,?,?,?,'BRONZE',?,?,USER)"; 
	
	
	
	// 회원 정보를 변경 하기위한 쿼리문
	// MEMBER의 각 열을 업데이트할 값 MEMBER_NAME=?, MEMBER_PW=?, MEMBER_EMAIL=?,
	// MEMBER_PHONE=?
	// WHERE MEMBER_ID=? 어떠한 회원 정보를 업데이트할지 결정하는 조건
	private static final String UPDATE = "UPDATE "
			+ "MEMBER SET MEMBER_NAME=?, MEMBER_PW=?, MEMBER_EMAIL=?, MEMBER_PHONE=? WHERE MEMBER_ID=?";

	private static final String UPDATE_PW = "UPDATE " 
			+ "MEMBER SET MEMBER_PW=? WHERE MEMBER_ID=?";

	private static final String UPDATE_NAME = "UPDATE " 
			+ "MEMBER SET MEMBER_NAME=? WHERE MEMBER_ID=?";

	private static final String UPDATE_EMAIL = "UPDATE " 
			+ "MEMBER SET MEMBER_EMAIL=? WHERE MEMBER_ID=?";

	private static final String DELETE = "DELETE FROM MEMBER WHERE MID=?";
			 	

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
      return jdbcTemplate.queryForObject(SELECTONE_LOGIN, args, new MemberRowMapperLogin());
      }
      else if(memberDTO.getSearchCondition().equals("memberCount")) {
        return jdbcTemplate.queryForObject(SELECT_MEMBER_COUNT, new MemberRowMapper1());
      }
      else if(memberDTO.getSearchCondition().equals("newMemberCount")) {
        return jdbcTemplate.queryForObject(SELECT_NEW_MEMBER_COUNT, new MemberRowMapper3());
      }// 아이디 중복검사 코드
       else if(memberDTO.getSearchCondition().equals("newMemberCount")) {
        return jdbcTemplate.queryForObject(SELECT_NEW_MEMBER_COUNT, new MemberRowMapperMemberCounts());
      }
      else if(memberDTO.getSearchCondition().equals("ID_CHECK")){
            Object[] args = { memberDTO.getMemberID() };
        return jdbcTemplate.queryForObject(SELECTONE_CHECK, args, new MemberRowMapper());
      }
      else {
       return null;
      }
		}catch(Exception e) {
			return null;
		}
	}
	

	
	public boolean insert(MemberDTO memberDTO) {
		int result =jdbcTemplate.update(INSERT,memberDTO.getMemberID(),memberDTO.getMemberPW(),memberDTO.getMemberName()
				,memberDTO.getMemberPhoneNumber(),memberDTO.getMemberEmail(),memberDTO.getMemberGrade(),memberDTO.getMemberGender()
				,memberDTO.getMemberBirth(),memberDTO.getMemberRole());
		if(result<=0) {
			return false;
		}
			return true;
	}

	public boolean update(MemberDTO memberDTO) {
		int result=0;
		if(memberDTO.getSearchCondition().equals("PW_UPDATE")) {
			result = jdbcTemplate.update(UPDATE_PW,memberDTO.getMemberPW());			
		}else if(memberDTO.getSearchCondition().equals("NAME_UPDATE")){
			result = jdbcTemplate.update(UPDATE_NAME, memberDTO.getMemberName());
		}else if(memberDTO.getSearchCondition().equals("EMAIL_UPDATE")){
			result = jdbcTemplate.update(UPDATE_EMAIL,memberDTO.getMemberEmail());
		}
		if(result<=0) {			
			return false;
		}
		return true;
	}

	public boolean delete(MemberDTO memberDTO) {
		return false;
	}
}



class MemberRowMapper implements org.springframework.jdbc.core.RowMapper<MemberDTO> {
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] MemberRowMapper 들어옴");
		MemberDTO memberDTO = new MemberDTO();
		System.out.println("[로그:정현진] memberID = "+rs.getString("MEMBER_ID"));
		memberDTO.setMemberID(rs.getString("MEMBER_ID"));
		memberDTO.setMemberRole(rs.getString("MEMBER_ROLE"));
		return memberDTO;
	}
}
class MemberRowMapper1 implements RowMapper<MemberDTO>{

	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberCount(rs.getInt("CNT_MEMBER"));
		return memberDTO;
	}
	
}
class MemberRowMapper2 implements RowMapper<MemberDTO> {
    
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO memberDTO=new MemberDTO();
		memberDTO.setMemberID(rs.getString("MEMBER_ID"));
		memberDTO.setMemberName(rs.getString("MEMBER_NAME"));
		memberDTO.setMemberGrade(rs.getString("MEMBER_GRADE"));
		return memberDTO;
	}
}
class MemberRowMapper3 implements RowMapper<MemberDTO>{

	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO memberDTO=new MemberDTO();
		memberDTO.setNewMemberCount(rs.getInt("CNT_NEW_MEMBER"));
		return memberDTO;
	}
	
}

//개발자의 편의를 위해 RowMapper인터페이스 사용
class MemberRowMapperLogin implements org.springframework.jdbc.core.RowMapper<MemberDTO> {
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] MemberRowMapper 들어옴");
		MemberDTO memberDTO = new MemberDTO();
		System.out.println("[로그:정현진] memberID = "+rs.getString("MEMBER_ID"));
		memberDTO.setMemberID(rs.getString("MEMBER_ID"));
		memberDTO.setMemberRole(rs.getString("MEMBER_ROLE"));
		return memberDTO;
	}
}

class MemberRowMapperMemberCounts implements RowMapper<MemberDTO>{
	
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO data=new MemberDTO();
		data.setMemberCount(rs.getInt("MEMBER_COUNTS"));
		return data;
	}
}

