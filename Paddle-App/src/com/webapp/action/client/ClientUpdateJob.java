package com.webapp.action.client;

 
	 import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	 import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.common.ParameterParser;
import com.persistence.PaddleDAO;
import com.persistence.mysql.MYSQLPaddleDAO;
import com.webapp.beans.JobBeans;
import com.webapp.beans.SupplierBeans;
import com.webapp.util.DateUtil;


	 public class  ClientUpdateJob extends AbstractController{
	  protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	       ModelAndView model =null; 
	       HttpSession session = request.getSession(false); // don't create if it doesn't exist
	      // SupplierDAO supplierDaoObj=null;
	     
	       PaddleDAO  daoObj=null;
	    	ParameterParser prsObj =null;
	    	JobBeans jobDetails=null;
	       if(session !=null) // checks for session
	       {
	    		  prsObj = new ParameterParser(request); 
	    		  daoObj = new MYSQLPaddleDAO(); // paddle DAO object
	    		  Integer userId = (Integer) session.getAttribute("userId"); // user Id 
	    		  Integer clientId = (Integer) session.getAttribute("clientId"); // supplier 
	    		  jobDetails = new JobBeans();
	    		  jobDetails.setClientId(clientId);
	    		  jobDetails.setSupplierId(prsObj.getIntParameter("editSupplierList",0));
	    		  jobDetails.setFileName(prsObj.getStringParameter("editfileName","")); //File Name;
	    		  jobDetails.setFileReference(prsObj.getStringParameter("editfileReference","")); //File Name;
	    		  jobDetails.setDateReceived (prsObj.getStringParameter("dateReceived",DateUtil.getCurrentDate())); //date Received
	    		  jobDetails.setExpectedDate(prsObj.getStringParameter("expectedDate","")); //expected Date
	    		  jobDetails.setDateReturned(prsObj.getStringParameter("dateReturned",null)); //expected Date
	    		  jobDetails.setYearEnding(prsObj.getStringParameter("yearEnding","")); //year Ending
	    		  jobDetails.setBudgetedTime(prsObj.getStringParameter("budgetedTime","")); //budgeted Time
	    		  jobDetails.setFileStorage(prsObj.getStringParameter("fileStorage","")); //file Storage
	    		  jobDetails.setPartnerId(prsObj.getIntParameter("editpartnerList",0)); //partner Id
	    		  jobDetails.setAccountantId(prsObj.getIntParameter("editaccountantList",0)); //accountant Id
	    		  jobDetails.setReviewerId(prsObj.getIntParameter("editReviewerList",0)); //Reviewer Id
	    		  jobDetails.setIsFollowLeadSchedules(prsObj.getBooleanParameter("FollowLeadSchedules",false)); //Reviewer Id
	    		  jobDetails.setPrincipalActivityId(prsObj.getIntParameter("PrincipalActivity",0)); //Principal Activity
	    		  jobDetails.setComments(prsObj.getStringParameter("comments","")); //comments
	    		  jobDetails.setUrgency(prsObj.getStringParameter("Urgency","")); //Urgency
	    		  jobDetails.setUserId(userId);
	    		  jobDetails.setId(prsObj.getIntParameter("jobId",0)); 
	    	      
	    	      
	    	     /* System.out.println("ClientId>>> "+ jobDetails.getClientId());
	    	      System.out.println("fileName>>> "+jobDetails.getFileName());
	    	      System.out.println("fileReference>>> "+jobDetails.getFileReference());
	    	      System.out.println("dateReceived>>> "+jobDetails.getDateReceived());
	    	      System.out.println("expectedDate>>> "+jobDetails.getExpectedDate());
	    	      System.out.println("dateReturned>>> "+jobDetails.getDateReturned());
	    	      System.out.println("yearEnding>>> "+jobDetails.getYearEnding());
	    	      System.out.println("budgetedTime>>> "+jobDetails.getBudgetedTime());
	    	      System.out.println("fileStorage>>> "+jobDetails.getFileStorage());
	    	      System.out.println("partnerId>>> "+jobDetails.getPartnerId());
	    	      System.out.println("accountantId>>> "+jobDetails.getAccountantId());
	    	      System.out.println("ReviewerId>>> "+jobDetails.getReviewerId());
	    	      System.out.println("isFollowLeadSchedules>>> "+jobDetails.getIsFollowLeadSchedules());
	    	      System.out.println("PrincipalActivity>>> "+jobDetails.getPrincipalActivityId());
	    	      System.out.println("comments>>> "+jobDetails.getComments());
	    	      System.out.println("Urgency>>> "+jobDetails.getUrgency()); 
	    	      */
	    	      
	       	     // supplierDaoObj = new MYSQLSupplierDAO(); // supplier DAO object
	    	    if(jobDetails.getId()!=0)  
	    	    {
	       	    daoObj.updateJob(jobDetails);
	       	   
	    	    }
	 			//Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier 
	 			 
	    	      model = new ModelAndView("redirect:ClientJobs.html"); 	
	        // model = new ModelAndView("/Supplier/Jobs"); 
	       } 
	       
	       
	     

	  
	  
	       return model;  
	   
	  }
	 }