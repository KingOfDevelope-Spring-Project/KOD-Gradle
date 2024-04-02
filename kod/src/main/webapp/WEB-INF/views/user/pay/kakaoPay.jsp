<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	
	<!-- 구매자 이름 -->
	<c:set var="name" value="${memberDTO.memberName}" />
	
	<!-- 상품 총 가격 -->
		<c:set var="totalPrice" value="${param.totalPrice}" />
	<!-- 구매할 상품 이름 -->
	<c:choose>
		<c:when test="${fn : length(productDatas) > 1}">
			<c:set var="productName" value="${productDatas.get(0).productName} 외 ${fn : length(productDatas) -1}개" />
		</c:when>
		<c:otherwise>
			<c:set var="productName" value="${payInfoProductNames[0]}" />
		</c:otherwise>
	</c:choose>
	
	<!-- payDTO를 통해 들어오는 데이터가 있다면 선택 구매(장바구니를 통한 구매) -->	
	<c:if test="${fn:length(productDatas) >= 1}">
		<c:forEach var="cart" items="${productDatas}">
			<input type="hidden" name="pid" id="pid" value="${cart.productID }">
			<input type="hidden" name="cnt" id="cnt" value="${cart.cartProductCnt}">
		</c:forEach>
		<input type="number" name="payCk" id="payCk" value="${param.payCk}" style="display:none">
	</c:if>
	
	<!-- 바로 구매 -->
	<c:if test="${cDatasSize < 1 }">
		<input type="hidden" name="pid" id="pid" value="${payNow.productID }">
		<input type="hidden" name="cnt" id="cnt" value="${payNow.cartProductCnt}">
		<input type="number" name="payCk" id="payCk" value="${param.payCk}" style="display:none">
	</c:if>
	
    <script>
    $(function(){
	    var pid = document.querySelectorAll('input[name=pid]');
	    var cnt = document.querySelectorAll('input[name=cnt]');
	    var payCk = document.querySelector('input[name=payCk]');
	    
	    /* 들어오는 결제 데이터 확인용 로그*/
	    console.log(pid);
        console.log(cnt);
        
	    var IMP = window.IMP; 
        IMP.init('imp01807501'); 
        var msg;
        var everythings_fine=true;
        
        // 결제할 상품 번호를 배열에 저장하기
        var productID = [];
        pid.forEach(function(cartItem){
        	productID.push(cartItem.value);
        });
        var productIDs = JSON.stringify(productID);
        //console.log("상품번호 : " + productIDs);
		
        // 결제할 상품의 개수를 배열에 저장하기
        var purchaseCnt = [];
        cnt.forEach(function(cartItem){
        	purchaseCnt.push(cartItem.value);
        });
        var purchaseCnts = JSON.stringify(purchaseCnt);
        //console.log("구매개수 : " + purchaseCnts);
        
        // 결제 방식 저장
        var payNows = payCk.value;
        //payCk.forEach(function(cartItem){
        //	payNow.push(cartItem.value);
        //});
        //var payNows = JSON.stringify(payNow);
        //console.log("결제방식 : "+payNows);
        
        IMP.request_pay({
            pg : 'kakaopay',			// 결제 방식
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(), 
            name : '${productName}', 	// 상품명
            amount : '${totalPrice}',	// 총 가격
            productIDs : 'productIDs',	// 상품 번호
            purchaseCnt : 'purchaseCnt',// 구매 개수
            buyer_email : 'email',
            buyer_name : 'name',
            buyer_tel : 'phone',
            buyer_addr : 'address',
            buyer_postcode : '123-456',
        }, function(rsp) {
            if ( rsp.success ) {
            	console.log('로그'+payCk);
                $.ajax({
                    url: "/asyncPayment", // 결제 서블릿
                    type: 'POST',
                    data: {
                        imp_uid : rsp.imp_uid,
                        productList : productIDs,		// 상품 번호
                        productCnt : purchaseCnts,	// 구매 개수
                        payCk : payNows				// 결제 방식
                    },
                	success: function(){
                		console.log('결제 성공');
                		//성공시 이동할 페이지
                        location.href='/getPayInfoPage';
                	},
                })
            } else if(rsp.success == false){ // 결제 취소할 경우 이전 페이지로 돌아감
            	alert('결제를 취소했습니다. 이전 화면으로 돌아갑니다.');
        		history.go(-2);
            } 
        }); 
    }); 
    </script>
 
</body>
</html>
