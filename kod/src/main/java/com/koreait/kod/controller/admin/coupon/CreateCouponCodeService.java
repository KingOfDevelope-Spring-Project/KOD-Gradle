package com.koreait.kod.controller.admin.coupon;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class CreateCouponCodeService {

	public String getCouponCode(int couponLength,String couponString) {
		System.out.println("[로그:정현진] 쿠폰코드 생성 시작");
		
		/* StringBuilder 클래스란 ?
		 * 문자열을 동적으로 구축하고 변경해야 할 때 유용하며, 
		 * 특히 문자열 연산이 많은 상황에서 성능을 향상시키는 데 유용함
		 * 특징 : 가변성, 메모리할당, 스레드 안정
		 * 
		 * StringBuilder와 StringBuffer의 차이
		 * StringBuilder는 단일 스레드 환경에서 유용, 속도가 빠름
		 * StringBuffer는 멀티 스레드 환경에서 유용, 동기화 처리로 인해 StringBuilder보다 속도가 느림, 스레드 안정성이 좋아서 데이터 일관성 보장
		 */
		
		StringBuilder couponCode = new StringBuilder();
		Random random = new Random();
		
		
		for (int i = 0; i < couponLength; i++) {
			int couponValue = random.nextInt(couponString.length());
			couponCode.append(couponString.charAt(couponValue));
		}
		System.out.println("[로그:정현진] 생성된 쿠폰 코드 : "+couponCode);
		
		System.out.println("[로그:정현진] 쿠폰코드 생성 완료");
		return couponCode.toString();
	}
}
