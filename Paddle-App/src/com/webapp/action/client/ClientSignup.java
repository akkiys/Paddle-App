package com.webapp.action.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.webapp.beans.PaddleBeans;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.common.ParameterParser;
import com.persistence.PaddleDAO;
import com.persistence.mysql.MYSQLPaddleDAO;;
 

public class ClientSignup  extends AbstractController{
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
		boolean isEmailAvaliable =false;
		ParameterParser prsObj = new ParameterParser(request); 
		String companyName=prsObj.getStringParameter("CompanyName"); // Company Name
		String clientName=prsObj.getStringParameter("ClientName");
		String clientEmail=prsObj.getStringParameter("ClientEmail");
		String password=prsObj.getStringParameter("Password");
		String clientMobile=prsObj.getStringParameter("ClientMobile");
		
		System.out.println("companyName  "+ companyName);
		System.out.println("clientName  "+ clientName);
		System.out.println("clientEmail  "+ clientEmail);
		System.out.println("password  "+ password);
		System.out.println("clientMobile "+ clientMobile);
		
	   if (!clientEmail.equals("")) // checks for email is available
	   {
		   isEmailAvaliable=daoObj.isEmailAvaliable(clientEmail);
		   
	   }
	   System.out.println("isEmailAvaliable>>>. " +isEmailAvaliable);
	   if(isEmailAvaliable==true)
	   {
       System.out.println("Email not registerd!!!");
       PaddleBeans details = new PaddleBeans(); // beans object
       details.setClientName(clientName);
       details.setCompanyName(companyName);
       details.setEmail(clientEmail);
       details.setPassword(password);
       details.setMobile(clientMobile);
       daoObj.insertNewClient(details); // inserts new Client
	   }
	   else
	   {
		   System.out.println("Email exists !!!");
		   
	   }
		
		
		
		
	 
        
		ModelAndView model = new ModelAndView("index");
		//model.addObject("msg", "hello world");
 
		return model;
	}	

}
