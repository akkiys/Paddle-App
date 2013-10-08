  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       
       <li><a href="#">Dashboard</a></li>
         	
        <c:if test="${sessionScope.isAdmin == true}">
                <li  ><a href="/ClientHandShaking.html">HandShaking</a></li>
					<li><a href="/ClientUsers.html">Users</a></li>
					<li><a href="/ClientJobs.html">Jobs</a></li>
	  </c:if>
	  
	 <c:if test="${sessionScope.isPartner == true}">
	  	       <li><a href="/PartnerJobs.html">Jobs</a></li> 
				<li><a href="#">Assign Job</a></li>
				<%System.out.println("I'm a partner") ;%>
		</c:if>	
	
	  
	 <c:if test="${ sessionScope.isManager  == true}">
                
				<li><a href="/Partners.html">Partners</a></li>
	  </c:if>
	   <li><a href="#">Analysis</a></li>	
	  <li><a href="#">Reports</a></li>
	  	 <li><a href="#">Settings</a></li>
	  	 