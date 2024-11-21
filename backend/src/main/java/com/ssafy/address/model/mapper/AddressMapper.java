package com.ssafy.address.model.mapper;

import java.util.List;

import com.ssafy.address.model.AddressDto;

public interface AddressMapper {
	void insertAddress(AddressDto addressDto);

	List<AddressDto> selectAddressesByMemberId(String memberId);
}
