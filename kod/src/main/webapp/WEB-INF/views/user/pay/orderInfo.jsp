<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*, java.util.*"%>
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
 		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

 		<!-- Slick -->
 		<link type="text/css" rel="stylesheet" href="css/slick.css"/>
 		<link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

 		<!-- nouislider -->
 		<link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

 		<!-- Font Awesome Icon -->
 		<link rel="stylesheet" href="css/font-awesome.min.css">

 		<!-- Custom stlylesheet -->
 		<link type="text/css" rel="stylesheet" href="css/style.css"/>
 		<link type="text/css" rel="stylesheet" href="css/payInfo.css"/>
 		<link type="text/css" rel="stylesheet" href="css/paySelect.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>
	<%
	
		System.out.println("[orderInfo jsp]");
		//ArrayList<CartDTO> cDatas =(ArrayList<CartDTO>)request.getAttribute("cartDTO");
		
		
		//ArrayList<OrderContentDTO> ocDatas = (ArrayList<OrderContentDTO>)request.getAttribute("oContentDTO");
		//System.out.println("ocDatas : "+ocDatas);
		
		//MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		//System.out.println(memberDTO);
		//AddressDTO addressDTO = (AddressDTO)request.getAttribute("addressDTO");
		//System.out.println("orderInfo 주소지 : "+addressDTO);
		
	%>
		<!-- HEADER, NAVIGATION -->
		<jsp:include page="util/header.jsp"></jsp:include>
        <jsp:include page="util/navigation.jsp"></jsp:include>
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
						<!-- Billing Details -->
						<%-- <div class="billing-details">
							<div class="section-title">
								<h3 class="title">구매자 정보</h3>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="memberName" placeholder="이름" value="${memberDTO.memberName}" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="email" name="memberEmail" placeholder="Email" value="${memberDTO.memberEmail}" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="tel" name="memberPhNum" placeholder="전화번호" value="${memberDTO.memberPhNum}" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="adrsStreet" placeholder="도로명주소" value="${addressDTO.adrsStreet}" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="adrsDetail" placeholder="상세주소" value="${addressDTO.adrsDetail}" disabled>
							</div>
						</div> --%>
						<!-- /Billing Details -->
						 
					 	<div class="memberInfo">
						<h2>구매자 정보</h2><br>
						<table style="width: 100%; border: 1px solid #000;">
				            <tbody>
				            	<tr>
				                	<th style="text-align: left; background-color: white; color:black; border: 1px solid gray; border-right: hidden; width: 15%;">배송지</th>
				                	<td style="background-color: white; border: 1px solid gray; text-align: left;">${addressDTO.adrsName}</td>
				            	</tr>
					            <tr>
					            	<th style="text-align: left; background-color: white; color:black; border: 1px solid gray; border-right: hidden; ">이름 / 연락처</th>
				                	<td style="background-color: white; border: 1px solid gray; text-align: left;">${memberDTO.memberName} | ${memberDTO.memberPhNum}</td>
					            </tr>
					            <tr>
					            	<th style="text-align: left; background-color: white; color:black; border: 1px solid gray; border-right: hidden;">주소</th>
				                	<td style="background-color: white; border: 1px solid gray; text-align: left;">(${addressDTO.adrsZipcode}) ${addressDTO.adrsStreet} ${addressDTO.adrsDetail} </td>
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
					                <th style="text-align: center;">구매개수</th>
					                <th style="text-align: center;">가격</th>
					            </tr>
					        </thead>
					        
					        <c:forEach var="ocdata" items="${oContentDTO}">
					        	<tbody>
					            <tr>
					                <td><img src="${ocdata.productImg}" alt="img" style="width: 200px; height: 200px;"></td>
					                <td>${ocdata.productName}</td>
					                <td>${ocdata.odContentCnt}개</td>
					                <td>${ocdata.productPrice*ocdata.odContentCnt}원</td>
					            </tr>
					       		</tbody>
					        </c:forEach>
					        
					        
					        
					        
					        <%-- <%for(OrderContentDTO ocdata : ocDatas){%>
					        <tbody>
					            <tr>
					                <td><img src="<%=ocdata.getProductImg()%>" alt="img" style="width: 200px; height: 200px;"></td>
					                <td><%=ocdata.getProductName()%></td>
					                <td><%=ocdata.getOdContentCnt()%>개</td>
					                <td><%=ocdata.getProductPrice()*ocdata.getOdContentCnt()%>원</td>
					            </tr>
					        </tbody>
					        <%} %> --%>
					    </table>
					    <div class="cart__mainbtns" style="margin-left: 40%;">
						    <form action="main.do" method="POST">
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
