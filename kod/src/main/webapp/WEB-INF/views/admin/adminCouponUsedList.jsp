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
					<div class="row">
						
							<!-- 발행 쿠폰 목록 -->
							<div class="col-sm-12">
								<div class="card">
					              <div class="card-header" style="display: flex; justify-content: flex-start;">
					                <h3 class="card-title" style="margin-top: 0.6%;">쿠폰 목록</h3>
					                <select class="form-control select2" id="coupon-type" style="width: 15%; margin-left: 2%;">
					                    <option selected="selected">사용 쿠폰 목록</option>
					                    <option>미사용 쿠폰 목록</option>
					                  </select>
					                <!-- <button type="button" class="btn btn-primary" style="margin-left: 70%;">쿠폰 관리</button> -->
					              </div>
					              <!-- /.card-header -->
					              <div class="card-body" id="usedCoupon">
					                <table id="example3" class="table table-bordered table-hover">
					                  <thead>
					                  <tr>
					                    <th style="width: 8%;">번호</th>
					                    <th style="width: 14%;">쿠폰명</th>
					                    <th style="width: *;">쿠폰 코드</th>
					                    <th style="width: *;">쿠폰 설명</th>
					                    <th style="width: 8%;">할인율</th>
					                    <th style="width: 10%;">발행일</th>
					                    <th style="width: 10%;">사용자</th>
					                    <th style="width: 10%;">사용일</th>
					                  </tr>
					                  </thead>
					                  <tbody>
					                  <tr>
					                    <td>1</td>
					                    <td>회원가입축하</td>
					                    <td>NEWPERSON</td>
					                    <td>회원가입을 축하합니다. 신규회원 10%할인 쿠폰을 드려요</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER1</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                  <tr>
					                    <td>2</td>
					                    <td>회원가입축하</td>
					                    <td>NEWPERSON</td>
					                    <td>회원가입을 축하합니다. 신규회원 10%할인 쿠폰을 드려요</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER2</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                  <tr>
					                    <td>3</td>
					                    <td>회원가입축하</td>
					                    <td>NEWPERSON</td>
					                    <td>회원가입을 축하합니다. 신규회원 10%할인 쿠폰을 드려요</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER3</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                  <tr>
					                    <td>4</td>
					                    <td>회원가입축하</td>
					                    <td>NEWPERSON</td>
					                    <td>회원가입을 축하합니다. 신규회원 10%할인 쿠폰을 드려요</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER4</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                   <tr>
					                    <td>5</td>
					                    <td>생일축하</td>
					                    <td>HBD</td>
					                    <td>생일을 축하합니다. 생일 20%할인 쿠폰을 드려요</td>
					                    <td>20%</td>
					                    <td>2024-03-11</td>
					                    <td>USER1</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                  <tr>
					                    <td>6</td>
					                    <td>골드등급쿠폰</td>
					                    <td>GOLDGRADE</td>
					                    <td>골드등급 회원 축하</td>
					                    <td>10%</td>
					                    <td>2024-03-01</td>
					                    <td>USER1</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                  <tr>
					                    <td>7</td>
					                    <td>골드등급쿠폰</td>
					                    <td>GOLDGRADE</td>
					                    <td>골드등급 회원 축하</td>
					                    <td>10%</td>
					                    <td>2024-03-02</td>
					                    <td>USER2</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                  <tr>
					                    <td>8</td>
					                    <td>골드등급쿠폰</td>
					                    <td>GOLDGRADE</td>
					                    <td>골드등급 회원 축하</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER3</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                  <tr>
					                    <td>9</td>
					                    <td>프로모션쿠폰</td>
					                    <td>HAPPYNEWYEAR2024</td>
					                    <td>신년맞이 프로모션 쿠폰</td>
					                    <td>40%</td>
					                    <td>2024-01-01</td>
					                    <td>USER1</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                   <tr>
					                    <td>10</td>
					                    <td>프로모션쿠폰</td>
					                    <td>HAPPYNEWYEAR2024</td>
					                    <td>신년맞이 프로모션 쿠폰</td>
					                    <td>40%</td>
					                    <td>2024-01-02</td>
					                    <td>USER2</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                   <tr>
					                    <td>11</td>
					                    <td>프로모션쿠폰</td>
					                    <td>HAPPYNEWYEAR2024</td>
					                    <td>신년맞이 프로모션 쿠폰</td>
					                    <td>40%</td>
					                    <td>2024-01-03</td>
					                    <td>USER3</td>
					                    <td>2024-03-15</td>
					                  </tr>
					                </table>
					              </div>
					              <!-- /.card-body -->
					              
					              <!-- 미사용 쿠폰 테이블 -->
					             
					              <div class="card-body" id="unusedCoupon-table" style="display: none;">
					                <table id="example4" class="table table-bordered table-hover">
					                  <thead>
					                  <tr>
					                    <th style="width: 8%;">번호</th>
					                    <th style="width: 14%;">쿠폰명</th>
					                    <th style="width: *%;">쿠폰 코드</th>
					                    <th style="width: *%;">쿠폰 설명</th>
					                    <th style="width: 10%;">할인율</th>
					                    <th style="width: 10%;">발행일</th>
					                    <th style="width: 10%;">사용자</th>
					                    <th style="width: 10%;">만료일</th>
					                  </tr>
					                  </thead>
					                  <tbody>
					                  <tr>
					                    <td>1</td>
					                    <td>회원가입축하</td>
					                    <td>NEWPERSON</td>
					                    <td>회원가입을 축하합니다. 신규회원 10%할인 쿠폰을 드려요</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER1</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                  <tr>
					                    <td>2</td>
					                    <td>회원가입축하</td>
					                    <td>NEWPERSON</td>
					                    <td>회원가입을 축하합니다. 신규회원 10%할인 쿠폰을 드려요</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER2</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                  <tr>
					                    <td>3</td>
					                    <td>회원가입축하</td>
					                    <td>NEWPERSON</td>
					                    <td>회원가입을 축하합니다. 신규회원 10%할인 쿠폰을 드려요</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER3</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                  <tr>
					                    <td>4</td>
					                    <td>회원가입축하</td>
					                    <td>NEWPERSON</td>
					                    <td>회원가입을 축하합니다. 신규회원 10%할인 쿠폰을 드려요</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER4</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                   <tr>
					                    <td>5</td>
					                    <td>생일축하</td>
					                    <td>HBD</td>
					                    <td>생일을 축하합니다. 생일 20%할인 쿠폰을 드려요</td>
					                    <td>20%</td>
					                    <td>2024-03-11</td>
					                    <td>USER1</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                  <tr>
					                    <td>6</td>
					                    <td>골드등급쿠폰</td>
					                    <td>GOLDGRADE</td>
					                    <td>골드등급 회원 축하</td>
					                    <td>10%</td>
					                    <td>2024-03-01</td>
					                    <td>USER1</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                  <tr>
					                    <td>7</td>
					                    <td>골드등급쿠폰</td>
					                    <td>GOLDGRADE</td>
					                    <td>골드등급 회원 축하</td>
					                    <td>10%</td>
					                    <td>2024-03-02</td>
					                    <td>USER2</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                  <tr>
					                    <td>8</td>
					                    <td>골드등급쿠폰</td>
					                    <td>GOLDGRADE</td>
					                    <td>골드등급 회원 축하</td>
					                    <td>10%</td>
					                    <td>2024-03-11</td>
					                    <td>USER3</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                  <tr>
					                    <td>9</td>
					                    <td>프로모션쿠폰</td>
					                    <td>HAPPYNEWYEAR2024</td>
					                    <td>신년맞이 프로모션 쿠폰</td>
					                    <td>40%</td>
					                    <td>2024-01-01</td>
					                    <td>USER1</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                   <tr>
					                    <td>10</td>
					                    <td>프로모션쿠폰</td>
					                    <td>HAPPYNEWYEAR2024</td>
					                    <td>신년맞이 프로모션 쿠폰</td>
					                    <td>40%</td>
					                    <td>2024-01-02</td>
					                    <td>USER2</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                   <tr>
					                    <td>11</td>
					                    <td>프로모션쿠폰</td>
					                    <td>HAPPYNEWYEAR2024</td>
					                    <td>신년맞이 프로모션 쿠폰</td>
					                    <td>40%</td>
					                    <td>2024-01-03</td>
					                    <td>USER3</td>
					                    <td>2024-03-11</td>
					                  </tr>
					                </table>
					              </div>
					              <!-- /미사용 쿠폰 테이블 -->
					            </div>
							<!-- /발행 쿠폰 목록 -->
							<!-- /.card-body -->
						
						<!-- /.card -->
					</div>
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
	<!-- DataTables  & Plugins -->
	<script src="plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script src="plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script src="plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script src="plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script src="plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="plugins/jszip/jszip.min.js"></script>
	<script src="plugins/pdfmake/pdfmake.min.js"></script>
	<script src="plugins/pdfmake/vfs_fonts.js"></script>
	<script src="plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script src="plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script src="plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
	
<!-- 쿠폰 목록 js -->
<!-- Page specific script -->
<script>
  $(function () {
    $('#example4').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false,
      "responsive": true,
      "columnDefs": [
    	  {"orderable": false, "targets":[1,2,3,4,5,7]} // target은 0부터 시작, 1,2,3(아이디, 이름, 전화번호)는 정렬에서 제외
      ],
    });
  });
</script>
<!-- 쿠폰 목록 js -->
<!-- Page specific script -->
<script>
  $(function () {
    $('#example3').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false,
      "responsive": true,
      "columnDefs": [
    	  {"orderable": false, "targets":[2,3,4]} // target은 0부터 시작, 1,2,3(아이디, 이름, 전화번호)는 정렬에서 제외
      ],
    });
  });
</script>

<script>
document.getElementById('coupon-type').addEventListener('change', function() {
	  var usedSection = document.getElementById('usedCoupon');
	  var unusedSection = document.getElementById('unusedCoupon-table');
	  if (this.value === '사용 쿠폰 목록') {
		  unusedSection.style.display = 'none';
		  usedSection.style.display = 'block';
	  } else if (this.value === '미사용 쿠폰 목록'){
		  usedSection.style.display = 'none';
		  unusedSection.style.display = 'block';
	  } else{
		  usedSection.style.display = 'none';
		  unusedSection.style.display = 'none';
	  }
	});
</script>

	<!-- jQuery -->


</body>
</html>
