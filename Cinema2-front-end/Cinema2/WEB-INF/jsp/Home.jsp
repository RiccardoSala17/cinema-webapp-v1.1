<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "model.*" import = "java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>HomeUtente</title>
		<style><%@include file="/WEB-INF/css/Login.css"%></style>
	</head>
	<body>
		<%
			HttpSession x = request.getSession();
			User user = (User) x.getAttribute("user");
			String str = user.getRole().getLevel().toString().toLowerCase();
			String roleString = str.substring(0, 1).toUpperCase() + str.substring(1);
			
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
		<div <% if (!roleString.equals("Admin")) {%> hidden<%} %>>
			<form method = "post" action="HomeServlet">
				<div class = "under-bar" >
					<input type = 'submit' name = 'action'  value = "Userlist Management">
				</div>
				<div class = "under-bar" >
					<input type = 'submit' name = 'action'  value = "Insights Management">
				</div><br><br>
			</form>
		</div>
		<div>
			<h3>Archive of movies:</h3>
			<form method = 'post' action= 'HomeServlet'>
				<select name='movies' id='movies'>
				<%  
					List<Movie> list = (List<Movie>) request.getAttribute("movieListSelect"); 
					for(Movie temp : list) {		
				%> 
					<option value = '<%= temp.getId() %>'><%=temp.getTitle()%> - <%=temp.getYear()%> - <%=temp.getGenre().getName()%></option>
		
				<% 
					} 
				%>
			
				</select><br> <br>
				<div <% if (roleString.equals("User")) {%> hidden<%} %>>
					<div class = "under-bar" >
						<input type = 'submit' name = 'action'  value = "Modify">
					</div>
					<div class = "under-bar" >
						<input type = 'submit' name = 'action'  value = "Remove">
					</div><br><br>	
				</div>
			</form>
		</div>
		<div>
			<form method = 'post' action="HomeServlet">
				<input type = 'submit'  value = "Order by: ">
				<select name = 'order'>
  					<option value="title">Title</option>
  					<option value="genre">Genre</option>
  					<option value="year">Year</option>
  				</select>
			</form><br><br>
		</div>
		<div <% if (roleString.equals("User")) {%> hidden<%} %>>
			<h3>Add a new movie:</h3>
			<form method = 'POST' action = 'HomeServlet'>
				<input type = 'text' name = 'title' placeholder = 'title'><br><br>
				<input type = 'number' name = 'year' placeholder = 'year' min = '1900' max = '2022'><br><br>
				<input type='radio' id="comedy" name="genre" value="comedy">
  				<label for="comedy">Comedy</label><br>
  				<input type="radio" id="horror" name="genre" value="horror">
  				<label for="horror">Horror</label><br>
  				<input type="radio" id="drama" name="genre" value="drama">
  				<label for="drama">Drama</label><br>
  				<input type="radio" id="thriller" name="genre" value="thriller">
  				<label for="thriller">Thriller</label><br><br>
  				<input type = "submit" value = "Add movie">
  			</form>
		</div><br><br>
		<div>
			<h3>Find a movie:</h3>
			<form method = 'post' action="HomeServlet">
      			<input type="text" placeholder="Title..." name="search">
      			<button type="submit">Search</button>
    		</form><br><br>
    	</div>
    	<div>
			<form method = 'GET' action="LoginServlet">
				<input type = 'submit'  value = "Back to Login">
			</form><br><br>
		</div>
	</body>
</html>