<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin-navigation</title>
</head>
<body>
	<!-- Sidebar Menu -->
	<nav class="mt-2">
		<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
			<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
			<li class="nav-item "><a href="#" class="nav-link active"> <i class="nav-icon fas fa-tachometer-alt"></i>
					<p>
						매출 현황 <i class="right fas fa-angle-left"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="#" class="nav-link active"> <i class="far fa-circle nav-icon"></i>
							<p>월 매출액</p>
					</a></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>상품별 판매량</p>
					</a></li>
				</ul></li>
			<li class="nav-item "><a href="#" class="nav-link active"> <i class="nav-icon fas fa-user-alt"></i>
					<p>
						회원 관리<i class="right fas fa-angle-left"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>회원 등급 관리</p>
					</a></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>회원 정보 관리</p>
					</a></li>
				</ul></li>
			<li class="nav-item "><a href="#" class="nav-link active"> <i class="nav-icon fas fa-store-alt"></i>
					<p>
						상품 관리 <i class="right fas fa-angle-left"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>상품 목록</p>
					</a></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>상품 등록</p>
					</a></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>재고 관리</p>
					</a></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>상품 삭제</p>
					</a></li>
				</ul></li>
			<li class="nav-item "><a href="#" class="nav-link active"> <i class="nav-icon fas fa-ticket-alt"></i>
					<p>
						쿠폰 관리<i class="right fas fa-angle-left"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="adminCouponIssue.jsp" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>쿠폰 발행</p>
					</a></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>쿠폰 발행 목록</p>
					</a></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>쿠폰 사용 목록</p>
					</a></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>쿠폰 삭제</p>
					</a></li>
				</ul></li>
			<li class="nav-item "><a href="#" class="nav-link active"> <i class="nav-icon fas fa-list-alt"></i>
					<p>
						리뷰 관리<i class="right fas fa-angle-left"></i>
					</p>
			</a>
				<ul class="nav nav-treeview">
					<li class="nav-item"><a href="#" class="nav-link"> <i class="far fa-circle nav-icon"></i>
							<p>리뷰 목록</p>
					</a></li>
				</ul></li>

			<!-- <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Simple Link
                <span class="right badge badge-danger">New</span>
              </p>
            </a>
          </li> -->
		</ul>
	</nav>
	<!-- /.sidebar-menu -->
</body>
</html>