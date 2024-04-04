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
		<jsp:include page="/WEB-INF/views/admin/admin-sidebar.jsp"></jsp:include>

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
								<form action="/updateProduct" method="POST" id="myDropzone" enctype="multipart/form-data">
								<div class="card-header" style="display: flex; justify-content: space-between;">
									<h3 class="card-title" style="margin-top: 0.6%;">상품 상세 정보 / 수정 </h3>
									<button type="submit" class="btn btn-primary" style="margin-left: auto;">수정하기</button>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
										<table id="example3" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th style="width: 20%">상품번호</th>
													<td><input class="form-control form-control-sm col-10" type="text" name="productID" style="margin: auto;" value="${productDatas.productID}" readonly></td>
												</tr>
												<tr>
													<th style="width: 20%">상품명</th>
													<td><input class="form-control form-control-sm col-10" type="text" name="productName" style="margin: auto;" value="${productDatas.productName}"></td>
												</tr>
												<tr>
													<th style="width: 20%">브랜드</th>
													<td>
														<select class="form-control form-control-sm col-10 select2" name="productBrand" style="margin : auto;" readonly>
									                    	<option value="뱅앤올룹슨" selected>뱅앤올룹슨</option>
								                  		</select>
													</td>
												</tr>
												<tr>
													<th style="width: 20%">카테고리</th>
													<td>
														<select class="form-control form-control-sm col-10 select2" style="margin : auto;" id="productCategory" name="productCategory" style="width: 100%;">
								                    	<c:forEach items="${categoryDatas}" var="category">
								                    		<option value="${category.categoryID}">${category.categoryType}</option>
							                  			</c:forEach>
							                  		</select>
													</td>
												</tr>
												<tr>
													<th style="width: 20%">가격</th>
													<td><input class="form-control form-control-sm col-10" type="text" name="productPrice" style="display:inline-block; margin : auto;" value="${productDatas.productPrice}"></td>
												</tr>
												<tr>
													<th style="width: 20%">재고</th>
													<td><input class="form-control form-control-sm col-10" type="text" name="productStock" style="display:inline-block; margion : auto;" value="${productDatas.productStock}"></td>
												</tr>
												<tr>
													<th style="width: 20%; vertical-align: middle; ">상품 정보</th>
													<td>
														<div class="card-body">
										              		<textarea name="productInfo" class="form-control" rows="5" cols="30">${productDatas.productInfo}
										              		</textarea>
										            	</div>
										            </td>
												</tr>
												<tr>
													<th>상품 이미지</th>
													<td>
														<!-- <div class="input-group">
															<div class="custom-file">
																<input type="file" class="file-input" id="exampleInputFile">
															</div>
														</div> -->
														
														<label for="imageUpload" class="imageUploadBtn">이미지 업로드<br />
															<input type="file" multiple id="image" name="productImageList" accept="image/*" onchange="setThumbnail(event)" /> 
															<!-- <img id="imagePreview" src="resources/img/imagePreview.png" alt="미리 보기 이미지" style="width: 40px; height: 40px;"> -->
														</label>
															<div id="image_container">
																<c:forEach items="${imageDatas}" var="image">
																	<img src="${image.imageUrl}">
																</c:forEach>
															</div>
														<button type="button" id="cancelImageButton" onclick="cancelImageUpload()">이미지 취소</button>
													</td>
												</tr>
											</thead>
										</table>
										</div>
									</form>
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
	
	<script src="resources/js/productInsert.js"></script>
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
	
	
	<!-- jQuery -->

</body>
</html>
