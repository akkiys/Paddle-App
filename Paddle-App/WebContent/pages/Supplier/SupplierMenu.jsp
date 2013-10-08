 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <li ><a href="#">Dashboard</a></li>       
 <c:if test="${sessionScope.isAdmin == true}">
                
    <li  ><a href="/HandShaking.html">HandShaking</a></li>
        <li ><a href="/SupplierUsers.html">Users</a></li>
    <li><a href="/AssignUsers.html">Assign Acc/Rev</a></li>
    
   </c:if>
   
   
    <c:if test="${ (sessionScope.isAdmin eq true) || (sessionScope.isReviewer eq true) }"> 
           <li><a href="/SupplierJobs.html">Jobs</a></li> 
    <li><a href="#">Assign Job</a></li>
    
  </c:if>
   
  <c:if test="${ sessionScope.isAccountant == true  || sessionScope.isReviewer == true }">
                
    <li><a href="/AccountantTimeSheet.html">TimeSheet</a></li>
   </c:if>
   
     <c:if test="${sessionScope.isReviewer eq true}"> 
           <li><a href="/Accountants.html">Accountants</a></li> 
    
    
  </c:if>
   
   
	   <li><a href="#">Settings</a></li>
	  	 