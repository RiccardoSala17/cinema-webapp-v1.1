package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Role;
import model.Role.Level;

public class JDBCRoleDAO implements RoleDAO {
	
private static JDBCRoleDAO instance;
	
	private  JDBCRoleDAO () {
		
	}
	
	public static JDBCRoleDAO getInstance() {
		if (instance == null) 
			return instance = new JDBCRoleDAO();
		else
			return instance;
		
	}

	@Override
	public List<Role> selectAll() {
		// TODO Auto-generated method stub
		String q1 = "select * from role";
		List<Role> list = new ArrayList<>();
		try {
			Connection connection = DAOFactoryJDBC.getConnection();
			Statement s1 = connection.createStatement();
			ResultSet resultSet1 = s1.executeQuery(q1);
			while(resultSet1.next()) {		
				long idRole = resultSet1.getLong(resultSet1.findColumn("role.id"));
				Level role  = Level.valueOf(resultSet1.getString(resultSet1.findColumn("role")));
				Role r = new Role(idRole, role);
				list.add(r);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}