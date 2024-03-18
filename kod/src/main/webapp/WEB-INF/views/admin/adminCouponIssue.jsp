<%@page import="model.dto.WishListDTO"%>
<%@page import="java.util.ArrayList"%>
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
<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/adminlte.min.css">
<!-- DataTables -->
  <link rel="stylesheet" href="plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
  <link rel="stylesheet" href="plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
  <link rel="stylesheet" href="plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
  <!-- daterange picker -->
  <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<!-- Navbar -->
		<jsp:include page="admin-sidebar.jsp"></jsp:include>

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
				          <div class="card-body" style="display: flex;">
				            
				              <div class="col-sm-6">
				              	<div class="col-sm-10">
					                <div class="form-group">
					                  <label>쿠폰 종류</label>
					                  <select class="form-control select2" id="coupon-type" style="width: 100%;">
					                    <option selected="selected">자동 발행</option>
					                    <option>관리자 발행</option>
					                    <option>프로모션 쿠폰</option>
					                  </select>
					                </div>
				                </div>
				                <!-- /.form-group -->
				                <div class="col-sm-10">
					                <div class="form-group">
					                  <label>쿠폰명</label>
					                  <input class="form-control" type="text" placeholder="쿠폰명을 입력하세요.">
					                </div>
				            	</div>
				                <!-- /.form-group -->
				              
				              <!-- /.col -->
				              	<div class="col-sm-10">
					                <div class="form-group">
					                  <label>쿠폰 코드</label>
					                   <input class="form-control" type="text" placeholder="쿠폰 코드를 입력하세요.">
					                </div>
					            </div>
				                <!-- /.form-group -->
				                <div class="col-sm-10">
					                <div class="form-group">
					                  <label>쿠폰 설명</label>
					                  <input class="form-control" type="text" placeholder="쿠폰 설명을 입력하세요.">
					                </div>
				                </div>
				                <!-- /.form-group -->
				                
				                <div class="col-sm-10">
					                <div class="form-group">
				                  		<label>쿠폰 기간</label>
				                  		<div class="input-group">
											<div class="input-group-prepend">
												<span class="input-group-text">
													<i class="far fa-calendar-alt"></i>
												</span>
											</div>
										<input type="text" class="form-control float-right" id="reservation">
										</div>
					                </div>
				                </div>
				                
				                <div class="col-sm-10">
				                <label>할인율</label>
					                <div class="form-group" >
					                  	<div style="width: 100%;">
					                  		<input class="form-control" type="text" placeholder="할인율을 입력하세요." style="width: 70%; display: inline-block;">
						                        <div class="form-check" style="display: inline-block; margin-left: 5%;">
						                          <input class="form-check-input" type="radio" name="radio1">
						                          <label class="form-check-label">%</label>
						                        </div>
						                        <div class="form-check" style="display: inline-block; margin-left: 5%;">
						                          <input class="form-check-input" type="radio" name="radio1" checked>
						                          <label class="form-check-label">원</label>
						                        </div>
				                  		</div>
					                </div>
				                </div>
				                
				                <!-- /.form-group -->
				                
				                <div class="col-sm-10">
					                <div class="form-group">
					                  <label>쿠폰 사용 최소금액</label>
					                  <input class="form-control" type="text" placeholder="쿠폰 사용 최소금액을 입력하세요.">
					                </div>
				                </div>
				                
				                <div class="col-sm-10">
					                <div class="form-group">
					                  <label>쿠폰 최대 할인금액</label>
					                  <input class="form-control" type="text" placeholder="쿠폰 최대 할인금액을 입력하세요.">
					                </div>
				                </div>
				                
				                <div class="col-sm-10">
					                <div class="form-group">
					                  <label>쿠폰 카테고리</label>
		        	          			<select class="form-control select2" style="width: 100%;" required>
					                    	<option selected="selected" style="display: none;"></option>
					                    	<option>전체</option>
					                    	<option>헤드폰</option>
					                    	<option>이어폰</option>
					                    	<option>스피커</option>
				                  		</select>
					                </div>
				                </div>
				                
				                <button type="button" class="btn btn-primary" style="float: right;">쿠폰 발행</button>
				              </div>
				              
				              <!-- 자동 발행 선택 시 div -->
				              	<div class="col-sm-6" id="issue-option" style="float: right; display: block;">
				              		<div class="col-sm-10">
					              		<div class="form-group">
					                  		<label>발급 조건</label>
				        	          		<select class="form-control select2" style="width: 100%;" required>
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
				              	<div class="col-sm-6" id="user-grade" style="float: right; display: none;">
				              		<div class="col-sm-10">
					              		<div class="form-group">
					                  		<label>발급 대상</label>
				        	          		<select class="form-control select2" style="width: 100%;">
				        	          			<option selected="selected" style="display: none;"></option>
							                    <option>전체</option>
							                    <option>Bronze</option>
							                    <option>Silver</option>
							                    <option>Gold</option>
							                    <option>VIP</option>
						                  	</select>
					                	</div>
				                	</div>
		                		</div>
		                		<!-- /관리자 발행 선택 시 div -->
		                		
				              <!-- /.col -->
				              
				        </div>
				              
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
	<script src="plugins/jquery/jquery.min.js"></script>
	<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="dist/js/adminlte.min.js"></script>
	<!-- Bootstrap 4 -->
	<!-- Select2 -->
	<script src="plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script src="plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script src="plugins/moment/moment.min.js"></script>
	<script src="plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- Date Range -->
	<script src="plugins/daterangepicker/daterangepicker.js"></script>
	
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
	  if (this.value === '관리자 발행') {
		  userOptionSection.style.display = 'none';
		  userGradeSection.style.display = 'block';
	  } else if (this.value === '자동 발행'){
		  userGradeSection.style.display = 'none';
		  userOptionSection.style.display = 'block';
	  } else{
		  userGradeSection.style.display = 'none';
		  userOptionSection.style.display = 'none';
	  }
	});
</script>
	<!-- jQuery -->

</body>
</html>
