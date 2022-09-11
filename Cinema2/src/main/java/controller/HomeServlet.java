package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Remove;

import dao.DAOFactory;
import model.Genre;
import model.Movie;
import util.Utility;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getAttribute("movieListSelect") == null)
			request.setAttribute("movieListSelect", DAOFactory.getDAOFactory("JDBC").getMovieDAO().selectAll());

		if (request.getAttribute("error") == null) {
			request.setAttribute("error", "");
		}
		request
		.getRequestDispatcher("/WEB-INF/jsp/Home.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		
		if (action != null) {
		
			if (action.equals("Userlist Management")) {
				
				HttpSession session = request.getSession();
				session.setAttribute("action", "userlist");	
				response.sendRedirect("ModifyServlet");
				return;
			
			}
			else if (action.equals("Insights Management")) {
				response.sendRedirect("HomeServlet");
				return;
			
			}
			else if (action.equals("Modify")) {
				String id = request.getParameter("movies");
				HttpSession session = request.getSession();
				session.setAttribute("movieId", id);
				session.setAttribute("action", "modifyMovie");	
				response.sendRedirect("ModifyServlet");
				return;
			
			}
			else if (action.equals("Remove")) {
				long id = Long.parseLong(request.getParameter("movies"));
				DAOFactory.getDAOFactory("JDBC").getMovieDAO().remove(id);
			}
		}
		
		String order = request.getParameter("order");	
		
		if (order != null) {
			List <Movie> list = DAOFactory.getDAOFactory("JDBC").getMovieDAO().selectAll();
			request.setAttribute("movieListSelect", list);
			Utility.sortMovies(order, list);
		}
		
		String search = request.getParameter("search");
		
		if (search != null) {
			List <Movie> list = DAOFactory.getDAOFactory("JDBC").getMovieDAO().selectAll();
			request.setAttribute("movieListSelect", list);
			list = Utility.searchMovies(search, list);
			request.setAttribute("movieListSelect", list);
		}
		
		String title = request.getParameter("title");
		Genre genre = new Genre (request.getParameter("genre"));
		String yearString = request.getParameter("year");
		
		boolean checkTitle;
		boolean checkYear;
		
		if (title != null || genre != null || yearString != null) {
			if (title != null && genre != null && yearString != null) {
				
				if (!(checkTitle = Utility.checkTitle(title)))
					request.setAttribute("error", "Invalid title");
			
				if (!(checkYear = Utility.checkYear(yearString)))
					request.setAttribute("error", "Invalid year");
			
				if (checkYear && checkTitle) {
				
					int year = Integer.parseInt(yearString);
				
					Movie newFilm = new Movie(title, year, genre);
				
					if (DAOFactory.getDAOFactory("JDBC").getMovieDAO().insert(newFilm)) {
						request.setAttribute("error", "Successful insertion");
						doGet(request, response);
					}
					else {
						request.setAttribute("error", "Movie already in archive");
					}		
				
				}	
			}
		
		doGet(request, response);
		}
	}
}
