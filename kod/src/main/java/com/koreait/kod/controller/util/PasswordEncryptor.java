package com.koreait.kod.controller.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptor {
	public String encrypt(String memberPW) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256"); // 256비트 해시 값을 생성
			messageDigest.update(memberPW.getBytes()); // 비밀번호(문자열)를 바이트 배열로 변환
			byte byteData[] = messageDigest.digest(); // 계산된 해시 값을 바이트 배열에 저장
//			StringBuilder stringBuilder = new StringBuilder(); // 다중 스레드 환경일 경우 StringBuffer 추천
//			for (int i = 0; i < byteData.length; i++) { // 비트연산을 수행하여 바이트를 양수로 변환 후 16진수로 표현
//				stringBuilder.append(Integer.toString((byteData[i] & 0xff)+0x100,16).substring(1));
//			}
			StringBuilder encryptedPassword = new StringBuilder();
			for (int i = 0; i < byteData.length; i++) { // 바이트를 양수로 변환 후 16진수 문자열로 변환
				String hex = Integer.toHexString(0xff & byteData[i]); // 16진수로 변환
				if(hex.length()==1) { // 만약 16진수가 1자리 수 일 경우 앞에 0을 추가하여 2자리수로 만듦
					encryptedPassword.append('0'); // 10 => 0A , 15 => 0F -> 암호화 결과의 일관성 유지
				}
				// 생성된 16진수 문자열을 StringBuilder 객체에 추가
				encryptedPassword.append(hex);
			}
			return encryptedPassword.toString(); // 생성된 (암호화)비밀번호 반환
			
		} catch (NoSuchAlgorithmException e) {
			System.out.println("[로그:정현진] 비밀번호 암호화 실패");
			throw new RuntimeException(); // 비밀번호 생성 실패시 예외처리
		} // try ~ catch
	} // encrypt
} // class
