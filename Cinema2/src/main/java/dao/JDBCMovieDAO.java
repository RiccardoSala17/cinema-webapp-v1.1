package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Genre;
import model.Movie;


public class JDBCMovieDAO implements MovieDAO {
	
private static JDBCMovieDAO instance;
	
	private  JDBCMovieDAO () {
		
	}
	
	public static JDBCMovieDAO getInstance() {
		if (instance == null) 
			return instance = new JDBCMovieDAO();
		else
			return instance;
		
	}

	@Override
	public boolean insert(Movie u) {
		String q1 = "select * from genre where name = ?";
		String q2 = "insert into movie(title, year, id_genre) values(?, ?, ?)";
		try {
			Connection connection = DAOFactoryJDBC.getConnection();
			PreparedStatement s1 = connection.prepareStatement(q1);
			s1.setString(1, u.getGenre().getName());
			ResultSet resultSet1 = s1.executeQuery();
			if(resultSet1.next()) {
				long idGenre = resultSet1.getLong(1);
				PreparedStatement s2 = connection.prepareStatement(q2);
				s2.setString(1, u.getTitle());
				s2.setInt(2, u.getYear());
				s2.setLong(3, idGenre);
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
	public List<Movie> selectAll() {
		String q1 = "select * from movie join genre on id_genre = genre.id";
		List<Movie> list = new ArrayList<>();
		try {
			Connection connection = DAOFactoryJDBC.getConnection();
			Statement s1 = connection.createStatement();
			ResultSet resultSet1 = s1.executeQuery(q1);
			while(resultSet1.next()) {
				long idMovie = resultSet1.getLong(resultSet1.findColumn("Movie.id"));
				String title = resultSet1.getString(resultSet1.findColumn("title"));
				int year = resultSet1.getInt(resultSet1.findColumn("year"));			
				long idGenre = resultSet1.getLong(resultSet1.findColumn("genre.id"));
				String name  = resultSet1.getString(resultSet1.findColumn("name"));
				Movie f = new Movie(idMovie, title, year, new Genre(idGenre, name));
				list.add(f);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean remove(long id) {
		// TODO Auto-generated method stub
		String q1 = "delete from movie where id = ? ";
				
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
	public Movie selectBy(long id) {
		String q1 = "select * from movie join genre on id_genre = genre.id where movie.id = ?";
		
		String title = ""; int year = 0; long idGenre = 0; String name = "";
		
		try {
			Connection connection = DAOFactoryJDBC.getConnection();
			PreparedStatement s1 = connection.prepareStatement(q1);
			s1.setLong(1, id);
			ResultSet resultSet1 = s1.executeQuery();
			if(resultSet1.next()) {
				title = resultSet1.getString(resultSet1.findColumn("title"));
				year = resultSet1.getInt(resultSet1.findColumn("year"));			
				idGenre = resultSet1.getLong(resultSet1.findColumn("genre.id"));
				name  = resultSet1.getString(resultSet1.findColumn("name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		Movie f2 = new Movie(id, title, year, new Genre(idGenre, name));
		return f2;
	}
	
	@Override
	public boolean update(long id, Movie newMovie) {
		// TODO Auto-generated method stub
		String q1 = "update movie set title = ?, year = ?, id_genre = ?  where id = ?";
		
		try {			
			Connection connection = DAOFactoryJDBC.getConnection();
			PreparedStatement s1 = connection.prepareStatement(q1);
			s1.setString(1, newMovie.getTitle());
			s1.setInt(2, newMovie.getYear());
			s1.setLong(3, newMovie.getGenre().getId());
			s1.setLong(4, id);		
			s1.executeUpdate();
			return true;				
		} 
		catch (SQLException e) {			
			return false;
		}	
		
	}



}
