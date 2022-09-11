package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactoryJDBC extends DAOFactory {
	
	public static final String URL = "jdbc:mysql://localhost:3306/cinema2";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return connection;
	}

	@Override
	public UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return JDBCUserDAO.getInstance();
	}

	@Override
	public MovieDAO getMovieDAO() {
		// TODO Auto-generated method stub
		return JDBCMovieDAO.getInstance();
	}

	@Override
	public GenreDAO getGenreDAO() {
		// TODO Auto-generated method stub
		return JDBCGenreDAO.getInstance();
	}

	@Override
	public RoleDAO getRoleDAO() {
		// TODO Auto-generated method stub
		return JDBCRoleDAO.getInstance();
	}
	
	

}
