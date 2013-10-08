<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PaddleApp</title>
 
 <link rel="stylesheet" href="assets/stylesheets/main.css" /> <!-- imports all the CSS Files -->
  
  <script src="assets/javascripts/vendor/custom.modernizr.js"></script>
</head>
<body>
<div id="model-login" class="reveal-modal">
	<h2>Login</h2>
	<form action="/Home.html" method="post">
		<input type="email"  placeholder="Email Id" name="email">
		<input type="password"  placeholder="Password" name="password">
		<input type="submit" class="rm-button" value="Login" />
	</form>
	<a class="close-reveal-modal">&#215;</a>
</div>


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
					  <li><a href="#works">How It Works</a></li>
					  <li><a href="#pricing">Pricing</a></li>
					  <li><a href="#">About</a></li>
					  <li><a href="#">FAQ</a></li>
					  <li><a href="#">Testimonial</a></li>
					  <li><a href="#">Contact</a></li>
					</ul>
					<!-- Right Nav Section -->
					<ul class="right">
						<li><a href="#" data-reveal-id="model-login">Login</a></li>
					</ul>
				</section>
			
		</nav>
	</div>
</div>

<div class="wrap-header">	

	<div class="row header-row">
	
	
	<center><h6>${msg}</h6></center>
		<div class="large-6 columns">
			<h1><a href="#">PaddleApp</a></h1>
			<ul>
				<li>Loyalty And Rewards</li>
				<li>Build Customer Relations</li>
				<li>Increase Retention</li>
				<li>Generate New Sales</li>
			</ul>
		</div>
		<div class="large-6 columns">
		
			<form>
				<div class="row">
					<div class="small-11 columns">
						<div class="row collapse">
							<div class="small-8 columns">
								<input type="text" placeholder="Enter Your Email ID">
							</div>
							<div class="small-4 columns">
						<a href="pages/SignUp.jsp">	<input type="button" class="pp-button prefix" value="SignUp" ></a>	
							</div>
							<div class="small-12 columns">
								<p>First 250 Transaction are free.</p>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="wrap-desc">	
	<div class="row">
		<div class="large-11 large-centered columns">
			<div class="row">
				<div class="large-4 columns">
					<ul>
						<li>Cloud Based System</li>
						<li>Sms Enabled</li>
					</ul>
				</div>
				<div class="large-4 columns">
					<ul>
						<li>Simple & Easy To Use</li>
						<li>Cross-platform</li>
					</ul>
				</div>
				<div class="large-4 columns">
					<ul>
						<li>No Cards Free & Premium</li>
						<li>No Equipment</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="small-8 small-centered columns">
			<p>According to Inc.com, it costs a business about 5-10 times more to acquire a new customer than it does to sell to an existing one & on average those current customers of yours spend 67% more than a new one.</p>
			<hr />
		</div>
		<div class="small-8 small-centered columns">
			<p class="second-desc">Perkplus is a loyalty reward system empowering businesses to increase customer retention & generate new sales from satisfied customers.</p>
		</div>
	</div>
</div>

<a name="works"></a>
<div class="wrap-works">	
	<div class="row">
		<div class="large-11 large-centered columns">
			<h3>How This Works</h3>
			<p>Use perkplus, our simple, yet effective, no nonsense loyalty system, to keep your customers coming back to your business.</p>
		</div>
		<div class="large-11 large-centered columns">
			<div class="row">
				<div class="large-3 columns">
					<h5>Customer Service</h5>
				</div>
				<div class="large-3 columns">
					<h5>Customer Referrals</h5>
				</div>
				<div class="large-3 columns">
					<h5>Customer Re-orders</h5>
				</div>
				<div class="large-3 columns">
					<h5>Complaint Management</h5>
				</div>
			</div>
		</div>
	</div>
</div>

<a name="pricing"></a>
<div class="wrap-pricing">	
	<div class="row">
		<div class="large-11 large-centered columns">
			<h3>Pricing</h3>
		</div>
		<div class="large-9 large-centered columns">
			<p>Pay month to month, upgrade, downgrade or cancel anytime.</p>
		</div>
		<div class="large-11 large-centered columns">
			<div class="row">
				<div class="large-3 columns">
					<ul class="pricing-table">
						<li class="title">FREE</li>
						<li class="price">£0</li>
						<li class="description">250 Transactions</li>
						<li class="description">£0.10 Extra Transaction</li>
						<li class="description">&nbsp;</li>
						<li class="cta-button"><a class="pt-button" href="#">Buy Now</a></li>
					</ul>
				</div>
				<div class="large-3 columns">
					<ul class="pricing-table pt-popular">
						<li class="title">Pro</li>
						<li class="price">£10/Mo</li>
						<li class="description">12,500 Transactions</li>
						<li class="description">£0.10 Extra Transaction</li>
						<li class="description">Most Popular</li>
						<li class="cta-button"><a class="pt-button" href="#">Buy Now</a></li>
					</ul>
				</div>
				<div class="large-3 columns">
					<ul class="pricing-table">
						<li class="title">Plus</li>
						<li class="price">£20/Mo</li>
						<li class="description">25,000 Transactions</li>
						<li class="description">£0.10 Extra Transaction</li>
						<li class="description">&nbsp;</li>
						<li class="cta-button"><a class="pt-button" href="#">Buy Now</a></li>
					</ul>
				</div>
				<div class="large-3 columns">
					<ul class="pricing-table">
						<li class="title">Advanced</li>
						<li class="price">£30/Mo</li>
						<li class="description">50,000 Transactions</li>
						<li class="description">£0.10 Extra Transaction</li>
						<li class="description">&nbsp;</li>
						<li class="cta-button"><a class="pt-button" href="#">Buy Now</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="large-9 large-centered columns pt-contact">
			<p>Contact support@earnperk.com if you want to cancel your subscription.</p>
		</div>
	</div>
</div>

<div class="wrap-footer">	
	<div class="row">
		<div class="small-10 small-centered columns">
			<ul class="inline-list">
				<li><a href="#">Copyright &copy; TallyO 2013</a></li>
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
  ('__proto__' in {} ? 'assets/javascripts/vendor/zepto' : 'assets/javascripts/vendor/jquery') +
  '.js><\/script>')
  </script>
  
  <script src="assets/javascripts/foundation/foundation.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.alerts.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.clearing.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.cookie.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.dropdown.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.forms.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.joyride.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.magellan.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.orbit.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.placeholder.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.reveal.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.section.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.tooltips.js"></script>
	
	<script src="assets/javascripts/foundation/foundation.topbar.js"></script>
	
  
  <script>
    $(document).foundation();
  </script>
</body>
</html>