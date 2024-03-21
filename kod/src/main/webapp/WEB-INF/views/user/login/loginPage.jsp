<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="resources/css/login.css" />
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<meta charset="UTF-8">
<title>로그인페이지</title>
<!-- 네이버 소셜로그인 -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2-nopolyfill.js"></script>
<!-- jQuery Plugin -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js"></script>
<!-- 카카오톡 소셜로그인 -->
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.0/kakao.min.js" integrity="sha384-l+xbElFSnPZ2rOaPrU//2FF5B4LB8FiX5q4fXYTlfcG4PGpMkE1vcL7kNXI6Cci0" crossorigin="anonymous"></script>
<script>
  Kakao.init('09f5245c373047d1b84985742c98e322'); // 사용하려는 앱의 JavaScript 키 입력
</script>
</head>
<body>
	<div id="container">
		<!-- Heading -->
		<h1>
			<img src="resources/img/shopimg.png">
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
		<button class="join__btn" onclick="location.href='joinTermsOfUse.do'">회원가입</button>
		<div id="naver_id_login" style="height: inherit;"></div>
		<a id="kakao-login-btn" href="" style="height: inherit;">
			<img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="200" alt="카카오 로그인 버튼" />
		</a>
		<p id="token-result"></p>
	</div>
	<!-- 네이버 로그인 버튼 노출 영역 -->
	<!-- //네이버 로그인 버튼 노출 영역 -->
<script type="text/javascript">
  	var naver_id_login = new naver_id_login("QknPldO_qP5hGm2Nhx6M", "http://localhost:8088/login/oauth2/code/naver");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("green", 60,55);
  	naver_id_login.setDomain("http://localhost:8088");
  	naver_id_login.setState(state);
  	naver_id_login.setPopup();
  	naver_id_login.init_naver_id_login();
</script>
<script type="text/javascript">
	$("#kakao-login-btn").on("click", function(e){
		e.preventDefault();
		window.location.href = '/oauth2/authorization/kakao'
	})
</script>
</body>
<script src="resources/js/join.js"></script>
<script src="resources/js/login.js"></script>
</html>
