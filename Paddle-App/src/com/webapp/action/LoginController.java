package com.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.webapp.beans.PaddleBeans;
import com.common.ParameterParser;
import com.persistence.PaddleDAO;
import com.persistence.mysql.MYSQLPaddleDAO;

public class LoginController extends AbstractController{

	private HttpSession session;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model =null;
		session = null;
		PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
		ParameterParser prsObj = new ParameterParser(request); 
		String email=prsObj.getStringParameter("email",""); // Company Name
		String password=prsObj.getStringParameter("password","");
		//System.out.println("email  "+ email);
		//System.out.println("password  "+ password);
		request.getSession(false).invalidate();
		
		if(!(email.equals("") ))
		{
			
			PaddleBeans PaddleBeans = daoObj.loginCheck(email, password); // check valid login & get details

	   if(PaddleBeans.getResult() == true) //Valid login 
	   {
		   
		if(PaddleBeans.getIsClient() == true) // CLIENT AREA
		{
			PaddleBeans details = daoObj.getClientUserDetails(PaddleBeans.getId(), PaddleBeans.getIsClient());
			session = request.getSession(); // create session
			session.setAttribute("clientName", details.getClientName()); // Client Name
			session.setAttribute("clientId", details.getClientId()); // Client Id
			session.setAttribute("userName", details.getUserName()); // User Name
			session.setAttribute("userId", details.getUserId()); // User ID
			session.setAttribute("email", details.getEmail()); // User Email
			session.setAttribute("isAdmin", details.getIsAdmin()); // Admin
			session.setAttribute("isPartner", details.getIsPartner()); // Partner
			session.setAttribute("isManager", details.getIsManager()); // Manager
			System.out.println("details.getIsPartner() "+details.getIsPartner());
		    model = new ModelAndView("/Client/ClientHome");
				
			
		}
		else  // SUPPLIER AREA
		{
			PaddleBeans details = daoObj.getSupplierUserDetails(PaddleBeans.getId(), PaddleBeans.getIsClient());
			session = request.getSession(); // create session
			 session.setAttribute("supplierName", details.getSupplierName()); // Supplier Name
			session.setAttribute("supplierId", details.getSupplierId()); // Supplier Id
			session.setAttribute("userName", details.getUserName()); // User Name
			session.setAttribute("userId", details.getUserId()); // User ID
			session.setAttribute("email", details.getEmail()); // User Email
			session.setAttribute("isAdmin", details.getIsAdmin()); 
			session.setAttribute("isAccountant", details.getIsAccountant()); 
			session.setAttribute("isReviewer", details.getIsReviewer()); 
			System.out.println(details.getIsAdmin());
			//session.setAttribute("supplier", details); 
			 model = new ModelAndView("/Supplier/SupplierHome");
				
			
		}
		}
	   else
	   {
		   model = new ModelAndView("index"); //invalid login 
		   model.addObject("msg", "Invalid Login Details");
	   }
			
		}
		else
		{
			model = new ModelAndView("index");
			model.addObject("msg", "Email is empty!");
		}
	
		
		
		 
		
		return model;
	}

}
