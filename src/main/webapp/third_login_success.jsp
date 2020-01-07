<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<c:set var="cp" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<script type="text/javascript" src="${cp}/js/jquery-1.7.2.min.js"></script>
<title>oauth2</title>
<style>
	body{ text-align:center} 
	#divcss5{margin:0 auto;border:0px solid #000;width:300px;height:120px} 
</style>
<script>
	

</script>


</head>
<body>
	<div id="divcss5">
		<h1>您好！${username}<% String username=(String)request.getAttribute("username"); %></h1>
	</div>
</body>
</html>