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
		<jsp:include page="admin-sidebar.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">대시보드</h1>
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
						<div class="col-sm-8">
						
						<!-- 쇼핑몰 통계 -->
							<div class="card">
				              <div class="card-header">
				                <h3 class="card-title">분기별 매출 비교</h3>
				
				                <div class="card-tools">
				                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
				                    <i class="fas fa-minus"></i>
				                  </button>
				                  <button type="button" class="btn btn-tool" data-card-widget="remove">
				                    <i class="fas fa-times"></i>
				                  </button>
				                </div>
				              </div>
				              <div class="card-body">
				                <div class="chart">
				                  <canvas id="barChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
				                </div>
				              </div>
					              <!-- /.card-body -->
							</div>
							<!-- /쇼핑몰 통계 -->
						</div>
							
							<!-- 메인 화면 항목2 -->
						<div class="col-sm-4">
							<!-- Small boxes (Stat box) -->
				          	<div class="col-lg-12 col-12">
					            <!-- small card -->
					            <div class="small-box bg-info" style="height: 160px;">
					              <div class="inner">
					                <h3>${orderListData.orderListCountsToday}</h3>
					
					                <p>주문건수(일)</p>
					              </div>
					              <div class="icon">
					                <i class="fas fa-shopping-cart"></i>
					              </div>
					              <a href="#" class="small-box-footer" style="margin-top: 17px;">
					                More info <i class="fas fa-arrow-circle-right"></i>
					              </a>
					            </div>
			          		</div>
						<!-- /회원 통계 -->
							<div class="col-lg-12 col-12">
					            <!-- small card -->
					            <div class="small-box" style="background-color: #87CEFA; height: 160px;">
					              <div class="inner">
					                <h3>28</h3>
					
					                <p>전일 주문건수</p>
					              </div>
					              <div class="icon">
					                <i class="ion ion-stats-bars"></i>
					              </div>
					              <a href="#" class="small-box-footer" style="margin-top: 17px;">
					                More info <i class="fas fa-arrow-circle-right"></i>
					              </a>
					            </div>
							</div>	
							<!-- /메인 화면 항목2 -->
						</div>	
						
						<!-- 메인 화면 항목3 -->
						<div class="col-sm-8">
							<!-- LINE CHART -->
				            <div class="card">
				              <div class="card-header">
				                <h3 class="card-title">월간 매출</h3>
				
				                <div class="card-tools">
				                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
				                    <i class="fas fa-minus"></i>
				                  </button>
				                  <button type="button" class="btn btn-tool" data-card-widget="remove">
				                    <i class="fas fa-times"></i>
				                  </button>
				                </div>
				              </div>
				              <div class="card-body">
				                <div class="chart">
				                  <canvas id="lineChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
				                </div>
				              </div>
				              <!-- /.card-body -->
				            </div>
						</div>	
							<!-- /메인 화면 항목3 -->
							<!-- 메인 화면 항목4 -->
						<div class="col-sm-4">
							<!-- Small boxes (Stat box) -->
				          	<div class="col-lg-12 col-12">
					            <!-- small card -->
					            <div class="small-box bg-success" style="height: 160px;">
					              <div class="inner">
					                <h3>5320000원</h3>
					
					                <p>일 매출</p>
					              </div>
					              <div class="icon">
					                <i class="ion ion-stats-bars"></i>
					              </div>
					              <a href="#" class="small-box-footer" style="margin-top: 17px;">
					                More info <i class="fas fa-arrow-circle-right"></i>
					              </a>
					            </div>
			          		</div>
						<!-- /회원 통계 -->
							<div class="col-lg-12 col-12">
					            <!-- small card -->
					            <div class="small-box bg-warning" style="height: 160px;">
					              <div class="inner">
					                <h3>44</h3>
					
					                <p>총 회원 수</p>
					              </div>
					              <div class="icon">
					                <i class="fas fa-user-plus"></i>
					              </div>
					              <a href="#" class="small-box-footer" style="margin-top: 17px;">
					                More info <i class="fas fa-arrow-circle-right"></i>
					              </a>
					            </div>
							</div>	
							<!-- /메인 화면 항목4 -->
						</div>	
							<!-- /.card-body -->
						</div>
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
	<script src="resources/plugins/jquery/jquery.min.js"></script>
	<script src="resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/dist/js/adminlte.min.js"></script>
	<!-- Bootstrap 4 -->
	<!-- AdminLTE App -->
	<script src="resources/plugins/chart.js/Chart.min.js"></script>
	<script src="resources/plugins/sparklines/sparkline.js"></script>
	<script src="resources/plugins/jquery-knob/jquery.knob.min.js"></script>
	<!-- ChartJS -->
	<!-- DataTables  & resources/plugins -->
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
	<!-- 분기별 매출 금액 차트 -->
	<script>
	  $(function () {
	    var areaChartData = {
	      labels  : ['1분기', '2분기', '3분기', '4분기'],
	      datasets: [
	        {
	          label               : '2024년',
	          backgroundColor     : 'rgba(60,141,188,0.9)',
	          borderColor         : 'rgba(60,141,188,0.8)',
	          pointRadius          : false,
	          pointColor          : '#3b8bba',
	          pointStrokeColor    : 'rgba(60,141,188,1)',
	          pointHighlightFill  : '#fff',
	          pointHighlightStroke: 'rgba(60,141,188,1)',
	          data                : [6500000, 5900000, 8000000, 8100000]
	        },
	        {
	          label               : '2023년',
	          backgroundColor     : 'rgba(210, 214, 222, 1)',
	          borderColor         : 'rgba(210, 214, 222, 1)',
	          pointRadius         : false,
	          pointColor          : 'rgba(210, 214, 222, 1)',
	          pointStrokeColor    : '#c1c7d1',
	          pointHighlightFill  : '#fff',
	          pointHighlightStroke: 'rgba(220,220,220,1)',
	          data                : [2800000, 4800000, 4000000, 1900000]
	        },
	      ]
	    }
	
	    var areaChartOptions = {
	      maintainAspectRatio : false,
	      responsive : true,
	      legend: {
	        display: false
	      },
	      scales: {
	        xAxes: [{
	        	gridLines : {
	            display : false,
	          }
	        }],
	        yAxes: [{
	        	ticks : {
	          	  stepSize: 2000000, // y축 간격을 100만 설정
	          	  maxTicksLimit: 5,
	          	  min: 0, // y축 최소값
	              max: 10000000, // y축 최대값
	            },
	        	gridLines : {
	            display : false,
	          }
	        }]
	      }
	    }
	
	    //-------------
	    //- BAR CHART -
	    //-------------
	    var barChartCanvas = $('#barChart').get(0).getContext('2d')
	    var barChartData = $.extend(true, {}, areaChartData)
	    var temp0 = areaChartData.datasets[0]
	    var temp1 = areaChartData.datasets[1]
	    barChartData.datasets[0] = temp1
	    barChartData.datasets[1] = temp0
	
	    var barChartOptions = {
	    	      maintainAspectRatio : false,
	    	      responsive : true,
	    	      legend: {
	    	        display: false
	    	      },
	    	      scales: {
	    	        xAxes: [{
	    	        	gridLines : {
	    	            display : true, // 차트 가로줄 
	    	          }
	    	        }],
	    	        yAxes: [{
	    	        	ticks : {
	    	          	  stepSize: 2000000, // y축 간격을 200만 설정
	    	          	  maxTicksLimit: 6,
	    	          	  min: 0, // y축 최소값
	    	              max: 10000000, // y축 최대값
	    	            },
	    	        	gridLines : {
	    	            display : false, // 차트 세로줄
	    	          }
	    	        }]
	    	      }
	    	    }
	
	    new Chart(barChartCanvas, {
	      type: 'bar',
	      data: barChartData,
	      options: barChartOptions
	    })
	  })
	</script>

	<!-- 월 주문건수 차트 -->
	<script>
	  $(function () {
	    var areaChartData = {
	      labels  : ['1','2','3','4','5','6','7','8','9','10'],
	      datasets: [
	        {
	          label               : '2024년',
	          backgroundColor     : 'rgba(60,141,188,0.9)',
	          borderColor         : 'rgba(60,141,188,0.8)',
	          pointRadius          : false,
	          pointColor          : '#3b8bba',
	          pointStrokeColor    : 'rgba(60,141,188,1)',
	          pointHighlightFill  : '#fff',
	          pointHighlightStroke: 'rgba(60,141,188,1)',
	          data                : [102, 201, 303, 140, 128, 384, 289, 291, 330, 135]
	        },
	        {
	          label               : '2023년',
	          backgroundColor     : 'rgba(210, 214, 222, 1)',
	          borderColor         : 'rgba(210, 214, 222, 1)',
	          pointRadius         : false,
	          pointColor          : 'rgba(210, 214, 222, 1)',
	          pointStrokeColor    : '#c1c7d1',
	          pointHighlightFill  : '#fff',
	          pointHighlightStroke: 'rgba(220,220,220,1)',
	          data                : [212, 345, 159, 332, 492, 289, 442, 382, 253, 247]
	        },
	      ]
	    }
	
	    var areaChartOptions = {
	      maintainAspectRatio : false,
	      responsive : true,
	      legend: {
	        display: false
	      },
	      scales: {
	        xAxes: [{
	        	gridLines : {
	            display : false,
	          }
	        }],
	        yAxes: [{
	        	ticks : {
	          	  stepSize: 10, // y축 간격을 10 설정
	          	  maxTicksLimit: 6,
	          	  min: 0, // y축 최소값
	              max: 500, // y축 최대값
	            },
	        	gridLines : {
	            display : false,
	          }
	        }]
	      },
	      tooltips: {
              mode: 'index',
              intersect: false,
          }
	    }
	
	    //-------------
	    //- LINE CHART -
	    //--------------
	    var lineChartCanvas = $('#lineChart').get(0).getContext('2d')
	    var lineChartOptions = $.extend(true, {}, areaChartOptions)
	    var lineChartData = $.extend(true, {}, areaChartData)
	    lineChartData.datasets[0].fill = false;
	    lineChartData.datasets[1].fill = false;
	    lineChartOptions.datasetFill = false

	    var lineChart = new Chart(lineChartCanvas, {
	      type: 'line',
	      data: lineChartData,
	      options: lineChartOptions
	    })
	  })
	</script>


<!-- Page specific script -->
<script>
  $(function () {
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



	<!-- jQuery -->


</body>
</html>
