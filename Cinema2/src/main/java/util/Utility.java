package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.DAOFactory;
import model.Movie;
import model.User;

public class Utility {
	
	//checking methods
	
	// 2 step movie checking
	
		public static boolean checkTitle(String title) {
			return (!title.isEmpty() && !title.isBlank() && title != null);
		}
		
		public static boolean checkYear(String year) {
			
			try {
				int x = Integer.parseInt(year);
				return x > 0;	
			}
			catch (NumberFormatException e) {
				return false;
			}		
		}
		
		
	//2 step user checking
		
		public static boolean checkUsername(String username) {
			if (username.isEmpty() || username.isBlank())
				return false;
			else if (username.length() < 5 )
				return false;
			else return true;
		}
		
		
		public static String checkPassword(String password) {
			
			if (password.isEmpty() || password.isBlank())
				return "Invalid password";
			else if (password.length() < 8 )
				return "Password too short";
			else if (password.length() > 20 )
				return "Password too long";
			else if(password.matches(".* .*"))
				return "Password cannot contain whitespaces";
			else if (!password.matches(".*[a-z]+.*"))
				return "Password requires at least a lowercase letter";
			else if (!password.matches(".*[A-Z]+.*"))
				return "Password requires at least a lowercase letter";
			else if (!password.matches(".*[0-9]+.*"))
				return "La password necessita di un carattere numerico";
			else if (!password.matches(".*[!?@#$%^&-+=()]+.*"))
					return "La password necessita di almeno un carattere speciale !?@#$%^&-+=()";
			else return "";
		}
		
		//check valid user elimination
		
		public static boolean checkAdminNumber (List<User> userlist) {
			
			int count = 0;
			for(User u : userlist) {
				if (u.getRole().getLevel().toString().toLowerCase().equals("admin"))
					count++;
			}
			
			return (count > 1);				
		}
		
		public static void sortMovies (String order, List<Movie> movies) {
			
			if(order.equals("title")) {			
				Collections.sort(movies, (s1, s2) -> s1.getTitle().compareToIgnoreCase(s2.getTitle()));		
			}
			else if (order.equals("genre")) {			
				Collections.sort(movies, (s1, s2) -> s1.getGenre().getName().compareTo(s2.getGenre().getName()));
			}	
			else {
				Collections.sort(movies, (s1, s2) -> ((Integer)s1.getYear()).compareTo((Integer)s2.getYear()));
			}	
		}
		
		public static List<Movie> searchMovies(String search, List<Movie> movies) {
			
			List<Movie> list = new ArrayList<>();
			System.out.println("search");
			
			for(Movie temp : movies) {
				
				if (temp.getTitle().toLowerCase().contains(search.toLowerCase())) {
					list.add(temp);
				}	
			}
			return list;	
		}
		
	

}
