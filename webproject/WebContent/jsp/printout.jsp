<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%= session.getAttribute("userid") %>
<%= session.getAttribute("pss") %>
<%= session.getAttribute("ps1") %>
<%= session.getAttribute("ps2") %>
<%= session.getAttribute("ps3") %>
</body>
</html>