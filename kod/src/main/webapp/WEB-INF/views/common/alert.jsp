<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림창</title>
</head>
<body>


<div id="test" style="display: none;">${msg}</div>

<script>
//window.onload(()=>{
var msg = document.getElementById('test').textContent;
			console.log(msg);
	alert(msg);
	location.href='/';
//})
</script>

</body>
</html>
