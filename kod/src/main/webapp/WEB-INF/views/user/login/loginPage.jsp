<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="resources/css/login.css" />
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<meta charset="UTF-8">
<title>로그인페이지</title>
<!-- jQuery Plugin -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
label:hover {
	cursor: pointer;
}

.notice {
	display: none;
}

.notice:hover {
	display: inline;
}

body {
	background-color: lightgray;
}

#container {
	background-color: #fff;
	padding: 7vh;
	border-radius: 5%;
	margin-top: 5vh;
	width: 35%;
}

h1 {
	margin: 0 0;
}

a img {
	width: 40vh
}
</style>
</head>
<body style="background-color: lightgray;">
	<div id="container" class="loginModal">
			<!-- Heading  -->
			<h1>
					<a href="/"> <img src="resources/img/logo_nagative.png">
					</a>
			</h1>
			<!-- Form -->
			<form name="loginform" action="/kodLogin" method="post">
					<!-- 아이디 입력란 -->
					<div class="first-input input__block first-input__block">
							<input type="text" placeholder="ID를 입력하세요." class="input" id="memberID" name="memberID" oninput="removeWhitespace(this)" />
					</div>

					<!-- 비밀번호 입력란 -->
					<div class="input__block">
							<input type="password" placeholder="비밀번호를 입력하세요." class="input" id="memberPW" name="memberPW" oninput="removeWhitespace(this)" />
					</div>
					<button class="signin__btn" onclick="submitLoginForm()">로그인</button>
			</form>
			<div style="text-align: center; font-size: larger;">
					<hr>
					<h5>SNS계정으로 간편 로그인/회원가입</h5>
					<div style="display: flex; justify-content: space-evenly;">
							<label for="kod_btn"><img src="resources/img/btn_kod.png" style="width: 10vh;"></label> <span class="notice">kod 회원가입 페이지로 이동</span>
							<button id="kod_btn" class="join__btn__kod" onclick="location.href='/checkTermsAgreement'" style="display: none;">회원가입</button>
							<label for="naver_btn"><img src="resources/img/btn_naver.png" style="width: 10vh;"></label> <span class="notice">네이버 로그인</span>
							<button id="naver_btn" class="join__btn__naver" onclick="location.href='/naverLogin'" style="display: none;">회원가입</button>
							<div class="kakao-btn" onclick="kakaoLogin()">
									<label for="kakao_btn"><img src="resources/img/btn_kakao.png" style="width: 10vh;"></label> <span class="notice">카카오 로그인</span>
									<button id="kakao_btn" class="join__btn__kakao" style="display: none;">회원가입</button>
							</div>
							<label for="google_btn"><img src="resources/img/btn_google.png" style="width: 10vh;"></label> <span class="notice">구글 로그인</span>
							<button id="google_btn" class="join__btn__google" onclick="location.href='/googleLogin'" style="display: none;">회원가입</button>
					</div>
			</div>
	</div>

<!-- 카카오 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script th:inline="javascript">
    // 클릭 이벤트를 관리하는 변수
    var kakaoBtnClicked = false;

    function kakaoLogin() {
        // 이미 클릭된 경우 더 이상 동작하지 않음
        if (kakaoBtnClicked) {
            alert('이미 로그인 요청이 진행 중입니다. 잠시 후 다시 시도해주세요.');
            return;
        }

        // 클릭 이벤트를 한 번만 실행되도록 변수 업데이트
        kakaoBtnClicked = true;

        // Kakao SDK 초기화
        Kakao.init('7a76e4c3e78f5c22de9c7a1a5ee8da15');

        // 카카오 로그인 요청
        Kakao.Auth.login({
            success: function(authObj) {
                // 로그인 성공 시 실행할 코드 작성
                var code = authObj.access_token; // access_token 값을 code로 변경

                // 서버로 획득한 code 전달
                $.ajax({
                    url: '/auth_kakao', // 수정되어야 할 부분
                    type: 'post',
                    data: { code: code },
                    success: function(response) {
                        // 로그인 후 처리할 작업 수행
                        console.log('로그인 성공:', response);
                        // 클릭 이벤트 변수 초기화
                        kakaoBtnClicked = false;

												location.href='/'; // 로그인을 성공했다면 
                    },
                    error: function(xhr, status, error) {
                        // 에러 처리
                        alert('로그인 요청 중 에러가 발생했습니다: ' + error);
                        // 클릭 이벤트 변수 초기화
                        kakaoBtnClicked = false;
                    }
                });
            },
            fail: function(err) {
                // 로그인 실패 시 실행할 코드 작성
                console.error('로그인 실패:', err);
                // 클릭 이벤트 변수 초기화
                kakaoBtnClicked = false;
            }
        });
    }
</script>

</body>
<script src="resources/js/join.js"></script>
<script src="resources/js/login.js"></script>
</html>

