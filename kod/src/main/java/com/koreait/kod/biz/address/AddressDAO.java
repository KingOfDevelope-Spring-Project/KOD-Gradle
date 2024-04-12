package com.koreait.kod.biz.address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("addressDAO")
public class AddressDAO {
	
	@Autowired // DI의존주입 /* @Autowired는 만능이 아니다.. 메모리에 로드가 되어있어야 가능하다. -> new JdbcTemplate();
	private JdbcTemplate jdbcTemplate; // 의존관계 -> DI(의존주입) -> @Autowired

	private static final String SELECTALL="";
	private static final String SELECTONE=
					  "SELECT  "
					  + "    M.MEMBER_NAME, "
					  + "    M.MEMBER_PHONENUMBER, "
					  + "    A.ADDRESS_NAME, "
					  + "    A.ADDRESS_STREET, "
					  + "    A.ADDRESS_LAND, "
					  + "    A.ADDRESS_DETAIL, "
					  + "    A.ADDRESS_ZIPCODE "
					  + "FROM  "
					  + "    MEMBER M "
					  + "INNER JOIN  "
					  + "    ADDRESS A ON M.MEMBER_ID = A.MEMBER_ID "
					  + "WHERE  "
					  + "    M.MEMBER_ID = ?";
	private static final String INSERT="";
	private static final String UPDATE="";
	private static final String DELETE="";

	public List<AddressDTO> selectAll(AddressDTO addressDTO) {
		return null;
	}

	public AddressDTO selectOne(AddressDTO addressDTO) {
		System.out.println("[로그:정현진] AddressDTO selectOne들어옴");
		Object[] args= {addressDTO.getMemberID()};
		try {
			return jdbcTemplate.queryForObject(SELECTONE,args,new AddressRowMapper());
		}catch(Exception e) {
			return null;
		}
	}

	public boolean insert(AddressDTO addressDTO) {
		return false;
	}

	public boolean update(AddressDTO addressDTO) {
		return false;
	}

	public boolean delete(AddressDTO addressDTO) {
		return false;
	}
}

class AddressRowMapper implements RowMapper<AddressDTO>{
	@Override
	public AddressDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[로그:정현진] AddressRowMapper 들어옴");
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setMemberName(rs.getString("MEMBER_NAME"));
		addressDTO.setMemberPhoneNumber(rs.getString("MEMBER_PHONENUMBER"));
		addressDTO.setAddressName(rs.getString("ADDRESS_NAME"));
		addressDTO.setAddressStreet(rs.getString("ADDRESS_STREET"));
		addressDTO.setAddressLand(rs.getString("ADDRESS_LAND"));
		addressDTO.setAddressDetail(rs.getString("ADDRESS_DETAIL"));
		addressDTO.setAddressZipCode(rs.getString("ADDRESS_ZIPCODE"));
		System.out.println("[로그:정현진] addressDTO : "+addressDTO);
		return addressDTO;
	}
}


