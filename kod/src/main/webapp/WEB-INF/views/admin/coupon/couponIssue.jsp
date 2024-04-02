<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 원화표시 functions함수집합 가져오기 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 원화표시 포맷 -->
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>KOD 관리자 페이지</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome Icons -->
<link rel="stylesheet" href="resources/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="resources/dist/css/adminlte.min.css">
<!-- DataTables -->
  <link rel="stylesheet" href="resources/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="resources/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
  <link rel="stylesheet" href="resources/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
  <!-- daterange picker -->
  <link rel="stylesheet" href="resources/plugins/daterangepicker/daterangepicker.css">
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<!-- Navbar -->
		<jsp:include page="/WEB-INF/views/admin/admin-sidebar.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">쿠폰 관리</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">대시보드</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<div class="content">
				<div class="container-fluid">
					
							<!-- 회원 목록 테이블 -->
							<!-- SELECT2 EXAMPLE -->
				        <div class="card">
				          <div class="card-header">
				            <h3 class="card-title">쿠폰 발행</h3>
				
				            <div class="card-tools">
				              <button type="button" class="btn btn-tool" data-card-widget="collapse">
				                <i class="fas fa-minus"></i>
				              </button>
				              <button type="button" class="btn btn-tool" data-card-widget="remove">
				                <i class="fas fa-times"></i>
				              </button>
				            </div>
				          </div>
				          <!-- /.card-header -->
				          <form action="/issueCouponByGrade" method="POST" id="couponIssue">
					          <div class="card-body" style="display: flex;">
					            
					              <div class="col-sm-6">
					              	<div class="col-sm-10">
						                <div class="form-group">
						                  <label>쿠폰 종류</label>
						                  <select class="form-control select2" id="coupon-type" name="couponType" style="width: 100%;">
						                    <option value="auto" selected="selected">자동 발행</option>
						                    <option value="adminIssue">관리자 발행</option>
						                  </select>
						                </div>
					                </div>
					                <!-- /.form-group -->
					                <div class="col-sm-10">
						                <div class="form-group">
						                  <label>쿠폰명</label>
						                  <input class="form-control" id="couponName" name="couponName" type="text" placeholder="쿠폰명을 입력하세요.">
						                </div>
					            	</div>
					                <!-- /.form-group -->
					              
					                <div class="col-sm-10">
						                <div class="form-group">
						                  <label>쿠폰 설명</label>
						                  <input class="form-control" id="couponContent" name="couponContent" type="text" placeholder="쿠폰 설명을 입력하세요.">
						                </div>
					                </div>
					                <!-- /.form-group -->
					                
					                <div class="col-sm-10" id="coupon-period" >
						                <div class="form-group">
					                  		<label>쿠폰 기간</label>
					                  		<input class="form-control" name="couponUseDate" type="Number" placeholder="쿠폰 사용 기간을 입력하세요. (단위 : 일)">
						                </div>
					                </div>
					                
					                <!-- <div class="col-sm-10" id="coupon-enddate" style="display: none;">
					                    Date
						                <div class="form-group">
						                  <label>만료일</label>
						                    <input class="form-control" type="date" name="couponStatusDTO.couponExpireDate" placeholder="쿠폰 만료일을 입력하세요.">
						                </div>
					                </div> -->
					                
					                <div class="col-sm-10">
					                <label>할인율</label>
						                <div class="form-group" >
						                  	<div style="width: 100%;">
						                  		<input class="form-control" id="couponDiscountRate" name="couponDiscountRate" type="text" placeholder="할인율을 입력하세요." style="width: 85%; display: inline-block;">
							                        <div class="form-check" style="display: inline-block; margin-left: 5%;">
							                          <input class="form-check-input" type="radio" name="radio1" checked>
							                          <label class="form-check-label" >%</label>
							                        </div>
					                  		</div>
						                </div>
					                </div>
					                
					                <!-- /.form-group -->
					                
					                <div class="col-sm-10">
						                <div class="form-group">
						                  <label>쿠폰 사용 최소금액</label>
						                  <input class="form-control" id="couponUseMinPrice" name="couponUseMinPrice" type="text" placeholder="쿠폰 사용 최소금액을 입력하세요.">
						                </div>
					                </div>
					                
					                <div class="col-sm-10">
						                <div class="form-group">
						                  <label>쿠폰 최대 할인금액</label>
						                  <input class="form-control" id="couponDiscountMaxPrice" name="couponDiscountMaxPrice" type="text" placeholder="쿠폰 최대 할인금액을 입력하세요.">
						                </div>
					                </div>
					                
					                <div class="col-sm-10">
						                <div class="form-group">
						                  <label>쿠폰 카테고리</label>
			        	          			<select class="form-control select2" id="couponCategory" name="couponCategory" style="width: 100%;" required>
						                    	<option selected="selected" style="display: none;"></option>
						                    	<option>전체</option>
						                    	<option>헤드폰</option>
						                    	<option>이어폰</option>
						                    	<option>스피커</option>
					                  		</select>
						                </div>
					                </div>
					                
					                
					              </div>
					              
					              <!-- 자동 발행 선택 시 div -->
					              	<div class="col-sm-6" id="issue-option" name="issueOption" style="float: right; display: block;">
					              		<div class="col-sm-10">
						              		<div class="form-group">
						                  		<label>발급 조건</label>
					        	          		<select class="form-control select2" id="register-type" style="width: 100%;" required>
								                    <option selected="selected" style="display: none;"></option>
								                    <option>회원가입</option>
								                    <option>생일</option>
								                    <option>등급업</option>
							                  	</select>
						                	</div>
					                	</div>
			                		</div>
			                		<!-- /자동 발행 선택 시 div -->
					              
					              
					              	<!-- 관리자 발행 선택 시 div -->
					              	<div class="col-sm-6" id="user-grade" name="memberGrade" style="float: right; display: none;">
					              		<div class="col-sm-10">
						              		<div class="form-group">
						                  		<label>발급 대상</label>
					        	          		<select class="form-control select2" id="memberGrade" name="memberGrade" style="width: 100%;">
					        	          			<option selected="selected" style="display: none;"></option>
								                    <option value="ALL">전체</option>
								                    <option value="BRONZE">Bronze</option>
								                    <option value="SILVER">Silver</option>
								                    <option value="GOLD">Gold</option>
								                    <option value="VIP">VIP</option>
							                  	</select>
						                	</div>
					                	</div>
			                		</div>
			                		<!-- /관리자 발행 선택 시 div -->
			                		
			                		<!-- 프로모션 선택 시 div -->
			                		<div class="col-sm-6" id="coupon-code" style="display: none;">
			                			<div class="col-sm-10">
				                			<div class="form-group" >
							                  <label>쿠폰 코드</label>
							                   <input class="form-control" id="couponCode" name="couponCode" type="text" placeholder="쿠폰 코드를 입력하세요.">
							                </div>
						                </div>
						            </div>
						            <!-- /프로모션 선택 시 div -->
					              <!-- /.col -->
					        </div>
					        <button type="submit" onclick="submitForm()" class="btn btn-primary" style="float: right;">쿠폰 발행</button>
				        </form>      
				        <!-- /.card -->
				
				        <!-- SELECT2 EXAMPLE -->
							<!-- /발행 쿠폰 목록 -->
							<!-- /.card-body -->
						
						<!-- /.card -->
					
					<!-- /.col-md-6 -->

					<!-- /.col-md-6 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->

	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Control sidebar content goes here -->
		<div class="p-3">
			<h5>Title</h5>
			<p>Sidebar content</p>
		</div>
	</aside>
	<!-- /.control-sidebar -->

	<!-- Main Footer -->
	<footer class="main-footer">
		<!-- To the right -->
		<div class="float-right d-none d-sm-inline">Anything you want</div>
		<!-- Default to the left -->
		<strong>Copyright &copy; 2023-2024 <a
			href="https://adminlte.io">KOD</a>.
		</strong> All rights reserved.
	</footer>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->
	<script src="resources/plugins/jquery/jquery.min.js"></script>
	<script src="resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/dist/js/adminlte.min.js"></script>
	<!-- Bootstrap 4 -->
	<!-- Select2 -->
	<script src="resources/plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script src="resources/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script src="resources/plugins/moment/moment.min.js"></script>
	<script src="resources/plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- Date Range -->
	<script src="resources/plugins/daterangepicker/daterangepicker.js"></script>
	
<!-- Date Range -->
<script>
  $(function () {
    $('#reservation').daterangepicker(
      {
     	startDate: moment(),					// 발행 날짜(moment : 현재 날짜)
        endDate  : moment().add(1,'month') , 	// 종료 날짜 (디폴트값 : 1달)
      }
    )
  });
</script>

<!-- 관리자 발행 선택 시 div -->
<script>
document.getElementById('coupon-type').addEventListener('change', function() {
	  var userGradeSection = document.getElementById('user-grade');
	  var userOptionSection = document.getElementById('issue-option');
	  var couponCodeSection = document.getElementById('coupon-code');
	  //var couponPeriodSection = document.getElementById('coupon-period');
	  //var couponEndSection = document.getElementById('coupon-enddate');
	  if (this.value === 'adminIssue') {
		  userOptionSection.style.display = 'none';
		  userGradeSection.style.display = 'block';
		  couponCodeSection.style.display = 'none';
		  //couponPeriodSection.style.display = 'none';
		  //couponEndSection.style.display = 'block';
	  } else if (this.value === 'auto'){
		  userGradeSection.style.display = 'none';
		  userOptionSection.style.display = 'block';
		  couponCodeSection.style.display = 'none';
		  //couponPeriodSection.style.display = 'block';
		  //couponEndSection.style.display = 'none';
	  } else{
		  userGradeSection.style.display = 'none';
		  userOptionSection.style.display = 'none';
		  couponCodeSection.style.display = 'block';
		  //couponPeriodSection.style.display = 'none';
		 //couponEndSection.style.display = 'block';
	  }
	});
</script>

<script>
function submitForm() {
    var form = document.getElementById("couponIssue");
    form.submit();
}
</script>
	<!-- jQuery -->

</body>
</html>
