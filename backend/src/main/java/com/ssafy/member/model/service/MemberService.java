package com.ssafy.member.model.service;

import com.ssafy.member.model.MemberDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MemberService {

    void updateMember(MemberDto memberDto) throws SQLException;
    
    MemberDto findMemberByMemberId(String memberId) throws SQLException;    
    
    void deleteMember(String memberId) throws SQLException;
    
    List<MemberDto> getAllMembers() throws SQLException;
    
    List<MemberDto> searchMembers(String memberId) throws SQLException;
}