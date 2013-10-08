<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Partners</title>

 <link rel="stylesheet" href="/assets/stylesheets/main.css" /> <!-- imports all the CSS Files -->
  <script src="/assets/javascripts/vendor/custom.modernizr.js"></script>
 <script src="/assets/javascripts/Custom.js"></script>
 
</head>
<body onload="loadClientMenu();" >
<input type="hidden"  name="clientId" value='<c:out value="${sessionScope.supplierId}"/>' />
<input type="hidden"  name="userId" value='<c:out value="${sessionScope.userId}"/>' />
<div class="wrap-menu">
	<div class="row">
		<nav class="top-bar">
			
				<ul class="title-area">
				    <!-- Title Area -->
				    <li class="name">
				      <h1><a href="#">PaddleApp</a></h1>
				    </li>
				    <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
				    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
				</ul>
			
				<section class="top-bar-section">
					<!-- Left Nav Section -->
					<ul class="left">
							<li><a href="#"> <span class="color-red"><c:out value="${sessionScope.clientName}"/></span></a></li>
					<!-- <li class="has-form"><a class="app-menu-button button" href="#pricing">Switch</a></li> -->	
					</ul>
					<ul class="show-for-small" id="loadMenu" >
						 
					</ul>
					<!-- Right Nav Section -->
					<ul class="right">
					     <li><a href="#">Welcome <span class="color-red"><c:out value="${sessionScope.userName}"/> ,  <c:if test="${sessionScope.isAdmin == true}">Admin</c:if> <c:if test="${sessionScope.isPartner == true}">Partner</c:if> <c:if test="${sessionScope.isManager == true}">Manager</c:if></span></a></li>
						<li class="has-form"><a href="/Logout.html" class="app-menu-button button-logout button">Logout</a></li>
					</ul>
				</section>			
		</nav>
	</div>
</div>


<div class="wrap-app">	
	<div class="row app-body">
		<div class="large-2 columns wrap-side-nav">
			<ul class="side-nav hide-for-small" id="loadMenu1">
				 
			</ul>
		</div>
		<div class="large-10 columns">
			<form class="custom">
			  <fieldset>
       <legend>Partners</legend>
       
   
       
     <!-- Details -->  
     <c:choose>
    <c:when test="${requestScope.partnerDetails!=null}">     
       
 
				 
			<table class="table-transactions">
				<thead>
					<tr> 
						<th  >Client Name</th>
						<th>Status</th>
						<th>Action</th>
						 
					</tr>
				</thead>
				<tbody>
				<c:forEach var="listItems" items="${requestScope.partnerDetails}">
					<tr>
						<td><c:out value='${listItems.userName}'/> </td>
						<td>Active</td>
						 <td> <i class="foundicon-trash" onClick="javascript:deletePartners(<c:out value='${listItems.id}'/>);"></i> </td> 
						 
					</tr>
							
		 
		</c:forEach> 
				</tbody>
			</table>
			
	 </c:when>
    <c:otherwise>
     <div class="large-8 columns" id="Addbutton">No Accountant Found! <input type="button"  class="button-add" value="ADD" onclick="javascript:showHideDiv('1');"/> </div>
    </c:otherwise>
    </c:choose>   
		
		
		
		
		
			
			 </fieldset>
		</div>
		
	</div>
</div>



<div class="wrap-footer">	
	<div class="row">
		<div class="small-10 small-centered columns">
			<ul class="inline-list">
				<li><a href="#">Copyright &copy; PaddleApp 2013</a></li>
				<li><a href="#">Terms & Conditions</a></li>
				<li><a href="#">Privacy Policy</a></li>
				<li><a href="#">Contact</a></li>
				<li><a href="#">Twitter</a></li>
				<li><a href="#">Facebook</a></li>
			</ul>			
		</div>
	</div>
</div>


  <script>
  document.write('<script src=' +
  ('__proto__' in {} ? '/assets/javascripts/vendor/zepto' : '/assets/javascripts/vendor/jquery') +
  '.js><\/script>')
  </script>
  
  <script src="assets/javascripts/foundation/foundation.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.alerts.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.clearing.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.cookie.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.dropdown.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.forms.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.joyride.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.magellan.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.orbit.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.placeholder.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.reveal.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.section.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.tooltips.js"></script>
	
	<script src="/assets/javascripts/foundation/foundation.topbar.js"></script>
	
  <!-- DWR CONFIGURATION -->
<script type='text/javascript' src="/assets/javascripts/dwr/ClientDWR.js"></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/ClientDWR.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>


  
  <script>
    $(document).foundation();
    $('table').stacktable();
  </script>
  
  
  
</body>
</html>