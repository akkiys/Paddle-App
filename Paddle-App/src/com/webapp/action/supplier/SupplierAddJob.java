 
	 package com.webapp.action.supplier;

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


	 public class  SupplierAddJob extends AbstractController{
	  protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	       ModelAndView model =null; 
	       HttpSession session = request.getSession(false); // don't create if it doesn't exist
	      // SupplierDAO supplierDaoObj=null;
	       ArrayList<SupplierBeans> clientDetails =null; 
	       PaddleDAO  daoObj=null;
	    	ParameterParser prsObj =null;
	    	JobBeans jobDetails=null;
	       if(session !=null) // checks for session
	       {
	    		  prsObj = new ParameterParser(request); 
	    		  daoObj = new MYSQLPaddleDAO(); // paddle DAO object
	    		  Integer userId = (Integer) session.getAttribute("userId"); // user Id 
	    		  Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier 
	    		  jobDetails = new JobBeans();
	    		  jobDetails.setClientId(prsObj.getIntParameter("clientList",0));
	    		  jobDetails.setSupplierId(supplierId);
	    		  jobDetails.setFileName(prsObj.getStringParameter("fileName","")); //File Name;
	    		  jobDetails.setFileReference(prsObj.getStringParameter("fileReference","")); //File Name;
	    		  jobDetails.setDateReceived (prsObj.getStringParameter("dateReceived",DateUtil.getCurrentDate())); //date Received
	    		  jobDetails.setExpectedDate(prsObj.getStringParameter("expectedDate","")); //expected Date
	    		  jobDetails.setDateReturned(prsObj.getStringParameter("dateReturned",null)); //expected Date
	    		  jobDetails.setYearEnding(prsObj.getStringParameter("yearEnding","")); //year Ending
	    		  jobDetails.setBudgetedTime(prsObj.getStringParameter("budgetedTime","")); //budgeted Time
	    		  jobDetails.setFileStorage(prsObj.getStringParameter("fileStorage","")); //file Storage
	    		  jobDetails.setPartnerId(prsObj.getIntParameter("partnerList",0)); //partner Id
	    		  jobDetails.setAccountantId(prsObj.getIntParameter("accountantList",0)); //accountant Id
	    		  jobDetails.setReviewerId(prsObj.getIntParameter("ReviewerList",0)); //Reviewer Id
	    		  jobDetails.setIsFollowLeadSchedules(prsObj.getBooleanParameter("FollowLeadSchedules",false)); //Reviewer Id
	    		  jobDetails.setPrincipalActivityId(prsObj.getIntParameter("PrincipalActivity",0)); //Principal Activity
	    		  jobDetails.setComments(prsObj.getStringParameter("comments","")); //comments
	    		  jobDetails.setUrgency(prsObj.getStringParameter("Urgency","")); //Urgency
	    		  jobDetails.setUserId(userId);
	    	      
	    	      
	    	/*      System.out.println("ClientId>>> "+ClientId);
	    	      System.out.println("fileName>>> "+fileName);
	    	      System.out.println("fileReference>>> "+fileReference);
	    	      System.out.println("dateReceived>>> "+dateReceived);
	    	      System.out.println("expectedDate>>> "+expectedDate);
	    	      System.out.println("dateReturned>>> "+dateReturned);
	    	      System.out.println("yearEnding>>> "+yearEnding);
	    	      System.out.println("budgetedTime>>> "+budgetedTime);
	    	      System.out.println("fileStorage>>> "+fileStorage);
	    	      System.out.println("partnerId>>> "+partnerId);
	    	      System.out.println("accountantId>>> "+accountantId);
	    	      System.out.println("ReviewerId>>> "+ReviewerId);
	    	      System.out.println("isFollowLeadSchedules>>> "+isFollowLeadSchedules);
	    	      System.out.println("PrincipalActivity>>> "+PrincipalActivity);
	    	      System.out.println("comments>>> "+comments);
	    	      System.out.println("Urgency>>> "+Urgency);*/
	    	      
	    	      
	       	     // supplierDaoObj = new MYSQLSupplierDAO(); // supplier DAO object
	       	     daoObj.insertNewJob(jobDetails);
	       	   
	       	   
	 			//Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier 
	 			 
	    	      model = new ModelAndView("redirect:SupplierJobs.html"); 	
	        // model = new ModelAndView("/Supplier/Jobs"); 
	       } 
	       
	       
	     

	  
	  
	       return model;  
	   
	  }
	 }