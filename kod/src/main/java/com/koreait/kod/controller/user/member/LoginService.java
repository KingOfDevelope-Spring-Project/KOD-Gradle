package com.koreait.kod.controller.user.member;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.member.SocialMemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService {

    @Autowired
    SocialMemberService socialMemberService;

    public String getUserInfo(String accessToken, HttpSession session) {
        log.info("[로그:정현진] getUserInfo 메소드 시작");

        // 액세스 토큰을 사용하여 사용자 정보 가져오기
        if (accessToken != null && !accessToken.isEmpty()) {
        	MemberDTO memberDTO = new MemberDTO();
            String requestURL = "https://kapi.kakao.com/v2/user/me";

            try {
                URL url = new URL(requestURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Bearer " + accessToken);

                int responseCode = conn.getResponseCode();
                log.info("responseCode: " + responseCode);

                BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder result = new StringBuilder();
                while ((line = buffer.readLine()) != null) {
                    result.append(line);
                }
                
                // Kakao API 응답 로그 출력
                log.info("[로그:정현진] Kakao API 응답: " + result.toString());
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(result.toString());
                JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
                JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
                System.out.println("[로그:정현진] kakao_account : "+kakao_account);
                
                
                String jsonString = result.toString();
                System.out.println("[로그:정현진] jsonString : "+jsonString);
                JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
                
                String memberID = null;
                JsonElement idElement = jsonObject.get("id");
                if (idElement != null && !idElement.isJsonNull()) {
                    memberID = idElement.getAsString();
                }
                System.out.println("[로그:정현진] memberID : "+memberID);

                // MemberDTO 객체에 memberID 값을 설정해줍니다.

                String memberName = null;
                JsonElement nicknameElement = properties.getAsJsonObject().get("nickname");
                if (nicknameElement != null && !nicknameElement.isJsonNull()) {
                    memberName = nicknameElement.getAsString();
                }
                System.out.println("[로그:정현진] memberName : "+memberName);

                memberDTO.setMemberID(memberID);
                memberDTO.setMemberName(memberName);

                buffer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            // 회원 정보가 있는지 확인하고 없으면 저장
            MemberDTO member = socialMemberService.selectOne(memberDTO);
            if (member == null) {
                //member null 이면 정보가 저장 안되어있는거라서 정보를 저장.
            	System.out.println("[로그:정현진] 카카오 회원가입 진행");
            	socialMemberService.insert(memberDTO);
                //저장한 member 정보 다시 가져오기 HashMap이라 형변환 시켜줌
            	System.out.println("[로그:정현진] 카카오 로그인 진행");
                member = socialMemberService.selectOne(memberDTO);
            } 
        	System.out.println("[로그:정현진] 카카오 로그인 성공");
            session.setAttribute("memberID", member.getMemberID());
        }
        return "user/main";
    }
}