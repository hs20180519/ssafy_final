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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/member")
@AllArgsConstructor
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {
	private final MemberService memberService;

	@Operation(summary = "로그인", description = "사용자 로그인 처리")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "로그인 성공"),
			@ApiResponse(responseCode = "401", description = "로그인 실패 - 인증되지 않은 사용자"),
			@ApiResponse(responseCode = "500", description = "서버 오류") })
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

	@Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "회원 가입 성공"),
			@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 가입 중 오류 발생") })
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) {
		try {
			memberService.join(memberDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입이 완료되었습니다. 로그인 해주세요.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입 중 오류가 발생했습니다.");
		}
	}

	@Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공"),
			@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 정보 수정 중 오류 발생") })
	@PutMapping("/{memberId}")
	public ResponseEntity<MemberDto> updateMember(@PathVariable String memberId, @RequestBody MemberDto memberDto) {
		try {
			memberService.updateMember(memberDto);
			return ResponseEntity.ok(memberDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Operation(summary = "회원 정보 조회", description = "회원 ID로 회원 정보를 조회합니다.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공"),
			@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 정보 조회 중 오류 발생") })
	@GetMapping("/{memberId}")
	public ResponseEntity<MemberDto> findMember(@PathVariable String memberId) {
		try {
			MemberDto memberDto = memberService.findMemberByMemberId(memberId);
			return ResponseEntity.ok(memberDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Operation(summary = "회원 삭제", description = "회원 ID로 회원 정보를 삭제합니다.")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "회원 삭제 성공"),
			@ApiResponse(responseCode = "404", description = "회원 없음 - 존재하지 않는 회원 ID"),
			@ApiResponse(responseCode = "500", description = "서버 오류 - 삭제 중 오류 발생") })
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

	@Operation(summary = "전체 회원 조회", description = "모든 회원 정보를 조회합니다.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "전체 회원 조회 성공"),
			@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 조회 중 오류 발생") })
	@GetMapping("/member-list")
	public ResponseEntity<List<MemberDto>> getAllMembers() {
		try {
			List<MemberDto> members = memberService.getAllMembers();
			return ResponseEntity.ok(members);
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Operation(summary = "회원 검색", description = "회원 ID로 회원 정보를 검색합니다.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "회원 검색 성공"),
			@ApiResponse(responseCode = "404", description = "회원 없음 - 해당 ID의 회원을 찾을 수 없음"),
			@ApiResponse(responseCode = "500", description = "서버 오류 - 회원 검색 중 오류 발생") })
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
