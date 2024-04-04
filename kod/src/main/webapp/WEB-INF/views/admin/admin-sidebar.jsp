<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin-navigation</title>
</head>
<body>
	<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="/getAdminMainPage" class="nav-link">Home</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="/getProductSalesStatisticsByQuarterPage"
					class="nav-link">매출 현황</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="/getMemberManagePage"
					class="nav-link">회원 관리</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="/getProductManagePage"
					class="nav-link">상품 관리</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="/getCouponManagePage" 
					class="nav-link">쿠폰 관리</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="/getReviewManagePage"
					class="nav-link">리뷰 관리</a></li>
				<button class="btn btn-primary btn-right" onclick="logout()" style="margin-left: 400px;">로그아웃</button>	
			</ul>

			<!-- Right navbar links -->
			<!-- <ul class="navbar-nav ml-auto">
				Navbar Search
				<li class="nav-item"><a class="nav-link"
					data-widget="navbar-search" href="#" role="button"> <i
						class="fas fa-search"></i>
				</a>
					<div class="navbar-search-block">
						<form class="form-inline">
							<div class="input-group input-group-sm">
								<input class="form-control form-control-navbar" type="search"
									placeholder="Search" aria-label="Search">
								<div class="input-group-append">
									<button class="btn btn-navbar" type="submit">
										<i class="fas fa-search"></i>
									</button>
									<button class="btn btn-navbar" type="button"
										data-widget="navbar-search">
										<i class="fas fa-times"></i>
									</button>
								</div>
							</div>
						</form>
					</div></li>

				Messages Dropdown Menu
				<li class="nav-item dropdown"><a class="nav-link"
					data-toggle="dropdown" href="#"> <i class="far fa-comments"></i>
						<span class="badge badge-danger navbar-badge">3</span>
				</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<a href="#" class="dropdown-item"> Message Start
							<div class="media">
								<img src="resources/dist/img/user1-128x128.jpg" alt="User Avatar"
									class="img-size-50 mr-3 img-circle">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										건의 사항 <span class="float-right text-sm text-danger"><i
											class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">배송지를 잘못 입력했습니다..</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div> Message End
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> Message Start
							<div class="media">
								<img src="resources/dist/img/user8-128x128.jpg" alt="User Avatar"
									class="img-size-50 img-circle mr-3">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										건의 사항 <span class="float-right text-sm text-muted"><i
											class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">환불은 어떻게 신청하나요?...</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div> Message End
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> Message Start
							<div class="media">
								<img src="resources/dist/img/user3-128x128.jpg" alt="User Avatar"
									class="img-size-50 img-circle mr-3">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										건의 사항 <span class="float-right text-sm text-warning"><i
											class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">홈페이지에 에러가 있습니다...</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div> Message End
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All
							Messages</a>
					</div></li>
				Notifications Dropdown Menu
				<li class="nav-item dropdown"><a class="nav-link"
					data-toggle="dropdown" href="#"> <i class="far fa-bell"></i> <span
						class="badge badge-warning navbar-badge">15</span>
				</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<span class="dropdown-header">15 Notifications</span>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i
							class="fas fa-envelope mr-2"></i> 4 new messages <span
							class="float-right text-muted text-sm">3 mins</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i
							class="fas fa-users mr-2"></i> 8 friend requests <span
							class="float-right text-muted text-sm">12 hours</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i>
							3 new reports <span class="float-right text-muted text-sm">2
								days</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All
							Notifications</a>
					</div></li>
				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					data-widget="control-sidebar" data-slide="true" href="#"
					role="button"> <i class="fas fa-th-large"></i>
				</a></li>
			</ul> -->
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<!-- <a href="index3.html" class="brand-link">
      <img src="resources/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">AdminLTE 3</span>
    </a> -->

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img src="resources/img/logo.gif" class="img-circle elevation-2"
							alt="User Image">
					</div>
					<div class="info">
						<a href="adminMain.jsp" class="d-block">KOD 관리자</a>
					</div>
				</div>

				<!-- SidebarSearch Form -->
				<div class="form-inline">
					<div class="input-group" data-widget="sidebar-search">
						<input class="form-control form-control-sidebar" type="search"
							placeholder="Search" aria-label="Search">
						<div class="input-group-append">
							<button class="btn btn-sidebar">
								<i class="fas fa-search fa-fw"></i>
							</button>
						</div>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
						<li class="nav-item ">
							<a href="/getProductSalesStatisticsByQuarterPage" class="nav-link active">
								<i class="nav-icon fas fa-tachometer-alt"></i>
								<p>
									매출 현황
								</p>
							</a>
						</li>
						<li class="nav-item "><a href="#" class="nav-link active">
								<i class="nav-icon fas fa-user-alt"></i>
								<p>
									회원 관리<i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="/getAllMembersPage" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>회원 정보 관리</p>
								</a></li>
								<li class="nav-item"><a href="/getMemberListByGrade" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>등급별 회원 목록</p>
								</a></li>
								<li class="nav-item"><a href="/getMemberRecoveryPage" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>회원 복구 신청</p>
								</a></li>
							</ul></li>
						<li class="nav-item "><a href="#" class="nav-link active">
								<i class="nav-icon fas fa-store-alt"></i>
								<p>
									상품 관리 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="/getProductListPage" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>상품 목록</p>
								</a></li>
								<li class="nav-item"><a href="/insertProductPage" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>상품 등록</p>
								</a></li>
								<li class="nav-item"><a href="/getUpdateProductPage" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>상품 정보 수정</p>
								</a></li>
								<li class="nav-item"><a href="/getCategoryManagePage" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>상품 카테고리 추가</p>
								</a></li>
							</ul>
						</li>
						<li class="nav-item "><a href="#" class="nav-link active">
								<i class="nav-icon fas fa-ticket-alt"></i>
								<p>
									쿠폰 관리<i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="/couponIssuePage"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>쿠폰 발행</p>
								</a></li>
								<li class="nav-item"><a href="/getIssuedCouponListPage" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>쿠폰 발행 목록</p>
								</a></li>
								<li class="nav-item"><a href="/memberCouponListPage" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>회원별 쿠폰 목록</p>
								</a></li>
							</ul></li>
						<li class="nav-item ">
							<a href="/getReviewManagePage" class="nav-link active">
								<i class="nav-icon fas fa-list-alt"></i>
								<p>
									리뷰 관리
								</p>
							</a>
						</li>

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
			</div>
			<!-- /.sidebar -->
		</aside>
</body>

<script>
	function logout(){
		location.href='/logout'
	};
</script>
</html>