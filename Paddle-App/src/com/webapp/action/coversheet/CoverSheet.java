package com.webapp.action.coversheet;

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.common.ParameterParser;
import com.persistence.PaddleDAO;
 
import com.persistence.mysql.MYSQLPaddleDAO;
 
import com.webapp.beans.CoverSheetBeans;
import com.webapp.beans.JobBeans;
 

public class CoverSheet extends AbstractController{
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    ModelAndView model =null;	
		    HttpSession session = request.getSession(false); // don't create if it doesn't exist
		    PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
		    ParameterParser prsObj = new ParameterParser(request); 
			JobBeans details=null;
			CoverSheetBeans coverdetails=null,coverCheckdetails=null;
		    if(session !=null) // checks for session
		    {
		    	details=new JobBeans();
		    	coverdetails=new CoverSheetBeans();
		    	coverCheckdetails=new CoverSheetBeans();
				int jobId=prsObj.getIntParameter("jobId",0); // Job Id
				  
				System.out.println("EditJob>>> "+jobId);
			 
				if(jobId!=0)
				{
				details= daoObj.getJobDetails(jobId) ;
				request.setAttribute("jobDetails", details); 
				
				coverdetails =daoObj.getCoverSheetJob(jobId);
				request.setAttribute("coverdetails", coverdetails); 
				
				coverCheckdetails=daoObj.getCoverSheetCheckListJob(jobId);
			    request.setAttribute("coverCheckdetails", coverCheckdetails);
				} 
				
				if(session.getAttribute("supplierId")!=null)
				{
		    	 model = new ModelAndView("/Supplier/CoverSheet");	
				}
				else if(session.getAttribute("clientId")!=null)
				{
					 model = new ModelAndView("/Client/CoverSheet");	
				}
		    	 
		   // }	
		    
		    
		   
		  
	
	
	
	}
		    return model;		
		
	}
}
		
		


	
