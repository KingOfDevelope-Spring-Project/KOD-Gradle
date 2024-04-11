<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
	
<!DOCTYPE html>
<html>
<head>

<meta charset="EUC-KR">
<title>계정 복구 신청 페이지</title>

<!-- Theme style -->
<link rel="stylesheet" href="resources/dist/css/adminlte.min.css">
<!-- DataTables -->
<link rel="stylesheet"
	href="resources/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="resources/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="resources/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

<!-- Bootstrap -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />

<!-- Slick -->
<link rel="stylesheet" href="resources/css/slick.css" />
<link rel="stylesheet" href="resources/css/slick-theme.css" />

<!-- nouislider -->
<link rel="stylesheet" href="resources/css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="resources/css/font-awesome.min.css">

<!-- 로그인 후 이용해주세요. -->
<link rel="stylesheet" href="resources/css/checkLogin.css">

<!-- Custom stlylesheet -->
<link rel="stylesheet" href="resources/css/style.css" />
</head>
<body>
	<!-- UNREGISTER 탈퇴 신청 상태 회원 -->
	

	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>


	<!-- BREADCRUMB -->
	<div id="breadcrumb" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<h3 class="breadcrumb-header">계정 복구 신청 페이지</h3>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /BREADCRUMB -->

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		



		<form method="post" action="/requestMemberRestoration"
			id="restoreForm" style="margin: 0 auto; width: 40%;">
			<label for="memberName">이름</label>
			<input type="text" class="input" name="memberName" id="memberName" value="${member.memberName}" readonly><br><br>
			<label for="memberID">아이디</label>
			<input type="text" class="input" name="memberID" id="memberID" value="${member.memberID}" readonly><br><br>
			<label for="memberEmail">이메일</label>
			<input type="text" class="input" name="memberEmail" id="memberEmail" value="${member.memberEmail}" readonly><br><br>
			<label for="memberPhoneNumber">핸드폰</label>
			<input type="text" class="input" name="memberPhoneNumber" id="memberPhoneNumber" value="${member.memberPhoneNumber}" readonly> <br><br>
			 
			 <input type="submit" class="btn btn-primary btn-right"
				value="아이디 복구신청">
		</form>

		<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->


	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	<!-- jQuery Plugins -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/slick.min.js"></script>
	<script src="resources/js/nouislider.min.js"></script>
	<script src="resources/js/jquery.zoom.min.js"></script>
	<script src="resources/js/main.js"></script>



</body>
</html>







