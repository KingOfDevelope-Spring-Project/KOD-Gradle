package com.koreait.kod.model.address;

import java.util.List;

public interface AddressService {
	List<AddressDTO> selectAll(AddressDTO addressDTO);
	AddressDTO selectOne(AddressDTO addressDTO);
	
	boolean insert(AddressDTO addressDTO);
	boolean update(AddressDTO addressDTO);
	boolean delete(AddressDTO addressDTO);
}
