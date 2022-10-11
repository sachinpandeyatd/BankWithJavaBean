package com.employ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.ConnectionHelper;

public class EmployDAO {
	Connection connection;
	PreparedStatement preparedStatement;
	
	public List<Employ> showEmploy() throws ClassNotFoundException, SQLException {
		List<Employ> employList = new ArrayList<Employ>();
		
		connection = ConnectionHelper.getConnection();
		
		preparedStatement = connection.prepareStatement("select * from employ");
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Employ employ = null;
		while (resultSet.next()) {
			employ = new Employ();
			employ.setEmpno(resultSet.getInt("empno"));
			employ.setName(resultSet.getString("name"));
			employ.setDept(resultSet.getString("dept"));
			employ.setDesig(resultSet.getString("desig"));
			employ.setBasic(resultSet.getInt("basic"));
			employList.add(employ);
		}
		return employList;
	}
	
	public Employ searchEmployDAO(int empno) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		preparedStatement = connection.prepareStatement("select * from employ where empno = ?");
		preparedStatement.setInt(1, empno);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Employ employ = null;
		if (resultSet.next()) {
			employ = new Employ();
			employ.setEmpno(resultSet.getInt("empno"));
			employ.setName(resultSet.getString("name"));
			employ.setDept(resultSet.getString("dept"));
			employ.setDesig(resultSet.getString("desig"));
			employ.setBasic(resultSet.getInt("basic"));
		}
		return employ;
	}
	
	public String addEmployDAO(Employ employ) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		
		preparedStatement = connection.prepareStatement("insert into employ (name, dept, desig, basic) values(?, ?, ?, ?)");
		preparedStatement.setString(1, employ.getName());
		preparedStatement.setString(2, employ.getDept());
		preparedStatement.setString(3, employ.getDesig());
		preparedStatement.setInt(4, employ.getBasic());
		
		preparedStatement.executeUpdate();
		return "Record INSERTED.";
	}
	
	public String deleteEmployDAO(int empno) throws ClassNotFoundException, SQLException {
		Employ employFound = searchEmployDAO(empno);
		
		if (employFound != null) {
			preparedStatement = connection.prepareStatement("delete from employ where empno = ?");
			preparedStatement.setInt(1, empno);
			preparedStatement.executeUpdate();
			return "Employ Record DELETED.";
		}else {
			return "Employ NOT found.";
		}
	}
	
	public String updateEmployDAO(Employ employNew) throws ClassNotFoundException, SQLException {
		Employ employFound = searchEmployDAO(employNew.getEmpno());
		
		if (employFound != null) {
			preparedStatement = connection.prepareStatement("update employ SET name=?, dept=?, desig=?, basic=? where empno = ?");
			preparedStatement.setString(1, employNew.getName());
			preparedStatement.setString(2, employNew.getDept());
			preparedStatement.setString(3, employNew.getDesig());
			preparedStatement.setDouble(4, employNew.getBasic());
			preparedStatement.setInt(5, employNew.getEmpno());
			
			preparedStatement.executeUpdate();
			
			return "Record Updated.";
		}else{
			return "Record NOT Found";
		}
		
	}
}
