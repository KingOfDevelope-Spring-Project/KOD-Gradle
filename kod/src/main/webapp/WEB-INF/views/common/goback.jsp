<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뒤로가기</title>
</head>
<body>

<script>
	alert('${msg}');
	history.go(-1);
</script>

</body>
</html>
