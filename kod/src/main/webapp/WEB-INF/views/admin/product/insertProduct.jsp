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
<!-- summernote -->
<link rel="stylesheet" href="resources/plugins/summernote/summernote-bs4.min.css">
<!-- dropzonejs -->
  <link rel="stylesheet" href="resources/plugins/dropzone/min/dropzone.min.css">
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
						<!-- 상품 등록 -->
						<div class="col-sm-12">
						<form action="/insertProduct" method="POST" id="myDropzone" enctype="multipart/form-data">
							<div class="card">
								<div class="card-header" style="display: flex; justify-content: space-between;">
									<h3 class="card-title" style="margin-top: 0.6%;">신규 상품 등록</h3>
									<button type="submit" class="btn btn-primary" style="margin-left: auto;" onclick="submitForm()">등록하기</button>
								</div>
								<div class="card-body">
									<table id="example3" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th style="width: 20%">상품명</th>
												<td><input class="form-control form-control-sm col-6" name="productName" type="text"></td>
											</tr>
											<tr>
												<th style="width: 20%">브랜드</th>
												<td><input class="form-control form-control-sm col-6" name="productBrand" type="text" value="뱅앤올룹슨" readonly></td>
											</tr>
											<tr>
												<th style="width: 20%">카테고리</th>
												<td>
													<select class="form-control form-control-sm col-6 select2" name="productCategory" required>
								                    	<option selected value="999">전체</option>
								                    	<option value="1">헤드폰</option>
								                    	<option value="2">이어폰</option>
								                    	<option value="3">스피커</option>
							                  		</select>
							                  		
												</td>
											</tr>
											<tr>
												<th style="width: 20%">가격</th>
												<td><input class="form-control form-control-sm col-6" name="productPrice" type="text"></td>
											</tr>
											<tr>
												<th style="width: 20%">재고</th>
												<td><input class="form-control form-control-sm col-6" name="productStock" type="text"></td>
											</tr>
											<tr>
												<th style="width: 20%; vertical-align: middle;" >상품 정보</th>
												<td>
													<div class="card-body" style="padding: 0%;">
									              		<textarea class="form-control" name="productInfo"></textarea>
									            	</div>
									            </td>
											</tr>
											<tr>
												<th>상품 이미지</th>
												<td>
													<label for="imageUpload" class="imageUploadBtn">이미지 업로드<br />
														<input type="file" multiple id="image" name="productImageList" accept="image/*" onchange="setThumbnail(event)" /> 
														<!-- <img id="imagePreview" src="resources/img/imagePreview.png" alt="미리 보기 이미지" style="width: 40px; height: 40px;"> -->
													</label>
														<div id="image_container"></div>
													<button type="button" id="cancelImageButton" style="display: none;" onclick="cancelImageUpload()">이미지 취소</button>
												
													<!-- <div class="input-group">
														<div class="">
															<input type="file" class="file-input" id="exampleInputFile" multiple>
														</div>
													</div> -->
													<!-- <div class="card-body">
										                <div id="actions" class="row">
										                  <div class="col-lg-6">
										                    <div class="btn-group w-100">
										                      <span class="btn btn-success col fileinput-button">
										                        <i class="fas fa-plus"></i>
										                        <span>Add files</span>
										                      </span>
										                      <button type="submit" class="btn btn-primary col start">
										                        <i class="fas fa-upload"></i>
										                        <span>Start upload</span>
										                      </button>
										                    </div>
										                  </div>
										                </div>
										                <div class="table table-striped files" id="previews">
										                  <div id="template" class="row mt-2">
										                    <div class="col-auto">
										                        <span class="preview"><img src="data:," alt="" data-dz-thumbnail /></span>
										                    </div>
										                    <div class="col d-flex align-items-center">
										                        <p class="mb-0">
										                          <span class="lead" data-dz-name></span>
										                          (<span data-dz-size></span>)
										                        </p>
										                        <strong class="error text-danger" data-dz-errormessage></strong>
										                    </div>
										                    <div class="col-auto d-flex align-items-center">
										                      <div class="btn-group">
										                        <button class="btn btn-primary start">
										                          <i class="fas fa-upload"></i>
										                          <span>Start</span>
										                        </button>
										                        <button data-dz-remove class="btn btn-warning cancel">
										                          <i class="fas fa-times-circle"></i>
										                          <span>Cancel</span>
										                        </button>
										                        <button data-dz-remove class="btn btn-danger delete">
										                          <i class="fas fa-trash"></i>
										                          <span>Delete</span>
										                        </button>
										                      </div>
										                    </div>
										                  </div>
										                </div>
										              </div> -->
												</td>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</form>
						</div>
						<!-- /상품 등록 -->
						
						
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
	<!-- Summernote -->
	<script src="resources/plugins/summernote/summernote-bs4.min.js"></script>
	<!-- bs-custom-file-input -->
	<script src="resources/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
	<!-- dropzonejs -->
	<script src="resources/plugins/dropzone/min/dropzone.min.js"></script>
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
					"targets" : [ 0,3,4,6,7,8 ]
				} 
				],
			});
		});
	</script>
	<!-- editor -->
	<script>
	  $(function () {
	    // Summernote
	    $('#summernote').summernote()
	
	    // CodeMirror
	    CodeMirror.fromTextArea(document.getElementById("codeMirrorDemo"), {
	      mode: "htmlmixed",
	      theme: "monokai"
	    });
	  })
	</script>
	
	<script>
	$(function () {
	  bsCustomFileInput.init();
	});
	</script>
	
	<script>
	    
	  // DropzoneJS Demo Code Start
	  Dropzone.autoDiscover = false
	
	  // Get the template HTML and remove it from the doumenthe template HTML and remove it from the doument
	  var previewNode = document.querySelector("#template")
	  previewNode.id = ""
	  var previewTemplate = previewNode.parentNode.innerHTML
	  previewNode.parentNode.removeChild(previewNode)
	
	  var myDropzone = new Dropzone("#myDropzone", { // Make the whole body a dropzone
	    url: "/target-url", // Set the url
	    thumbnailWidth: 80,
	    thumbnailHeight: 80,
	    parallelUploads: 20,
	    previewTemplate: previewTemplate,
	    autoQueue: false, // Make sure the files aren't queued until manually added
	    previewsContainer: "#previews", // Define the container to display the previews
	    clickable: ".fileinput-button" // Define the element that should be used as click trigger to select files.
	  })
		
	  myDropzone.on("addedfile", function(file) {
	    // Hookup the start button
	    file.previewElement.querySelector(".start").onclick = function() { myDropzone.enqueueFile(file) }
	  })
	
	
	  // Setup the buttons for all transfers
	  // The "add files" button doesn't need to be setup because the config
	  // `clickable` has already been specified.
	  document.querySelector("#actions .start").onclick = function() {
	    myDropzone.enqueueFiles(myDropzone.getFilesWithStatus(Dropzone.ADDED))
	  }
	  document.querySelector("#actions .cancel").onclick = function() {
	    myDropzone.removeAllFiles(true)
	  }
	  // DropzoneJS Demo Code End
	</script>
	
	<script>
	function submitForm() {
	    var form = document.getElementById("myDropzone");
	    form.submit();
	}
	</script>
	<!-- jQuery -->

</body>
</html>
