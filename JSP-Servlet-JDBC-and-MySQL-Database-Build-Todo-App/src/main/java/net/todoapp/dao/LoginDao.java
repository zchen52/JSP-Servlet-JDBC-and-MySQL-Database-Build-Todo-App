package net.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.todoapp.model.LoginBean;
import net.todoapp.utils.JDBCUtils;

public class LoginDao {
	
	public boolean validate(LoginBean loginBean)throws ClassNotFoundException {
		boolean status=false;
		
		String Query_USERS_SQL = "Select * "
				+ "From users "
				+ "Where username = ? and password = ?";
		
		try(Connection conn=JDBCUtils.getConnection();
				PreparedStatement preparedStatement=conn.prepareStatement(Query_USERS_SQL);){
			
			preparedStatement.setString(1,loginBean.getUsername());
			preparedStatement.setString(2,loginBean.getPassword());
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status=rs.next();
			
		} catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
		
		return status;
	}

}
