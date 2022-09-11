package dao;

public abstract class DAOFactory {
	
public static DAOFactory getDAOFactory(String type) {
		
		switch(type ) {
		
			case "JDBC":
				return new DAOFactoryJDBC();
			case "JPA":
			default:
				return new DAOFactoryJDBC();
		}
	}
	
	public abstract UserDAO getUserDAO();
	public abstract MovieDAO getMovieDAO();
	public abstract GenreDAO getGenreDAO();
	public abstract RoleDAO getRoleDAO();

}
