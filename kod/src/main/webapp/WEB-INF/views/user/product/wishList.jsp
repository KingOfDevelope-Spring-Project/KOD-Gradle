<%@page import="model.dto.MemberDTO"%>
<%@page import="model.dto.WishListDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
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
		<link type="text/css" rel="stylesheet" href="css/soldOut.css"/>

<title>위시리스트</title>
<style>
 #soldOutDelBtn {
    display: inline-block;
    padding: 8px 16px; /* 작게 조절 */
    font-size: 14px; /* 작게 조절 */
    font-weight: bold;
    text-align: center;
    text-decoration: none;
    cursor: pointer;
    border: 2px solid #3498db;
    border-radius: 5px;
    color: #3498db;
    background-color: #fff;
    transition: background-color 0.3s, color 0.3s;
}

#soldOutDelBtn:hover {
    background-color: #3498db;
    color: #fff;
}
</style>
<jsp:include page="util/header.jsp"></jsp:include>
</head>
<body>


		
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
    <div class="section-title">
        <h3 class="title">
            <div class="box-page-count" style="display: flex; align-items: center; ">
                <div class="l-cont">
                    <c:set var="memberName" value="${sessionScope.memberDTO.memberName}" />
                    <div>${memberName}님의 위시리스트</div>

                    <c:set var="wishListDatas" value="${requestScope.wishListDatas}" />
                    <c:set var="wishListCntObj" value="${requestScope.wishListCnt}" />
                    <c:set var="wishListCnt" value="${empty wishListCntObj ? 0 : wishListCntObj}" />
                    
                    <c:set var="updatedWishListCntStr" value="${requestScope.updatedWishListCnt}" />
                    <c:set var="updatedWishListCnt" value="${empty updatedWishListCntStr ? wishListCnt : updatedWishListCntStr}" />

                    <strong class="tit">총 <span class="wishListCnt">${updatedWishListCnt}</span>개 상품</strong>
                </div>
            </div>
        </h3>
        <div class="section-nav">
            <ul class="section-tab-nav tab-nav">
                <li class="active">
                    <div class="r-cont">
                        <button class="btn-underline" id="soldOutDelBtn" data-di-id="#soldOutDelBtn">
						    <span>품절상품 삭제</span>
						</button>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
					
					
					
					<!-- Products tab & slick -->
<div class="col-md-12">
    <div class="row">
        <div class="products-tabs">
            <!-- tab -->
            <div id="tab1" class="section">
                <div class="row" data-nav="#slick-nav-1" style="display: flex; flex-wrap: wrap;">
                    <!-- /product -->
                    <c:choose>
                        <c:when test="${empty wishListDatas}">
                            <h4 style="margin: 0 auto;">위시리스트에 추가된 상품이 없습니다.</h4>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="wishList" items="${wishListDatas}">
                                    <div class="product ${wishList.productStock == 0 ? 'sold-out' : ''}" style="flex: 0 0 calc(33.33% - 20px); margin: 10px;">
                                    <div class="product-body">
                                        <div class="product-label" style="display: flex; justify-content: space-between; align-items: center;">
                                            <span class="new" style="color: #D10024;"><strong>NEW</strong></span>
                                            <div class="product-btns">
                                                <button class="add-to-wishlist">
                                                    <div class="productID" hidden>${wishList.productID}</div>
                                                    <i class="fa ${wishList.isWished == 1 ? 'fa-heart-o' : 'fa-heart'}" id="heartIcon"></i>
                                                    <span class="tooltipp">위시리스트에 추가</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="productDetail.do?productCategory=${wishList.productCategory}&productID=${wishList.productID}">
                                    <div class="product-img">
                                        <img src="${wishList.productImg}" alt="Product Image" />
                                    </div>
                                    </a>
                                    <div class="product-body">
                                        <p class="product-category">${wishList.productCategory}</p>
                                        <h3 class="product-name"><a href="#" tabindex="-1">${wishList.productName}</a></h3>
                                        <h4 class="product-price">
                                        <fmt:setLocale value="ko_KR" />
						                <fmt:formatNumber value="${wishList.productPrice}" type="currency" />
                                        </h4>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
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
		
		




<jsp:include page="util/footer.jsp"></jsp:include>

<!-- 찜기능 비동기 js  -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="js/wishList/isWished.js"></script>
<script src="js/wishList/RemoveSoldOutProducts.js"></script>


</body>
</html>
