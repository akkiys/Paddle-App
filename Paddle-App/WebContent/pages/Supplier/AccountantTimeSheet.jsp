<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accountant TimeSheet</title>

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


	<div class="wrap-app">   <!--  div starting -->
		<div class="row app-body">
			<div class="large-2 columns wrap-side-nav">
				<ul class="side-nav hide-for-small" id="loadMenu1">

				</ul>
			</div>
			<div class="large-10 columns">
				
					<fieldset>
						<legend>Accountant TimeSheet</legend>
                 <form class="custom"> 
			  
			  
		 <c:if test="${currentJob.isCurrentJob eq false}"> 	 
		 
		  
		<div id="timeSheetMainFunction">	 
		    
			 <div class="row" >
 
   <div  class="large-4 columns"  >
  <label style="display:inline;" for="NewJob"><input name="userType" type="radio"  id="NewJob"  onChange="showHideAccTimeSheet('NewJob');"  value="" style="display:none;" CHECKED><span class="custom radio" ></span> Available Job</label>
   </div>	
   
  <div  class="large-4 columns"  >    
  <label style="display:inline;" for="AssistJob"><input name="userType" type="radio"   id="AssistJob" onChange="showHideAccTimeSheet('AssistJob');"  value="" style="display:none;"><span class="custom radio"></span> Assist Job</label>
  </div>
  
  <div  class="large-4 columns"  > 
  <label style="display:inline;" for="Others"><input name="userType" type="radio"   id="Others"  onChange="showHideAccTimeSheet('Others');" value="" style="display:none;"><span class="custom radio"></span> Others</label>
  </div>
 
		</div>   
		
		<input type="hidden" name="selectedradiovalue" id="selectedradiovalue" value="NewJob"> <!-- selected value - NEW JOB or Assist Job or Others-->
		
			 	<!-- ************* NEW JOB ****************** -->	 		    
			    <div class="row" style="margin-top:30px" id="newJob">
			     <div class="large-6 columns"  >
			       <label for="jobId">Your Job</label>
				      <select id="jobId" name="jobId">
				      <c:forEach var="listItems" items="${requestScope.jobDetails}">
				    <option value="<c:out value='${listItems.id}'/>"  >  <c:out value='${listItems.fileName}'/> </option>  
				    </c:forEach>
				       <!--  <option value >Job1</option> -->
				        
				      </select>
			      </div>
			        <div class="large-5 columns" >
			       <label for="jobStatusId">Job Status</label>
				     <select id="jobStatusId" name="jobStatusId">
				      <c:forEach var="listItems" items="${requestScope.jobStatusList}">
				    <option value="<c:out value='${listItems.id}'/>"  >  <c:out value='${listItems.jobStatus}'/> </option>  
				    </c:forEach>			   
				      </select>
			      </div>	
			   </div>
		<!-- ************* ASSIST JOB ****************** -->	 
		
		  <div class="row" style="margin-top:30px;display:none;" id="assistJob">
		     <div class="large-6 columns"  >
			       <label for="AssitjobId">Jobs</label>
				      <select id="AssitjobId" name="jobId">
				      <c:forEach var="listItems" items="${requestScope.assistJobs}">
				    <option value="<c:out value='${listItems.id}'/>"  >  <c:out value='${listItems.fileName}'/> </option>  
				    </c:forEach>
				       <!--  <option value >Job1</option> -->
				        
				      </select>
			      </div>
			        <div class="large-5 columns" >
			       <label for="AssitJobStatus">ASSIST JOB</label>
				      <select id="AssitJobStatus">
				        <option value="7">ASSISTING</option>
				         
				      </select>
			      </div>	 
			   </div>
			   
			   
		<!-- ************* OTHERS ****************** -->			   
		
			  <div class="row" style="margin-top:30px;display:none;" id="others">
	 
			        <div class="large-5 columns" >
			       <label for="othersStatus">OTHERS</label>
				      <select id="othersStatus" onChange="accountantOthers(this.value)" >
				        <option value="8">Training</option>
				        <option value="9">General Admin</option>
				        <option value="10">Paid Leave</option>
				        <option value="11">Unpaid Leave</option>
				         <option value="12">Sick Leave</option>
				          <option value="13">Break</option>
<option value="14">Public Holiday</option>
<option value="15">Exams</option>
<option value="16">Idle</option>
				      </select>
			      </div>	 
			   </div>
			   
		</div>	   
			   
		</c:if>	   
			   
			    <c:if test="${currentJob.isCurrentJob eq true}"> 	
			    
	<div id="showStatusOfTimeSheet" ><!--style="display:none"  -->
	
	
		<table class="table-transactions">
				<thead>
					<tr>
						<th>Current Jobs</th>
						<th>Status</th>
						<th>Time Spent</th>
						 
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><c:out value='${currentJob.fileName}'/></td>
						<td><c:out value='${currentJob.jobStatus}'/></td>
						<td>6:15</td>
						 
					</tr>
				</tbody>
			</table>
	
	
	</div>		   
			</c:if>   
			   
			  <!-- ******** START STOP*****************  --> 
			   	    
		 <div id="StartStop"> <!-- StartStop Div  -->
		<div class="row"  style="margin-top:30px"> 
		<center>
		<div class="switch large round small-6" >
  <input id="z" name="switch-z" type="radio" <c:out value='${currentJob.startJob}'/>  onchange="startJob('accountant'); "    ><!-- enableFunctionOnStartTimeSheet(); -->
  <label for="z" onclick="">START </label>

  <input id="z1" name="switch-z" type="radio"  <c:out value='${currentJob.stopJob}'/> onchange="stopJob('accountant',<c:out value='${currentJob.id}'/>); " ><!--disableFunctionOnStartTimeSheet();  -->
  <label for="z1" onclick=""> STOP</label>

  <span></span>
  
</div>
</center>
</div>
  <div class="row">
<div class="large-12 columns" style="padding-top:10px;">
        <label>Notes</label>
        <textarea placeholder="Enter Comments " name="Comments" id="Comments"  ></textarea>
      </div>
    </div>

</div>   <!--end StartStop Div  -->


<!-- ******** LEAVE FORM ************  -->
  
 
			
		<div id="leaveForm" style="display:none">	
			   <div class="row">
			   <div class="large-6 columns">
			        <label>Start Date</label>
			        <input type="date"  name="StartDate" id="StartDate" required = "required"> <!--START DATE   -->
			      </div>
			      
			        <div class="large-6 columns">
			        <label>End Date</label>
			        <input type="date" placeholder="Enter the date" name="EndDate" id="EndDate" required = "required"> <!-- END DATE -->
			       </div>
			       
			   
			     
			
				
			 </div> 
			
			 <!--    <div class="row">
			     <div class="large-6 columns">
			        <label>Start Time</label>
			        <input type="time" name="StartTime" id="StartTime" required = "required"> Start TIME
			      </div>
			   
			   <div class="large-6 columns">
			        <label>End Time</label>
			        <input type="time" name="EndTime" id="EndTime" required = "required"> End TIME
			      </div>
			    </div> -->
			       
			       
			         <div class="row">
<div class="large-12 columns"  >
        <label>Notes</label>
        <textarea placeholder="Enter Comments " name="LeaveComments" id="LeaveComments"  ></textarea>
      </div>
    </div>

 
			    
			     <div class="row">
			     	<div class="small-4 columns">
			     		<input type="button" class="button-submit" value="Add" onClick="addLeaveForm()" />
			     	</div>
			     	<!-- <div class="small-8 columns">
			     		<input type="button" class="button-submit" value="Reset" />
			     	</div> -->
			     </div>
              
</div>
 

<!--************ END LEAVE FORM *************  -->


<input type="hidden" id="usertType" name="usertType" value="true"> <!--True= accountant; false= reviewer   -->
			  </form>





					</fieldset>
			</div>

		</div>
	</div> <!-- div ending  -->

   
   
   
   
   		 
						    
		 
   
   
   

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