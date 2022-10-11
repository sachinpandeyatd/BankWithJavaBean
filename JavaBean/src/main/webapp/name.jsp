<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Name</title>
</head>
<body>
	<jsp:useBean id="nameBean" class="infinite.JavaBean.NameBean" scope="page" />
	<jsp:setProperty property="fName" name="nameBean"/>
	<jsp:setProperty property="lName" name="nameBean"/>
	First Name:
	<jsp:getProperty property="fName" name="nameBean"/><br>
	Last Name:
	<jsp:getProperty property="lName" name="nameBean"/>
</body>
</html>