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
<title>결제 전 정보확인</title>
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
<link type="text/css" rel="stylesheet" href="resources/css/payInfo.css" />

<!-- HEADER, NAVIGATION -->
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
</head>
<body>
   <jsp:include page="/WEB-INF/views/common/navigation.jsp"></jsp:include>
   <!-- /HEADER, NAVIGATION -->
   <!-- BREADCRUMB -->
   <div id="breadcrumb" class="section">
      <!-- container -->
      <div class="container">
         <!-- row -->
         <div class="row">
            <div class="col-md-12">
               <h3 class="breadcrumb-header">결제</h3>
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
            <!-- Payment Form-->

            <div class="col-md-9" style="margin-left: 10%;">
               <%-- Member Address Info --%>
               <div class="memberInfo">
                  <h2>배송 정보</h2>
                  <br>
                  <table style="width: 100%; border: 1px solid #000;">
                     <tbody>
                        <tr>
                           <th style="text-align: left; background-color: white; color: black; border: 1px solid gray; border-right: hidden; width: 15%;">배송지</th>
                           <td style="background-color: white; border: 1px solid gray; text-align: left;">${addressDatas.addressName}</td>
                        </tr>
                        <tr>
                           <th style="text-align: left; background-color: white; color: black; border: 1px solid gray; border-right: hidden;">이름 / 연락처</th>
                           <td style="background-color: white; border: 1px solid gray; text-align: left;">${addressDatas.memberName}| ${addressDatas.memberPhoneNumber}</td>
                        </tr>
                        <tr>
                           <th style="text-align: left; background-color: white; color: black; border: 1px solid gray; border-right: hidden;">주소</th>
                           <td style="background-color: white; border: 1px solid gray; text-align: left;">(${addressDatas.addressZipCode}) ${addressDatas.addressStreet} ${addressDatas.addressDetail}</td>
                        </tr>
                     </tbody>
                  </table>
               </div>
               <%-- /Member Address Info --%>

               <!-- Billing Details -->
               <br>
                <br>
               <h2>상품 정보</h2>
               <br>
               <div class="billing-details">
                  <table style="width: 100%; text-align: center;">
                     <thead>
                        <tr>
                           <th style="text-align: center;">상품</th>
                           <th style="text-align: center;">상품이름</th>
                           <th style="text-align: center;">구매개수</th>
                           <th style="text-align: center;">가격</th>
                           <th style="text-align: center;">쿠폰</th>
                        </tr>
                     </thead>
                     <c:set var="productDatasSize" value="${fn:length(productDatas)}" />
                     <c:if test="${productDatasSize >= 1}">
                        <c:forEach var="productData" items="${productDatas}" varStatus="i" begin="0">
                           <tbody>
                              <tr>
                                 <td class="img"><img src="${productData.productImg}" alt="img" style="width: 200px; height: 200px;"></td>
                                 <td class="name">${productData.getProductName()}</td>
                                 <td class="cnt">${productData.cartProductCnt}개</td>
                                 <td class="price">
                                    ${productData.cartProductCnt*productData.productPrice}원
                                 </td>
                                 <td class="select"><select class="couponSelectBox" id="${i.index}" onchange="selectBoxController(this)">
                                       <option class="-1" value="0">선택 안함</option>
                                       <c:forEach items="${couponDatas}" var="coupon">
                                          <c:if test="${productData.categoryID == coupon.categoryID}">
                                             <option class="${coupon.couponID}" value="${coupon.couponDiscountRate}">${coupon.couponName}</option>
                                          </c:if>
                                       </c:forEach>
                                 </select></td>
                              </tr>
                           </tbody>
                        </c:forEach>
                        <c:forEach items="${couponDatas}" var="coupon">
                           <input name="${coupon.couponID}" type="number" style="display: none;" value="${coupon.couponDiscountMaxPrice}" />
                        </c:forEach>
                     </c:if>


                  </table>
               </div>
               <!-- /Billing Details -->
               <div class="order-details">
                  <div class="section-title text-center">
                     <h3 class="title">Your Order</h3>
                  </div>
                  <form action="/getKakaoPayPage" method="get">
                     <div class="order-summary">
                        <div class="order-col">
                           <div>배송비</div>
                           <div>
                              <strong>FREE</strong>
                           </div>
                        </div>
                        <div class="order-col">
                           <div>
                              <strong>TOTAL</strong>
                           </div>
                           <c:if test="${productDatasSize >= 1}">
                              <c:set var="total" value="0"></c:set>
                              <c:forEach var="payInfoData" items="${productDatas}">
                                 <c:set var="total" value="${total + payInfoData.productPrice * payInfoData.cartProductCnt}"></c:set>
                              </c:forEach>
                           </c:if>
                           <c:if test="${productDatasSize < 1}">
                              <c:set var="nowTotal" value="${param.productPrice * param.cartProductCnt}"></c:set>
                           </c:if>
                           <c:if test="${productDatasSize >= 1}">
                              <div style="text-align: right;">
                                 <strong class="order-total"> 
                                 ${total}원
                                 </strong>
                              </div>
                              <input type="hidden" name="totalPrice" value="${total}">
                           </c:if>
                           <c:if test="${productDatasSize < 1}">
                              <div style="text-align: right;">
                                 <strong class="order-total"> <%-- [김진영] 통화표시를 위한 지역선택 --%> <fmt:setLocale value="ko_KR" /> <%-- [김진영] 해당 지역 통화에 맞게 가격 표시 'type' --%> <fmt:formatNumber value="${nowTotal}" type="currency" />원
                                 </strong>
                              </div>
                              <input type="hidden" name="totalPrice" value="${nowTotal}">
                           </c:if>
                        </div>
                     </div>
                     <div class="payment-method" style="align-items: center; display: flex;">
                        <div style="display: inline-block;">
                           <strong>결제수단</strong>
                        </div>
                        <select class="input-select" id="pgSelect" name="paymentProvider" style="display: inline-block; position: absolute; right: 4%;">
                           <option value="kakaopay">카카오페이</option>
                           <option value="tosspay">토스페이</option>
                           <option value="html5_inicis">이니시스</option>
                        </select>
                     </div>

                     <input type="number" name="payCk" value="${payCk}" style="display: none;" />
                     <!-- 상품 정보와 쿠폰 정보를 리스트로 전송 -->
                     <c:forEach var="productData" items="${productDatas}" varStatus="i" begin="0">
                        <input type="hidden" name="productIDs" value="${productData.productID}" />
                        <input type="hidden" name="productCnts" value="${productData.cartProductCnt}" />

                        <!-- 선택된 쿠폰ID 추가 -->
                        <input type="hidden" name="couponIDs" value="0" />
                     </c:forEach>

                     <!-- 제출 버튼 -->
                     <button type="submit" class="primary-btn order-submit" style="width: 50%; margin-left: 25%" onclick="validateAndSubmitForm()">결제하기</button>
                  </form>
               </div>
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


   <!-- JavaScript 코드 -->
   <script>
function validateAndSubmitForm() {
   console.log('validateAndSubmitForm 함수 실행됨');
   $('.couponSelectBox').each(function(index) {
         var selectedCoupon = $(this).val(); // 선택된 쿠폰ID 가져오기
         var selectedClass = $(this).attr('class'); // 선택된 쿠폰 선택상자의 클래스 가져오기
         console.log('선택된 클래스: ' + selectedClass);

         if (selectedCoupon !== '') {
               var selectedOption = $(this).find('option:selected'); // 선택된 option 요소 가져오기
               var selectedCouponClass = selectedOption.attr('class'); // 선택된 option의 class 속성 값 가져오기 (couponID 값)
               console.log('선택된 쿠폰ID (couponID): ' + selectedCouponClass);
               // 숨겨진 input 필드에 쿠폰ID 설정
               $('input[name="couponIDs"]').eq(index).val(selectedCouponClass);
         } else {
               // 쿠폰이 선택되지 않았다면 기본값인 0으로 설정
               $('input[name="couponIDs"]').eq(index).val('0');
         }
   });

   // 폼 제출
   $('form').submit();
}
</script>

   <script>
   // 상품들의 원가격을 저장하는 배열
var priceList = document.querySelectorAll('tr td.price');
var $priceList = [];
priceList.forEach(data => {
        $priceList.push(data.textContent);
});
// 선택박스 변경 시 실행할 함수
function selectBoxController(selectBox){
    // 이벤트가 발생한 선택상자의 정보 가져오기
    var $selectBox = selectBox; // 전체
    var $selectBoxId = selectBox.id; // 속성값 id를 가져오기
    console.log('선택된 BoxID : '+$selectBoxId);
    // 전체 선택상자 가져오기
    var $selectBoxIds = [] // 전체 속성값 id가져오기
    var $selectBoxes = $('.couponSelectBox'); // 전체 가져오기
    $selectBoxes.each((i, data) => {
        $selectBoxIds.push($(data).attr("id")); // 아이디를 배열에 저장하기
    });
    console.log('선택된 BoxIDs : '+$selectBoxIds);
    // 선택된 요소의 클래스명 가져오기 == couponID 가져오기
    $couponID = $selectBoxes.eq($selectBoxId).find('option:selected').attr('class');
    console.log('선택된 couponID : '+$couponID);
   $(`input[name="couponIDs[${$selectBoxId}]"]`).val($couponID);
    // 가격을 수정하기 위해 가져오기
    var $price = $('tr td.price').eq($selectBoxId).text().replace('원', '');
    console.log('가격 : ' + $price);
    var selectedOption = [];
    var $selectedList = $('option:selected');
   $selectedList.each((i, data) => {
      selectedOption.push($(data).attr('class'));
   });
   // 전체를 순회하면서 id가 동일하지 않은 경우에만 적용
   $selectBoxIds.forEach(selectBoxId => {
       console.log('반복문 실행');
       var couponDiscountMaxPrice = document.querySelector('input[name="'+$couponID+'"]');
       console.log(couponDiscountMaxPrice);
       if(selectBoxId != $selectBoxId){
           console.log('조건문 실행');
           if($couponID == '-1'){ // 쿠폰을 선택하지 않은 경우
               $('tr td.price').eq($selectBoxId).text($priceList[$selectBoxId]); // 미리 저장해 둔 원래가격으로 변경
           }else{
                 var valueable = ($priceList[$selectBoxId].replace('원','')*$selectBox.value/100);
               if(parseInt(valueable) > parseInt(couponDiscountMaxPrice.value)){ // couponDiscountMaxPrice.value의 타입이 문자열..
                   console.log('최대할인금액 적용')
                  // 쿠폰을 적용한 가격을 반올림해서 적용
                   $('tr td.price').eq($selectBoxId).text(Math.round(parseInt($priceList[$selectBoxId].replace('원',''))-parseInt(couponDiscountMaxPrice.value))+'원'); 
               }else{
                   console.log('할인금액 적용')
                   // 쿠폰을 적용한 가격을 반올림해서 적용
                   $('tr td.price').eq($selectBoxId).text(Math.round(eval($priceList[$selectBoxId].replace('원','')-valueable))+'원');
               }
           }
       }
      changeOption(selectBoxId, $selectBoxes, selectedOption);
      changeTotalPrice();
   });
}
function changeOption(selectBoxId, $selectBoxes, selectedOption){
//   var $selectedList = $('option:selected').attr('class');
   console.log(selectedOption);
//   $selectBoxes.eq(selectBoxId).find('option[class="'+$couponID+'"]').show();
   $selectBoxes.eq(selectBoxId).find('option').show();
   selectedOption.forEach((data) => {
      if(data!=-1){
          $selectBoxes.eq(selectBoxId).find('option[class="'+data+'"]').hide();
      }
   })
}

// 합계를 수정해주는 함수
var total = 0;
function changeTotalPrice(){
   total = 0;
   var getPrice = document.querySelectorAll('tr td.price')
   getPrice.forEach( data => {
      total+= parseInt(data.textContent.replace('원', ''));
   })
   $('strong.order-total').text(total);
}
</script>
</body>
</html>
