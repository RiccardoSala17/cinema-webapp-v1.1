package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;
import model.Role.Level;

public class JDBCUserDAO implements UserDAO {

	private static JDBCUserDAO instance;
	
	private JDBCUserDAO() {
		
	}
	
	public static JDBCUserDAO getInstance() {
		if(instance == null)
			instance = new JDBCUserDAO();
		return instance;
	}
	
	@Override
	public List<User> selectAll() {
		String q1 = "select * from user join role on id_role = role.id";
		List<User> list = new ArrayList<>();
		try {
			Connection connection = DAOFactoryJDBC.getConnection();
			Statement s1 = connection.createStatement();
			ResultSet resultSet1 = s1.executeQuery(q1);
			while(resultSet1.next()) {
				long idUser = resultSet1.getLong(resultSet1.findColumn("user.id"));
				String username = resultSet1.getString(resultSet1.findColumn("username"));
				String password = resultSet1.getString(resultSet1.findColumn("password"));			
				long idRole = resultSet1.getLong(resultSet1.findColumn("role.id"));
				Level role  = Level.valueOf(resultSet1.getString(resultSet1.findColumn("role")));
				User u = new User(idUser, username, password, new Role(idRole, role));
				list.add(u);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User selectBy(String username, String password) {
		// TODO Auto-generated method stub
		String q1 = "select * from user join role on id_role = role.id where username = ? and password = ?";
		try {
			
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection c1 = DAOFactoryJDBC.getConnection();
			PreparedStatement s1 = c1.prepareStatement(q1);
			s1.setString(1, username);
			s1.setString(2, password);
			ResultSet r1 = s1.executeQuery();
			if(r1.next()) {
				long idUser = r1.getLong(r1.findColumn("user.id"));
				long idRole = r1.getLong(r1.findColumn("role.id"));
				Level role = Level.valueOf(r1.getString(r1.findColumn("role")));

				return new User(idUser, username, password, new Role(idRole, role));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public User selectBy(long id) {
		// TODO Auto-generated method stub
		String q1 = "select * from user join role on id_role = role.id where id = ?";
		try {
			
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection c1 = DAOFactoryJDBC.getConnection();
			PreparedStatement s1 = c1.prepareStatement(q1);
			s1.setLong(1, id);
			ResultSet r1 = s1.executeQuery();
			if(r1.next()) {
				String username = r1.getString(r1.findColumn("username"));
				String password = r1.getString(r1.findColumn("password"));
				long idRole = r1.getLong(r1.findColumn("role.id"));
				Level role = Level.valueOf(r1.getString(r1.findColumn("role")));

				return new User(id, username, password, new Role(idRole, role));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean insert(User u) {
		// TODO Auto-generated method stub
		String q1 = "select * from role where role = ?";
		String q2 = "insert into user(username, password, id_role) values(?, ?, ?)";
		try {
			Connection connection = DAOFactoryJDBC.getConnection();
			PreparedStatement s1 = connection.prepareStatement(q1);
			s1.setString(1, u.getRole().getLevel().toString());
			ResultSet resultSet1 = s1.executeQuery();
			if(resultSet1.next()) {
				long idRole = resultSet1.getLong(1);
				PreparedStatement s2 = connection.prepareStatement(q2);
				s2.setString(1, u.getUsername());
				s2.setString(2, u.getPassword());
				s2.setLong(3, idRole);
				s2.executeUpdate();
				return true;
			}
			return false;
		}
		catch(SQLException e) {
			return false;
		}
		
	}


	@Override
	public boolean remove(long id) {
		// TODO Auto-generated method stub
		String q1 = "delete from user where id = ? ";
		
		try {			
			Connection connection = DAOFactoryJDBC.getConnection();
			PreparedStatement s1 = connection.prepareStatement(q1);
			s1.setLong(1, id);
			s1.executeUpdate();
			return true;				
		} 
		catch (SQLException e) {			
			return false;
		}	
		
	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(long idUser, long idRole) {
		// TODO Auto-generated method stub
		String q1 = "update user set id_role = ? where id = ?";
		
		try {			
			Connection connection = DAOFactoryJDBC.getConnection();
			PreparedStatement s1 = connection.prepareStatement(q1);
			s1.setLong(1, idRole);
			s1.setLong(2, idUser);
			s1.executeUpdate();
			return true;				
		} 
		catch (SQLException e) {			
			return false;
		}	
		
	}
}
