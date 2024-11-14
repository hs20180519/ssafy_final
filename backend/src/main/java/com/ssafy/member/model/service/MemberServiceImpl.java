package com.ssafy.member.model.service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

	@Override
	public void updateMember(MemberDto memberDto) throws SQLException {
		memberMapper.updateMember(memberDto);
	}

	@Override
	public MemberDto findMemberByMemberId(String memberId) throws SQLException {
		return memberMapper.getMemberByMemberId(memberId);
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
