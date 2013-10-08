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
						<legend>  Job</legend>
			<div id="addDiv" style="display:none">			
       <form action="/ClientAddJob.html" method="post" class="custom">    <!--Action   -->
			    

		   <div class="row">
		   			 
		    <div class="large-4 columns">
			  <label for="customDropdown">Client</label>
				      <select id="customDropdown" name="supplierList"  class="medium" > <!--onChange="javascript:getPartnerList();"  -->
				       		<option  selected>Select Supplier</option>
				       	<c:forEach var="listItems" items="${requestScope.supplierDetails}">
				       
				        <option value="<c:out value='${listItems.supplierId}'/>"><c:out value='${listItems.supplierName}'/></option>
				        </c:forEach> 
				      </select>
			      </div>
<c:if test="${sessionScope.isAdmin == true}">			      
<div class="large-4 columns"   >
<label for="partnerList">Partner</label>
<select id="partnerList" name="partnerList" required = "required">
<option  selected>Select Partner</option>
<c:forEach var="listItems" items="${requestScope.partnerList}">
<option value="<c:out value='${listItems.userId}'/>"><c:out value='${listItems.userName}'/></option>
</c:forEach> 
</select>
</div>   
</c:if>	


<c:if test="${sessionScope.isPartner == true}">	
  <div class="large-4 columns"   >
<label for="partnerList">Partner</label>
<select id="partnerList" name="partnerList" required = "required">
<option  value="<c:out value="${sessionScope.userId}"/>" selected><c:out value="${sessionScope.userName}"/></option>
</select>
</div>   
  
</c:if>		      
			      
			      <div class="large-4 columns">
			        <label>File Name</label>
			        <input type="text" placeholder="Enter FileName" name="fileName" id="fileName" required = "required"> <!-- FILE NAME -->
			      </div>
			     
			 </div>  
			
			
			   <div class="row">
			   <div class="large-4 columns">
			        <label>Date Received</label>
			        <input type="date"  name="dateReceived" id="dateReceived" required = "required"> <!-- DATE RECEIVED -->
			      </div>
			      
			      <div class="large-4 columns">
			        <label>Expected date of completion</label>
			        <input type="date" name="expectedDate" id="expectedDate" required = "required"> <!-- DATE EXCEPTED -->
			      </div>
			   <div class="large-4 columns">
			        <label>File Reference</label>
			        <input type="text" placeholder="Enter File Reference number" name="fileReference" id="fileReference" required = "required"> <!-- FILE REFERENCE -->
			       </div>
			     
			
				
			 </div> 
			
			    <div class="row">
			    <div class="large-4 columns">
			        <label>Year Ending</label>
			        <input type="date" name="yearEnding" id="yearEnding" required = "required"> <!--YEAR ENDING -->
			      </div>
			         <div class="large-4 columns">
			        <label>Budgeted Time</label>
			        <input type="time" name="budgetedTime" id="budgetedTime" required = "required"> <!--BUDGETED TIME -->
			      </div>
			       <div class="large-4 columns">
			        <label>File Storage</label>
			        <input type="text" placeholder="Enter File storage number" name="fileStorage" id="fileStorage"> <!--FILE STORAGE -->
			      </div>
			      
			  
			    </div>
			       
			    
			    <div class="row">
		
			     <div class="large-4 columns"  id="loadPartner" style="display:none;">
			       <label for="partnerList">Partner</label>
			       
				      <select id="partnerList" name="partnerList" id="partnerList"  onChange="getAccountantListForPartner();" Style="width:100%;">
			 
				      </select>
				      
			      </div>
			       
			       
			       
			    	 <div class="large-4 columns" id="loadAccountant" style="display:none;">
			       <label for="customDropdown">Accountant</label>
				      <select id="accountantList" name="accountantList" class="medium" Style="width:100%;"  onChange="getReviewerListForAccountant();">
				       	 
				      </select>
			      </div>
			       	 <div class="large-4 columns" id="loadReviewer" style="display:none;">
			       <label for="customDropdown">Reviewer</label>
				      <select id="ReviewerList" name="ReviewerList" class="medium" Style="width:100%;">
				       	 
				      </select>
			      </div>
			      
			   </div>
			    
			
		 	
			
			
			        
			      
			    <div class="row">
			    
		   <div class="large-4 columns">
			        <label>Urgency/Reason</label>
			        <input type="text" placeholder="Enter Comments or description"  name="Urgency" id="Urgency"> <!--Urgency-->
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
			        <input type="text" placeholder="Enter Comments or description"  name="comments" id="comments"> <!--COMMENTS-->
			      </div>
			   
			   
			   
			    </div>
			 
			    <div class="row">
			     <div class="large-4 columns"  > 
			     
  <label for="FollowLeadSchedules">
    <input type="checkbox" id="FollowLeadSchedules" name="FollowLeadSchedules" style="display: none;">
    <span class="custom checkbox"></span> Follow Lead Schedules
  </label>
</div>
			      </div>
			    
			     <div class="row">
			     	<div class="small-4 columns">
			     		<input type="submit" class="button-submit" value="Add" />
			     	</div>
			     	<div class="small-8 columns">
			     		<input type="button" class="button-submit" value="Reset" />
			     	</div>
			     </div>
              


</form>    

 </div>
       
        
	<div class="row">
				<div class="large-4 columns">
					<h5>Job Details</h5>
				</div>
				<div class="large-8 columns">
					<a class="button-add" href="javascript:showHideDiv('1');">Add</a>
				</div>
			</div>

<table class="table-transactions">
				<thead>
					<tr>
						<th>Date</th>
						<th>File Name</th>
						<th>File Reference</th>
						<th>Job Status</th>
						<th>Upload</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="listItems" items="${requestScope.jobDetails}">
					<tr>
						<td><c:out value='${listItems.dateReceived}'/></td>					 
						<td><c:out value='${listItems.fileName}'/></td>
						<td><c:out value='${listItems.fileReference}'/></td>
						<td><form class="custom"  >
						<select id="jobStatus" name="jobStatus"  onChange="javascript:updateJobStatus(this.value,<c:out value='${listItems.id}'/>);" >
     <c:forEach var="listItems1" items="${listItems.jobstatusList}">
    <option value="<c:out value='${listItems1.jobStatusId}'/>" <c:out value='${listItems1.status}'/>><c:out value='${listItems1.jobStatus}'/></option>
    </c:forEach>
   
  </select></form></td> 
   <td><a href="/CoverSheet.html?jobId=<c:out value='${listItems.id}'/>" class="small button">Upload</a></td><!--javascript:UploadFileDiv(<c:out value='${listItems.id}'/>)  -->
						<td><i  class="foundicon-edit"  onclick="javascript:loadEditJobPage(<c:out value='${listItems.id}'/>)"></i> </td>
					</tr>
				</c:forEach>	 
				</tbody>
			</table>

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