 package com.webapp.action.supplier;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.persistence.PaddleDAO;
import com.persistence.SupplierDAO;
import com.persistence.mysql.MYSQLPaddleDAO;
import com.persistence.mysql.MYSQLSupplierDAO;
import com.webapp.beans.JobBeans;
import com.webapp.beans.SupplierBeans;


public class  Jobs extends AbstractController{
 protected ModelAndView handleRequestInternal(HttpServletRequest request,
   HttpServletResponse response) throws Exception {
      ModelAndView model =null; 
      HttpSession session = request.getSession(false); // don't create if it doesn't exist
      SupplierDAO supplierDaoObj=null;
      PaddleDAO  daoObj=null;
      ArrayList<SupplierBeans> clientDetails =null;
      ArrayList<JobBeans> jobDetails =null; 
      if(session !=null) // checks for session
      {
    	  Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier 
      	     supplierDaoObj = new MYSQLSupplierDAO(); // supplier DAO object
    	    clientDetails = new ArrayList<SupplierBeans>();
			clientDetails = supplierDaoObj.getClientConnections(supplierId);
			if (clientDetails.size() > 0) {

				request.setAttribute("clientDetails", clientDetails);
			}
			
			jobDetails= new ArrayList<JobBeans>();
			daoObj = new MYSQLPaddleDAO(); // paddle DAO object
			jobDetails=daoObj.getJobDetails(supplierId, "SupplierId"); 
			if (jobDetails.size() > 0) {

				request.setAttribute("jobDetails", jobDetails);
			}
			
			
        model = new ModelAndView("/Supplier/Jobs"); 
       
      } 
      
      
    

 
 
      return model;  
  
 }
}