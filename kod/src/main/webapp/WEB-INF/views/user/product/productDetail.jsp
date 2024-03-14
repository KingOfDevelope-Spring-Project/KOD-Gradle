<%@page import="model.dto.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.WishListDTO"%>
<%@page import="model.dto.ProductDTO"%>
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>Electro - HTML Ecommerce Template</title>

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


	<script src="https://code.jquery.com/jquery-3.7.1.js"
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
		crossorigin="anonymous">
</script>

<!-- <script>
$(document).ready(function(){
	  $(".add-to-wishlist2").on("click", function(e){
	    e.preventDefault(); // 기본 클릭 이벤트를 중단하여 링크가 이동하는 것을 방지

	    console.log("위시리스트 버튼 클릭됨");
	    
	    var productID = $(this).find(".productID").text();
	    var heartIcon = $(this).find("#heartIcon");
	    console.log("productID", productID);
	    
	    $.ajax({
	      type: "POST",
	      url: "isWishedServlet",
	      data: {"productID": productID},
	      success: function(data){
	        console.log(data);
	        heartIcon.toggleClass('fa-heart-o fa-heart');
	        
	        var updatedWishListCnt = parseInt(data);
	        $(".wishListCnt").text(updatedWishListCnt);
	        console.log("updatedWishListCnt >> " + updatedWishListCnt);
	        
		    $.ajax({
			      type: "POST",
			      url: "wishTotalCntServlet",
			      data: {"productID": productID},
			      success: function(data){
			        console.log(data);

			        var updatedwishTotalCnt = parseInt(data);
			        $(".wishTotalCnt").text(updatedwishTotalCnt); // 상품의 찜 합계수량
			        console.log("updatedwishTotalCnt >> " + updatedwishTotalCnt);

			      },
			      error: function(error){
			        console.log("에러: " + error);
			      } 
			    });
	        
	      },
	      error: function(error){
	        console.log("에러: " + error);
	      } 
	    });
	    
	  });
	});

</script>

<script>
$(document).ready(function(){
	  $(".add-to-wishlist").on("click", function(e){
	    e.preventDefault(); // 기본 클릭 이벤트를 중단하여 링크가 이동하는 것을 방지

	    console.log("위시리스트 버튼 클릭됨");
	    
	    var productID = $(this).find(".productID").text();
	    var heartIcon = $(this).find("#heartIcon");
	    console.log("productID", productID);
	    
	    $.ajax({
	      type: "POST",
	      url: "isWishedServlet",
	      data: {"productID": productID},
	      success: function(data){
	        console.log(data);
	        heartIcon.toggleClass('fa-heart-o fa-heart');
	        
	        var updatedWishListCnt = parseInt(data);
	        $(".wishListCnt").text(updatedWishListCnt);
	        console.log("updatedWishListCnt >> " + updatedWishListCnt);
	        
	      },
	      error: function(error){
	        console.log("에러: " + error);
	      } 
	    });
	  });
	});
</script> -->

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


	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- Product main img -->
				<div class="col-md-5 col-md-push-2">
					<div id="product-main-img">
						<div class="product-preview">
							<img src="${productWishDetailData.productImg}" alt="">
						</div>

						<!-- <div class="product-preview">
								<img src="./img/product03.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product06.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product08.png" alt="">
							</div> -->
					</div>
				</div>
				<!-- /Product main img -->

				<!-- Product thumb imgs -->
				<div class="col-md-2  col-md-pull-5">
					<div id="product-imgs">
						<div class="product-preview">
							<img src="${productWishDetailData.productImg}" alt="">
						</div>

						<!-- <div class="product-preview">
								<img src="./img/product03.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product06.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product08.png" alt="">
							</div> -->
					</div>
				</div>
				<!-- /Product thumb imgs -->

				<!-- Product details -->
				<div class="col-md-5">
					<div class="product-details">
						<h2 class="product-name">${productWishDetailData.productName}</h2>
						<c:if test="${not empty reviewAvgScore}"> <!-- 평점이 존재하는 경우에만 아래 정보를 표시함 -->
						    <div class="rating-avg"> <!-- 별점 평균점수 -->
						        <div class="rating-stars" id="averageRatingStars">
						            <!-- 별의 개수를 동적으로 조절할 부분 -->
						        </div>
						    </div>
						    <span>상품만족도 : ${reviewAvgScore}</span><br/>
						    <a class="review-link" href="#">${productReviewDatas.size()}
						        Review(s) | Add your review
						    </a>
						</c:if>
						<div>
							<h3 class="product-price">
								<fmt:setLocale value="ko_KR" />
								<fmt:formatNumber value="${productWishDetailData.productPrice}"
									type="currency" />
							</h3>
							<span class="product-available">In Stock</span>
						</div>
						<p>${productWishDetailData.productInfo}</p>

						<form method="POST" action="payInfo.do" id="form1">
							<div class="add-to-cart">
								<div class="qty-label">
									수량
									<div class="input-number">
										<input type="hidden" name="productImg" value="${productWishDetailData.productImg}">
										<input type="hidden" name="payCk" value="1" />
										<input type="hidden" name="productID" value="${productWishDetailData.productID}" ID="productID" />
										<input type="hidden" name="productName" value="${productWishDetailData.productName}" />
										<input type="hidden" name="productPrice" value="${productWishDetailData.productPrice}" />
										<div>
											<input id="purchaseCnt" name="purchaseCnt" type="number" value="1" min="1" max="10" readonly>
											<span class="qty-up">+</span> <span class="qty-down">-</span>
										</div>
									</div>
								</div>
								<button class="add-to-cart-btn" type="button" onclick="cartInsert()">
									<i class="fa fa-shopping-cart"></i>장바구니 담기
								</button>
								<button class="buy-now add-to-cart-btn" type="submit">
									<i class="fa fa-shopping-cart"></i>구매하기
								</button>
							</div>
						</form>

						<ul class="product-btns">
							<li><a href="#" class="add-to-wishlist2"
								onclick="checkLogin()"> <i
									class="fa ${productWishDetailData.isWished == 1 ? 'fa-heart' : 'fa-heart-o'}"
									id="heartIcon"></i> add to wishList <span class="productID"
									style="display: none;">${productWishDetailData.productID}</span>
							</a></li>
							<c:set var="wishTotalCnt"
								value="${empty wishTotalCnt ? 0 : wishTotalCnt}" />
							<Strong><span class="wishTotalCnt"
								style="padding-left: 10px">${wishTotalCnt}</span></Strong>
						</ul>

						<ul class="product-links">
							<li>Category:</li>
							<li><a href="#">${productWishDetailData.productCategory}</a></li>
						</ul>

						<ul class="product-links">
							<li>Share:</li>
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							<li><a href="#"><i class="fa fa-envelope"></i></a></li>
						</ul>
					</div>
				</div>
				<!-- /Product details -->
				<!--  /open modal -->
				<!--[조형련] 상품추가 안내와 함께 장바구니로 이동할 수 있는 모달창 -->
				<form action="paySelect.do" method="POST" id="form2">
					<div style="margin-bottom: 5px;">
						<div class="modalCart hidden">
							<div class="bg2"></div>
							<div class="modalBox">
								<p>장바구니에 상품이 추가되었습니다</p>
								<div>
									<button type="button" onclick="addToCart()" style="color: #FFF; background-color: #ef233c; , border-radius: 40px; , border: 2px solid;">장바구니로 이동하기</button>
								</div>
							</div>
						</div>
					</div>
				</form>
				<!--[조형련] /모달창 -->
				<!--  /open modal -->
				<script>

function modalCart() { 
    var modal = document.querySelector(".modalCart");
    modal.classList.remove("hidden");
    
    setTimeout(function() {
        var opacity = 1; 
        var timerFadeOut = setTimeout(function changeOpacity() {
            if (opacity <= 0) {
                // 투명도가 0 이하인 경우에는 모달을 숨김(hidden 클래스 추가)과 동시에 투명도 초기화
                modal.classList.add("hidden"); // 모달이 사라지면 hidden 클래스 추가
                modal.style.opacity = 1; // 투명도를 초기화하여 다음에 모달이 나타날 때 사용할 수 있도록 함
            } else {
                // 투명도가 0보다 큰 경우에는 투명도를 감소시킴
                modal.style.opacity = opacity; // 현재 투명도 설정
                opacity -= 0.1; // 0.1씩 감소시켜 부드럽게 페이드아웃
                setTimeout(changeOpacity, 50); // 50ms 후에 다시 호출하여 투명도를 변경함
            }
        }, 50); // 50ms마다 투명도를 변경하는 타이머
    }, 2000); // 2초 후에 실행될 setTimeout 함수
}

function addToCart(){ //안내멘트와 함께 장바구니로 이동하는 함수
	   document.getElementById('form2').submit();
	}
	

</script>




				<!-- Product tab -->
				<div class="col-md-12">
					<div id="product-tab">
						<!-- product tab nav -->
						<ul class="tab-nav" id="pageButtons">
						    <li class="active"><a data-toggle="tab" href="#tab1">Reviews (${productReviewDatas.size()})</a></li>
						</ul>
						<!-- /product tab nav -->

						<!-- product tab content -->
						<div class="tab-content">
							<!-- tab1  -->
							<div id="tab1" class="tab-pane fade in active">
								<div class="row">
									<div class="col-md-12">

										<!-- tab3  -->
										<div id="tab3" class="tab-pane fade in">
											<div class="row">
												<!-- Rating -->
												<div class="col-md-3">
													<div id="rating">
														<div class="rating-avg">
															<span>리뷰평점 : ${reviewAvgScore}</span>
															<div class="rating-stars" id="averageRatingStars">
																<!-- 별의 개수를 동적으로 조절할 부분 -->
															</div>
														</div>

<script>
    // 리뷰 평균 점수
    var avgScore = ${reviewAvgScore};

    // 별의 최대 개수
    var maxStars = 5;

    // 별의 HTML 코드 생성
    var starHtml = '';
    for (var i = 1; i <= maxStars; i++) {
        if (i <= avgScore) {
            starHtml += '<i class="fa fa-star"></i>';
        } else {
            starHtml += '<i class="fa fa-star-o"></i>';
        }
    }

    // 생성된 별의 HTML을 평점을 표시할 div에 삽입
    document.getElementById('averageRatingStars').innerHTML = starHtml;
</script>

<script>
function cartInsert() { // [조형련] 장바구니에 상품 추가하는 비동기 처리
    console.log('비동기진입');
	
    var productID = document.getElementById("productID").value; 
    var purchaseCnt = document.getElementById("purchaseCnt").value;

    $.ajax({
        type: 'POST', //POST방식으로 전달
        url: 'cartInsertActionServlet', // 장바구니 업데이트를 처리할 서블릿 URL
        dataType: 'json', //받아온 데이터의 타입
        data: {    
            productID: productID,
            purchaseCnt: purchaseCnt,
        },
        success: function(response) { //비동기처리를 성공하면 반환값을 받아옴
            console.log('성공화면1');
            if (response == 1) { // 응답으로 1이 들어온다면, 사용자에게 안내메시지를 보여주면서 장바구니로 이동할 수 있는 모달창을 보여줌
                modalCart(); 
            }else{
            	
            }
        },
        error: function(error) { //실패할 경우 
           
        }
    });
}
</script>
														<ul class="rating">
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${fiveScoreRatio}%;"></div>
																</div> <span class="sum">${fiveScoreCount }</span>
															</li>
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star-o"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${fourScoreRatio}%;"></div>
																</div> <span class="sum">${fourScoreCount }</span>
															</li>
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <i class="fa fa-star-o"></i> <i
																		class="fa fa-star-o"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${threeScoreRatio}%;"></div>
																</div> <span class="sum">${threeScoreCount }</span>
															</li>
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star-o"></i> <i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${twoScoreRatio}%;"></div>
																</div> <span class="sum">${twoScoreCount }</span>
															</li>
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i> <i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${oneScoreRatio}%;"></div>
																</div> <span class="sum">${oneScoreCount }</span>
															</li>
														</ul>
													</div>
												</div>
												<!-- /Rating -->

												<!-- Reviews -->
												<div class="col-md-6">
													<div id="reviews">
														<ul class="reviews">
															<c:forEach var="review" items="${currentPageProducts}">
																<li
																	style="display: flex; justify-content: space-between;">
																	<div class="review-heading">
																		<h5 class="name">${review.memberName}</h5>
																		<p class="date">${review.reviewDate}</p>
																		<div class="review-rating"
																			id="ratingContainer_${review.reviewID}">
																			<!-- Default: 5 empty stars -->
																			<i class="fa fa-star-o empty"></i> <i
																				class="fa fa-star-o empty"></i> <i
																				class="fa fa-star-o empty"></i> <i
																				class="fa fa-star-o empty"></i> <i
																				class="fa fa-star-o empty"></i>
																		</div>

																		<script>
											                            // 리뷰 레이팅 값 (1부터 5까지의 정수)
											                            var reviewRating_${review.reviewID} = ${review.reviewScore};
											
											                            // 별 아이콘의 상태를 업데이트하는 함수
											                            function updateRating_${review.reviewID}(rating) {
											                                var ratingContainer = document.getElementById("ratingContainer_${review.reviewID}");
											                                var stars = ratingContainer.children;
											
											                                for (var i = 0; i < stars.length; i++) {
											                                    if (i < rating) {
											                                        stars[i].classList.remove("fa-star-o", "empty");
											                                        stars[i].classList.add("fa-star");
											                                    } else {
											                                        stars[i].classList.remove("fa-star");
											                                        stars[i].classList.add("fa-star-o", "empty");
											                                    }
											                                }
											                            }
											
											                            // 리뷰 레이팅 값으로 별 아이콘 업데이트
											                            updateRating_${review.reviewID}(reviewRating_${review.reviewID});
											                        </script>
																	</div>

																	<div class="review-body" style="width: 80%">
																		<h5>${review.reviewTitle}</h5>
																		<p>${review.reviewContent}</p>
																	</div>

																	<div>
																		<div class="image-container">
																			<c:if test="${not empty review.reviewImg}">
																				<!-- 이미지 경로가 존재하는 경우에만 이미지 표시 -->
																				<img alt="${review.memberName}님의 이미지"
																					src='<c:url value="uploads/${review.reviewImg}" />'
																					style="height: 70px; width: 70px;"
																					class="img-thumbnail" data-toggle="modal"
																					data-target="#myModal"
																					title="${review.memberName}님의 이미지" />
																				<div style="height: 30px"></div>
																			</c:if>
																		</div>
																	</div>
																</li>
															</c:forEach>
														</ul>
													</div>
												</div>
												<!-- /Reviews -->


												<!-- 부트스트랩 CSS 파일 -->

												<!-- jQuery 파일 -->
												<script
													src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>

												<!-- 부트스트랩 JavaScript 파일 -->
												<script
													src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
												<script
													src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


												<!-- Review Form -->
												<%-- <div class="col-md-3">
												    <c:forEach var="review" items="${currentPageProducts}">
												        <div class="image-container">
												            <img alt=""
												                src='<c:url value="uploads/${review.reviewImg}" />'
												                style="height: 70px; width: 70px;"
												                class="img-thumbnail"
												                data-toggle="modal" data-target="#myModal"
												                onerror="this.style.display='none';" /> <!-- 이미지 로딩 실패 시 숨기기 -->
												            
												            <div style="height: 30px"></div>
												        </div>
												    </c:forEach>
												</div> --%>

												<!-- 모달 창 -->
												<div class="modal fade" id="myModal" tabindex="-1"
													role="dialog" aria-labelledby="exampleModalLabel"
													aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleModalLabel">이미지
																	미리보기</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body">
																<img id="previewImage" alt="" style="width: 100%;">
															</div>
														</div>
													</div>
												</div>

												<script>
    $(document).ready(function () {
        // 이미지를 클릭했을 때 모달에 이미지 로드
        $('.img-thumbnail').click(function () {
            var imgSrc = $(this).attr('src');
            $('#previewImage').attr('src', imgSrc);
        });
    });
</script>
												<!-- /Review Form -->

											</div>
										</div>
										<!-- /tab3  -->
										<!-- 정적인 페이징 -->
										<%-- <ul class="reviews-pagination">
											<c:forEach begin="1" end="${totalPages}" varStatus="loop">
												<li
													<c:if test="${loop.index == currentPage}">class="active"</c:if>>
													<a href="productDetail.do?productID=${param.productID}&productCategory=${param.productCategory}&page=${loop.index}">${loop.index}</a>
												</li>
											</c:forEach>
										</ul> --%>


										<ul class="reviews-pagination">
											<!-- 첫 번째 페이지로 이동 -->
											<li
												<c:if test="${startPageOfGroup > 1}">style="color: black;"</c:if>
												<c:if test="${startPageOfGroup == 1}">style="display: none;"</c:if>>
												<a
												href="productDetail.do?productID=${param.productID}&productCategory=${param.productCategory}&page=1"><<</a>
											</li>
											<!-- 이전 페이지 그룹 버튼 -->
											<li
												<c:if test="${startPageOfGroup > 1}">style="color: black;"</c:if>
												<c:if test="${startPageOfGroup == 1}">style="display: none;"</c:if>>
												<a
												href="productDetail.do?productID=${param.productID}&productCategory=${param.productCategory}&page=${startPageOfGroup - 1}">&lt;</a>
											</li>

											<!-- 페이지 그룹 표시 -->
											<c:forEach var="pageNumber" begin="${startPageOfGroup}"
												end="${endPageOfGroup}" varStatus="loop">
												<li
													<c:if test="${loop.index == currentPage}">class="active"</c:if>>
													<a
													href="productDetail.do?productID=${param.productID}&productCategory=${param.productCategory}&page=${loop.index}">${loop.index}</a>
												</li>
											</c:forEach>


											<!-- 다음 페이지 그룹 버튼 -->
											<li
												<c:if test="${endPageOfGroup < totalPages}">style="color: black;"</c:if>
												<c:if test="${endPageOfGroup == totalPages}">style="display: none;"</c:if>>
												<a
												href="productDetail.do?productID=${param.productID}&productCategory=${param.productCategory}&page=${endPageOfGroup + 1}">&gt;</a>
											</li>

											<!-- 마지막 페이지로 이동 -->
											<li
												<c:if test="${endPageOfGroup < totalPages}">style="color: black;"</c:if>
												<c:if test="${endPageOfGroup == totalPages}">style="display: none;"</c:if>>
												<a
												href="productDetail.do?productID=${param.productID}&productCategory=${param.productCategory}&page=${totalPages}">>></a>
											</li>
										</ul>

									</div>
									<!-- /product tab content  -->
								</div>
							</div>
							<!-- /product tab -->

						</div>
						<!-- /row -->
					</div>
					<!-- /container -->
				</div>
				<!-- /SECTION -->
				</div>
				</div>
				</div>
				<!-- Section -->
				<div class="section">
					<!-- container -->
					<div class="container">
						<!-- row -->
						<div class="row">
							<div class="col-md-12">
								<div class="section-title text-center">
									<c:if test="${not empty memberDTO.memberID}">
										<h3 class="title">${memberDTO.memberName}님을 위한 추천상품</h3>
									</c:if>
									<c:if test="${empty memberDTO.memberID}">
										<h3 class="title">회원님을 위한 추천상품</h3>
									</c:if>
								</div>
							</div>
							<c:set var="productWishDatas"
								value="${requestScope.productWishDatas}" />
							<!-- Products tab & slick -->
							<div class="col-md-12">

								<div class="row">
									<div class="products-tabs">
										<!-- tab -->
										<div id="tab1" class="tab-pane active">
											<div class="products-slick" data-nav="#slick-nav-1">
												<c:forEach var="data" items="${productWishDatas}">
													<!-- product -->
													<div class="col-md-4 col-xs-6" style="margin-top: 30px;">
														<div class="product">
															<div class="product-body">
																<div class="product-label"
																	style="display: flex; justify-content: space-between; align-items: center;">
																	<span class="new" style="color: #D10024;"><strong>NEW</strong></span>
																	<div class="product-btns">
																		<button class="add-to-wishlist" onclick="checkLogin()">
																			<div class="productID" hidden>${data.productID}</div>
																			<i
																				class="fa ${data.isWished == 1 ? 'fa-heart' : 'fa-heart-o'}"
																				id="heartIcon"></i> <span class="tooltipp">위시리스트에
																				추가</span>
																		</button>
																	</div>
																</div>
															</div>
															<a href="productDetail.do?productCategory=${data.productCategory}&productID=${data.productID}">
																<div class="product-img">
																	<img src="${data.productImg}" alt="">
																</div>
															</a>
															<div class="product-body">
																<p class="product-category">${data.productCategory}</p>
																<h3 class="product-name" style="height: 31px;">
																	<a href="productDetail.do?productID=${data.productID}">${data.productName}</a>
																</h3>
																<h4 class="product-price">
								                                  <fmt:setLocale value="ko_KR" />
								                                  <fmt:formatNumber value="${data.productPrice}" type="currency" />
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
													<!-- /product -->
												</c:forEach>
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
				<!-- /Section -->

				<jsp:include page="util/footer.jsp"></jsp:include>

				<!-- jQuery Plugins -->
				<script src="js/jquery.min.js"></script>
				<script src="js/bootstrap.min.js"></script>
				<script src="js/slick.min.js"></script>
				<script src="js/nouislider.min.js"></script>
				<script src="js/jquery.zoom.min.js"></script>
				<script src="js/main.js"></script>
				<script src="js/wishList/isWished.js"></script>
				<script src="js/wishList/isWishedVersion2.js"></script>
				<script src="js/wishList/checkLogin.js"></script>
				<script src="js/review/moveToPage.js"></script>
				
</body>
</html>
