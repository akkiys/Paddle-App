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

public class SupplierUsers extends AbstractController{
		protected ModelAndView handleRequestInternal(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			    ModelAndView model =null;	
			    HttpSession session = request.getSession(false); // don't create if it doesn't exist
			    SupplierDAO supplierDaoObj=null;
			    ArrayList<SupplierBeans> userDetails =null;
			    if(session !=null) // checks for session
			    {
			    Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id		
			    supplierDaoObj=new MYSQLSupplierDAO(); // supplier DAO object	
			    userDetails=new ArrayList<SupplierBeans>();   		
			  //  System.out.println("supplierId>>> "+supplierId);
			    userDetails=supplierDaoObj.getSupplierUsers(supplierId); // gets user details
			    if(userDetails.size()>0){
		        	System.out.println("Sizzeee>>> "+userDetails.size());
		            request.setAttribute("userDetails", userDetails);
			    }	
			    
			    model = new ModelAndView("/Supplier/SupplierUsers");	
			    	
			  
			    	
			 		
			    	
			    }
			    else
			    {
			    	
			    	
			    }
			   
		
			    return model;		
			  
		
		
		
		}
			
			
			
	}
		
		
