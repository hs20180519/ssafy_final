package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.util.PasswordUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 해시

	@Override
	public void updateMember(MemberDto memberDto) throws SQLException {
		memberMapper.updateMember(memberDto);
	}

	@Override
	public MemberDto findMemberByMemberId(String memberId) throws SQLException {
		return memberMapper.getMemberByMemberId(memberId);
	}

	@Override
	public boolean verifyPassword(String inputPassword, String EncodedPassword) throws SQLException {
		if (!PasswordUtil.verifyPassword(inputPassword, EncodedPassword)) {
			return false;
			//throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	@Override
	public void deleteMember(String memberId) throws SQLException {
		memberMapper.deleteMember(memberId);
	}

	@Override
	public List<MemberDto> getAllMembers() throws SQLException {
		return memberMapper.getAllMembers();
	}

	@Override
	public List<MemberDto> searchMembers(String memberId) throws SQLException {
		return memberMapper.searchMembers(memberId);
	}

}
