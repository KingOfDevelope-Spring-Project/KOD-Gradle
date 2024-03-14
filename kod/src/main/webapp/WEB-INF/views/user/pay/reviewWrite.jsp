<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 원화표시 functions함수집합 가져오기 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 원화표시 포맷 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 후기 작성</title>
<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />
<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- Custom CSS -->
<link type="text/css" rel="stylesheet" href="css/reviewWrite.css">
</head>

<body>
	<div class="container">
		<h2>상품 후기 작성</h2>
		<form action="reviewWriteAction.do" method="POST"
			enctype="multipart/form-data" onsubmit="return validateForm();">

			<c:set var="productID" value="${empty param.productID ? 0 : param.productID}" />
			<c:set var="orderContentID" value="${empty param.orderContentID ? 0 : param.orderContentID}" />

			<!-- productID hidden field -->
			<input type="hidden" name="productID" value="${productID}" /> 
			<input type="hidden" name="orderContentID" value="${orderContentID}" />

			<div class="FormRow">
				<label for="title">제목 (최대 50자)</label> 
				<input type="text" id="title" name="title" placeholder="제목을 입력하세요" maxlength="50" required /> 
				<span id="titleCounter">0/50</span>
			</div>
			<div class="FormRow">
				<label for="content">내용 (최대 500자)</label>
				<textarea id="content" name="content" placeholder="내용을 입력하세요" style="height: 200px" maxlength="500" required></textarea>
				<span id="contentCounter">0/500</span>
			</div>
			<div class="FormRow">
				<label for="rating">별점</label>
				<div class="review-form">
					<div class="input-rating">
						<div class="stars">
							<input id="star5" name="score" value="5" type="radio">
							<label for="star5"></label> 
							<input id="star4" name="score" value="4" type="radio">
							<label for="star4"></label> 
							<input id="star3" name="score" value="3" type="radio">
							<label for="star3"></label> 
							<input id="star2" name="score" value="2" type="radio">
							<label for="star2"></label> 
							<input id="star1" name="score" value="1" type="radio">
							<label for="star1"></label>
						</div>
					</div>
				</div>
			</div>
			<div class="FormRow">
				<label for="imageUpload" class="imageUploadBtn">이미지 업로드<br />
					<input type="file" id="imageUpload" name="imageUpload" accept="image/*" onchange="previewImage(event)" /> 
					<img id="imagePreview" src="img/imagePreview.png" alt="미리 보기 이미지" style="width: 40px; height: 40px;">
				</label>
				<button type="button" id="cancelImageButton" style="display: none;" onclick="cancelImageUpload()">이미지 취소</button>
			</div>
			<div class="FormRow">
				<button type="submit" class="SubmitBtn">작성하기</button>
			</div>
		</form>
	</div>

	<!-- 리뷰작성js -->
	<script src="js/review/reviewWrite.js"></script>

</body>
</html>