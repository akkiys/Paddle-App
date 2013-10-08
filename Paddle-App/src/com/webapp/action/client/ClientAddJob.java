package com.webapp.action.client;
 

 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.common.ParameterParser;
import com.persistence.ClientDAO;
import com.persistence.PaddleDAO;
import com.persistence.mysql.MYSQLClientDAO;
import com.persistence.mysql.MYSQLPaddleDAO;
import com.webapp.beans.JobBeans;
import com.webapp.util.DateUtil; 


public class  ClientAddJob extends AbstractController{
 protected ModelAndView handleRequestInternal(HttpServletRequest request,
   HttpServletResponse response) throws Exception {
      ModelAndView model =null; 
      HttpSession session = request.getSession(false); // don't create if it doesn't exist
      PaddleDAO  daoObj=null;
      ClientDAO clientDaoObj=null;
   	ParameterParser prsObj =null;
   	JobBeans jobDetails=null;
      if(session !=null) // checks for session
      {
   		  prsObj = new ParameterParser(request);
   		  clientDaoObj = new MYSQLClientDAO(); // Client DAO object
   		  daoObj = new MYSQLPaddleDAO(); // paddle DAO object
   		  Integer userId = (Integer) session.getAttribute("userId"); // user Id 
   		  Integer clientId = (Integer) session.getAttribute("clientId"); // Client  id
   		  jobDetails = new JobBeans();
   		  jobDetails.setClientId(clientId);
   		  jobDetails.setSupplierId(prsObj.getIntParameter("supplierList",0));
   		  jobDetails.setFileName(prsObj.getStringParameter("fileName","")); //File Name;
   		  jobDetails.setFileReference(prsObj.getStringParameter("fileReference","")); //File Name;
   		  jobDetails.setDateReceived (prsObj.getStringParameter("dateReceived",DateUtil.getCurrentDate())); //date Received
   		  jobDetails.setExpectedDate(prsObj.getStringParameter("expectedDate","")); //expected Date
   		  jobDetails.setDateReturned(prsObj.getStringParameter("dateReturned",null)); //expected Date
   		  jobDetails.setYearEnding(prsObj.getStringParameter("yearEnding","")); //year Ending
   		  jobDetails.setBudgetedTime(prsObj.getStringParameter("budgetedTime","")); //budgeted Time
   		  jobDetails.setFileStorage(prsObj.getStringParameter("fileStorage","")); //file Storage
   		  jobDetails.setPartnerId(prsObj.getIntParameter("partnerList",0)); //partner Id
   		  
   		  
   		  jobDetails.setAccountantId(clientDaoObj.getDefaultAccountantForPartner(jobDetails.getPartnerId(), clientId)); //accountant Id
   		  
   		  jobDetails.setReviewerId(clientDaoObj.getDefaultReviewerForPartner(jobDetails.getPartnerId(), clientId,  jobDetails.getAccountantId())); //Reviewer Id
   		  
   		  
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
   	      
   	      
      	     
   		daoObj.insertNewJob(jobDetails);
      	   
      	   
			//Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier 
			 
   	      model = new ModelAndView("redirect:ClientJobs.html"); 	
       // model = new ModelAndView("/Supplier/Jobs"); 
      } 
      
      
    

 
 
      return model;  
  
 }
}