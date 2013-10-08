package com.webapp.action.client;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.persistence.ClientDAO;
import com.persistence.SupplierDAO;
import com.persistence.mysql.MYSQLClientDAO;
import com.persistence.mysql.MYSQLSupplierDAO;
import com.webapp.beans.ClientBeans;
import com.webapp.beans.SupplierBeans;

 
public class ClientUsers extends AbstractController{
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    ModelAndView model =null;	
		    HttpSession session = request.getSession(false); // don't create if it doesn't exist
		    ClientDAO clientDaoObj=null;
		    ArrayList<ClientBeans> userDetails =null,partnerDetails =null;
		    if(session !=null) // checks for session
		    {
		    Integer clientId = (Integer) session.getAttribute("clientId"); // Client Id		
		    clientDaoObj=new MYSQLClientDAO(); // Client DAO object	
		    userDetails=new ArrayList<ClientBeans>();   		
		    userDetails=clientDaoObj.getClientUsers(clientId); // gets user details
		    if(userDetails.size()>0){
	        	 
	            request.setAttribute("userDetails", userDetails);
		    }	
		    
		    
		    partnerDetails=new ArrayList<ClientBeans>();   	
		    partnerDetails=clientDaoObj.getPartnerList(clientId); // gets Partner List
		    if(userDetails.size()>0){
	        	 
	            request.setAttribute("partnerDetails", partnerDetails);
		    }	
		    
		     
		    model = new ModelAndView("/Client/ClientUsers");	
		    	
		  
		    	
		 		
		    	
		    }
		    else
		    {
		    	
		    	
		    }
		   
	
		    return model;		
		  
	
	
	
	}
		
		
		
}
	
	