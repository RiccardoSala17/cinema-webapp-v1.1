package dao;

import java.util.List;

import model.Movie;

public interface MovieDAO {
	
	boolean insert(Movie u);
	List<Movie> selectAll();
	boolean remove(long id);
	Movie selectBy(long id);
	boolean update(long id, Movie newMovie);

}
