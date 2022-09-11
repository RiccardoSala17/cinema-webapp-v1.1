package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import model.User;
import util.Utility;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String registerCheck = request.getParameter("register");
		System.out.println(registerCheck + "1");
		HttpSession session = request.getSession();
		session.setAttribute("register", registerCheck);
		
		request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String registerCheck = (String) session.getAttribute("register");
		System.out.println(registerCheck + "2");
		
			if (registerCheck == null) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String rememberme = request.getParameter("rememberme");
				User user = DAOFactory.getDAOFactory("JDBC").getUserDAO().selectBy(username, password);
				
				if(user != null) {
					if(rememberme != null) {
						Cookie cookieUsername = new Cookie("username", username);
						Cookie cookiePassword = new Cookie("password", password);
						response.addCookie(cookieUsername);
						response.addCookie(cookiePassword);
					}
					session.setAttribute("user", user);
					response.sendRedirect("HomeServlet");
					return;
				}
				else
				request.setAttribute("error", "Wrong credentials");
			}
			
			if (registerCheck != null) {
				
				String username = request.getParameter("usernameRegister");
				String password = request.getParameter("passwordRegister");
				String repeatPassword = request.getParameter("repeatPassword");
				
				if (username != null || password != null || repeatPassword != null) {
					
					if (!Utility.checkUsername(username))
						request.setAttribute("error", "Username is too short");
					else					
						if (!password.equals(repeatPassword))
							request.setAttribute("error", "Password doesn't match");					
						else {
						
							String checkQuote = Utility.checkPassword(password);
						
							if(!checkQuote.isEmpty())							
								request.setAttribute("error", checkQuote);
							else {
							
								User newUser = new User (username, password);
							
								if (DAOFactory.getDAOFactory("JDBC").getUserDAO().insert(newUser)) {
								   request.setAttribute("error", "User successfully registered");
								}
								else
									request.setAttribute("error", "User already registered");
							}
						}
				}
			}
				
				
			doGet(request, response);
	}
}
			


