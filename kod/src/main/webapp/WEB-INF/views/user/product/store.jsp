<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*, java.util.*"%>
<%-- [김진영] jstl 및 추가기능 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>너의 목소리가 보여</title>
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
<link type="text/css" rel="stylesheet" href="css/checkLogin.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
/* [김진영] slider css */
#price-slider-container {
	width: 100%;
	margin: 2px;
}

#price-range {
	width: 100%;
}

#price-output {
	font-size: 18px;
	margin-top: 10px;
}
/* [김진영] 검색 버튼(돋보기 label) */
label:hover {
	cursor: pointer;
}
/* [김진영] 이미지 크기가 달라서 정렬이 깨지는 부분 수정을 위한 미디어 쿼리 */
@media only screen and (min-width : 991px) {
	.product .product-img>img{
		max-height: 260px;
	}
}
@media only screen and (min-width: 200px) {
   .product .product-img>img{
    	max-height: 212.5px;
  }
}
</style>
<jsp:include page="util/header.jsp"></jsp:include>
</head>
<!-- jQuery Plugins -->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<%-- [김진영] 미구현 chart 주석처리 --%>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script> -->
<%-- [김진영] 가격필터검색용 silder가 구현되어있는 jQuery-UI --%>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%-- [김진영] 라이브러리의 충돌로 인해 slider가 나오지 않는 현상으로 주석처리 --%>
<!-- <script src="js/jquery.min.js"></script> -->
<script src="js/bootstrap.min.js"></script>
<script src="js/slick.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/jquery.zoom.min.js"></script>
<script src="js/main.js"></script>
<script src="js/filter.js"></script>
<body>
	<%-- 
		[김진영] 헤더와 네비게이션은 활용되는 페이지가 많아서 
		액션태그로 페이지를 분리하여 유지보수 활용성을 향상
	--%>
	
	<jsp:include page="util/navigation.jsp"></jsp:include>
	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- ASIDE -->
				<div id="aside" class="col-md-3">
					<%-- [김진영] 사이드바 전체상품카테고리의 종류 및 카테고리별 상품의 갯수 --%>
					<%-- productActionCategory.java --%>
					<div class="aside">
						<h3 class="aside-title">Categories</h3>
						<div class="checkbox-filter">
							<c:forEach var="productCategoryData" items="${productCategoryDatas}" varStatus="i">
								<div class="input-checkbox">
									<input type="checkbox" id="category-${i.index+1}" name="${productCategoryData.productCategory}">
									<label for="category-${i.index+1}"> 
										<span></span>${productCategoryData.productCategory}
										<small> (<fmt:formatNumber value="${productCategoryData.productCnt}" type="currency" pattern="#,###" />)</small>
									</label>
								</div>
							</c:forEach>
						</div>
					</div>
					<%-- [김진영] 사이드바 slider --%>
					<%-- [김진영] 사이드바를 분리해서 작업하던 이유 --%>
					<%-- 
						 한개의 페이지 내에서 여러사람이 작업하다보니 github등
						 파일관리를 하는 측면에서 충돌이 발생할 우려가 있으므로
						 별도로 파일을 분리하여 작업 후 위치에 넣어주는 방식으로 작업
					--%>
					<div class="aside">
						<jsp:include page="slider.jsp"></jsp:include>
					</div>
					<%-- [김진영] 사이드바 판매량 top3 --%>
					<%-- productRankAction.java --%>
					<div class="aside">
						<h3 class="aside-title">Top selling</h3>
						<c:forEach var="productRankData" items="${orderRankDatas}">
							<div class="product-widget">
								<div class="product-img">
									<img src="${productRankData.productImg}" alt="">
								</div>
								<div class="product-body">
									<p class="product-category">${productRankData.productCategory}</p>
									<h3 class="product-name">
										<a
											href="productDetail.do?productID=${productRankData.productID}">${productRankData.productName}</a>
									</h3>
									<h4 class="product-price">
										<%-- [김진영] 통화표시를 위한 지역선택 --%>
										<fmt:setLocale value="ko_KR" />
										<%-- [김진영] 해당 지역 통화에 맞게 가격 표시 'type' --%>
										<fmt:formatNumber value="${productRankData.productPrice}"
											type="currency" />
									</h4>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- /aside Widget -->
				</div>
				<!-- /ASIDE -->

				<!-- STORE -->
				<div id="store" class="col-md-9">
					<!-- store top filter -->
					<!-- <div class="store-filter clearfix">
						<div class="store-sort">
							<label> Sort By: <select class="input-select">
									<option value="0">Popular</option>
									<option value="1">Position</option>
							</select>
							</label> <label> Show: <select class="input-select">
									<option value="0">20</option>
									<option value="1">50</option>
							</select>
							</label>
						</div>
						<ul class="store-grid">
							<li class="active"><i class="fa fa-th"></i></li>
							<li><a href="#"><i class="fa fa-th-list"></i></a></li>
						</ul>
					</div> -->
					<!-- /store top filter -->

					<!-- store products -->
					<div class="row">

<!-- 	
<script>
$(document).ready(function(){
    $('.add-to-wishlist').on('click', function(){
        console.log('[로그:정현진] 위시리스트 버튼 클릭됨');
        
        var productID = $(this).find('.productID').text();
        var heartIcon = $(this).find('#heartIcon');
        console.log('productID:', productID);
        
        $.ajax({
            type: "POST",
            url: 'isWishedServlet',
            data: { 'productID': productID },
            success: function(data){
                console.log(data);
                // 클릭 시 하트 아이콘 토글
                heartIcon.toggleClass('fa-heart-o fa-heart');
                
                var updatedWishListCnt = parseInt(data); // data가 업데이트된 카운트를 받아와야합니다.
                $('.wishListCnt').text(updatedWishListCnt); // 위시리스트의 개수를 업데이트해줌
                console.log("[로그:정현진] updatedWishListCnt >> "+updatedWishListCnt)
            },
            error: function(error){
                console.log("에러: " + error);
            }
        });
    });
});
</script> 
-->

<!--
V는 C한테
   ♥♡를 구분해야하니까
   1,0 등의 신호를 주세요.
C는 V한테 줘야되니까
   SELECTALL 해올적에
   SELECT ??? FROM
      ???에 1,0 등의 값을
      받아올수있도록 해달라고
      M한테 요청
M은 C한테 1,0 등의 값을 줘야하니까
   SQL문을 수정해야됨
   SELECTALL이 되는상황
-->
  					
						<!-- product -->
					<c:if test="${not empty currentPageProducts}">
						<c:forEach var="isWishedData" items="${currentPageProducts}">
						    <div class="col-md-4 col-xs-6" style="margin-top: 30px;">
						        <div class="product">
						            <div class="product-body">
						                <div class="product-label" style="display: flex; justify-content: space-between; align-items: center;">
						                    <span class="new" style="color: #D10024;"><strong>NEW</strong></span>
						                    <div class="product-btns">
						                        <button class="add-to-wishlist" onclick="checkLogin()"> <!-- 찜 버튼 -->
						                            <div class="productID" hidden>${isWishedData.productID}</div>
						                            <i class="fa ${isWishedData.isWished != 0 ? 'fa-heart' : 'fa-heart-o'}" id="heartIcon"></i>
						                            <span class="tooltipp">위시리스트에 추가</span>
						                        </button>
						                    </div>
						                </div>
						            </div>
						            <a href="productDetail.do?productID=${isWishedData.productID}&productCategory=${isWishedData.productCategory}">
						                <div class="product-img">
						                    <img src="${isWishedData.productImg}" alt="${isWishedData.productName}" />
						                </div>
						            </a>
						            <div class="product-body">
						                <p class="product-category">${isWishedData.productCategory}</p>
						                <h3 class="product-name" style="height: 31px;">
						                    <a href="productDetail.do?productID=${isWishedData.productID}&productCategory=${isWishedData.productCategory}">
						                        ${isWishedData.productName}
						                    </a>
						                </h3>
						                <h4 class="product-price">
     										<fmt:setLocale value="ko_KR" />
											<fmt:formatNumber value="${isWishedData.productPrice}" type="currency" />
						                </h4>
						                <div class="product-rating">
						                    <%-- 평점 들어가는 라인 --%>
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
					</c:if>
					
					<c:if test="${empty currentPageProducts}">
						<h3>검색결과가 없습니다</h3>
					</c:if>
						<!-- /product -->
						<!-- /store products -->

						
					</div>
					<!-- /STORE -->
					<!-- store bottom filter -->
						<div class="store-filter clearfix">
						    <span class="store-qty">Showing ${startIndex + 1} - ${endIndex} products</span>
						    <ul class="store-pagination">
						        <%-- 이전 페이지 링크 --%>
						        <c:if test="${currentPage > 1}">
						            <li><a href="store.do?page=${currentPage - 1}"><i class="fa fa-angle-left"></i></a></li>
						        </c:if>
						
						        <%-- 페이지 번호 출력 --%>
						        <c:forEach var="i" begin="1" end="${totalPages}">
						            <li class="${currentPage == i ? 'active' : ''}">
						                <a href="store.do?page=${i}">${i}</a>
						            </li>
						        </c:forEach>
						
						        <%-- 다음 페이지 링크 --%>
						        <c:if test="${currentPage < totalPages}">
						            <li><a href="store.do?page=${currentPage + 1}"><i class="fa fa-angle-right"></i></a></li>
						        </c:if>
						    </ul>
						</div>
						<!-- /store bottom filter -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
	</div>
	
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

	<jsp:include page="util/footer.jsp"></jsp:include>
	<script src="js/wishList/isWished.js"></script>
	<script src="js/wishList/checkLogin.js"></script>
	


</body>
</html>
