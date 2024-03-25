<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>navigation</title>
</head>
<body>
<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li><a href="/">Home</a></li>
						<li><a href="/getStorePage?page=1">상품목록</a></li>
						<c:if test="${memberID == null}">
							<li><a href="/loginPage">장바구니</a></li>
							<li><a href="/loginPage">마이페이지</a></li>
						</c:if>
						<c:if test="${memberID != null}">
							<li><a href="/getCartPage">장바구니</a></li>
							<li><a href="/getMypage">마이페이지</a></li>
						</c:if>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->
</body>

<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	var lists = document.querySelectorAll(".nav li");
	lists.addEventListener('click', clickEvent());
	function clickEvent(){
		this.classList.add("active");
	}
</script>
</html>
