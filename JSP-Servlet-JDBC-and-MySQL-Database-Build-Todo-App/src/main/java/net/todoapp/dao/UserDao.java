package net.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.todoapp.model.User;
import net.todoapp.utils.JDBCUtils;

public class UserDao {
	public int registerEmployee(User employee) throws ClassNotFoundException{
		int result =0;
		
		String INSERT_USERS_SQL = "INSERT INTO users"
				+ "(first_name,last_name,username,password) VALUES"
				+ "(?,?,?,?);";
		
		try(Connection conn=JDBCUtils.getConnection();
			PreparedStatement preparedStatement=conn.prepareStatement(INSERT_USERS_SQL);){
			
			preparedStatement.setString(1,employee.getFirstName());
			preparedStatement.setString(2,employee.getLastName());
			preparedStatement.setString(3,employee.getUsername());
			preparedStatement.setString(4,employee.getPassword());
			
			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
			
		}catch(SQLException e){
			JDBCUtils.printSQLException(e);
		}
		
		
		return result;
	}
}
