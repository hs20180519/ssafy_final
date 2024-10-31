package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.member.model.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	MemberDto getMember(Map<String, String> request) throws SQLException;
	void insertMember(MemberDto memberDto) throws SQLException;

}
