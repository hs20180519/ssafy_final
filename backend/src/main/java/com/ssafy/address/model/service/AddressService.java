package com.ssafy.address.model.service;

import java.util.List;

import com.ssafy.address.model.AddressDto;

public interface AddressService {
	List<AddressDto> getAddressByMemberId(String memberId);

	void addAddress(AddressDto addressDto);

	void updateAddress(AddressDto addressDto);

	void deleteAddress(Long addressId);
}
