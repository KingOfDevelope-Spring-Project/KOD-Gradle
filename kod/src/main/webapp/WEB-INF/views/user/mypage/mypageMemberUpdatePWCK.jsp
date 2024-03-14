<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>본인확인 - 마이페이지</title>


<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<!--  my page bigbox -->

<!-- 
    <link rel="shortcut icon" href="https://static.msscdn.net/favicon.ico?202307311841" type="image/x-icon">
     -->
<link rel="stylesheet" type="text/css" href="https://static.msscdn.net/skin/musinsa/css/magazine_common.css?202307311841">
<!-- 폰트 -->
<!-- <link rel="stylesheet" type="text/css" href="https://static.msscdn.net/skin/musinsa/css/store_common.css?202307311841"> -->
<!--  
    <link rel="stylesheet" href="https://static.msscdn.net/ui/build/pc/css/common.css">
    <link rel="stylesheet" type="text/css" href="https://static.msscdn.net/ui/musinsa/resources/common/css/icon.min.css?202307311841">
    <link rel="shortcut icon" href="https://static.msscdn.net/favicon.ico?202307311841" type="image/x-icon">
    <link rel="stylesheet" href="https://static.msscdn.net/platform/css/common.css">
    <link rel="stylesheet" type="text/css" href="https://static.msscdn.net/skin/musinsa/css/new.css?202307311841">
    -->
<link rel="stylesheet" type="text/css" href="https://static.msscdn.net/skin/musinsa/css/sub.css?202307311841">
<!-- 중요한거 -->
<link rel="stylesheet" type="text/css" href="https://static.msscdn.net/skin/musinsa/css/layout.min.css?202307311841">
<link rel="stylesheet" type="text/css" href="https://static.msscdn.net/skin/musinsa/css/style.min.css?202307311841">
<link rel="stylesheet" type="text/css" href="https://static.msscdn.net/skin/musinsa/css/media_query.css?202307311841">
<script src="https://static.msscdn.net/skin/musinsa/js/jquery.lazyload.min.js?202307311841" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="https://static.msscdn.net/skin/musinsa/css/mypage.min.css?202307311841">
<link rel="stylesheet" type="text/css" href="https://static.msscdn.net/skin/musinsa/css/guide.min.css?202307311841">
<link type="text/css" rel="stylesheet" href="css/mypage2.css" />
<script src="https://static.msscdn.net/static/member/js/ajax.js?202307311841" type="text/javascript"></script>
<script src="https://static.msscdn.net/static/member/js/crypto-js.min.js?202307311841"></script>
<script src="https://static.msscdn.net/static/member/js/security.js?202307311841"></script>
<script type="text/javascript" src="https://appleid.cdn-apple.com/appleauth/static/jsapi/appleid/1/en_US/appleid.auth.js"></script>
<script type="text/javascript" src="https://static.msscdn.net/platform/js/common.js"></script>
<script src="https://static.msscdn.net/static/member/js/constant.js?202307311841" type="text/javascript"></script>
<script src="https://static.msscdn.net/static/member/js/ui/config.js?202307311841" type="text/javascript"></script>
<script src="https://static.msscdn.net/static/member/js/ui.js?202307311841" type="text/javascript"></script>
<script src="https://static.msscdn.net/static/member/js/string.js?202307311841" type="text/javascript"></script>
<!-- /중요한거 -->

<link type="text/css" rel="stylesheet" href="css/mypage.css" />
<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />
<!-- Slick -->
<link type="text/css" rel="stylesheet" href="css/slick.css" />
<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

<!-- nouislider -->
<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- 
        <script type="text/javascript" src="https://static.msscdn.net/static/common/1.2.0/pc.js"></script>
    	<input type="hidden" id="imageResourceUrl" value="https://image.msscdn.net">
     	-->

<body>
	<jsp:include page="util/header.jsp"></jsp:include>
	<jsp:include page="util/navigation.jsp"></jsp:include>
	<div class="container mypage musinsa">
		<main class="content" style="height: 100%; margin-bottom: 10vh;">
			<div id="commonMypage" style="position: absolute; top: 250px; left: 170px;" >
				<nav class="sc-1t1fxnz-0 bdKVYa">
					<div class="sc-1t1fxnz-3 dMLoMw">
						<h3>나의 쇼핑 활동</h3>
						<a href="" class="sc-14dbciz-0 bblXMI">개인 정보 변경</a>
						<a href="myOrderList.do" class="sc-14dbciz-0 bblXMI">주문 목록 조회</a>
						<a href="paySelect.do" class="sc-14dbciz-0 bblXMI">장바구니 관리</a>
						<a href="javascript:handleAddressManage()" id="addressManage" class="sc-14dbciz-0 bblXMI">배송지 관리</a>
					</div>
					<div class="sc-1t1fxnz-3 dMLoMw">
						<h3>개발예정</h3>
						<a href="" class="sc-14dbciz-0 bblXMI">커뮤니티</a>
					</div>
				</nav>
			</div>
			<section class="mypage-cont" style="min-height: 40vh;">
				<input type="hidden" id="defaultImage" name="defaultImage">
				<h1 class="hidden">회원 정보 변경</h1>
				<!-- 기본 회원정보 -->
				<section class="n-section-block">
					<header class="n-section-title first info_views-area">
						<h1 class="tit">
							본인인증
						</h1>
					</header>
					<table class="n-table table-row my-info-modify">
						<colgroup>
							<col style="width: 15%">
							<col style="width: *">
							<col style="width: 30%">
						</colgroup>
						<tbody>
						<form action="mypageMemberUpdate.do" method="POST">
								<tr>
									<td>비밀번호 확인</td>
									<td>
										<div class="form-floating">
											<input type="password" class="form-control" name="memberPW" placeholder="Password" style="display: inline-block; width: 100%;" required>
										</div>
									</td>
									
									<td><button type="submit" class="btn btn-outline-primary" value="회원정보변경 입장" style="margin-left: 10%; border: 1px solid gray; background-color: gray; color: white;">확인</button></td>
								</tr>
						</form>
						</tbody>
					</table>
				</section>
			</section>
		</main>
	</div>
<jsp:include page="util/footer.jsp"></jsp:include>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="js/mypageAddress.js"></script>
</html>