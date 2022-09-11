<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login</title>
		<style><%@include file="/WEB-INF/css/Login.css"%></style>
	</head>
	<body>
		<div <% if (request.getAttribute("register") != null)  {%> hidden<%} %>>

			<h1>Login</h1>
			<%
				String error = (String) request.getAttribute("error");
				if(error != null) {
			%>
					<h2><%= error %></h2>
			<%
				}
			%>
			<% 
				
					Cookie[] cookies = request.getCookies();
					String username = "";
					String password = "";
					if(cookies != null) {
						for(Cookie c : cookies) {
							if(c.getName().equals("username")) {
								username = (String) c.getValue();
							}
							else if(c.getName().equals("password")) {
								password = (String) c.getValue();
							}
						}
					}
				
			%>
			<form method="POST" action="LoginServlet">
				<input type="text" name="username" value="<%= username %>"><br><br>
				<input type="password" name="password" value="<%=password%>"><br><br>				
				<input type="checkbox" name="rememberme" <%if(!username.isEmpty())  {%>checked<%}%>>
				<label for="remeberme">Remember me</label><br><br>
				<input type="submit" value = "Login"><br><br>
			</form>
			<form method = 'post' action="LoginServlet">
				<input type = 'submit' name = 'register'  value = "Register"><br><br>
			</form>
		</div>	
		<div <% if (request.getAttribute("register") == null)  {%> hidden<%} %>>
			<h1>Register</h1>
			<%	
				
				error = (String) request.getAttribute("error");
				if(error != null) {
			%>
					<h2><%= error %></h2>
			<%
				}
			%>
			<form method = 'POST' action = 'LoginServlet'>
				<input type = 'text' name = 'usernameRegister'  placeholder = 'Enter username' autocomplete = "chrome-off"><br><br>
				<input type = 'password' name = 'passwordRegister'  placeholder = 'Enter password' autocomplete = "chrome-off"><br><br>
				<input type = 'password' name = 'repeatPassword' placeholder = 'Re-enter password' autocomplete = "chrome-off"><br><br>
				<input type = 'submit' value = 'Register'><br><br>
			</form>
			<form method = 'post' action="LoginServlet">
				<input type = 'submit'  value = "Back to Login"><br><br>
			</form>
		</div>
	</body>
</html>