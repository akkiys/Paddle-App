<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>

  <link rel="stylesheet" href="/assets/stylesheets/main.css" /> <!-- imports all the CSS Files -->
  <script src="/assets/javascripts/vendor/custom.modernizr.js"></script>
 <script src="/assets/javascripts/Custom.js"></script>

<!-- Multiple select  -->

<link rel="stylesheet" type="text/css" href="/assets/stylesheets/multiselect/jquery.multiselect.css"  />
<link rel="stylesheet" type="text/css" href="/assets/stylesheets/multiselect/style.css"  />
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
 <script type="text/javascript" src="/assets/javascripts/multiselect/jquery.multiselect.js" ></script>

<script type="text/javascript">
$(function(){
	$("select").multiselect({header: false});
});
</script>
<!-- End  select -->

</head>
<body   onload="loadClientMenu();">
<input type="hidden"  name="clientId" value='<c:out value="${sessionScope.clientId}"/>' />
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
					<!-- Load Menu -->	 
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
		 
			  <fieldset>
       <legend>USERS</legend>
       
       <!-- ADD DIV -->
       

 
       <div id="addDiv" style="display:none">
       <form class="custom">
			 
			
			    <div class="row">
			      <div class="large-6 columns">
			        <label>Name</label>
			        <input type="text" placeholder="Enter a User Name or Description" name="UserName" id="UserName">  <!-- Name -->
			      </div>
			       <div class="large-6 columns ">
			        <label>Email</label>
			        <input type="email" placeholder="email@email.com" name="UserEmail" id="UserEmail">  <!-- Email -->
			      </div>
			    </div>
			
			    <div class="row">
			    <div class="large-6 columns">
			        <label>Password</label>
			        <input type="password" placeholder="password" name="UserPassword" id="UserPassword">  <!-- Password -->
			      </div>
			 
			    <div  class="large-6 columns" style="padding-top:25px;">
			     
	  <label style="display:inline;" for="userType1"><input name="userType" type="radio"  id="userType1"   value="isPartner" style="display:none;" CHECKED><span class="custom radio checked"></span> Partner</label>
      
      <label style="display:inline;" for="userType2"><input name="userType" type="radio"   id="userType2"  value="isManager" style="display:none;"><span class="custom radio"></span> Manager</label>
			    
     <label style="display:inline;" for="isAdmin"><input type="checkbox" id="isAdmin" name="isAdmin"   style="display: none;"><span class="custom checkbox"></span> Admin </label> 
			      </div> 
			        
			    </div>
			
			    
		 <div class="row">
         <div class="small-4 columns">
          <input type="button" class="button-submit" value="ADD"  onclick="javascript:addClientUsers();"/>
         </div>
         <div class="small-8 columns">
          <input type="button" class="button-submit" value="Cancel" onclick="javascript:showHideDiv('0');"/>
         </div>
        </div>
	 
			</form>
			
       </div>
        <!--************************************ Edit Users *************************************** -->
        <div id="editDiv" style="display:none" >
        <form  >
			 
		<input type="hidden" name="editUserID" id="editUserID" >	
			    <div class="row">
			      <div class="large-6 columns">
			        <label>Name</label>
			         <input type="text" placeholder="Enter a User Name or Description" name="editUserName" id="editUserName" disabled>  <!-- Name -->
			      </div>
			         <div  class="large-6 columns" style="padding-top:25px;">
	 
	
	  <label style="display:inline;" for="editIsPartner"><input name="editUserType" type="radio"  id="editIsPartner" value="isPartner" >  Partner</label>
      
      <label style="display:inline;" for="editIsManager"><input name="editUserType" type="radio"   id="editIsManager" value="isManager" > Manager</label>
			    
     <label style="display:inline;" for="editIsAdmin"><input type="checkbox" id="editIsAdmin" name="editIsAdmin" > Admin </label>      
      </div> 
			    </div>
			
		 
			
			    
		 <div class="row">
         <div class="small-4 columns">
          <input type="button" class="button-submit" value="Update"  onclick="javascript:updateUsers();"/>
         </div>
         <div class="small-8 columns">
          <input type="button" class="button-submit" value="Cancel" onclick="javascript:showHideDiv('2');"/>
         </div>
        </div>
	 
			</form>
			
       </div> 
       
     <!-- Details -->  
    <c:choose>
    <c:when test="${requestScope.userDetails!=null}">     
       
	<div class="row">
				<div class="large-4 columns">
					<h5>Users Details</h5>
				</div>
				<div class="large-8 columns">
					<a class="button-add" href="javascript:showHideDiv('1');">Add</a>
				</div>
			</div>
				 
			<table class="table-transactions">
				<thead>
					<tr> 
						<th  >User Name</th>
						<th>UserType</th>
						<th>Action</th>
							<th>Assign</th> 
					</tr>
				</thead>
				<tbody>
				<c:forEach var="listItems" items="${requestScope.userDetails}">
					<tr>
						<td><c:out value='${listItems.userName}'/> </td>
						<td>  <c:if test="${listItems.isAdmin == true}">Admin</c:if> <c:if test="${listItems.isPartner == true}">Partner</c:if> <c:if test="${listItems.isManager == true}">Manager</c:if> </td>
						 <td> <i class="foundicon-edit" onClick="javascript:editClientUsers('<c:out value='${listItems.id}'/>','<c:out value='${listItems.userName}'/>','<c:out value='${listItems.isAdmin}'/>','<c:out value='${listItems.isPartner}'/>','<c:out value='${listItems.isManager}'/>');"></i><font style="color:black;">/</font><i class="foundicon-trash" onClick="javascript:deleteClientUsers(<c:out value='${listItems.id}'/>);"></i> </td> 
						    <td>  
	<c:if test="${ listItems.isManager  eq true }">		  
	   
   <select title="Basic example" multiple="multiple"  name="example-basic" id="<c:out value='${listItems.id}'/>" style="width:30px;" onChange="javascript:assignPartnerstoManager('#<c:out value='${listItems.id}'/>','<c:out value='${listItems.userId}'/>');">

	<c:forEach var="listItems1" items="${listItems.partnerList}">
	<option value="<c:out value='${listItems1.userId}'/>" <c:out value='${listItems1.status}'/> > <c:out value='${listItems1.userName}'/>  </option> <!--Partner Name  -->
	</c:forEach>
	</select>
	</c:if>
	
	</td>
					</tr>
							
		 
		</c:forEach> 
				</tbody>
			</table>
			
	 </c:when>
    <c:otherwise>
     <div class="large-8 columns" id="Addbutton">No Connection Found! <input type="button"  class="button-add" value="ADD" onclick="javascript:showHideDiv('1');"/> </div>
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