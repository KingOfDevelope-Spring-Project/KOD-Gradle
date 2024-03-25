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
			<button class="signin__btn" loginform.submit>로그인</button>
		</form>
		<div style="text-align: center; font-size: larger;">
			<hr>
			<h5>SNS계정으로 간편 로그인/회원가입</h5>
			<div style="display: flex; justify-content: space-evenly;">
				<label for="kod_btn"><img src="resources/img/btn_kod.png" style="width: 10vh;"></label> <span class="notice">kod 회원가입 페이지로 이동</span>
				<button id="kod_btn" class="join__btn__kod" onclick="location.href='/checkTermsAgreement'" style="display: none;">회원가입</button>
				<label for="naver_btn"><img src="resources/img/btn_naver.png" style="width: 10vh;"></label> <span class="notice">네이버 로그인</span>
				<button id="naver_btn" class="join__btn__naver" onclick="location.href='/naverLogin'" style="display: none;">회원가입</button>
				<label for="kakao_btn"><img src="resources/img/btn_kakao.png" style="width: 10vh;"></label> <span class="notice">카카오 로그인</span>
				<button id="kakao_btn" class="join__btn__kakao" onclick="location.href='/kakaoLogin'" style="display: none;">회원가입</button>
				<label for="google_btn"><img src="resources/img/btn_google.png" style="width: 10vh;"></label> <span class="notice">구글 로그인</span>
				<button id="google_btn" class="join__btn__google" onclick="location.href='/googleLogin'" style="display: none;">회원가입</button>
			</div>
		</div>
	</div>
</body>
<script src="resources/js/join.js"></script>
<script src="resources/js/login.js"></script>
</html>
