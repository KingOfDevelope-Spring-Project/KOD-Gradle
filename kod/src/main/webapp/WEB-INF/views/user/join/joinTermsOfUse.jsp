<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용 약관 페이지</title>
<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="resources/css/login.css" />
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="icon" type="image/x-icon" href="resources/img/favion.png">

<style>
.header-logo {
	background-color: #000; /* 검정색 배경으로 설정 */
}
</style>

</head>
<body>

<header>
	<!-- LOGO -->
	<div>
		<div class="header-logo">
			<a href="/main" class="logo"> <img src="resources/img/logo.gif" style="width: 250px" height="65px" alt=""></a>
		</div>
	</div>
	<!-- /LOGO -->
</header>
	
	<!-- 폼 제출 버튼을 누르면 유효성을 검사하는 함수실행후 회원가입 페이지.do로 이동 post 타입 요청으로 데이터전송-->
	<!-- post 타입 요청은 get타입 요청과 달리 URL에 데이터를 노출시키지않아 보안적으로 더안전 데이터 크기제한이없어 대량의 데이터를 보낼수있음-->
	<form onsubmit="validateForm(form);" action="joinPage.do" method="post">

		<!-- 약관 전체 동의 체크박스 -->
		<div style="padding-left: 27px;">
			<!-- 약관 전체 동의 체크박스를 클릭하면 아래 자바스크립트에서 생성한 함수실행 -->
			<input type="checkbox" id="checkAll" name="checkAll" onclick="checkAllCheckboxes()"> <label for="checkAll"> 약관 전체 동의하기</label>
		</div>
		<br>

		<!-- 개인정보 수집동의 체크박스 -->
		<div style="padding-left: 27px;">
			<input type="checkbox" class="input" id="privacyAgreeCheckbox" name="privacyAgreeCheckbox"> <label for="privacyAgreeCheckbox"> [필수] 개인정보 수집 및 이용 동의 </label>
			<!-- 자세히 버튼을 클릭하면 아래 자바스크립트에서 생성한 함수실행 -->
			<button type="button" onclick="javascript:openWin1()" class="btn btn-secondary" style="border: 1px solid black; margin-bottom: 1%;">약관보기</button>
			<br>
		</div>
		<br>

		<!-- KOD 이용약관 동의 체크박스 -->
		<div style="padding-left: 27px;">
			<input type="checkbox" class="input" id="KODAgreeCheckbox" name="KODAgreeCheckbox"> <label for="KODAgreeCheckbox"> [필수] KOD 스토어 이용 악관 </label>
			<!-- 자세히 버튼을 클릭하면 아래 자바스크립트에서 생성한 함수실행 -->
			<button type="button" onclick="javascript:openWin2()" class="btn btn-secondary" style="border: 1px solid black; margin-bottom: 1%;">약관보기</button>
			<br> <br>
		</div>
		<br>

		<!-- 마케팅 활용 및 광고성 정보 수신 동의 체크박스 -->
		<div style="padding-left: 27px;">
			<input type="checkbox" class="input" id="marketingAgreeCheckbox" name="marketingAgreeCheckbox"> <label for="marketingAgreeCheckbox"> [선택] 마케팅 활용 및 광고성 정보 수신 동의 </label>
			<!-- 자세히 버튼을 클릭하면 아래 자바스크립트에서 생성한 함수실행 -->
			<button type="button" onclick="javascript:openWin3()" class="btn btn-secondary" style="border: 1px solid black; margin-bottom: 1%;">약관보기</button>
			<br> <br>
		</div>
		<br>
		<!-- 다음 버튼을 클릭하면 자바스크립트에서 생성한 유효성검사 함수실행 -->
		<button class="join__btn" type="button" onclick="validateForm(this.form)">다음</button>
	</form>
</body>
<script src="resources/js/join.js"></script>
</html>
