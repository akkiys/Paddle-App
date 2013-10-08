<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Spring MVC Hello Worlds Example</h1>
 
	<h2>${msg}</h2>
	<form action="/TallyO/Formsubmit.html" method="post" >
	<input type="text" name="username"  >
	<input type="text" name="password" >
	<input type="submit" value="Submit">
	</form>


	<ul  >
	
	 <c:forEach var="listItems" items="${requestScope.test}">
 
		<li >
			<c:out value='${listItems.name}'/>  <c:out value='${listItems.password}'/> 
		</li>	
		</c:forEach>
	
	</ul>
	
</body>
</html>