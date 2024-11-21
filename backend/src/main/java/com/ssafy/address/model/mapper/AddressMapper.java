package com.ssafy.address.model.mapper;

import java.util.List;

import com.ssafy.address.model.AddressDto;

public interface AddressMapper {
	List<AddressDto> selectAddressesByMemberId(String memberId);

	void insertAddress(AddressDto addressDto);

	void updateAddress(AddressDto addressDto);

	void deleteAddress(Long addressId);
}
