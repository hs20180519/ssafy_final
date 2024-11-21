package com.ssafy.address.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.address.model.AddressDto;
import com.ssafy.address.model.mapper.AddressMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressMapper addressMapper;

	// TODO : 예외 처리 추가
	@Override
	public List<AddressDto> getAddressByMemberId(String memberId) {
		return addressMapper.selectAddressesByMemberId(memberId);
	}

	@Override
	public void addAddress(AddressDto addressDto) {
		addressMapper.insertAddress(addressDto);
	}

	@Override
	public void updateAddress(AddressDto addressDto) {
		addressMapper.updateAddress(addressDto);
	}

	@Override
	public void deleteAddress(Long addressId) {
		addressMapper.deleteAddress(addressId);
	}
}