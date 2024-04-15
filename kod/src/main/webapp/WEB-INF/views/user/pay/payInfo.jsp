<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>결제내역</title>

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
 		<link type="text/css" rel="stylesheet" href="resources/css/paySelect.css"/>

    </head>
	<body>
	<%
		System.out.println("[payInfo.jsp]");
	%>
		<!-- HEADER, NAVIGATION -->
		<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
        <jsp:include page="/WEB-INF/views/common/navigation.jsp"></jsp:include>
		<!-- /HEADER, NAVIGATION -->
		
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<h3 class="breadcrumb-header">결제내역</h3>
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
			<div class="container">
				<!-- row -->
				<div class="row">

					<div class="col-md-9" style="margin-left: 10%;">
					 	<div class="memberInfo">
						<h2>구매자 정보</h2><br>
						<table style="width: 100%; border: 1px solid #000;">
				            <tbody>
				            	<tr>
				                	<th style="text-align: left; background-color: white; color:black; border: 1px solid gray; border-right: hidden; width: 15%;">배송지</th>
				                	<td style="background-color: white; border: 1px solid gray; text-align: left;">${addressDatas.addressName}</td>
				            	</tr>
					            <tr>
					            	<th style="text-align: left; background-color: white; color:black; border: 1px solid gray; border-right: hidden; ">이름 / 연락처</th>
				                	<td style="background-color: white; border: 1px solid gray; text-align: left;">${addressDatas.memberName} | ${addressDatas.memberPhoneNumber}</td>
					            </tr>
					            <tr>
					            	<th style="text-align: left; background-color: white; color:black; border: 1px solid gray; border-right: hidden;">주소</th>
				                	<td style="background-color: white; border: 1px solid gray; text-align: left;">(${addressDatas.addressZipCode}) ${addressDatas.addressStreet} ${addressDatas.addressDetail} </td>
					            </tr>
				        	</tbody>
					    </table>
					    </div>
						<br>
						<!-- Billing Details -->
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">주문 내역</h3>
							</div>
							
							<table style="width: 100%; text-align: center;">
					        <thead>
					            <tr>
					                <th style="text-align: center;">상품</th>
					                <th style="text-align: center;">상품이름</th>
					                <th style="text-align: center;">가격</th>
					                <th style="text-align: center;">수량</th>
					                <th style="text-align: center;">쿠폰</th>
					                <th style="text-align: center;">결제금액</th>
					            </tr>
					        </thead>
					        
					        <c:forEach var="payInfoData" items="${orderDatas}">
					        	<tbody>
					            <tr>
					                <td><img src="${payInfoData.productImg}" alt="img" style="width: 200px; height: 200px;"></td>
					                <td>${payInfoData.productName}</td>
					                <td>${payInfoData.productPrice}원</td>
					                <td>${payInfoData.orderContentCnt}개</td>
					                <td>${payInfoData.couponName} : ${payInfoData.discountPrice}원 할인</td>
					                <td>${payInfoData.paymentPrice}원</td>
												</tr>
											</tbody>
										</c:forEach>
										<h3 class="title">결제금액 합계 : ${totalPaymentPrice}원</h3>
					    </table>
					    <div class="cart__mainbtns" style="margin-left: 40%;">
						    <form action="/" method="GET">
						    	<button class="cart__bigorderbtn left" type="submit">메인으로</button>
							</form>
						</div>
						</div>
						<!-- /Billing Details -->
							
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
