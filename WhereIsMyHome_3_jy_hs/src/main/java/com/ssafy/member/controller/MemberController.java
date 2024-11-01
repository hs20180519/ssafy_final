package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/member")
@AllArgsConstructor
public class MemberController {
	private final MemberService memberService;

	// 사용자 인증 처리 추가시 restful하게 하려면 post
	// responseBody : restController 적용시 모든 메소드에 자동 적용
	// @RequestBody : JSON 데이터를 요청 본문에 담아서 보내면, Spring이 이를 해당 메서드 파라미터로 변환
	@PostMapping("/login")
	public ResponseEntity<MemberDto> login(@RequestBody Map<String, String> request) {
		try {
			MemberDto memberDto = memberService.login(request);
			if (memberDto != null) {
				// String token = jwtService.generateToken(member); // 로그인 성공 시 토큰 발급 : 추후 추가
				// return ResponseEntity.ok().header("Authorization", "Bearer " +
				// token).body("로그인 성공");
				return ResponseEntity.ok(memberDto);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) {
		try {
			memberService.join(memberDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입이 완료되었습니다. 로그인 해주세요.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입 중 오류가 발생했습니다.");
		}
	}

	// 회원 수정
	@PutMapping("/{memberId}")
	public ResponseEntity<MemberDto> updateMember(@PathVariable String memberId, @RequestBody MemberDto memberDto) {
		try {
			memberService.updateMember(memberDto);
			return ResponseEntity.ok(memberDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// 회원 조회
	@GetMapping("/{memberId}")
	public ResponseEntity<MemberDto> findMember(@PathVariable String memberId) {
		try {
			MemberDto memberDto = memberService.findMemberByMemberId(memberId);
			return ResponseEntity.ok(memberDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	// 회원 삭제
	// TODO : 관심 지역 삭제 CASCADE
	@DeleteMapping("/{memberId}")
	public ResponseEntity<String> deleteMember(@PathVariable String memberId) {
	    try {
	        MemberDto memberDto = memberService.findMemberByMemberId(memberId);
	        if (memberDto == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원이 존재하지 않습니다.");
	        }
	        memberService.deleteMember(memberId); // 회원 삭제 메소드 호출
	        return ResponseEntity.noContent().build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 중 오류가 발생했습니다.");
	    }
	}
	
	// 모든 회원 조회
	@GetMapping("/member-list")
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        try {
            List<MemberDto> members = memberService.getAllMembers();
            return ResponseEntity.ok(members);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	// 회원 검색
	@GetMapping("/search")
	public ResponseEntity<List<MemberDto>> searchMembers(@RequestParam String memberId) {
		try {
			List<MemberDto> members = memberService.searchMembers(memberId);
			if (members.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(members);
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
}
