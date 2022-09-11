package dao;

import java.util.List;

import model.Movie;

public class JDBCGenreDAO implements GenreDAO {
	
	private static JDBCGenreDAO instance;
	
	private JDBCGenreDAO() {
		
	}
	
	public static JDBCGenreDAO getInstance() {
		if(instance == null)
			instance = new JDBCGenreDAO();
		return instance;
	}

	@Override
	public boolean insert(Movie u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Movie> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Movie selectBy(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
