<%@page import="model.dto.WishListDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 원화표시 functions함수집합 가져오기 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 원화표시 포맷 -->

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>쇼핑몰 메인</title>

<!-- Google font -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<!-- Slick -->
<link type="text/css" rel="stylesheet" href="css/slick.css" />
<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

<!-- nouislider -->
<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- 로그인 후 이용해주세요. -->
<link rel="stylesheet" href="css/checkLogin.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />





<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	<jsp:include page="util/header.jsp"></jsp:include>
</head>
<body>
	
	<jsp:include page="util/navigation.jsp"></jsp:include>

<!-- SECTION -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- shop 1 -->
            <div class="col-md-4 col-xs-6">
                <a href="store.do" class="cta-btn">
                    <div class="shop">
                        <div class="shop-img">
                            <img src="//images.ctfassets.net/8cd2csgvqd3m/5ffQPWX2hMWg1Lcvv4Ndmh/715d29139574b6992a7db3b00ff04053/A9_Gold_1_Resized.png?q=85&fm=png&w=375&h=375&fit=fill" alt="스피커사진">
                        </div>
                        <div class="shop-body">
                            <h3>스피커<br>Collection</h3>
                            <a href="store.do" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </a>
            </div>
            <!-- /shop 1 -->

            <!-- shop 2 -->
            <div class="col-md-4 col-xs-6">
                <a href="store.do" class="cta-btn">
                    <div class="shop">
                        <div class="shop-img">
                            <img src="//images.ctfassets.net/8cd2csgvqd3m/26XtRwxmomi69pWKqcuyS8/3a5285a2e0b4a5cb29484bcbe0763258/Packshot-Beoplay-H95-Gold-Tone-0006-Perspective-1200x1200px.png?q=85&fm=png&w=375&h=375&fit=fill" alt="헤드폰사진" />
                        </div>
                        <div class="shop-body">
                            <h3>헤드폰 및 이어폰<br>Collection</h3>
                            <a href="store.do" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </a>
            </div>
            <!-- /shop 2 -->

            <!-- shop 3 -->
            <div class="col-md-4 col-xs-6">
                <a href="store.do" class="cta-btn">
                    <div class="shop">
                        <div class="shop-img">
                            <img src="//images.ctfassets.net/8cd2csgvqd3m/2sbVDgqKMaiDxmrYMHzA71/95fd4c1e43850920a7fbe23bf55560de/Packshot-Beosound-A1-2nd-Gen-Black-Anthracite-0027-Perspective-1200x1200px.png?q=85&fm=png&w=375&h=375&fit=fill" alt="악세사리사진" />
                        </div>
                        <div class="shop-body">
                            <h3>Accessories<br>Collection</h3>
                            <a href="store.do" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </a>
            </div>
            <!-- /shop 3 -->

        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">

				<!-- section title -->
				<div class="col-md-12">
					<div class="section-title">
						<h3 class="title">인기 상품</h3>
						<div class="section-nav">
							<ul class="section-tab-nav tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">실시간
										랭킹</a></li>
								<li><a data-toggle="tab" href="#tab2">스피커</a></li>
								<li><a data-toggle="tab" href="#tab3">헤드폰</a></li>
								<li><a data-toggle="tab" href="#tab4">이어폰</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /section title -->


				<!-- 모달창을 추가합니다. -->
				<div id="memberID" memberID="${memberDTO.memberID}"></div>
				<div id="checkLoginModal" class="modal checkLoginModal">
					<div class="modal-content checkLoginModal">
						<span class="close checkLoginModal" onclick="closeModal()">&times;</span>
						<p>로그인 후 이용가능합니다.</p>
						<p>로그인 화면으로 이동하시겠습니까?</p>
						<button id="cancelButton">취소</button>
						<button id="confirmButton">확인</button>
					</div>
				</div>


				<!-- Products tab & slick -->
				<div class="col-md-12">
					<div class="row">
						<div class="products-tabs">
							<!-- tab -->
							<div id="tab1" class="tab-pane active">
								<div class="products-slick" data-nav="#slick-nav-1">

									<!-- product -->
									<c:forEach var="item" items="${popularAllItems}">
										<div class="col-md-4 col-xs-6" style="margin-top: 30px;">
											<div class="product">
												<div class="product-body">
													<div class="product-label"
														style="display: flex; justify-content: space-between; align-items: center;">
														<span class="new" style="color: #D10024;"><strong>NEW</strong></span>
														<div class="product-btns">
															<button class="add-to-wishlist" onclick="checkLogin()"> <!-- 찜 여부 -->
																<div class="productID" hidden>${item.productID}</div>
																<i class="fa ${item.isWished == 1 ? 'fa-heart' : 'fa-heart-o'}" id="heartIcon"></i>
																<span class="tooltipp">위시리스트에 추가</span>
															</button>
														</div>
													</div>
												</div>
												<a
													href="productDetail.do?productCategory=${item.productCategory}&productID=${item.productID}">
													<div class="product-img">
														<img src="${item.productImg}" alt="">
													</div>
												</a>
												<div class="product-body">
													<p class="product-category">${item.productCategory}</p>
													<h3 class="product-name" style="height: 31px;">
														<a href="productDetail.do?productID=${item.productID}">${item.productName}</a>
													</h3>
													<h4 class="product-price">
														<fmt:setLocale value="ko_KR" />
														<fmt:formatNumber value="${item.productPrice}"
															type="currency" />
														<del class="product-old-price"></del>
													</h4>
													<div class="product-rating">
														<%--평점 들어가는 라인 --%>
													</div>
												</div>
												<!-- <div class="add-to-cart">
													<button class="add-to-cart-btn">
														<i class="fa fa-shopping-cart"></i> add to cart
													</button>
												</div> -->
											</div>
										</div>
									</c:forEach>
									<!-- /product -->


								</div>
								<div id="slick-nav-1" class="products-slick-nav"></div>
							</div>
							<!-- /tab -->
						</div>
					</div>
				</div>
				<!-- Products tab & slick -->

			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->

	<!-- HOT DEAL SECTION 
	<div id="hot-deal" class="section">
		container
		<div class="container">
			row
			<div class="row">
				<div class="col-md-12">
					<div class="hot-deal">
						<ul class="hot-deal-countdown">
							<li>
								<div>
									<h3>02</h3>
									<span>Days</span>
								</div>
							</li>
							<li>
								<div>
									<h3>10</h3>
									<span>Hours</span>
								</div>
							</li>
							<li>
								<div>
									<h3>34</h3>
									<span>Mins</span>
								</div>
							</li>
							<li>
								<div>
									<h3>60</h3>
									<span>Secs</span>
								</div>
							</li>
						</ul>
						<h2 class="text-uppercase">hot deal this week</h2>
						<p>New Collection Up to 50% OFF</p>
						<a class="primary-btn cta-btn" href="#">Shop now</a>
					</div>
				</div>
			</div>
			/row
		</div>
		/container
	</div>
	/HOT DEAL SECTION

	SECTION
	<div class="section">
		container
		<div class="container">
			row
			<div class="row">

				section title
				<div class="col-md-12">
					<div class="section-title">
						<h3 class="title">Top selling</h3>
						<div class="section-nav">
							<ul class="section-tab-nav tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab2">Laptops</a></li>
								<li><a data-toggle="tab" href="#tab2">Smartphones</a></li>
								<li><a data-toggle="tab" href="#tab2">Cameras</a></li>
								<li><a data-toggle="tab" href="#tab2">Accessories</a></li>
							</ul>
						</div>
					</div>
				</div>
				/section title

				Products tab & slick
				<div class="col-md-12">
					<div class="row">
						<div class="products-tabs">
							tab
							<div id="tab2" class="tab-pane fade in active">
								<div class="products-slick" data-nav="#slick-nav-2">
									product
									<div class="product">
										<div class="product-img">
											<img src="./img/product06.png" alt="">
											<div class="product-label">
												<span class="sale">-30%</span> <span class="new">NEW</span>
											</div>
										</div>
										<div class="product-body">
											<p class="product-category">Category</p>
											<h3 class="product-name">
												<a href="#">product name goes here</a>
											</h3>
											<h4 class="product-price">
												$980.00
												<del class="product-old-price">$990.00</del>
											</h4>
											<div class="product-rating">
												<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i>
											</div>
											<div class="product-btns">
												<button class="add-to-wishlist">
													<i class="fa fa-heart-o"></i><span class="tooltipp">add
														to wishlist</span>
												</button>
												<button class="add-to-compare">
													<i class="fa fa-exchange"></i><span class="tooltipp">add
														to compare</span>
												</button>
												<button class="quick-view">
													<i class="fa fa-eye"></i><span class="tooltipp">quick
														view</span>
												</button>
											</div>
										</div>
										<div class="add-to-cart">
											<button class="add-to-cart-btn">
												<i class="fa fa-shopping-cart"></i> add to cart
											</button>
										</div>
									</div>
									/product

									product
									<div class="product">
										<div class="product-img">
											<img src="./img/product07.png" alt="">
											<div class="product-label">
												<span class="new">NEW</span>
											</div>
										</div>
										<div class="product-body">
											<p class="product-category">Category</p>
											<h3 class="product-name">
												<a href="#">product name goes here</a>
											</h3>
											<h4 class="product-price">
												$980.00
												<del class="product-old-price">$990.00</del>
											</h4>
											<div class="product-rating">
												<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star-o"></i>
											</div>
											<div class="product-btns">
												<button class="add-to-wishlist">
													<i class="fa fa-heart-o"></i><span class="tooltipp">add
														to wishlist</span>
												</button>
												<button class="add-to-compare">
													<i class="fa fa-exchange"></i><span class="tooltipp">add
														to compare</span>
												</button>
												<button class="quick-view">
													<i class="fa fa-eye"></i><span class="tooltipp">quick
														view</span>
												</button>
											</div>
										</div>
										<div class="add-to-cart">
											<button class="add-to-cart-btn">
												<i class="fa fa-shopping-cart"></i> add to cart
											</button>
										</div>
									</div>
									/product

									product
									<div class="product">
										<div class="product-img">
											<img src="./img/product08.png" alt="">
											<div class="product-label">
												<span class="sale">-30%</span>
											</div>
										</div>
										<div class="product-body">
											<p class="product-category">Category</p>
											<h3 class="product-name">
												<a href="#">product name goes here</a>
											</h3>
											<h4 class="product-price">
												$980.00
												<del class="product-old-price">$990.00</del>
											</h4>
											<div class="product-rating"></div>
											<div class="product-btns">
												<button class="add-to-wishlist">
													<i class="fa fa-heart-o"></i><span class="tooltipp">add
														to wishlist</span>
												</button>
												<button class="add-to-compare">
													<i class="fa fa-exchange"></i><span class="tooltipp">add
														to compare</span>
												</button>
												<button class="quick-view">
													<i class="fa fa-eye"></i><span class="tooltipp">quick
														view</span>
												</button>
											</div>
										</div>
										<div class="add-to-cart">
											<button class="add-to-cart-btn">
												<i class="fa fa-shopping-cart"></i> add to cart
											</button>
										</div>
									</div>
									/product

									product
									<div class="product">
										<div class="product-img">
											<img src="./img/product09.png" alt="">
										</div>
										<div class="product-body">
											<p class="product-category">Category</p>
											<h3 class="product-name">
												<a href="#">product name goes here</a>
											</h3>
											<h4 class="product-price">
												$980.00
												<del class="product-old-price">$990.00</del>
											</h4>
											<div class="product-rating">
												<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i>
											</div>
											<div class="product-btns">
												<button class="add-to-wishlist">
													<i class="fa fa-heart-o"></i><span class="tooltipp">add
														to wishlist</span>
												</button>
												<button class="add-to-compare">
													<i class="fa fa-exchange"></i><span class="tooltipp">add
														to compare</span>
												</button>
												<button class="quick-view">
													<i class="fa fa-eye"></i><span class="tooltipp">quick
														view</span>
												</button>
											</div>
										</div>
										<div class="add-to-cart">
											<button class="add-to-cart-btn">
												<i class="fa fa-shopping-cart"></i> add to cart
											</button>
										</div>
									</div>
									/product

									product
									<div class="product">
										<div class="product-img">
											<img src="./img/product01.png" alt="">
										</div>
										<div class="product-body">
											<p class="product-category">Category</p>
											<h3 class="product-name">
												<a href="#">product name goes here</a>
											</h3>
											<h4 class="product-price">
												$980.00
												<del class="product-old-price">$990.00</del>
											</h4>
											<div class="product-rating">
												<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i> <i class="fa fa-star"></i> <i
													class="fa fa-star"></i>
											</div>
											<div class="product-btns">
												<button class="add-to-wishlist">
													<i class="fa fa-heart-o"></i><span class="tooltipp">add
														to wishlist</span>
												</button>
												<button class="add-to-compare">
													<i class="fa fa-exchange"></i><span class="tooltipp">add
														to compare</span>
												</button>
												<button class="quick-view">
													<i class="fa fa-eye"></i><span class="tooltipp">quick
														view</span>
												</button>
											</div>
										</div>
										<div class="add-to-cart">
											<button class="add-to-cart-btn">
												<i class="fa fa-shopping-cart"></i> add to cart
											</button>
										</div>
									</div>
									/product
								</div>
								<div id="slick-nav-2" class="products-slick-nav"></div>
							</div>
							/tab
						</div>
					</div>
				</div>
				/Products tab & slick
			</div>
			/row
		</div>
		/container
	</div>
	/SECTION -->


	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<c:if
					test="${not empty teenagerRanking and teenagerRanking.size() > 5}">
					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">10대 추천 PICK</h4>
							<div class="section-nav">
								<div id="slick-nav-3" class="products-slick-nav"></div>
							</div>
						</div>
						<div class="products-widget-slick" data-nav="#slick-nav-3">
							<div>
								<!-- product widget -->
								<c:forEach var="product" begin="0"
									end="${teenagerRanking.size()-4}" items="${teenagerRanking}"
									varStatus="status">
									<div class="product-widget">
										<a
											href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">
											<div class="product-img">
												<img src="${product.productImg}" alt="">
											</div>
										</a>
										<div class="product-body">
											<p class="product-category">${product.productCategory}</p>
											<h3 class="product-name">
												<a
													href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">${product.productName}</a>
											</h3>
											<h4 class="product-price">
												<fmt:setLocale value="ko_KR" />
												<fmt:formatNumber value="${product.productPrice}"
													type="currency" />
											</h4>
										</div>
									</div>
								</c:forEach>
								<!-- /product widget -->

							</div>

							<div>
								<!-- product widget -->
								<c:forEach var="product" begin="3" items="${teenagerRanking}"
									varStatus="status">
									<div class="product-widget">
										<a
											href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">
											<div class="product-img">
												<img src="${product.productImg}" alt="">
											</div>
										</a>
										<div class="product-body">
											<p class="product-category">${product.productCategory}</p>
											<h3 class="product-name">
												<a
													href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">${product.productName}</a>
											</h3>
											<h4 class="product-price">
												<fmt:setLocale value="ko_KR" />
												<fmt:formatNumber value="${product.productPrice}"
													type="currency" />
												<del class="product-old-price">$990.00</del>
											</h4>
										</div>
									</div>
								</c:forEach>
								<!-- /product widget -->
							</div>
						</div>
					</div>
				</c:if>

				<c:if
					test="${not empty teenagerRanking and teenagerRanking.size() > 5}">
					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">20대 추천 PICK</h4>
							<div class="section-nav">
								<div id="slick-nav-4" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-4">
							<div>
								<!-- product widget -->
								<c:forEach var="product" begin="0"
									end="${twentyRanking.size()-4}" items="${twentyRanking}"
									varStatus="status">
									<div class="product-widget">
										<a
											href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">
											<div class="product-img">
												<img src="${product.productImg}" alt="">
											</div>
										</a>
										<div class="product-body">
											<p class="product-category">${product.productCategory}</p>
											<h3 class="product-name">
												<a
													href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">${product.productName}</a>
											</h3>
											<h4 class="product-price">
												<fmt:setLocale value="ko_KR" />
												<fmt:formatNumber value="${product.productPrice}"
													type="currency" />
												<del class="product-old-price">$990.00</del>
											</h4>
										</div>
									</div>
								</c:forEach>
								<!-- /product widget -->

							</div>

							<div>
								<!-- product widget -->
								<c:forEach var="product" begin="3" items="${twentyRanking}"
									varStatus="status">
									<div class="product-widget">
										<a
											href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">
											<div class="product-img">
												<img src="${product.productImg}" alt="">
											</div>
										</a>
										<div class="product-body">
											<p class="product-category">${product.productCategory}</p>
											<h3 class="product-name">
												<a
													href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">${product.productName}</a>
											</h3>
											<h4 class="product-price">
												<fmt:setLocale value="ko_KR" />
												<fmt:formatNumber value="${product.productPrice}"
													type="currency" />
												<del class="product-old-price">$990.00</del>
											</h4>
										</div>
									</div>
								</c:forEach>
								<!-- /product widget -->
							</div>
						</div>
					</div>
				</c:if>

				<div class="clearfix visible-sm visible-xs"></div>

				<c:if
					test="${not empty teenagerRanking and teenagerRanking.size() > 5}">
					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">30대 추천 PICK</h4>
							<div class="section-nav">
								<div id="slick-nav-5" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-5">
							<div>
								<!-- product widget -->
								<c:forEach var="product" begin="0"
									end="${thirtyRanking.size()-4}" items="${thirtyRanking}"
									varStatus="status">
									<div class="product-widget">
										<div class="productID" hidden>${wishList.productID}</div>
										<a
											href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">
											<div class="product-img">
												<img src="${product.productImg}" alt="">
											</div>
										</a>
										<div class="product-body">
											<p class="product-category">${product.productCategory}</p>
											<h3 class="product-name">
												<a
													href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">${product.productName}</a>
											</h3>
											<h4 class="product-price">
												<fmt:setLocale value="ko_KR" />
												<fmt:formatNumber value="${product.productPrice}"
													type="currency" />
												<del class="product-old-price">$990.00</del>
											</h4>
										</div>
									</div>
								</c:forEach>
								<!-- /product widget -->
							</div>

							<div>
								<!-- product widget -->
								<c:forEach var="product" begin="3" items="${thirtyRanking}"
									varStatus="status">
									<div class="product-widget">
										<a
											href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">
											<div class="product-img">
												<img src="${product.productImg}" alt="">
											</div>
										</a>
										<div class="product-body">
											<p class="product-category">${product.productCategory}</p>
											<h3 class="product-name">
												<a
													href="productDetail.do?productCategory=${product.productCategory}&productID=${product.productID}">${product.productName}</a>
											</h3>
											<h4 class="product-price">
												<fmt:setLocale value="ko_KR" />
												<fmt:formatNumber value="${product.productPrice}"
													type="currency" />
												<del class="product-old-price">$990.00</del>
											</h4>
										</div>
									</div>
								</c:forEach>
								<!-- /product widget -->
							</div>
						</div>
					</div>
				</c:if>

			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->


	<jsp:include page="util/footer.jsp"></jsp:include>

	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>
	<script src="js/wishList/checkLogin.js"></script>
	<script src="js/wishList/isWished.js"></script>

</body>
</html>
