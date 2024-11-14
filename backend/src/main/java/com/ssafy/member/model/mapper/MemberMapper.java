package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.auth.model.request.LoginRequest;
import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	MemberDto getMember(LoginRequest loginInfo) throws SQLException;

	MemberDto getMemberByNameAndEmail(@Param("name")
	String name, @Param("email")
	String email);

	void insertMember(MemberDto memberDto) throws SQLException;

	void updateMember(MemberDto memberDto) throws SQLException;

	MemberDto getMemberByMemberId(String memberId) throws SQLException;

	void deleteMember(String memberId) throws SQLException;

	List<MemberDto> getAllMembers() throws SQLException;

	List<MemberDto> searchMembers(String memberId) throws SQLException;
}
