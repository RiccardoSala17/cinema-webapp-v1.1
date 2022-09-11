package dao;

import java.util.List;

import model.Movie;

public interface GenreDAO {
	
	boolean insert(Movie u);
	List<Movie> selectAll();
	boolean remove(long id);
	Movie selectBy(String name);

}
