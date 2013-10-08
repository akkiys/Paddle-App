 package com.webapp.action.supplier;

 

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.webapp.beans.JobBeans;
import com.persistence.SupplierDAO;
import com.persistence.mysql.MYSQLSupplierDAO;

 


public class AccountantTimeSheet extends AbstractController{
 protected ModelAndView handleRequestInternal(HttpServletRequest request,
   HttpServletResponse response) throws Exception {
      ModelAndView model =null; 
      HttpSession session = request.getSession(false); // don't create if it doesn't exist
      SupplierDAO supplierDaoObj=null;
      ArrayList<JobBeans> jobDetails =null,jobStatusList=null,assistJobs=null; 
      JobBeans currentJob=null;
      if(session !=null) // checks for session
      {
     Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id
    Integer userId = (Integer) session.getAttribute("userId"); // user Id
     supplierDaoObj=new MYSQLSupplierDAO(); // supplier DAO object 
   
     // get current jobList
     
     jobDetails=new ArrayList<JobBeans>();
    jobDetails=supplierDaoObj.getJobListForAccountant(userId); // gets assigned JobList
    if(jobDetails.size()>0){
        
           request.setAttribute("jobDetails", jobDetails); 
    }
      
    
    // gives current details about the job
    currentJob=new JobBeans();
    currentJob=supplierDaoObj.getCurrentAccountantJob(userId, true);
    request.setAttribute("currentJob", currentJob); 
    
 // gets jobstatusList for Accountant
    jobStatusList=new ArrayList<JobBeans>();
    jobStatusList=supplierDaoObj.getJobStatusForAccountant(supplierId); 
    if(jobStatusList.size()>0){
    request.setAttribute("jobStatusList", jobStatusList); 
      }
    
    
    //get Assists Job list
    
    
    assistJobs=new ArrayList<JobBeans>();
    assistJobs=supplierDaoObj.getAssistJobList(); 
    if(assistJobs.size()>0){
    request.setAttribute("assistJobs", assistJobs); 
      }
    
        model = new ModelAndView("/Supplier/AccountantTimeSheet"); 
      } 
      
      
    

 
 
      return model;  
  
 }
}