<%@page import="com.bank.BankDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposit Money</title>
</head>
<body>
	<form action="deposit-amount.jsp">
		Account Number:
		<input type="number" name="accountNo" /><br>
		Deposit Amount:
		<input type="number" name="amount" /><br>
		<input type="submit" value="SUBMIT" />
	</form>
	
	<%
		if(request.getParameter("accountNo") != null){
			BankDAO dao = new BankDAO();
	%>
	<jsp:useBean id="deposit" class="com.bank.Trans" scope="page"></jsp:useBean>
	<jsp:setProperty property="*" name="deposit" />
	<%
			out.println(dao.depositAmount(deposit));
		}
	%>
</body>
</html>