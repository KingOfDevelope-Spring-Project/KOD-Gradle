<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Price Range Slider</title>
<style>
#price-slider-container {
	width: 98%;
	margin: 2px;
}

#price-range {
	width: 100%;
}

#price-output {
	font-size: 18px;
	margin-top: 10px;
}
label:hover{
	cursor: pointer;
}
</style>
</head>
<body>

	<div id="price-slider-container">
		<div class="aside">
			<h3 class="aside-title">
				PRICE: <span id="price-output"></span>
			</h3>
			<div id="price-range"></div>
			<form method="POST" action="" style="position: absolute; top: 0.5em; right: 1em;">
				<input type="hidden" id="maxPrice" name="maxPrice" value="" /> 
				<input type="hidden" id="minPrice" name="minPrice" value="" /> 
				<label for="searchBtn" ><img alt="search" src="img/search.png" style="position: absolute; top: 0.5em; right: 1em;" width="30px" height="30px" /></label>
				<input type="button" name="searchBtn" id="searchBtn" value="검색" style="display: none;" onclick="selectcheckbox()">
			</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			// jQuery UI의 slider를 초기화
			$("#price-range").slider({
				range : true, // 두 개의 핸들을 허용
// 개선사항으로 max, min의 값을 조회해서 기준값으로 설정하는 방법 고민
				min : 0,
				max : 1000,
				values : [ 0, 1000 ], // 초기값 설정
				slide : function(event, ui) {
					updatePriceDisplay(ui.values[0], ui.values[1]);
				}
			});

			// 초기화 함수 호출
			initialize();

			// 초기화 함수
			function initialize() {
				var initialValues = $("#price-range")
						.slider("option", "values");
				updatePriceDisplay(initialValues[0], initialValues[1]);
			}

			// 화면에 표시된 PRICE을 업데이트하는 함수
			function updatePriceDisplay(minPrice, maxPrice) {
				$("#price-output").text(minPrice + " - " + maxPrice + " 만원");
				$("input[name=maxPrice]").attr("value", maxPrice);
				$("input[name=minPrice]").attr("value", minPrice);
			}
		});
	</script>
</body>
</html>
