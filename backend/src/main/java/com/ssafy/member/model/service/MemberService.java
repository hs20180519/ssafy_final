package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.member.model.MemberDto;

public interface MemberService {
	void updateMember(MemberDto memberDto) throws SQLException;

	MemberDto findMemberByMemberId(String memberId) throws SQLException;

	boolean verifyPassword(String inputPassword, String EncodedPassword) throws SQLException;

	void deleteMember(String memberId) throws SQLException;

	List<MemberDto> getAllMembers() throws SQLException;

	List<MemberDto> searchMembers(String memberId) throws SQLException;
	
	boolean existsByEmail(String email);
}
