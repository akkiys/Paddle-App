package com.webapp.action.client;

 
	 

	 

	import java.util.ArrayList;

	import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

	import com.persistence.ClientDAO;
import com.persistence.PaddleDAO;
import com.persistence.mysql.MYSQLClientDAO;
import com.persistence.mysql.MYSQLPaddleDAO;
import com.webapp.beans.ClientBeans;
import com.webapp.beans.JobBeans;


	public class  PartnerJobs extends AbstractController{
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
	  HttpServletResponse response) throws Exception {
	     ModelAndView model =null; 
	     HttpSession session = request.getSession(false); // don't create if it doesn't exist
	     ClientDAO clientDaoObj=null;
	     PaddleDAO  daoObj=null;
	     ArrayList<ClientBeans> supplierDetails =null,partnerList =null; 
	     ArrayList<JobBeans> jobDetails =null;  
	     if(session !=null) // checks for session
	     {

	    	     clientDaoObj = new MYSQLClientDAO(); // Client DAO object
	    	    supplierDetails = new ArrayList<ClientBeans>();
	    		Integer userId = (Integer) session.getAttribute("userId"); // user Id  
				Integer clientId = (Integer) session.getAttribute("clientId"); // Client Id 
				supplierDetails = clientDaoObj.getSupplierConnections(clientId);
				if (supplierDetails.size() > 0) {

					request.setAttribute("supplierDetails", supplierDetails);
				} 
				
				
				
				
				jobDetails= new ArrayList<JobBeans>();
				daoObj = new MYSQLPaddleDAO(); // paddle DAO object
				jobDetails=daoObj.getJobDetails(userId, "partnerId"); 
				if (jobDetails.size() > 0) {

					request.setAttribute("jobDetails", jobDetails);
				}
					
				
				
		 
	      	  model = new ModelAndView("/Client/Jobs"); 
	      
	     } 
	     
	     
	   



	     return model;  
	 
	}
	}