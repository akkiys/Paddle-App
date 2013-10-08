<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Job</title>

<link rel="stylesheet" href="/assets/stylesheets/main.css" />
<!-- imports all the CSS Files -->
<script src="/assets/javascripts/vendor/custom.modernizr.js"></script>
<script src="/assets/javascripts/Custom.js"></script>

</head>
<body onload="loadSupplierMenu();">
	<input type="hidden" name="supplierId"
		value='<c:out value="${sessionScope.supplierId}"/>' />
	<input type="hidden" name="userId"
		value='<c:out value="${sessionScope.userId}"/>' />
	<div class="wrap-menu">
		<div class="row">
			<nav class="top-bar">

			<ul class="title-area">
				<!-- Title Area -->
				<li class="name">
					<h1>
						<a href="#">PaddleApp</a>
					</h1>
				</li>
				<!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
				<li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
			</ul>

			<section class="top-bar-section"> <!-- Left Nav Section -->
			<ul class="left">
				<li><a href="#"> <span class="color-red"><c:out
								value="${sessionScope.supplierName}" /></span></a></li>
				<!-- <li class="has-form"><a class="app-menu-button button" href="#pricing">Switch</a></li> -->
			</ul>
			<ul class="show-for-small" id="loadMenu">

			</ul>
			<!-- Right Nav Section -->
			<ul class="right">
			<li><a href="#">Welcome <span class="color-red"><c:out
								value="${sessionScope.userName}" />, <c:if
								test="${sessionScope.isAdmin == true}">Admin</c:if> <c:if
								test="${sessionScope.isAccountant == true}">Accountant</c:if> <c:if
								test="${sessionScope.isReviewer == true}">Reviewer</c:if></span></a></li>
				<li class="has-form"><a href="/Logout.html"
					class="app-menu-button button-logout button">Logout</a></li>
			</ul>
			</section> </nav>
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
						<legend>Edit  Job</legend>
			 	
       <form action="/SupplierUpdateJob.html" method="post" class="custom" >    <!--Action   -->
			    
 <input type="hidden" name="jobId" value="<c:out value='${jobDetails.id}'/>">
		   <div class="row">
		   			 
	             <div class="large-4 columns">  
			        <label>Client Name</label>
			        <input type="hidden" name="editClientList" value="<c:out value='${jobDetails.clientId}'/>">
			        <input type="text" placeholder="Enter FileName" name="EditClientName" id="EditClientName" required = "required" value="<c:out value='${jobDetails.clientName}'/>"> <!-- FILE NAME -->
			      </div>
			      <div class="large-4 columns">
			        <label>File Name</label>
			        <input type="text" placeholder="Enter FileName" name="editfileName" id="editfileName" required = "required" value="<c:out value='${jobDetails.fileName}'/>"> <!-- FILE NAME -->
			      </div>
			      <div class="large-4 columns">
			        <label>File Reference</label>
			        <input type="text" placeholder="Enter File Reference number" name="editfileReference" id="fileReference" required = "required" value="<c:out value='${jobDetails.fileReference}'/>"> <!-- FILE REFERENCE -->
			       </div>
			     </div> 
			
			
			
			
			   <div class="row">
			   <div class="large-4 columns">
			        <label>Date Received</label>
			        <input type="date"  name="dateReceived" id="dateReceived" required = "required" value="<c:out value='${jobDetails.dateReceived}'/>"> <!-- DATE RECEIVED -->
			      </div>
			      <div class="large-4 columns">
			        <label>Expected date of completion</label>
			        <input type="date" name="expectedDate" id="expectedDate" required = "required" value="<c:out value='${jobDetails.expectedDate}'/>"> <!-- DATE EXCEPTED -->
			      </div>
			      <!-- <div class="large-4 columns">
			        <label>Date Returned</label>
			        <input type="date" name="dateReturned" id="dateReturned" required = "required"> DATE RETURNED
			      </div>
			      --> 
				
			     <div class="large-4 columns" style="margin-top:25px;"> 
			     
  <label for="FollowLeadSchedules">
    <input type="checkbox" id="FollowLeadSchedules" name="FollowLeadSchedules" value="<c:out value='${jobDetails.isFollowLeadSchedules}'/>" style="display: none;">
    <span class="custom checkbox"></span> Follow Lead Schedules
  </label>

			      </div>
			 </div> 
			
			    <div class="row">
			    <div class="large-4 columns">
			        <label>Year Ending</label>
			        <input type="date" name="yearEnding" id="yearEnding" required = "required" value="<c:out value='${jobDetails.yearEnding}'/>"> <!--YEAR ENDING -->
			      </div>
			         <div class="large-4 columns">
			        <label>Budgeted Time</label>
			        <input type="time" name="budgetedTime" id="budgetedTime" required = "required" value="<c:out value='${jobDetails.budgetedTime}'/>"> <!--BUDGETED TIME -->
			      </div>
			       <div class="large-4 columns">
			        <label>File Storage</label>
			        <input type="text" placeholder="Enter File storage number" name="fileStorage" id="fileStorage" value="<c:out value='${jobDetails.fileStorage}'/>"> <!--FILE STORAGE -->
			      </div>
			      
			  
			    </div>
			       
			    
			    <div class="row">
		
			     <div class="large-4 columns"  id="loadPartner" >
			       <label for="partnerList">Partner</label>
			       
				    <select id="editpartnerList" name="editpartnerList" required = "required"  >
     
    <option value="<c:out value='${jobDetails.partnerId}'/>" selected><c:out value='${jobDetails.partnerName}'/></option>
     	<c:forEach var="listItems2" items="${requestScope.partnerDetails}">
		 <option value="<c:out value='${listItems2.userId}'/>"><c:out value='${listItems2.userName}'/></option>
			 </c:forEach> 

  </select>
				  
				  
				       
				      
			      </div>
			       
			       
			       
			    	 <div class="large-4 columns" id="loadAccountant"  >
			       <label for="customDropdown">Accountant</label>
				      <select id="editaccountantList" name="editaccountantList" class="medium" Style="width:100%;"   >
				<option value="<c:out value='${jobDetails.accountantId}'/>" selected><c:out value='${jobDetails.accountantName}'/></option>
     	<c:forEach var="listItems3" items="${requestScope.accountantDetails}">
		 <option value="<c:out value='${listItems3.userId}'/>"><c:out value='${listItems3.userName}'/></option>
			 </c:forEach> 	 
				      </select>
			      </div>
			       	 <div class="large-4 columns" id="loadReviewer"  >
			       <label for="customDropdown">Reviewer</label>
				      <select id="ReviewerList" name="editReviewerList" class="medium" Style="width:100%;">
				       <option value="<c:out value='${jobDetails.reviewerId}'/>" selected><c:out value='${jobDetails.reviewerName}'/></option>
     	<c:forEach var="listItems4" items="${requestScope.reviewerDetails}">
		 <option value="<c:out value='${listItems4.userId}'/>"><c:out value='${listItems4.userName}'/></option>
			 </c:forEach> 		 
				      </select>
			      </div>
			      
			   </div>
			    
			
		 	
			
			
			        
			      
			    <div class="row">
			    
		   <div class="large-4 columns">
			        <label>Urgency/Reason</label>
			        <input type="text" placeholder="Enter Comments or description"  name="Urgency" id="Urgency" value="<c:out value='${jobDetails.urgency}'/>"> <!--Urgency-->
			      </div>
			      
				        	     <div class="large-4 columns"   >
			       <label for="PrincipalActivity">Principal Activity</label>
  <select id="PrincipalActivity" name="PrincipalActivity" required = "required">
     
    <option value="1">Film and TV Production</option>
    <option value="2">Fishing</option>
    <option value="3">Banking</option>
  </select>
				      
			      </div>      
			      
			      
			       <div class="large-4 columns">
			        <label>Comments</label>
			        <input type="text" placeholder="Enter Comments or description"  name="comments" id="comments" value="<c:out value='${jobDetails.comments}'/>"> <!--COMMENTS-->
			      </div>
			   
			   
			   
			    </div>
			 
			  
			    
			     <div class="row">
			     	<div class="small-4 columns">
			     		<input type="submit" class="button-submit" value="Update" />
			     	</div>
			     	<div class="small-8 columns">
			     		<a href="/SupplierJobs.html"><input type="button" class="button-submit" value="Cancel"  /></a>
			     	</div>
			     </div>
              


</form> 

 
       
 

 
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
	<script type='text/javascript'
		src="/assets/javascripts/dwr/SupplierDWR.js"></script>
	<script type='text/javascript' src='/dwr/engine.js'></script>
	<script type='text/javascript' src='/dwr/interface/SupplierDWR.js'></script>
	<script type='text/javascript' src='/dwr/util.js'></script>




	<script>
    $(document).foundation();
    $('table').stacktable();
  </script>


			
			
			
</body>
</html>