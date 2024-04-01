<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>결제 전 정보확인</title>
	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css"/>
	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="resources/css/slick.css"/>
	<link type="text/css" rel="stylesheet" href="resources/css/slick-theme.css"/>
	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="resources/css/nouislider.min.css"/>
	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="resources/css/font-awesome.min.css">
	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="resources/css/style.css"/>
	<link type="text/css" rel="stylesheet" href="resources/css/payInfo.css"/>
	<!-- HEADER, NAVIGATION -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/navigation.jsp"></jsp:include>
	<!-- /HEADER, NAVIGATION -->
	<!-- BREADCRUMB -->
	<div id="breadcrumb" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<h3 class="breadcrumb-header">결제</h3>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /BREADCRUMB -->

	<!-- SECTION -->
	<div class="section" >
		<!-- container -->
		<div class="container" >
			<!-- row -->
			<div class="row">
				<!-- Payment Form-->
				
				<div class="col-md-9" style="margin-left: 10%;">
					<%-- Member Address Info --%>
					<div class="memberInfo">
						<h2>배송 정보</h2><br>
						<table style="width: 100%; border: 1px solid #000;">
							<tbody>
								<tr>
									<th style="text-align: left; background-color: white; color:black; border: 1px solid gray; border-right: hidden; width: 15%;">배송지</th>
									<td style="background-color: white; border: 1px solid gray; text-align: left;">${shippingAddress.addressName}</td>
								</tr>
								<tr>
									<th style="text-align: left; background-color: white; color:black; border: 1px solid gray; border-right: hidden; ">이름 / 연락처</th>
									<td style="background-color: white; border: 1px solid gray; text-align: left;"> ${memberDTO.memberName} | ${memberDTO.memberPhoneNumber} </td>
								</tr>
								<tr>
									<th style="text-align: left; background-color: white; color:black; border: 1px solid gray; border-right: hidden;">주소</th>
									<td style="background-color: white; border: 1px solid gray; text-align: left;">(${shippingAddress.addressZipCode}) ${shippingAddress.addressStreet} ${shippingAddress.addressDetail} </td>
								</tr>
							</tbody>
						</table>
					</div>
					<%-- /Member Address Info --%>
					
					<!-- Billing Details -->
					<br><br>
					<h2>상품 정보</h2><br>
					<div class="billing-details">
					<table style="width: 100%; text-align: center;">
						<thead>
							<tr>
								<th style="text-align: center;">상품</th>
								<th style="text-align: center;">상품이름</th>
								<th style="text-align: center;">구매개수</th>
								<th style="text-align: center;">가격</th>
								<th style="text-align: center;">쿠폰</th>
							</tr>
						</thead>
						<c:set var="productDatasSize" value="${fn:length(productDatas)}" />
						<c:if test="${productDatasSize >= 1}">
							<c:forEach var="productData" items="${productDatas}">
								<tbody>
									<tr>
										<td><img src="${productData.productImg}" alt="img" style="width: 200px; height: 200px;"></td>
										<td>${productData.getProductName()}</td>
										<td>${productData.cartProductCnt}개</td>
										<td>${productData.productPrice}원</td>
										<td>
											<select>
												<c:forEach items="${couponDatas}" var="coupon">
													<option value="${coupon.couponStatusID}">${coupon.couponName}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</tbody>
							</c:forEach>
						</c:if>
						<c:if test="${productDatasSize < 1}">
							<tbody>
								<tr>
									<td><img src="${param.productImg}" alt="img" style="width: 200px; height: 200px;"></td>
									<td>${param.productName}</td>
									<td>${param.cartProductCnt}개</td>
									<td>${param.productPrice*param.cartProductCnt}원</td>
									<td>
										<select>
											<c:forEach items="${couponDatas}" var="coupon">
												<option value="${coupon.couponStatusID}">${coupon.couponName}</option>
											</c:forEach>
										</select>
										</td>
								</tr>
							</tbody>
						</c:if>
						
						
					</table>
					</div>
					<!-- /Billing Details -->
					<div class="order-details">
						<div class="section-title text-center">
							<h3 class="title">Your Order</h3>
						</div>
						<form action="/getKakaoPayPage" method="get">
						<div class="order-summary">
							<div class="order-col">
								<div><strong>PRODUCT</strong></div>
								<div><strong>TOTAL</strong></div>
							</div>
							<div class="order-products">
								<c:if test="${productDatasSize >= 1}">
									<input style="display:none;" type="number" name="payCk" value="${param.payCk}">
									<c:forEach var="productData" items="${productDatas}">
										<div class="order-col">
											<div>${productData.productName}</div>
											<div style="text-align: right;">${productData.productPrice*productData.cartProductCnt}원</div>
											<input style="display:none;" type="number" name="productID" value="${productData.productID}">
											<input style="display:none;" type="number" name="cartProductCnt" value="${productData.cartProductCnt}">
										</div>
									</c:forEach>
									
								</c:if>
								
								<c:if test="${productDatasSize < 1}">
									<div class="order-col">
										<div>${param.productName}</div>
										<div style="text-align: right;">${param.productPrice*param.cartProductCnt}원</div>
										<input type="hidden" name="productID" value="${param.productID}">
										<input type="hidden" name="productName" value="${param.productName}">
										<input type="hidden" name="cartProductCnt" value="${param.cartProductCnt}">
										<input type="hidden" name="productPrice" value="${param.productPrice}">
										<input style="display:none;" type="number" name="payCk" value="${param.payCk}">
									</div>
								</c:if>
								
							</div>
							<div class="order-col">
								<div>배송비</div>
								<div><strong>FREE</strong></div>
							</div>
							<div class="order-col">
								<div><strong>TOTAL</strong></div>
								<c:if test="${productDatasSize >= 1}">
									<c:set var="total" value="0"></c:set>
									<c:forEach var="payInfoData" items="${productDatas}">
										<c:set var="total" value="${total + payInfoData.productPrice*payInfoData.cartProductCnt}"></c:set>
									</c:forEach>
								</c:if>
								<c:if test="${productDatasSize < 1}">
									<c:set var="nowTotal" value="${param.productPrice*param.cartProductCnt}"></c:set>
								</c:if>
								<c:if test="${productDatasSize >= 1}">
									<div style="text-align: right;"><strong class="order-total" >${total}원</strong></div>
									<input type="hidden" name="totalPrice" value="${total}">
								</c:if>
								<c:if test="${productDatasSize < 1}">
									<div style="text-align: right;"><strong class="order-total" >${nowTotal}원</strong></div>
									<input type="hidden" name="totalPrice" value="${nowTotal}">
								</c:if>
							</div>
						</div>
						<div class="payment-method" style="align-items: center; display: flex;">
							<div style="display: inline-block; "><strong>결제수단</strong></div>
							<select class="input-select" id="pgSelect" name="paymentProvider" style="display: inline-block; position: absolute; right: 4%; ">
								<option value="kakaopay">카카오페이</option>
								<option value="tosspay">토스페이</option>
								<option value="html5_inicis">이니시스</option>
							</select>
						</div>
						<button type="submit" class="primary-btn order-submit" style="width: 50%; margin-left: 25%">결제하기</button>
						</form>
					</div>
				</div>
			</div>
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
