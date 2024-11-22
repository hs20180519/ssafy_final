package com.ssafy.address.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.address.model.AddressDto;

@Mapper
public interface AddressMapper {
	List<AddressDto> selectAddressesByMemberId(String memberId);

	void insertAddress(AddressDto addressDto);

	void updateAddress(AddressDto addressDto);

	void deleteAddress(Long addressId);
}
