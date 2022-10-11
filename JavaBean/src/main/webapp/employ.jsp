<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employ Bean</title>
</head>
<body>
	<jsp:useBean id="employBean" class="infinite.JavaBean.Employ" scope="page" />
	<jsp:setProperty property="*" name="employBean"/>
	
	<table border="3">
		<tr>
			<th>Employ Number</th>
			<th>Employ Name</th>
			<th>Employ Department</th>
			<th>Employ Designation</th>
			<th>Employ Basic</th>
		</tr>
		<tr>
			<td><jsp:getProperty property="empno" name="employBean"/></td>
			<td><jsp:getProperty property="name" name="employBean"/></td>
			<td><jsp:getProperty property="dept" name="employBean"/></td>
			<td><jsp:getProperty property="desig" name="employBean"/></td>
			<td><jsp:getProperty property="basic" name="employBean"/></td>
		</tr>
	</table>
</body>
</html>