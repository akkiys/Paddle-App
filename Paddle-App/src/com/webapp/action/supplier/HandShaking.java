package com.webapp.action.supplier;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.persistence.SupplierDAO;
import com.persistence.mysql.MYSQLSupplierDAO;
import com.webapp.beans.SupplierBeans;

public class HandShaking extends AbstractController{
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    ModelAndView model =null;	
		    HttpSession session = request.getSession(false); // don't create if it doesn't exist
		    SupplierDAO supplierDaoObj=null;
		    ArrayList<SupplierBeans> clientDetails =null;
		    if(session !=null) // checks for session
		    {
		    supplierDaoObj=new MYSQLSupplierDAO(); // supplier DAO object	
		    clientDetails= new ArrayList<SupplierBeans>();   	
		    //Integer userId = (Integer) session.getAttribute("userId"); // user Id	
		    Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id
		    clientDetails=  supplierDaoObj.getClientConnections(supplierId);
		    if(clientDetails.size()>0){
	        	 
	            request.setAttribute("clientDetails", clientDetails);
		    }
		    model = new ModelAndView("/Supplier/HandShaking");	
		    	
		  
		    	
		 		
		    	
		    }
		    else
		    {
		    	
		    	
		    }
		   
	
		    return model;		
		  
	
	
	
	}
		
		
		
}
	
	

 
