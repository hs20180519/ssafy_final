package com.ssafy.member.controller;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("api/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 사용자 인증 처리 추가시 restful하게 하려면 post
    // responseBody : restController 적용시 모든 메소드에 자동 적용
    // @RequestBody : JSON 데이터를 요청 본문에 담아서 보내면, Spring이 이를 해당 메서드 파라미터로 변환
    @PostMapping("/login")
    public ResponseEntity<MemberDto> login(@RequestBody Map<String, String> request){
        try {
            MemberDto memberDto = memberService.login(request);
            if (memberDto != null) {
                // String token = jwtService.generateToken(member); // 로그인 성공 시 토큰 발급 : 추후 추가
                // return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("로그인 성공");
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
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("회원 가입이 완료되었습니다. 로그인 해주세요.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원 가입 중 오류가 발생했습니다.");
        }
    }

}
