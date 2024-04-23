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
	
	// 회원정보 전체 출력 쿼리문
	private static final String SELECTALL_MEMBER=
			"SELECT "
			+ "MEMBER_ID, "
			+ "MEMBER_PW, "
			+ "MEMBER_NAME, "
			+ "MEMBER_PHONENUMBER, "
			+ "MEMBER_EMAIL, "
			+ "MEMBER_GRADE, "
			+ "MEMBER_ROLE, "
			+ "MEMBER_GENDER, "
			+ "MEMBER_BIRTH, "
			+ "MEMBER_REGDATE "
			+ "FROM MEMBER ";
		
	private static final String SELECTONE_LOGIN=
//			"SELECT MEMBER_ID,MEMBER_ROLE  FROM MEMBER  WHERE MEMBER_ID=? AND MEMBER_PW=? ";
			"SELECT MEMBER_ID,MEMBER_ROLE,MEMBER_NAME,MEMBER_PHONENUMBER,MEMBER_EMAIL  FROM MEMBER  WHERE MEMBER_ID=? AND MEMBER_PW=? ";
	
    
    // 테이블에 특정 회원 정보조회를 하기위한 쿼리문
    private static final String SELECTONE_CHECK = "SELECT"
            // 회원 아이디, 비밀번호, 이름, 핸드폰 번호, 이메일, 등급, 성별, 생년월일 정보를 선택
            + "MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_PHONE, "
            + "MEMBER_EMAIL, MEMBER_GRADE, MEMBER_GENDER, MEMBER_BIRTH, MEMBER_ROLE"
            // 회원 테이블에서 데이터를 가져옴
            + "FROM MEMBER "
            // WHERE절을 사용하여 조회할 아이디(MEMBER_ID)지정
            + "WHERE MEMBER_ID=?";
    
    // 테이블에 특정 회원 정보조회를 하기위한 쿼리문
    private static final String SELECTONE_RESTORE = "SELECT"
          // 회원 아이디, 비밀번호, 이름, 핸드폰 번호, 이메일, 등급, 성별, 생년월일 정보를 선택
          + "MEMBER_ID, "
          + "MEMBER_EMAIL "
          // 회원 테이블에서 데이터를 가져옴
          + "FROM MEMBER "
          // WHERE절을 사용하여 조회할 아이디(MEMBER_ID)지정
          + "WHERE MEMBER_ID=? AND MEMBER_EMAIL = ? ";

    
	private static final String SELECTONE_MEMBER_COUNT=
			"SELECT COUNT(MEMBER_ID) AS MEMBER_COUNTS FROM MEMBER";
	
	private static final String SELECT_NEW_MEMBER_COUNT="SELECT COUNT(MEMBER_ID) AS CNT_NEW_MEMBER FROM MEMBER WHERE MEMBER_REGDATE >= DATE_SUB(CURDATE(), INTERVAL 14 DAY)";
   
	private static final String INSERT = "INSERT INTO MEMBER( "
            + "MEMBER_ID, "
            + "MEMBER_PW, "
            + "MEMBER_NAME, "
            + "MEMBER_PHONENUMBER, "
            + "MEMBER_EMAIL, "
            + "MEMBER_GRADE, "
            + "MEMBER_GENDER, "
            + "MEMBER_BIRTH, "
            + "MEMBER_ROLE) "
            + "VALUES(?,?,?,?,?,'WELCOME',?,?,'USER')"; 
    
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

    private static final String DELETE = "DELETE FROM MEMBER WHERE MEMBER_ID=?";
    
    //=================================
    
	private static final String SELECTONE_GET_ENCRYPT_PW=
			"SELECT MEMBER_ID,MEMBER_PW,MEMBER_ROLE  FROM MEMBER  WHERE MEMBER_ID=?";
	
	private static final String SELECTONE_CHECK_ROLE=
			"SELECT MEMBER_ROLE FROM MEMBER WHERE MEMBER_ID=?";
	private static final String SELECTONE_CHECK_MEMBER_ID=
			"SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID=?";
    
    
    
                 
    
    public List<MemberDTO> selectAll(MemberDTO memberDTO) {
    	System.err.println(memberDTO);
		try {
	    if (memberDTO.getSearchCondition().equals("getMembersByGrade")) {
	    	System.out.println("[로그:정현진] memberDAO 들어옴");
	    	System.out.println("[로그:정현진] 회원등급 : "+memberDTO.getMemberGrade());
	        Object[] args = {memberDTO.getMemberGrade().toUpperCase()};
	        return jdbcTemplate.query(SELECTALL_MEMBERS_BY_GRADE,args, new MemberRowMapperByGrade());
	    } 
	    else if(memberDTO.getSearchCondition().equals("selectAllMember")) {
	    	System.out.println("[로그:구본승] memberDAO 들어옴  selectAllMember");
	    	return jdbcTemplate.query(SELECTALL_MEMBER, new MemberRowMapper());
	    	
	    }
	    
	    
	    else {
	        return null; // 다른 조건을 처리하는 코드를 여기에 추가해야 합니다.
	    }
		}catch(Exception e) {
			System.err.println("ㅇㅇㅇㅇㅇㅇㅇㅁㅁㅁㅁㅁㅁㅁㅂㅂㅂㅂㅂ");
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
			return jdbcTemplate.queryForObject(SELECTONE_MEMBER_COUNT, new MemberRowMapperMemberCounts());
		}
		else if(memberDTO.getSearchCondition().equals("ID_CHECK")){
            Object[] args = { memberDTO.getMemberID()};
            return jdbcTemplate.queryForObject(SELECTONE_CHECK, args, new MemberIDCKRowMapper() );
		}
		else if(memberDTO.getSearchCondition().equals("memberInfo")){
	        System.out.println("[로그:구본승] memberinfo 들어옴");
	        System.out.println("[로그:구본승] memberinfo memberID"+memberDTO.getMemberID());
	        Object[] args = { memberDTO.getMemberID()};
	        return jdbcTemplate.queryForObject(SELECTONE_CHECK, args, new MemberRowMapper() );
	      }
	    else if(memberDTO.getSearchCondition().equals("restore")){
	        Object[] args = { memberDTO.getMemberID(), memberDTO.getMemberEmail()};
	        return jdbcTemplate.queryForObject(SELECTONE_RESTORE, args, new MemberRestoreRowMapper() );
	    }

		// =====================
		else if(memberDTO.getSearchCondition().equals("checkRole")) {
			System.out.println("[로그:정현진] checkRole조건 memberDAO 들어옴");
			System.out.println("[로그:정현진] getMemberID : "+memberDTO.getMemberID());
			Object[] args = { memberDTO.getMemberID()};
			return jdbcTemplate.queryForObject(SELECTONE_CHECK_ROLE, args, new MemberRowMapperCheckRole() );
		}
		else if(memberDTO.getSearchCondition().equals("getEncryptPW")) {
			System.out.println("[로그:정현진] getEncryptPW조건 memberDAO 들어옴");
			Object[] args = { memberDTO.getMemberID()};
			return jdbcTemplate.queryForObject(SELECTONE_GET_ENCRYPT_PW, args, new MemberRowMapperGetEncryptPW() );
		}
		else {
			return null;
		}
		}catch (Exception e) {
			return null;
		}
	}
	
    
	public boolean insert(MemberDTO memberDTO) {
		System.out.println("[로그:정현진] MemberDAO 들어옴");
		System.out.println("[로그:정현진] memberDTO : "+memberDTO);
        int result =jdbcTemplate.update(INSERT,memberDTO.getMemberID(),memberDTO.getMemberPW(),memberDTO.getMemberName()
                ,memberDTO.getMemberPhoneNumber(),memberDTO.getMemberEmail(),memberDTO.getMemberGender()
                ,memberDTO.getMemberBirth());
        if(result<=0) {
            return false;
        }
            return true;
    }
   
    
	public boolean update(MemberDTO memberDTO) {
        int result=0;
        if(memberDTO.getSearchCondition().equals("UPDATE_PW")) {
            result = jdbcTemplate.update(UPDATE_PW,memberDTO.getMemberPW());            
        }else if(memberDTO.getSearchCondition().equals("UPDATE_NAME")){
            result = jdbcTemplate.update(UPDATE_NAME, memberDTO.getMemberName());
        }else if(memberDTO.getSearchCondition().equals("UPDATE_EMAIL")){
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



class MemberRowMapper1 implements RowMapper<MemberDTO>{

	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberCnt(rs.getInt("CNT_MEMBER"));
		return memberDTO;
	}
	
}

class MemberRowMapperByGrade implements RowMapper<MemberDTO> {
    
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO memberDTO=new MemberDTO();
		memberDTO.setMemberID(rs.getString("MEMBER_ID"));
		memberDTO.setMemberName(rs.getString("MEMBER_NAME"));
		memberDTO.setMemberGrade(rs.getString("MEMBER_GRADE"));
		return memberDTO;
	}
}

class MemberRowMapper implements RowMapper<MemberDTO> {
    
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO memberDTO=new MemberDTO();
		memberDTO.setMemberID(rs.getString("MEMBER_ID"));
		memberDTO.setMemberName(rs.getString("MEMBER_NAME"));
		memberDTO.setMemberPhoneNumber(rs.getString("MEMBER_PHONENUMBER"));
		memberDTO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
		memberDTO.setMemberGender(rs.getString("MEMBER_GENDER"));
		memberDTO.setMemberBirth(rs.getString("MEMBER_BIRTH"));
		memberDTO.setMemberGrade(rs.getString("MEMBER_GRADE"));
		memberDTO.setMemberRole(rs.getString("MEMBER_ROLE"));
		return memberDTO;
	}
}

class MemberRowMapper3 implements RowMapper<MemberDTO>{
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO memberDTO=new MemberDTO();
		memberDTO.setNewMemberCnt(rs.getInt("CNT_NEW_MEMBER"));
		return memberDTO;
	}
	
}

class MemberRowMapperMemberCounts implements RowMapper<MemberDTO>{
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDTO memberDTO=new MemberDTO();
		memberDTO.setMemberCnt(rs.getInt("MEMBER_COUNTS"));
		return memberDTO;
	}
}

// 로그인 RowMapper
class MemberRowMapperLogin implements org.springframework.jdbc.core.RowMapper<MemberDTO> {
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] MemberRowMapper 들어옴");
		MemberDTO memberDTO = new MemberDTO();
		System.out.println("[로그:정현진] memberID = "+rs.getString("MEMBER_ID"));
		memberDTO.setMemberID(rs.getString("MEMBER_ID"));
		memberDTO.setMemberRole(rs.getString("MEMBER_ROLE"));
		memberDTO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
	    memberDTO.setMemberName(rs.getString("MEMBER_NAME"));
	    memberDTO.setMemberPhoneNumber(rs.getString("MEMBER_PHONENUMBER"));
		return memberDTO;
	}
}

// 아이디 중복검사 RowMapper
class MemberIDCKRowMapper implements RowMapper<MemberDTO>{
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:구본승] MemberIDCKRowMapper 들어옴" );
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberID(rs.getString("MEMBER_ID"));
		System.out.println("[로그:구본승] memberID = "+rs.getString("MEMBER_ID"));
		return memberDTO;
	}
}
class MemberRestoreRowMapper implements RowMapper<MemberDTO>{
	   @Override
	   public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	      System.out.println("[로그:구본승] MemberRestoreRowMapper 들어옴" );
	      MemberDTO memberDTO = new MemberDTO();
	      memberDTO.setMemberID(rs.getString("MEMBER_ID"));
	      memberDTO.setMemberID(rs.getString("MEMBER_EMAIL"));
	      return memberDTO;
	   }
	}


// =============================================

// 비밀번호 암호화 RowMapper
class MemberRowMapperGetEncryptPW implements org.springframework.jdbc.core.RowMapper<MemberDTO> {
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] MemberRowMapperGetEncryptPW 들어옴");
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberPW(rs.getString("MEMBER_PW"));
		return memberDTO;
	}
}
// CheckRole RowMapper
class MemberRowMapperCheckRole implements org.springframework.jdbc.core.RowMapper<MemberDTO> {
	@Override
	public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] MemberRowMapperCheckRole 들어옴");
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberRole(rs.getString("MEMBER_ROLE"));
		return memberDTO;
	}
}
