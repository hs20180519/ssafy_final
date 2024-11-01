package com.ssafy.member.model.service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberDto login(Map<String, String> request) throws SQLException {
        return memberMapper.getMember(request);
    }

    @Override
    public void join(MemberDto memberDto) throws SQLException {
        memberMapper.insertMember(memberDto);
    }

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
