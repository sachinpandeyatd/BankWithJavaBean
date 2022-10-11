package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDAO {
	Connection connection;
	PreparedStatement pst;
	
	public int generateAccountNo() throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "select case when max(accountNo) is null then 1 else max(accountNo)+1 end accno from Bank";
		pst = connection.prepareStatement(cmd);
		ResultSet resultSet = pst.executeQuery();
		resultSet.next();
		int accno = resultSet.getInt("accno");
		return accno;
	}
	
	public String createAccount(Bank bank) throws SQLException, ClassNotFoundException {
		int accountNo = generateAccountNo();
		String cmd = "insert into Bank(AccountNo, firstname, lastname, city, state, amount, cheqfacil, accounttype) values(?,?,?,?,?,?,?,?)";
		connection = ConnectionHelper.getConnection();
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, accountNo);
		pst.setString(2, bank.getFirstName());
		pst.setString(3, bank.getLastName());
		pst.setString(4, bank.getCity());
		pst.setString(5, bank.getState());
		pst.setInt(6, bank.getAmount());
		pst.setString(7, bank.getCheqFacil());
		pst.setString(8, bank.getAccountType());
		pst.executeUpdate();
		
		return "Account Created.";
	}
	
	public Bank searchBankAccount(int accountNo) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		
		pst = connection.prepareStatement("select * from bank where accountno = ?");
		pst.setInt(1, accountNo);
		ResultSet resultSet = pst.executeQuery();
		
		Bank account = null;
		if (resultSet.next()) {
			account = new Bank();
			account.setAccountNo(resultSet.getInt("accountno"));
			account.setFirstName(resultSet.getString("firstname"));
			account.setLastName(resultSet.getString("lastname"));
			account.setCity(resultSet.getString("city"));
			account.setState(resultSet.getString("state"));
			account.setAmount(resultSet.getInt("amount"));
			account.setCheqFacil(resultSet.getString("cheqfacil"));
			account.setAccountType(resultSet.getString("accounttype"));
		}
		return account;
	}
	
	public String closeAccount(int accountNo) throws ClassNotFoundException, SQLException {
		Bank bank = searchBankAccount(accountNo);
		if (bank != null) {
			connection = ConnectionHelper.getConnection();
			pst = connection.prepareStatement("update bank set status = 'inactive' where accountNo=?");
			pst.setInt(1, accountNo);
			pst.executeUpdate();
			return "Account Closed.";
		}
		return "Account Number NOT found.";
	}
	
	public String depositAmount(Trans trans) throws ClassNotFoundException, SQLException {
		Bank bank = searchBankAccount(trans.getAccountNo());
		if (bank != null) {
			connection = ConnectionHelper.getConnection();
			pst = connection.prepareStatement("update Bank set Amount = Amount+? where AccountNo = ?");
			pst.setDouble(1, trans.getAmount());
			pst.setInt(2, trans.getAccountNo());
			pst.executeUpdate();
			
			pst = connection.prepareStatement("insert into Trans(AccountNo, TransAmount, TransType) values(?, ?, ?)");
			pst.setInt(1, trans.getAccountNo());
			pst.setDouble(2, trans.getAmount());
			pst.setString(3, "C");
			pst.executeUpdate();
			return "Amount Credited.";
		}
		return "Account Number NOT Found.";
	}
	
	public String withdrawAmount(Trans trans) throws SQLException, ClassNotFoundException {
		Bank bank = searchBankAccount(trans.getAccountNo());
		
		if (bank != null) {
			double balance = bank.getAmount();
			if (balance - trans.getAmount() >= 1000) {
				connection = ConnectionHelper.getConnection();
				pst = connection.prepareStatement("update Bank set Amount = Amount-? where AccountNo = ?");
				pst.setDouble(1, trans.getAmount());
				pst.setInt(2, trans.getAccountNo());
				pst.executeUpdate();
				
				pst = connection.prepareStatement("insert into Trans(AccountNo, TransAmount, TransType) values(?, ?, ?)");
				pst.setInt(1, trans.getAccountNo());
				pst.setDouble(2, trans.getAmount());
				pst.setString(3, "D");
				pst.executeUpdate();
				return "Amount Withdrawn.";
			}else {
				return "Insufficient Funds.";
			}
		}
		return "Account NOT found.";
	}
}
