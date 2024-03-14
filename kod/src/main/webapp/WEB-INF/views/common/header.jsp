<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/x-icon" href="img/favicon.png" >
</head>
<body>
	<!-- HEADER -->
	<header>
		<!-- TOP HEADER -->
		<div id="top-header">
			<div class="container">
				<ul class="header-links pull-left">
					<li><a href="mapPage.do"><i class="fa fa-map-marker"></i>
							찾아오시는 길</a></li>
				</ul>
				<c:if test="${sessionScope.memberDTO == null}">
					<ul class="header-links pull-right">
						<li>
							<a href="loginPage.do"><i class="fa fa-user-o"></i>로그인</a>
						</li>
						<li>
							<a href="joinTermsOfUse.do"><i class="fa fa-user-o"></i>회원가입</a>
						</li>
					</ul>
				
				</c:if>
				<c:if test="${sessionScope.memberDTO != null}">
					<ul class="header-links pull-right">
						<li><a href="logout.do"><i class="fa fa-user-o"></i>
								로그아웃</a></li>
						<li><a href="mypageMemberUpdatePWCK.do"><i class="fa fa-user-o"></i>
								마이페이지</a></li>
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
							<a href="main.do" class="logo"> <img src="./img/logo.gif" style="width: 250px; height=65px;" alt="logo image">
							</a>
						</div>
					</div>
					<!-- /LOGO -->

					<!-- SEARCH BAR -->
					<div class="col-md-6" style="padding-top : 1%">
						<div class="header-search" >
							<form method="POST" action="store.do" style="padding-left: 10%;">
								<!-- <select class="input-select" style="">
									<option value="0">All Categories</option>
									<option value="1">Category 01</option>
									<option value="1">Category 02</option>
								</select> -->  
								<input name="searchKeyword" id="searchKeyword" class="input" placeholder="Search here" style="border-bottom-left-radius: 40px; border-top-left-radius: 40px; padding-left: 4%; width: 75%;">
								<button class="search-btn" >Search</button>
							</form>
						</div>
					</div>
					<!-- /SEARCH BAR -->

					<!-- ACCOUNT -->
					<div class="col-md-3 clearfix" style="padding-top : 2%">
						<div class="header-ctn" style="display : flex">
							<!-- Wishlist -->
							<div> <!-- 정현진 -->
								<a href="wishList.do"> <!-- 위시리스트 페이지로 이동하는 링크 -->
								    <i class="fa fa-heart-o"></i> <!-- 하트 아이콘 -->
								    <span>My Wishlist</span> <!-- 위시리스트 링크의 텍스트 -->
								
								    <!-- 페이지로드시 위시리스트 수량 --> <!-- 값이 null이면 0으로 설정 -->
								    <c:set var="wishListCnt" value="${empty wishListCnt ? 0 : wishListCnt}" /> 
								    <!-- 업데이트된 위시리스트 수량 --> <!-- 비동기반응 반응 -->	
								    <c:set var="updatedWishListCnt" value="${updatedWishListCnt}" />
								    <c:if test="${empty updatedWishListCnt}"> <!-- 값이 비어 있다면 0으로 설정 -->  
							        <c:set var="updatedWishListCnt" value="${wishListCnt}" /> 
								    </c:if>
								
								    <div class="qty wishListCnt">${updatedWishListCnt}</div> <!-- 위시리스트 개수를 출력하는 부분 -->
								</a>
							</div> <!-- /정현진  -->
							<!-- /Wishlist -->

							<!-- Cart -->
							<!-- Cart -->
                     <div class="dropdown">
                     	<c:if test="${memberDTO != null}">
	                        <a href="paySelect.do" class="dropdown-toggle" aria-expanded="false">
	                            <i class="fa fa-shopping-cart"></i> <span>Your Cart</span>
	                        </a>
                        </c:if>
                        <c:if test="${memberDTO == null}">
	                        <a href="loginPage.do" class="dropdown-toggle" aria-expanded="false">
	                            <i class="fa fa-shopping-cart"></i> <span>Your Cart</span>
	                        </a>
                        </c:if>
                         <div class="cart-dropdown">
                           <div class="cart-list">
                              <div class="product-widget">
                                 <div class="product-img">
                                    <img src="./img/product01.png" alt="">
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
                              <a href="#">View Cart</a> <a href="#">Checkout <i
                                 class="fa fa-arrow-circle-right"></i></a>
                           </div>
                        </div> -->
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
		<!-- /MAIN HEADER -->
	</header>
	<!-- /HEADER -->
</body>
</html>
