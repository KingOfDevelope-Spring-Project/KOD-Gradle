<!-- jsp라이브러리 및 페이지 설정 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/x-icon" href="resources/img/favicon.png">
</head>
<body>
	<!-- HEADER -->
	<header>
		<!-- TOP HEADER -->
		<div id="top-header">
			<div class="container">
				<!-- 김진영 / 가상의 회사 위치를 나타내는 페이지로 이동 요청 -->
				<ul class="header-links pull-left">
					<li><a href="/mapPage"><i class="fa fa-map-marker"></i> 찾아오시는 길</a></li>
				</ul>
				<!-- 로그인 여부에 따라 보여지는 메뉴 -->
				<!-- 비 로그인 시 로그인 페이지 및 회원가입 페이지로 이동 -->
				<c:if test="${memberID == null}">
					<ul class="header-links pull-right">
						<li><a href="/loginPage"><i class="fa fa-user-o"></i>로그인</a></li>
						<li><a href="/checkTermsAgreementPage"><i class="fa fa-user-o"></i>회원가입</a></li>
					</ul>
				</c:if>
				<!-- 로그인 시 로그아웃 요청 및 마이페이지로 이동 -->
				<c:if test="${memberID != null}">
					<ul class="header-links pull-right">
						<li><a href="/logout"><i class="fa fa-user-o"></i> 로그아웃</a></li>
						<li><a href="/getMypage"><i class="fa fa-user-o"></i> 마이페이지</a></li>
					</ul>
				</c:if>
			</div>
		</div>
		<!-- /TOP HEADER -->

		<!-- MAIN HEADER -->
		<div id="header">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- LOGO -->
					<div class="col-md-3">
						<div class="header-logo">
							<a href="/" class="logo"> <img src="resources/img/logo.gif" style="width: 250px;" alt="logo image">
							</a>
						</div>
					</div>
					<!-- /LOGO -->

					<!-- SEARCH BAR -->
					<div class="col-md-6" style="padding-top: 1%">
						<div class="header-search">
							<!-- 검색기능 사용 시 페이지의 정보와 검색조건을 GET방식으로 전달 / 주소에 변수명과 값 노출 -->
							<form method="GET" action="/getStorePage" style="padding-left: 10%;">
								<input name="page" type="hidden" value="1">
								<input name="searchKeyword" id="searchKeyword" class="input" placeholder="Search here" style="border-bottom-left-radius: 40px; border-top-left-radius: 40px; padding-left: 4%; width: 75%;">
								<button class="search-btn">Search</button>
							</form>
						</div>
					</div>
					<!-- /SEARCH BAR -->

					<!-- ACCOUNT -->
					<div class="col-md-3 clearfix" style="padding-top: 2%">
						<div class="header-ctn" style="display: flex">
							<!-- WISHLIST -->
							<div>
								<!-- 위시리스트 페이지로 요청 -->
								<c:if test="${memberID != null}">
									<a href="/getWishListPage?page=1">
										<i class="fa fa-heart-o"></i>
										<span>My Wishlist</span>
										<!-- 위시리스트에 담긴 항목이 없다면 수량이 0으로 설정 --> 
										<c:set var="wishListCnt" value="${empty wishListCnt ? 0 : wishListCnt}" /> 
										<c:set var="updatedWishListCnt" value="${updatedWishListCnt}" /> 
										<c:if test="${empty updatedWishListCnt}">
											<!-- 값이 비어 있다면 0으로 설정 -->
											<c:set var="updatedWishListCnt" value="${wishListCnt}" />
										</c:if>
										<!-- 위시리스트 개수를 출력하는 부분 -->
										<div class="qty wishListCnt">${updatedWishListCnt}</div>
									</a>
								</c:if>
								<c:if test="${memberID == null}">
									<a href="/loginPage">
										<i class="fa fa-heart-o"></i>
										<span>My Wishlist</span>
										<div class="qty wishListCnt">0</div>
									</a>
								</c:if>
							</div>
							<!-- /WISHLIST -->

							<!-- Cart -->
							<div class="dropdown">
								<c:if test="${not empty memberID}">
									<a href="/getCartPage" class="dropdown-toggle" aria-expanded="false"> <i class="fa fa-shopping-cart"></i> <span>Your Cart</span>
									<!-- 로그인 및 장바구니 유무에 따른 장바구니 개수 -->
										<c:if test="${not empty updateCartCnt}">
											<!-- 로그인 상태이며, 장바구니에 상품이 1개 이상 존재하는 경우 -->
											<div class="qty">${updateCartCnt}</div>
											<!-- 그 외의 모든 경우 -->
										</c:if>
										<c:if test="${empty updateCartCnt}">
											<div class="qty">0</div>
										</c:if>
									</a>
								</c:if>
								<c:if test="${empty memberID}">
									<a href="/loginPage" class="dropdown-toggle" aria-expanded="false"> <i class="fa fa-shopping-cart"></i> <span>Your Cart</span>
									<!-- 로그인 및 장바구니 유무에 따른 장바구니 개수 -->
										<div class="qty">0</div>
									</a>
								</c:if>
								<div class="cart-dropdown">
									<div class="cart-list">
										<div class="product-widget">
											<div class="product-img">
												<img src="resources/img/product01.png" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-name">
													<a href="#">product name goes here</a>
												</h3>
												<h4 class="product-price">
													<span class="qty">1x</span>$980.00
												</h4>
											</div>
											<button class="delete">
												<i class="fa fa-close"></i>
											</button>
										</div>
									</div>
									<div class="cart-summary">
										<small>3 Item(s) selected</small>
										<h5>SUBTOTAL: $2940.00</h5>
									</div>
									<div class="cart-btns">
										<a href="#">View Cart</a> <a href="#">Checkout <i class="fa fa-arrow-circle-right"></i></a>
									</div>
								</div>
							</div>
							<!-- /Cart -->
							<!-- /Cart -->

							<!-- Menu Toogle -->
							<div class="menu-toggle">
								<a href="#"> <i class="fa fa-bars"></i> <span>Menu</span>
								</a>
							</div>
							<!-- /Menu Toogle -->
						</div>
					</div>
					<!-- /ACCOUNT -->
				</div>
				<!-- row -->
			</div>
			<!-- container -->
		</div>
		<!-- /getMainPage HEADER -->
	</header>
	<!-- /HEADER -->
</body>
<script src="resources/js/login.js"></script>
</html>
