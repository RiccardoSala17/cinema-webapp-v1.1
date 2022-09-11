package dao;

import java.util.List;

import model.User;

public interface UserDAO {
		
	List<User> selectAll();
	User selectBy(String username, String password);
	User selectBy(long id);
	
	boolean insert(User u);
	boolean remove(long id);		
	boolean update (long idUser, long idRole);
	void update(User u);
}
