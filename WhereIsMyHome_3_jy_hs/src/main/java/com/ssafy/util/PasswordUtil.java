package com.ssafy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    // SHA-256 해시화 메서드
    public static String hashPassword(String password) {
        try {
            // SHA-256 해시 알고리즘을 사용한 MessageDigest 객체 생성
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 입력된 비밀번호를 해시 처리
            byte[] hash = digest.digest(password.getBytes());

            // 해시된 값을 16진수로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();  // 해시된 비밀번호 반환
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 알고리즘이 존재하지 않는 경우 예외 처리
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
}
