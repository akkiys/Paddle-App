<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SignUp</title>

 <link rel="stylesheet" href="/assets/stylesheets/main.css" /> <!-- imports all the CSS Files -->
  <script src="/assets/javascripts/vendor/custom.modernizr.js"></script>
  <script type="text/javascript">
  
  function showHide(type)
  {

   
  	if(type=="Customer")
  	{
  	 
   document.getElementById("businessSignup").style.display="none";

  document.getElementById("customerSignup").style.display="";

  	}
  else if(type=="Business")
  {
  	 
  	 document.getElementById("businessSignup").style.display="";
  document.getElementById("customerSignup").style.display="none";
   

   
  }
  }

  
  </script>


</head>
<body>

<div class="wrap-menu">
	<div class="row">
		<nav class="top-bar">
			
				<ul class="title-area">
				    <!-- Title Area -->
				    <li class="name">
				      <h1><a href="#">PaddleApp </a>  </h1>
					 
				    </li>
               
				    <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
				     <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>  
				</ul>
			
				<section class="top-bar-section">
				 
		 	<ul class="show-for-small">
					 
					</ul>  
					<!-- Right Nav Section -->
				 <ul class="right">
						 
					<form class="custom">	
					<li class="has-radio" onclick="showHide('Business');"><label for="Customer" style="color:white;">
    <input name="type" type="radio" id="Business"  style="display:none;" CHECKED value="BUSINESS"  >
    <span class="custom radio"></span> Client</label> 
  </li>
<li class="has-radio"  onclick="showHide('Customer');"><label for="Customer" style="color:white;">
    <input name="type" type="radio" id="Customer" style="display:none;" value="CUSTOMER" >
    <span class="custom radio"></span> Supplier
  </label> 
  </li></form>
					</ul>  
				</section>			
		</nav>
	</div>
</div>

<!-- ******************************************* Client Sign Up*********************************************************************8 -->

<div class="wrap-app">	
<div class="error"><BR><BR></div>
	<div class="row app-body" id="businessSignup" >
    <div class="large-10 columns"   style="">
			<form class="custom" method="post" action="/ClientSignup.html">
			  <fieldset>
			    <legend>CLIENT SIGNUP</legend>
			
			    <div class="row">
			      <div class="large-012 columns">
			        <label>Company Name</label>
			        <input type="text" placeholder="Enter Company Name or Description" id="CompanyName" name="CompanyName">
			      </div>
			      <div class="large-012 columns">
			        <label>Client Name</label>
			        <input type="text" placeholder="Enter Client Name or Description" id="ClientName" name="ClientName">
			      </div>
			    </div>
			
			    <div class="row">
			      <div class="large-4 columns">
			        <label>Client Email</label>
			        <input type="email" placeholder="email@email.com" id="ClientEmail" name="ClientEmail">
			      </div>
			      <div class="large-4 columns">
			        <label>Password</label>
			        <input type="password" placeholder="password" id="Password" name="Password">
			      </div>
			       <div class="large-4 columns">
			        <label>Mobile</label>
			        <input type="text" placeholder="mobile number with country code" id="ClientMobile" name="ClientMobile">
			      </div>
			    </div>
			
		 
			     <div class="row">
			     	<div class="small-4 columns">
			     		<input type="submit" class="button-submit" value="Save" />
			     	</div>
			     	<div class="small-8 columns">
			     		<A HREF="/Home.do"><input type="button" class="button-submit" value="Cancel" /></A>
			     	</div>
			     </div>
			  </fieldset>
			</form>
			
		</div>

</div>
<!-- ******************************************* Supplier Sign Up*********************************************************************8 -->


<div class="row app-body"   id="customerSignup"  style="display:none;">
<div class="large-10 columns" >
			 <form class="custom" method="post" action="/SupplierSignup.html">
     <fieldset>
       <legend>SUPPLIER SIGNUP</legend>
   
       <div class="row">
         <div class="large-012 columns">
           <label>Company Name</label>
           <input type="text" placeholder="Enter Company Name or description" id="SupplierCompanyname" name="SupplierCompanyname">
         </div>
          <div class="large-012 columns">
           <label>Supplier Name</label>
           <input type="text" placeholder="Enter Supplier Name or Description" id="SupplierName" name="SupplierName">
         </div>
       </div>
   
       <div class="row">
         <div class="large-4 columns">
           <label>Supplier Email</label>
           <input type="text" placeholder="email@email.com" id="SupplierEmail" name="SupplierEmail">
         </div>
         <div class="large-4 columns">
           <label>Password</label>
           <input type="password" placeholder="password" id="SupplierPassword" name="SupplierPassword">
         </div>
          <div class="large-4 columns">
           <label>Mobile</label>
           <input type="text" placeholder="mobile number with country code" id="SupplierMobile" name="SupplierMobile">
         </div>
       </div>
   
   
        <div class="row">
         <div class="small-4 columns">
          <input type="submit" class="button-submit" value="Save" />
         </div>
         <div class="small-8 columns">
          <A HREF="/Home.do"><input type="button" class="button-submit" value="Cancel" /></A>
         </div>
        </div>
     </fieldset>
   </form>
			
		</div>
	</div>

<!--  -->





</div>




















<div class="wrap-footer">	
	<div class="row">
		<div class="small-10 small-centered columns">
			<ul class="inline-list">
				<li><a href="#">Copyright &copy; PerkPlus 2013</a></li>
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
  
  <script src="/assets/javascripts/foundation/foundation.js"></script>
	
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
	
	 
  
  <script>
    $(document).foundation();
    
  </script>
</body> 
</html>