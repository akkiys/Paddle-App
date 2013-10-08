package com.webapp.action.supplier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.beans.PaddleBeans;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.common.ParameterParser;
import com.persistence.PaddleDAO;
import com.persistence.mysql.MYSQLPaddleDAO;

public class SupplierSignup extends AbstractController{
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
		boolean isEmailAvaliable =false;
		ParameterParser prsObj = new ParameterParser(request); 
		String SupplierCompanyname=prsObj.getStringParameter("SupplierCompanyname"); // Company Name
		String SupplierName=prsObj.getStringParameter("SupplierName");
		String SupplierEmail=prsObj.getStringParameter("SupplierEmail");
		String SupplierPassword=prsObj.getStringParameter("SupplierPassword");
		String SupplierMobile=prsObj.getStringParameter("SupplierMobile");
		
		//System.out.println("SupplierCompanyname  "+ SupplierCompanyname);
		//System.out.println("SupplierName  "+ SupplierName);
		//System.out.println("SupplierEmail  "+ SupplierEmail);
		//System.out.println("SupplierPassword  "+ SupplierPassword);
		//System.out.println("SupplierMobile "+ SupplierMobile); 
	     
		 if (!SupplierEmail.equals("")) // checks for email is available
		   {
			   isEmailAvaliable=daoObj.isEmailAvaliable(SupplierEmail);
			   
		   }
		   //System.out.println("isEmailAvaliable>>>. " +isEmailAvaliable);
		   if(isEmailAvaliable==true)
		   {
	      // System.out.println("Email not registerd!!!");
	       PaddleBeans details = new PaddleBeans(); // beans object
	       details.setSupplierName(SupplierName);
	       details.setCompanyName(SupplierCompanyname);
	       details.setEmail(SupplierEmail);
	       details.setPassword(SupplierPassword);
	       details.setMobile(SupplierMobile);
	       daoObj. insertNewSupplier(details); // inserts new Client
		   }
		   else
		   {
			   System.out.println("Email exists !!!");
			   
		   }
			
			
			
			
	        
		ModelAndView model = new ModelAndView("index");
	 
			return model;
		}	

}
