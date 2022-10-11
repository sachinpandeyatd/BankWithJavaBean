<%@page import="com.employ.EmployDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="add-employ.jsp">
		Employ Name:
		<input type="text" name="name" /><br>
		Department:
		<select name="dept">
			<option value="Dotnet">Dotnet</option>
			<option value="Java">Java</option>
			<option value="React">React</option>
		</select><br>
		Designation:
		<select name="desig">
			<option value="Engineer">Engineer</option>
			<option value="Programmer">Programmer</option>
		</select><br>
		Basic:
		<input type="number" name="basic" /><br>
		<input type="submit" value="Submit"/>
	</form>
	
	<%
		if (request.getParameter("basic")!=null) {
			EmployDAO dao = new EmployDAO();
	%>
	<jsp:useBean id="addEmploy" class="com.employ.Employ"/>
	<jsp:setProperty property="*" name="addEmploy"/>
	<%=dao.addEmployDAO(addEmploy) %>
	<%
		}
	%>
	
</body>
</html>