<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*" import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>주문목록조회</title>

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

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />

<link type="text/css" rel="stylesheet" href="css/paySelect.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<!--[조형련] 액션에서 전달받은 request.setAttribute를 가져옴 -->
	<c:set var="oDatas" value="${requestScope.oDTO}" />
	<c:set var="datasTotal" value="${requestScope.datasTotal}" />
	<!-- SECTION -->
	<jsp:include page="util/header.jsp"></jsp:include>
	<jsp:include page="util/navigation.jsp"></jsp:include>
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-9" style="margin-left: 10%;">
					<!-- Billing Details -->
					<!--[조형련] 주문번호 정보가 존재하지 않는 경우 -->
					<c:if test="${empty oDatas}">
						<div style="height: 400px; padding-top: 20%;">
							<h3 style="text-align: center;">주문목록이 없습니다</h3>
						</div>
					</c:if>
					<!--[조형련] 주문번호 정보가 존재하는 경우 -->
					<c:if test="${not empty oDatas}">
						<table class="cart__list">
							<tbody>
								<c:forEach var="oData" items="${oDatas}">
									<tr class="cart__list__detail">
										<td colspan="4">

											<h5 style="text-align: center">주문 날짜 : ${oData.odListDate}</h5>
										</td>
									</tr>
									<tr>
										<!--[조형련] 각 주문번호에 주문내역이 몇개 담겨있는지 확인하여 줄간격을 조정 -->
										<td rowspan="${oData.cnt + 1}">
											<h5 style="text-align: center">주문번호 : ${oData.odListID}</h5>
										</td>
									<tr>
										<!--[조형련] 주문번호가 같은 상품별로 해당 상품의 정보를 표시 -->
										<c:forEach var="data" items="${datasTotal}">
											<c:if test="${oData.odListID == data.odListID}">
												<td>
													<a href="productDetail.do?productCategory=${data.productCategory}&productID=${data.productID}">
													<img src="${data.productImg}" alt="product">
													</a>
												</td>
												<td>
													<p>${data.productName}${data.productID}번/수량 : ${data.odContentCnt} 개</p>
													<p>${data.productCategory}</p>
												</td>
												<td>
													<!--[조형련] 구매한 상품의 가격과 수량을 곱하여 계산 --> <span class="price">${data.productPrice * data.odContentCnt}원</span><br>
													<form action="reviewWritePage.do" method="POST" id="form1">
														<input type="hidden" name="orderContentID" value="${data.getOdContentID()}">
														<input type="hidden" name="productID" value="${data.productID}">
														<c:choose>
															<c:when test="${data.reviewButtonStatus eq 'enabled'}">
																<%--[조형련] 리뷰 작성이 완료된 경우 --%>
																<button class="cart__list__orderbtn" disabled>리뷰 작성 완료</button>
															</c:when>
															<c:otherwise>
																<%--[조형련] 리뷰 작성이 완료되지 않은 경우 --%>
																<label for="${data.productID}"> <img alt="" src="img/writeReview.png" style="width: 30px; height: 30px;">
																	<button id="${data.productID}" class="cart__list__orderbtn" onclick="openReviewWrite()" style="display: none;">리뷰 작성하기</button>
																</label>
															</c:otherwise>
														</c:choose>
													</form>
												</td>
												<tr>
											</c:if>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<!-- /Billing Details -->
					<!-- Billing Details -->
					<br> <br>
					<!-- /Billing Details -->
				</div>
				<!-- Order Details -->
				<!-- /Order Details -->
			</div>
			<!-- /row -->
		</div>
	</div>
	<!-- /container -->
	<!-- /SECTION -->
	<jsp:include page="util/footer.jsp"></jsp:include>

	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>

</body>
</html>
