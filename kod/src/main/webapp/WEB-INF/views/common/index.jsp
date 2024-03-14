<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- [김진영] jstl로 index에서 main으로 바로 요청할 수 있도록 작성 --%>
<%-- <c:redirect url="/main.do" context="/kod"></c:redirect> --%>
<%
   response.sendRedirect("main.do");
%>
