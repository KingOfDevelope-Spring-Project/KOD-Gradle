<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
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
							<h1 class="m-0">매출 현황</h1>
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
						<!-- Small boxes (Stat box) -->
				          <div class="col-lg-3 col-6">
				            <!-- small box -->
				            <div class="small-box bg-info">
				              <div class="inner">
				                <h3>150</h3>
				
				                <p>주문 건수(월)</p>
				              </div>
				            </div>
				          </div>
				          <!-- ./col -->
				          <div class="col-lg-3 col-6">
				            <!-- small box -->
				            <div class="small-box" style="background-color: #20c997;">
				              <div class="inner">
				                <h3>732</h3>
				
				                <p>주문 건수(연)</p>
				              </div>
				            </div>
				          </div>
				          <!-- ./col -->
				          <div class="col-lg-3 col-6">
				            <!-- small box -->
				            <div class="small-box bg-success">
				              <div class="inner">
				                <h3>532000원</h3>
				
				                <p>월 매출</p>
				              </div>
				            </div>
				          </div>
				          <!-- ./col -->
				          <div class="col-lg-3 col-6">
				            <!-- small box -->
				            <div class="small-box bg-warning">
				              <div class="inner">
				                <h3>83210000원</h3>
				
				                <p>연 매출</p>
				              </div>
				            </div>
				          </div>
				          <!-- ./col -->
				        <!-- /.row -->
						<!-- 발행 쿠폰 목록 -->
						<div class="col-sm-12">
							<div class="card">
								<div class="card-header" style="display: flex; justify-content: space-between;">
									<h3 class="card-title" style="margin-top: 0.6%;">2024년도 매출</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<table id="example3" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th width="20%;">분기</th>
												<th>상품 금액</th>
												<th>판매 수량</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>40000000원</td>
												<td>150건</td>
											</tr>
											<tr id="hidden-table" style="display: none;">
												<td colspan="3">
									              <table class="table table-bordered table-hover" id="hidden1" >
														<thead>
															<tr>
																<th>순위</th>
																<th>상품명</th>
																<th>상품가격</th>
																<th>판매수량</th>
																<th>매출액</th>
															</tr>
														</thead>
														<tbody style="white-space: nowrap;">
															<tr>
																<td>1</td>
																<td>Beoplay EX Ferrari Edition</td>
																<td>649000원</td>
																<td>12</td>
																<td>1230000원</td>
															</tr>
															<tr>
																<td>2</td>
																<td>Beoplay EX</td>
																<td>498000원</td>
																<td>24</td>
																<td>152000원</td>
															</tr>
															<tr>
																<td>3</td>
																<td>Beoplay H95 Ferrari Edition</td>
																<td>189900원</td>
																<td>32</td>
																<td>2321000원</td>
															</tr>
															<tr>
																<td>4</td>
																<td>Beoplay H95</td>
																<td>14480원</td>
																<td>27</td>
																<td>1234000원</td>
															</tr>
															<tr>
																<td>5</td>
																<td>Beoplay HX</td>
																<td>678000원</td>
																<td>17</td>
																<td>221000원</td>
															</tr>
															<tr>
																<td>6</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>7</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>8</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>9</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>10</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>11</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>12</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>13</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>14</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>15</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>16</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>17</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td>2</td>
												<td>30000000원</td>
												<td>125건</td>
											</tr>
											<tr id="hidden-table2" style="display: none; ">
												<td colspan="3">
									              <table class="table table-bordered table-hover" id="hidden2">
														<thead>
															<tr>
																<th>순위</th>
																<th>상품명</th>
																<th>상품가격</th>
																<th>판매수량</th>
																<th>매출액</th>
															</tr>
														</thead>
														<tbody style=" overflow: scroll; height: 100px; white-space: nowrap;">
															<tr>
																<td>1</td>
																<td>Beoplay EX Ferrari Edition</td>
																<td>649000원</td>
																<td>12</td>
																<td>1230000원</td>
															</tr>
															<tr>
																<td>2</td>
																<td>Beoplay EX</td>
																<td>498000원</td>
																<td>24</td>
																<td>152000원</td>
															</tr>
															<tr>
																<td>3</td>
																<td>Beoplay H95 Ferrari Edition</td>
																<td>189900원</td>
																<td>32</td>
																<td>2321000원</td>
															</tr>
															<tr>
																<td>4</td>
																<td>Beoplay H95</td>
																<td>14480원</td>
																<td>27</td>
																<td>1234000원</td>
															</tr>
															<tr>
																<td>5</td>
																<td>Beoplay HX</td>
																<td>678000원</td>
																<td>17</td>
																<td>221000원</td>
															</tr>
															<tr>
																<td>6</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>7</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>8</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>9</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>10</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>11</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>12</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>13</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>14</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>15</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>16</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
															<tr>
																<td>17</td>
																<td>Beoplay Portal Beoplay Portal</td>
																<td>679000원</td>
																<td>19</td>
																<td>251000원</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td>3</td>
												<td>46500000원</td>
												<td>162건</td>
											</tr>
											<tr>
												<td>4</td>
												<td>13200000원</td>
												<td>321건</td>
											</tr>
									</table>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
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
		<strong>Copyright &copy; 2023-2024 <a href="https://adminlte.io">KOD</a>.
		</strong> All rights reserved.
	</footer>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED SCRIPTS -->
	<script src="resources/plugins/jquery/jquery.min.js"></script>
	<script src="resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/dist/js/adminlte.min.js"></script>
	<script src="resources/dist/js/demo.js"></script>
	<!-- Bootstrap 4 -->
	<!-- AdminLTE App -->
	<script src="resources/plugins/chart.js/Chart.min.js"></script>
	<script src="resources/plugins/sparklines/sparkline.js"></script>
	<script src="resources/plugins/jquery-knob/jquery.knob.min.js"></script>
	<!-- ChartJS -->
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
	<script>
    document.addEventListener("DOMContentLoaded", function() {
        // 첫 번째 테이블의 모든 행을 가져옵니다.
        var rows = document.querySelectorAll("#example3 tbody tr");

        // 각 행에 클릭 이벤트 리스너를 추가합니다.
        rows.forEach(function(row) {
            row.addEventListener("click", function() {
                // 두 번째 테이블의 가시성을 토글합니다.
                	var hiddenTable = document.getElementById("hidden-table");
                    var hiddenTable2 = document.getElementById("hidden-table2");
                    hiddenTable.style.display = (hiddenTable.style.display === "none") ? "table-row" : "none";
                    hiddenTable2.style.display = (hiddenTable2.style.display === "none") ? "table-row" : "none";
                
            });
        });
    });
</script>

	<!-- 쿠폰 목록 js -->
	<!-- Page specific script -->
	<script>
  $(function () {
    /* $("#example1").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)'); */
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false,
      "responsive": true,
      "columnDefs": [
    	  {"orderable": false, "targets":[1,2,3]} // target은 0부터 시작, 1,2,3(아이디, 이름, 전화번호)는 정렬에서 제외
      ],
    });
  });
</script>
	<!-- 쿠폰 목록 js -->
	<!-- Page specific script -->
	<script>
  $(function () {
    /* $("#example1").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)'); */
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

	<!-- jQuery -->


</body>
</html>
