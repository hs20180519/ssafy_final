package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberMapper {
	MemberDto login(Map<String, String> map) throws SQLException;
	void join(MemberDto memberDto) throws SQLException;

}
