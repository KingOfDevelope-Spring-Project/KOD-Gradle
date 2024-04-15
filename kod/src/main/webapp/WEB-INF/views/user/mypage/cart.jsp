<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>장바구니 선택 페이지</title>

<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css" />

<!-- Slick -->
<link type="text/css" rel="stylesheet" href="resources/css/slick.css" />
<link type="text/css" rel="stylesheet" href="resources/css/slick-theme.css" />

<!-- nouislider -->
<link type="text/css" rel="stylesheet" href="resources/css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="resources/css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="resources/css/style.css" />

<link type="text/css" rel="stylesheet" href="resources/css/paySelect.css" />

</head>

<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/navigation.jsp"></jsp:include>
	<c:set var="cDatas" value="${cartDatas}" />
	<%-- <c:set var="memberDTO" value="${sessionScope.memberDTO}" /> --%>
	<section class="cart">
		
		<div class="cart__information">
			<ul>
				<li>장바구니 상품은 최대 10개까지만 담을 수 있습니다.</li>
				<li>가격, 옵션 등 정보가 변경된 경우 주문이 불가할 수 있습니다.</li>
			</ul>
		</div>
		
<!-- <script>
	var selectedProducts = []; // 선택한 상품들의 ID를 임시 저장할 배열

	function addToSelectedProducts(productID) {
			// 이미 선택된 상품인지 확인하고 선택 상태를 업데이트합니다.
			var index = selectedProducts.indexOf(productID);
			if (index === -1) {
					// 선택되지 않은 상품이면 배열에 추가합니다.
					selectedProducts.push(productID);
			} else {
					// 이미 선택된 상품이면 배열에서 제거합니다.
					selectedProducts.splice(index, 1);
			}
	}
</script>

<script>
	function submitForm() {
			// 선택한 상품들의 정보를 숨은 입력 필드에 추가합니다.
			document.querySelector('input[name="selectedProducts"]').value = selectedProducts.join(',');
			return true; // 폼을 제출합니다.
	}
</script> -->

<script>
	var selectedProducts = []; // 선택한 상품들의 ID를 임시 저장할 배열

	function addToSelectedProducts(productID, cartProductCnt) {
			// 이미 선택된 상품인지 확인하고 선택 상태를 업데이트합니다.
			var index = selectedProducts.findIndex(item => item.productID === productID);
			if (index === -1) {
					// 선택되지 않은 상품이면 배열에 추가합니다.
					selectedProducts.push({ productID: productID, cartProductCnt: cartProductCnt });
			} else {
					// 이미 선택된 상품이면 배열에서 제거합니다.
					selectedProducts.splice(index, 1);
			}
	}
</script>
<script>
    function submitForm() {
        var selectedProducts = []; // 선택한 상품들의 정보를 임시 저장할 배열

        // 모든 체크된 상품의 ID와 수량을 수집합니다.
        document.querySelectorAll('input[name="selectedProducts"]:checked').forEach(function(checkbox) {
            var productID = checkbox.value;
            var cartProductCnt = document.querySelector('input[name="cartProductCnt"][data-productID="' + productID + '"]').value;
            selectedProducts.push({ productID: productID, cartProductCnt: cartProductCnt });
        });

        // 숨은 입력 필드에 선택된 상품들의 정보를 JSON 형태로 설정합니다.
        document.querySelector('input[name="selectedProducts"]').value = JSON.stringify(selectedProducts);
        return true; // 폼을 제출합니다.
    }
</script>

		<form action="/getInitOrderPage" method="GET" onsubmit="return submitForm()">
			<input type="hidden" name="payCk" value="0">
			<table class="cart__list">
				<thead>
					<tr>
						<td><input type="checkbox" onclick='selectAll(this)' /> <b></b></td>
						<td colspan="2">상품정보</td>
						<td>옵션</td>
						<td style="width: 15%;">상품금액</td>
						<td>배송비</td>
						<td></td>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${empty cartDatas}">
						<tbody>
							<tr>
								<td colspan="7"><h3 style="text-align: center;">장바구니에 상품이 없습니다</h3></td>
							</tr>
						</tbody>
					</c:when>
					<c:otherwise>
						<c:forEach var="cData" items="${cartDatas}" varStatus="status">
							<tbody>
								<input type="hidden" value="${cData.cartID}" />
								<tr class="cart__list__detail">
									<td><input type="checkbox" name="selectedProducts" value="${cData.productID}" id="selectedCheckBox_${status.index}" onchange="addToSelectedProducts('${cData.productID}')"></td>
									<td><img src="${cData.productImg}" alt="product"></td>
									<td><a href="/">KOD스토어</a>
										<p>${cData.productName}</p> <span class="price" id="eachPrice_${status.index}">${cData.productPrice}원</span></td>
									<td class="bseq_ea" style="text-align: center;">
										<p>${cData.productName}</p>
										<button style="border: none; color: black;" type="button" onclick="fnCalCount('m', this, ${status.index});">-</button> <input type="text" id="changedCnt_${status.index}" name="cartProductCnt" value="${cData.cartProductCnt}" readonly="readonly" style="text-align: center;" />
										<button style="border: none; color: black;" type="button" onclick="fnCalCount('p', this, ${status.index});">+</button> <!-- <button class="cart__list__optionbtn">주문조건 추가/변경</button> -->
									</td>
									<td><span class="price" id="totalPrice_${status.index}"> ${cData.productTotalPrice}원 </span><br> <!--  	<button class="cart__list__orderbtn">주문하기</button> --></td>
									<td>무료</td>
									<td><a href="/deleteSelectProductsToCart?productID=${cData.productID}" class="cart__list__optionbtn">상품 삭제 </a></td>
								</tr>
							</tbody>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<div class="cart__mainbtns" style="float: right">
				<button class="cart__bigorderbtn right" onclick="return submitForm()">주문하기</button>
			</div>
		</form>
		<form action="/getStorePage" method="GET" style="display: inline-block;">
			<button class="cart__bigorderbtn left" style="width:">쇼핑 계속하기</button>
		</form>

		<form action="/deleteAllProductsToCart" method="POST" style="display: inline-block;">
			<c:forEach var="cData" items="${cartDatas}" varStatus="status">
				<input type="hidden" name="cartId" value="${cData.cartID}">
			</c:forEach>
			<div class="cart__mainbtns">
				<button class="cart__bigorderbtn right" style="background-color:">장바구니 비우기</button>
			</div>
		</form>
	</section>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	<!-- jQuery Plugins -->
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/slick.min.js"></script>
	<script src="resources/js/nouislider.min.js"></script>
	<script src="resources/js/jquery.zoom.min.js"></script>
	<script src="resources/js/main.js"></script>

	<script>
    function updateCart(productID, productCnt, index) { // 장바구니 수량 변경을 처리할 비동기 함수
        $.ajax({
            type: 'POST',
            url: 'asyncUpdateProductCntToCart', // 장바구니 업데이트를 처리할 서블릿 URL
            dataType: 'json',
            data: {
                productID: productID, // 상품번호
                cartProductCnt: productCnt // 상품수량
            },
            success: function(response) {
                var changedCnt = response;
                console.log('장바구니 업데이트 성공');
                console.log(response);
                console.log('cart 변경수량 :  ' + changedCnt);

                $('#changedCnt_' + index).val(changedCnt);

                // 상품 가격 계산 및 업데이트
                var totalPrice = changedCnt * parseInt($('#eachPrice_' + index).text().replace('원', ''));
                $('#totalPrice_' + index).text(totalPrice + '원');
            },
            error: function(xhr, status, error) {
                console.error('장바구니 업데이트 실패:', status, error);
            }
        });
    }

    function fnCalCount(type, ths, index) {
        console.log('수량변경 진입');
        // 해당 상품의 ID 가져오기
        var productID = $(ths).closest('.cart__list__detail').find('input[name="selectedProducts"]').val();

        // 해당 상품의 수량 가져오기
        var $input = $(ths).parents("td").find("input[name='cartProductCnt']");
        var productCnt = $input.val();

        // 변경된 수량 계산
        var newProductCnt;

        if (type == 'p') {
            newProductCnt = Number(productCnt) + 1;
            if (newProductCnt > 10) {
                newProductCnt = 10;
                alert('최대수량은 10개를 넘길 수 없습니다');
            }
        } else {
            if (productCnt > 1) {
                newProductCnt = Number(productCnt) - 1;
            } else {
                newProductCnt = 1; // 최소 수량은 1로 설정
            }
        }
        // 장바구니 수량 비동기처리 함수
        updateCart(productID, newProductCnt, index);
    }

    function selectAll(selectAll) { //상품 체크박스 전체선택 함수 
        const checkboxes = document.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
        });
    }
			
			  // function submitForm() { //장바구니에서 주문으로 이동하는 submit 함수
			  //       var checkboxes = document.getElementsByName('selectedProducts');
			  // // ※id가 아니라 name으로 호출하는 이유 : 폼 내에 여러개의 체크박스가 생성될 수 있기 때문에 단일 요소에 대한 참조를 가져오는 id 대신 name을 사용
			  //       var isChecked = false;
			  //       for (var i = 0; i < checkboxes.length; i++) {
			  //           if (checkboxes[i].checked) {
			  //           	isChecked = true;
			  //               break;
			  //           }
			  //       }
			  //       if (!isChecked) { //하나의 상품도 선택하지 않은 경우
			  //           alert('최소 하나의 상품을 선택해주세요');
			  //           return false; 
			  //       }
			  //       return true; // 하나이상의 상품을 선택한 경우 
			  //   }
		</script>
		<script type="text/javascript"></script>
</body>
</html>
