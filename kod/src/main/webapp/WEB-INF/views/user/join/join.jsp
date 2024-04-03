<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="resources/css/login.css" />
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />


<meta charset="UTF-8">
<title>회원가입 페이지</title>

<link rel="icon" type="image/x-icon" href="resources/img/favion.png">

<style>
.header-logo {
	background-color: #000;
	/* 검정색 배경으로 설정 */
}
</style>

<style>
.container h1 {
	color: black;
}
</style>

</head>

<body>

	<header>
		<!-- LOGO -->
		<div>
			<div class="header-logo">
				<a href="/" class="logo"> <img src="resources/img/logo.gif" style="width: 250px" height="65px" alt="">
				</a>
			</div>
		</div>
		<!-- /LOGO -->
	</header>

	<div class="container">
		<!-- Heading -->
		<h1>회원가입</h1>
		<!-- Form -->
		<form name="joinform" onsubmit="formCheck(this);" action="/join" method="post">

			<!-- 아이디 입력란 -->
			<!-- oninput이벤트를 사용해 사용자가 입력할때마다 공백을 제거하는 함수 호출 -->
			<div class="input__block" style="padding-left: 27px;">
				<input type="text" placeholder="아이디" class="input" id="memberID" name="memberID" style="display: inline-block; width: 70%;" minlength="6" maxlength="13" oninput="removeWhitespace(this)" required />

				<!-- ID중복검사 버튼생성 클릭시 check()함수호출 -->
				<input type="button" id="but" value="ID중복검사" onclick="asyncCheckMemberID()" style="display: inline-block; width: 25%; padding-right: 2%;"><br> <label> 아이디는 6~13자의 영문 소문자, 숫자로 입력해주세요. </label>
			</div>
			<div class="first-input input__block first-input__block" style="padding-left: 27px;">
				<span id="msg"></span>
			</div>

			<!-- 비밀번호 입력란 -->
			<div class="input__block" style="padding-left: 27px;">
				<input type="password" placeholder="비밀번호" class="input" id="memberPW" name="memberPW" style="width: 95%; display: inline-block;" minlength="6" maxlength="13" oninput="removeWhitespace(this)" required /><br>
				<!-- oninput이벤트를 사용해 사용자가 입력할때마다 공백을 제거하는 함수 호출 -->
				<label> 비밀번호는 6~13자의 영문 소문자, 숫자, 특수문자가 각각 최소 1개 이상 포함되어야 합니다. </label>
			</div>

			<!-- 비밀번호 확인입력란 -->
			<div class="input__block" style="padding-left: 27px;">
				<input type="password" placeholder="비밀번호 확인" class="input" id="memberPWCK" name="memberPWCK" style="width: 95%; display: inline-block;" minlength="6" maxlength="13" oninput="removeWhitespace(this)" required />
				<!-- 비밀번호 불일치 메시지 -->
				<!-- memberPWCK'라는 id를 가진 입력란의 입력값이 변경될 때마다 호출되는 이벤트 리스너입니다.
			사용자가 비밀번호확인란을 입력하면 비밀번호입력란과 값이 일치하는지확인
			일치하면 메시지를 숨기고, 일치하지않으면 메시지를 화면에 표시합니다 -->
				<span id="passwordMismatch" style="color: red; display: none;">비밀번호가 일치하지 않습니다.</span>
			</div>

			<!-- 이름 입력란 -->
			<div class="input__block" style="padding-left: 27px;">
				<input type="name" placeholder="이름" class="input" id="memberName" name="memberName" style="width: 95%; display: inline-block; margin-top: 2%;" minlength="2" maxlength="10" oninput="removeWhitespace(this)" required /> <br>
				<label> 2글자 이상 한글로 입력해주세요. </label>
			</div>

			<!-- 성별 선택버튼 -->
			<div style="padding-left: 27px; border: 1.5px solid rgb(192, 192, 192); border-radius: 8px; width: 91.5%; margin-left: 4%; align-items: center; display: flex; height: 5vh;">
				<input type="radio" class="form-check-label" id="memberGender" name="memberGender" value="male" style="margin-left: 30%;">남 <input type="radio" class="form-check-label" id="memberGender" name="memberGender" value="female" style="margin-left: 30%;">여
			</div>
			<label style="margin-left: 4%;"> 성별을 체크해주세요. </label>
			<!-- 생년월일 입력란 -->
			<div class="input__block" style="padding-left: 27px;">
				<input type="text" id="year" name="year" placeholder=" ex) 1997 " style="display: inline-block; width: 30%;" minlength="4" maxlength="4" oninput="removeWhitespace(this)" required> 
				<select class="box" id="month" name="month" style="width: 30%; display: inline-block; margin-left: 2%;"></select> 
				<input type="text" id="day" name="day" placeholder=" ex) 11 " style="display: inline-block; width: 30%; margin-left: 2%;" minlength="2" maxlength="2" oninput="removeWhitespace(this)" required>
			</div>


			<!-- 핸드폰번호 입력란 -->
			<div class="input__block" style="padding-left: 27px;">
				<input type="text" id="phoneNumberPrefix" name="phoneNumberPrefix" placeholder=" 010 " style="display: inline-block; width: 22%;" minlength="3" maxlength="3" oninput="removeWhitespace(this)" required> 
				<span style="display: inline-block; margin-left: 5px; margin-right: 5px;">-</span> 
				<input type="text" id="phoneNumberMiddle" name="phoneNumberMiddle" placeholder=" 0000 " style="display: inline-block; width: 22%;" minlength="4" maxlength="4" oninput="removeWhitespace(this)" required> 
				<span style="display: inline-block; margin-left: 5px; margin-right: 5px;">-</span> 
				<input type="text" id="phoneNumberSuffix" name="phoneNumberSuffix" placeholder=" 0000 " style="display: inline-block; width: 22%;" minlength="4" maxlength="4" oninput="removeWhitespace(this)" required>
	



			<input type="button" id="authenticationNumber" value="인증번호 발송" onclick="authentication_Number()"style="display: inline-block; width: 21%; padding-right: 2%;">
			
					
			
			</div>
			
	
		
			
								
				<div class="input__block" style="padding-left: 27px;">
				<input type="text" placeholder="인증번호" class="input" id="phoneNumberCK" name="phoneNumberCK"
					style="display: inline-block; width: 70%;" minlength="6" maxlength="6"
					oninput="removeWhitespace(this)" required />

				
				
				
				<input type="button" id="authenticationNumberCheck" value="인증번호 확인" onclick="authentication_Number_Check()"
					style="display: inline-block; width: 25%; padding-right: 2%;"><br>
			</div>


	<!-- 이메일 입력란  -->
			<div class="input__block" style="padding-left: 27px;">
				<!-- <div class="input__block" style="display: flex; justify-content: center;"> -->
				<input type="text" placeholder="Email1" class="input" id="emailUsername" name="emailUsername" style="display: inline-block; width: 29%;" oninput="removeWhitespace(this)" required />
				<span style="display: inline-block;">@</span>
				<input class="input" id="domain-txt" type="text" placeholder="domain.com" name="emailDomain" style="display: inline-block; width: 29%;" oninput="removeWhitespace(this)" required /> 
				<select class="box" id="domain-list" name="emailDomain" style="display: inline-block; width: 29%; margin-left: 3%;" required>
					<option value="type">직접 입력</option>
					<option value="naver.com">naver.com</option>
					<option value="google.com">google.com</option>
					<option value="hanmail.net">hanmail.net</option>
					<option value="nate.com">nate.com</option>
					<option value="kakao.com">kakao.com</option>
				</select>
			</div>
			<!-- 배송지 입력 임시 주석처리 -->
			<!-- <div class="input__block">
				<input class="input" type="text" id="adrsName" name="adrsName" placeholder="집 / 회사 / 친구" oninput="removeWhitespace(this)" style="margin-bottom: 2%; width: 91%; margin-left: 4%;"> <input type="text" id="sample4_postcode" name="adrsZipcode" placeholder="우편번호" style="display: inline-block; width: 30%; margin-left: 4%;" readonly> <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" style="display: inline-block; width: 20%; height: inherit; padding: 1rem 1rem;"><br>
				<br> <input type="text" id="sample4_roadAddress" name="adrsStreet" placeholder="도로명주소" style="margin-bottom: 2%; margin-left: 4%; width: 91%;" readonly> <input type="text" id="sample4_jibunAddress" placeholder="지번주소" name="adrsLotNum" style="margin-bottom: 2%; margin-left: 4%; width: 91%;" readonly> <span id="guide" style="color: #999; display: none"></span> <input type="text" id="sample4_detailAddress" name="adrsDetail" placeholder="상세주소" style="margin-bottom: 2%; margin-left: 4%; width: 91%;" oninput="removeWhitespace(this)"> <br>
			</div> -->

			<!-- 회원가입 버튼  -->
			<button class="join__btn" onclick="return formCheck(this.form)">회원가입</button>
		</form>
	</div>
	<!-- jQuery Plugin -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- 인증번호 발송 , 체크 유효성검사 js -->
	<script src="resources/js/authentication_Number.js"></script> 
	<script src="resources/js/authentication_Number_Check.js"></script>
	<!-- 아이디 중복검사 js -->
	<script src="resources/js/asyncCheckMemberID.js"></script>
	<!-- 주소 API -->
	<!-- <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> -->
	<!-- [김진영] 2024.03.18 유효성 검사 스크립트 join.js로 모듈화 진행 -->
	<script src="resources/js/join.js"></script>
	<script src="resources/js/login.js"></script>
	

	
</body>

</html>