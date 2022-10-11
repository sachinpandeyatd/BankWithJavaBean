<%@page import="com.bank.BankDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Account</title>
</head>
<body>
	<form action="create-account.jsp">
		First Name:
		<input type="text" name="firstName" /><br>
		Last Name:
		<input type="text" name="lastName" /><br>
		City:
		<input type="text" name="city" /><br>
		State:
		<input type="text" name="state" /><br>
		Amount:
		<input type="number" name="amount" /><br>
		CheqFacil:
		<input type="text" name="cheqFacil" /><br>
		Account Type:
		<input type="text" name="accountType" /><br>
		<input type="submit" value="SUBMIT" />
	</form>
	
	<%
		if(request.getParameter("amount") != null){
			BankDAO dao = new BankDAO();
	%>
	<jsp:useBean id="createAccount" class="com.bank.Bank" scope="page"></jsp:useBean>
	<jsp:setProperty property="*" name="createAccount" />
	<%
			out.println(dao.createAccount(createAccount));
		}
	%>
</body>
</html>