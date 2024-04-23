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
