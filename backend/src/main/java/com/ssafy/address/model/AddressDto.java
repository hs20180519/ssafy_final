package com.ssafy.address.model;

import lombok.Data;

@Data
public class AddressDto {
	private Long addressId;
	private String memberId;
	private String name;
	private String roadNameAddress;
}
