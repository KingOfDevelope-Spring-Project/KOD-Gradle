<%@page import="model.dto.WishListDTO"%>
<%@page import="java.util.ArrayList"%>
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
<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/adminlte.min.css">
<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet" href="plugins/datatables-buttons/css/buttons.bootstrap4.min.css">

<style>
	table th,td{
		text-align: center;
		vertical-align: middle;
	}
</style>
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
							<h1 class="m-0">상품 관리</h1>
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
						
						<!-- 상품 목록 테이블 -->
						<div class="col-sm-12">
							<div class="card">
								<div class="card-header" style="display: flex; justify-content: space-between;">
									<h3 class="card-title" style="margin-top: 0.6%;">상품 목록</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									
									<!-- 나중에 실제 사용할 테이블 -->
									<%-- <table id="example2" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th><input type="checkbox" onmouseup=""></th>
												<th>번호</th>
												<th>상품번호</th>
												<th>상품 이미지</th>
												<th>상품명</th>
												<th>상품가격</th>
												<th>브랜드</th>
												<th>카테고리</th>
												<th>상품정보</th>
												<th>재고</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${productList}" var="product" varStatus="i" begin="0" step="1">
											<tr>
												<td><input type="checkbox"></td>
												<td>${i.count}</td>
												<td>${product.productID}</td>
												<td>${img.imageURL}</td>
												<td>${product.productName}</td>
												<td>${product.productPrice}</td>
												<td>${product.productBrand}</td>
												<td>${product.productCategory}</td>
												<td>${product.productInfo}</td>
												<td>${product.productStock}</td>
											</tr>
											</c:forEach>
										</tbody>
									</table> --%>
									<!-- /나중에 실제 사용할 테이블 -->
									
									<!-- 테스트용 -->
									<table id="example2" class="table table-bordered table-hover">
										<thead>
											<tr onclick="openModal()">
												<th>번호</th>
												<th>상품번호</th>
												<th>상품 이미지</th>
												<th>상품명</th>
												<th>상품가격</th>
												<th>브랜드</th>
												<th>카테고리</th>
												<th>재고</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td align="center">1</td>
												<td>1001</td>
												<td><img alt="상품1" width="100" height="100" src="//images.ctfassets.net/8cd2csgvqd3m/6CauM4itfCytfs4vYrSOVB/87fd4cbbacb842da0c4781bc558d4fdd/07_Beoplay_EX_Ferrari_8_Full_Angular_5K.png?q=85&fm=png&w=375&h=375&fit=fill"></td>
												<td>Beoplay EX Ferrari Edition</td>
												<td>649000</td>
												<td>Bang&Olufsen</td>
												<td>헤드폰</td>
												<td>100</td>
											</tr>
											<tr>
												<td>2</td>
												<td>1002</td>
												<td><img alt="상품2" width="100" height="100" src="//images.ctfassets.net/8cd2csgvqd3m/7AdyFHvn7QPcZ9aMbfEewY/033578369b617f673a919aa98ff4b4db/Beoplay-EX-Black-Anthracite-Hero.png?q=85&fm=png&w=375&h=375&fit=fill"></td>
												<td>Beoplay EX Beoplay EX</td>
												<td>498000</td>
												<td>Bang&Olufsen</td>
												<td>이어폰</td>
												<td>100</td>
											</tr>
										</tbody>
									</table>
									<!-- /테스트용 -->
									
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						<!-- /상품 목록 테이블 -->
						
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
		<strong>Copyright &copy; 2023-2024 <a href="https://adminlte.io">KOD</a>.
		</strong> All rights reserved.
	</footer>
	</div>
	<!-- ./wrapper -->

	<div class="modal fade" id="modal-lg">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">상세 정보</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <table id="example3" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 20%">상품번호</th>
							<td><input class="form-control form-control-sm col-10" type="text" style="margin: auto;" value="1001" readonly></td>
						</tr>
						<tr>
							<th style="width: 20%">상품명</th>
							<td><input class="form-control form-control-sm col-10" type="text" style="margin: auto;" value="Beoplay EX Ferrari Edition"></td>
						</tr>
						<tr>
							<th style="width: 20%">브랜드</th>
							<td>
								<select class="form-control form-control-sm col-10 select2" style="margin : auto;" disabled>
			                    	<option selected>뱅앤올룹슨</option>
			                    	<option>삼성</option>
			                    	<option>LG</option>
		                  		</select>
							</td>
						</tr>
						<tr>
							<th style="width: 20%">카테고리</th>
							<td>
								<select class="form-control form-control-sm col-10 select2" style="margin : auto;" required>
			                    	<option>전체</option>
			                    	<option selected>헤드폰</option>
			                    	<option>이어폰</option>
			                    	<option>스피커</option>
		                  		</select>
							</td>
						</tr>
						<tr>
							<th style="width: 20%">가격</th>
							<td><input class="form-control form-control-sm col-10" type="text" style="display:inline-block; margin : auto;" value="649000"></td>
						</tr>
						<tr>
							<th style="width: 20%">재고</th>
							<td><input class="form-control form-control-sm col-10" type="text" style="display:inline-block; margion : auto;" value="100"></td>
						</tr>
						<tr>
							<th style="width: 20%; vertical-align: middle; ">상품 정보</th>
							<td>
								<div class="card-body">
				              		<textarea class="form-control" rows="5" cols="30">페라리 레드 패키지의 아름다운 디자인만큼 뛰어난 다양성을 지닌 이어버드는 무선 충전 케이스와 액티브 노이즈 캔슬링 기능을 갖추고 있으며 깊고 풍부한 사운드와 편안한 착용감을 제공합니다.
				              		</textarea>
				            	</div>
				            </td>
						</tr>
						<tr>
							<th>상품 이미지</th>
							<td>
								<div class="input-group">
									<div class="custom-file">
										<input type="file" class="file-input" id="exampleInputFile">
									</div>
								</div>
							</td>
						</tr>
					</thead>
				</table>
            </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
              <button type="button" class="btn btn-primary">수정</button>
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
      <!-- /.modal -->

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
	<!-- Page specific script -->
	<script>
		$(function() {
			$('#example2').DataTable({
				"paging" : true,
				"lengthChange" : false,
				"searching" : false,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false,
				"responsive" : true,
				"columnDefs" : [ {
					"orderable" : false,
					"targets" : [2,3,5,6]
				} 
				],
			});
		});
	</script>
	
	<script>
	function openModal() {
        $('#modal-lg').modal('show');
    }
    $(document).ready(function() {
        $('tr').click(function() {
            openModal();
        });
    });
	</script>
	<!-- jQuery -->

</body>
</html>
