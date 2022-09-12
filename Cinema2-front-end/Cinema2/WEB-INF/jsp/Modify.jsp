<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import = "model.*" import = "java.util.List" import = "dao.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Modify</title>
		<style><%@include file="/WEB-INF/css/Login.css"%></style>
	</head>
	<body>
		<%
			HttpSession x = request.getSession();
			User user = (User) x.getAttribute("user");
			String str = user.getRole().getLevel().toString().toLowerCase();
			String roleString = str.substring(0, 1).toUpperCase() + str.substring(1);
			String action = (String) x.getAttribute("action");
			
		%>
		<h1>Welcome dear <%=roleString%></h1><br>
		<%
			String error = (String) request.getAttribute("error");
			if(error != null) {
		%>
				<h2><%= error %></h2>
		<%
			}
		%>
		<div <% if (!action.equals("modifyMovie")) {%> hidden<%} %>>
			<h3>Insert new data for the movie:</h3>
			<%
				long id = Long.parseLong((String)x.getAttribute("movieId"));
				Movie movieMod = DAOFactory.getDAOFactory("JDBC").getMovieDAO().selectBy(id);
				String titMod = movieMod.getTitle();
				int yearMod = movieMod.getYear();
				String genreMod = movieMod.getGenre().getName().toString();
			
			%>
			<form method = 'post' action = 'ModifyServlet'> 
				<input type = 'text' name = 'titleMod' value = '<%=titMod%>'><br><br>
				<input type = 'number' name = 'yearMod' value = '<%= yearMod %>'min = '1900' max = '2022'><br><br>
				<input type='radio' id="comedy" name="genreMod" value="comedy" <% if (genreMod.toLowerCase().equals("comedy")) {%>checked<%} %>>
  				<label for="comedy">Comedy</label><br>
  				<input type="radio" id="horror" name="genreMod" value="horror" <% if (genreMod.toLowerCase().equals("horror")) {%>checked<%} %>>
  				<label for="horror">Horror</label><br>
  				<input type="radio" id="drama" name="genreMod" value="drama"<% if (genreMod.toLowerCase().equals("drama")) {%>checked<%} %>>
  				<label for="drama">Drama</label><br>
  				<input type="radio" id="thriller" name="genreMod" value="thriller" <% if (genreMod.toLowerCase().equals("thriller")) {%>checked<%} %>>
  				<label for="thriller">Thriller</label><br><br>
  				<input type = "submit" value = "Apply changes to movie">
			</form>	
		</div><br><br>
		
		
		
		<div <% if (!action.equals("userlist") || !roleString.toLowerCase().equals("admin")) {%> hidden<%} %>>
			<h3>Userlist:</h3>
			<form method = 'post' action= 'ModifyServlet'>
				<select name='userMod' id='users'>
				<%  
					List<User> listUser = DAOFactory.getDAOFactory("JDBC").getUserDAO().selectAll();
					for(User temp : listUser) {		
				%> 
					<option value = '<%= temp.getId() %>'><%=temp.getUsername()%> - <%=temp.getPassword()%> - <%=temp.getRole().getLevel().toString()%></option>
		
				<% 
					} 
				%>
			
				</select><br><br>
				<div>
					<div class = "under-bar" >
						<select name = 'newRole'>
  							<%  
								List<Role> listRole = DAOFactory.getDAOFactory("JDBC").getRoleDAO().selectAll();
								for(Role temp : listRole) {		
							%> 
								<option value = '<%= temp.getId() %>'><%=temp.getLevel().toString()%></option>
		
							<% 
								} 
							%>
  							<input type = 'submit' name = 'actionModUser'  value = "Update permission level">
  						</select>
					</div>
					<div class = "under-bar" >
						<input type = 'submit' name = 'actionModUser'  value = "Remove user">
					</div><br><br>		
				</div>
			</form>
		</div>
		<div class = "under-bar" >
			<form method = 'GET' action="HomeServlet">
				<input type = 'submit'  value = "Back">
			</form>
		</div>
		<div class = "under-bar" >
			<form method = 'GET' action="LoginServlet">
				<input type = 'submit'  value = "Back to Login">
			</form>
		</div><br><br>
	</body>
</html>