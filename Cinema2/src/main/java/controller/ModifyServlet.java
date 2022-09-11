package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import model.Genre;
import model.Movie;
import model.User;
import util.Utility;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request
		.getRequestDispatcher("/WEB-INF/jsp/Modify.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String action = (String) session.getAttribute("action");
		
		if (action.equals("modifyMovie")) {
		
		
			String title = request.getParameter("titleMod");
			Genre genre = new Genre (request.getParameter("genreMod"));
			String yearString = request.getParameter("yearMod");
		
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
				
						Movie newMovie = new Movie(title, year, genre);
						long id = Long.parseLong((String) session.getAttribute("movieId"));
				
						if (DAOFactory.getDAOFactory("JDBC").getMovieDAO().update(id, newMovie)) {
							request.setAttribute("error", "Successful insertion");
							response.sendRedirect("HomeServlet");
							return;
						}
						else {
							request.setAttribute("error", "Movie already in archive");
						}		
				
					}	
				}
			}
		}
		
		if (action.equals("userlist")) {

			User user = DAOFactory.getDAOFactory("JDBC").getUserDAO().selectBy(Long.parseLong(request.getParameter("userMod")));
			String actionModUser = request.getParameter("actionModUser");
			
			if (actionModUser.equals("Remove user")) {
				if (user.getRole().getLevel().toString().toLowerCase().equals("admin"))
					request.setAttribute("error", "Cannot remove ADMIN users, you have to downgrade their permission level first");
				else
					DAOFactory.getDAOFactory("JDBC").getUserDAO().remove(user.getId());
			}
			
			if (actionModUser.equals("Update permission level")) {
				
				if (Utility.checkAdminNumber(DAOFactory.getDAOFactory("JDBC").getUserDAO().selectAll())) {				
					long roleId = Long.parseLong(request.getParameter("newRole"));
					DAOFactory.getDAOFactory("JDBC").getUserDAO().update(user.getId(), roleId);
				}
				else 
					request.setAttribute("error", "Cannot downgrade permission of the only one ADMIN user");
				
			}
			
			
			
		}
		doGet(request, response);
	}

}
