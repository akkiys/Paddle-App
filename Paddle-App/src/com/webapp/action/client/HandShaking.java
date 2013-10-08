package com.webapp.action.client;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.persistence.ClientDAO;
import com.persistence.mysql.MYSQLClientDAO;
import com.webapp.beans.ClientBeans;
 
 

public class HandShaking extends AbstractController{
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    ModelAndView model =null;	
		    HttpSession session = request.getSession(false); // don't create if it doesn't exist
		    ClientDAO clientDaoObj=null;
		    ArrayList<ClientBeans> supplierDetails =null;
		    if(session !=null) // checks for session
		    {
		    clientDaoObj=new MYSQLClientDAO(); // Client DAO object	
		    supplierDetails= new ArrayList<ClientBeans>();   	
		   // Integer userId = (Integer) session.getAttribute("userId"); // user Id	
		    Integer clientId = (Integer) session.getAttribute("clientId"); // client  Id
		    supplierDetails=  clientDaoObj.getSupplierConnections(clientId); // get supplier details - connected
		    if(supplierDetails.size()>0){
	        	 
	            request.setAttribute("supplierDetails", supplierDetails);
			    
		    }
		    model = new ModelAndView("/Client/HandShaking");	
		    	
		    
		    	
		 		
		    	
		    }
		    else
		    {
		    	
		    	// session expired
		    }
		   
	
		    return model;		
		  
	
	
	
	}
		
		
		
}