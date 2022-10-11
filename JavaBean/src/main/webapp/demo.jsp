<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo Bean</title>
</head>
<body>
	<jsp:useBean id="beanDemo" class="infinite.JavaBean.DemoBean" scope="page" />
	Default Message:
	<jsp:getProperty property="greeting" name="beanDemo"/><br><hr>
	<jsp:setProperty property="greeting" name="beanDemo" value="Good Evening"/>
	
	Updated Message:
	<jsp:getProperty property="greeting" name="beanDemo"/>
</body>
</html>