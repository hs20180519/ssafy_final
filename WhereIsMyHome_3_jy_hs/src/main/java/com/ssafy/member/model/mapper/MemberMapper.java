package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.member.model.MemberDto;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper { 
	MemberDto getMember(Map<String, String> request) throws SQLException;
	void insertMember(MemberDto memberDto) throws SQLException;
	void updateMember(MemberDto memberDto) throws SQLException;
	MemberDto getMemberByMemberId(String memberId) throws SQLException;
	void deleteMember(String memberId) throws SQLException;
	List<MemberDto>	getAllMembers() throws SQLException;
	List<MemberDto> searchMembers(String memberId) throws SQLException;
}
