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
					<div class="row">
						
							<!-- 발행 쿠폰 목록 -->
							<div class="col-sm-12">
								<div class="card">
					              <div class="card-header" style="display: flex; justify-content: space-between; align-items: center;">
					                <h3 class="card-title" style="margin-top: 0.6%;">(회원가입쿠폰)보유 회원 목록</h3>
					                <div class="input-group input-group-lg" style="width: 20%; margin-left: auto;">
									<input type="search" class="form-control form-control-lg" placeholder="이름 검색" value="" >
										<div class="input-group-append">
										<button type="submit" class="btn btn-lg btn-default">
										<i class="fa fa-search"></i>
										</button>
										</div>
									</div>
					                <!-- <button type="button" class="btn btn-primary" style="margin-left: 70%;">쿠폰 관리</button> -->
					              </div>
					              <!-- /.card-header -->
					              <div class="card-body" id="usedCoupon">
					                <table id="example3" class="table table-bordered table-hover">
					                  <thead>
					                  <tr>
					                    <th style="width: 8%;">번호</th>
					                    <th style="width: 10%;">발행일</th>
					                    <th style="width: 10%;">만료일</th>
					                    <th style="width: 10%;">사용자</th>
					                  </tr>
					                  </thead>
					                  <tbody>
					                  <tr>
					                    <td>1</td>
					                    <td>2024-03-11</td>
					                    <td>2024-03-15</td>
					                    <td>USER1</td>
					                  </tr>
					                  <tr>
					                    <td>2</td>
					                    <td>2024-03-11</td>
					                    <td>2024-03-15</td>
					                    <td>USER2</td>
					                  </tr>
					                  <tr>
					                    <td>3</td>
					                    <td>2024-03-11</td>
					                    <td>2024-03-15</td>
					                    <td>USER3</td>
					                  </tr>
					                  <tr>
					                    <td>4</td>
					                    <td>2024-03-11</td>
					                    <td>2024-03-15</td>
					                    <td>USER4</td>
					                  </tr>
					                   <tr>
					                    <td>5</td>
					                    <td>2024-03-11</td>
					                    <td>2024-03-15</td>
					                    <td>USER1</td>
					                  </tr>
					                  <tr>
					                    <td>6</td>
					                    <td>2024-03-01</td>
					                    <td>2024-03-15</td>
					                    <td>USER1</td>
					                  </tr>
					                  <tr>
					                    <td>7</td>
					                    <td>2024-03-02</td>
					                    <td>2024-03-15</td>
					                    <td>USER2</td>
					                  </tr>
					                  <tr>
					                    <td>8</td>
					                    <td>2024-03-11</td>
					                    <td>2024-03-15</td>
					                    <td>USER3</td>
					                  </tr>
					                  <tr>
					                    <td>9</td>
					                    <td>2024-01-01</td>
					                    <td>2024-03-15</td>
					                    <td>USER1</td>
					                  </tr>
					                   <tr>
					                    <td>10</td>
					                    <td>2024-01-02</td>
					                    <td>2024-03-15</td>
					                    <td>USER2</td>
					                  </tr>
					                   <tr>
					                    <td>11</td>
					                    <td>2024-01-03</td>
					                    <td>2024-03-15</td>
					                    <td>USER3</td>
					                  </tr>
					                </table>
					              </div>
					            </div>
							</div>
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
	<!-- DataTables  & Plugins -->
	<script src="resources/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="resources/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script src="resources/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script src="resources/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script src="resources/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script src="resources/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="resources/plugins/jszip/jszip.min.js"></script>
	<script src="resources/plugins/pdfmake/pdfmake.min.js"></script>
	<script src="resources/plugins/pdfmake/vfs_fonts.js"></script>
	<script src="resources/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script src="resources/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script src="resources/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
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
    	  {"orderable": false, "targets":[1,2]}
      ],
    });
  });
</script>

	<!-- jQuery -->


</body>
</html>
