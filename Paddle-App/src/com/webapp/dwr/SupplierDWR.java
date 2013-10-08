package com.webapp.dwr;

 
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

 





import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.ParameterParser;
import com.persistence.ClientDAO;
import com.persistence.PaddleDAO;
import com.persistence.SupplierDAO;
import com.persistence.mysql.MYSQLClientDAO;
import com.persistence.mysql.MYSQLPaddleDAO;
import com.persistence.mysql.MYSQLSupplierDAO;
import com.webapp.beans.ClientBeans;
import com.webapp.beans.SupplierBeans;
import com.webapp.beans.TimeSheetBeans;

public class SupplierDWR {
	
	private static final String PAGE = "page";
	PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
	SupplierDAO supplierDaoObj= new MYSQLSupplierDAO(); // supplier DAO object
	ClientDAO clientDaoObj= new MYSQLClientDAO(); // Client DAO object
	
	
	
	
    public String handShaking(String companyName,String email){
    org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
    
    HttpSession session = wc.getSession(false);
    int clientId=0; 
    try{
    	  
    	 if(session !=null) // checks for session
		    {
    		Integer userId = (Integer) session.getAttribute("userId"); // user Id	
 		    Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id 
 		    
 		    clientId= daoObj.isClientExists(email); // checks if client registered 
		    if(clientId!=0)
		    {
		    	 
		    	supplierDaoObj.clientSupplierConnect(supplierId, clientId,userId); // Adds new connection - client_supplier_connect
		    	
		    }
		    else
		    {
		    	// need to send Email - informs client about PaddleApp
		    }
		    
 		  
 		  
 		  
		    return "Added";
		    
		    }
    	
    	
    	
     

    }catch(NullPointerException ex){
    }catch(Exception e){
        e.printStackTrace();
    }
    return "session expired Please logout and login again";
}

	
    public String deleteClientSupplierConnection(int id){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get(); 
        HttpSession session = wc.getSession(false);
        boolean result=false; 
        try{
        	  
        	 if(session !=null) // checks for session
    		    {
        		//Integer userId = (Integer) session.getAttribute("userId"); // user Id	
        		// Integer clientId = (Integer) session.getAttribute("clientId"); // client  Id
     		    
    		    if(id!=0)
    		    {
    		    	 
    		    	result=daoObj.deleteClientSupplierConnection(id); // Approve handshaking - client_supplier_connect
    		    	
    		    if(result=true)
    		    	
    		    {
    		    	// send email to client - supplier has deleted your connection
    		    }
    		    
    		    
    		    }
     		  
    		    return "Added";
    		    
    		    }
        	
        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return "session expired Please logout and login again";
    } 	
	
    

    public String deleteSupplierUserdetails(int id){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get(); 
        HttpSession session = wc.getSession(false);
        boolean result=false; 
        try{
        	  
        	 if(session !=null) // checks for session
    		    {
        		//Integer userId = (Integer) session.getAttribute("userId"); // user Id	
        		// Integer clientId = (Integer) session.getAttribute("clientId"); // client  Id
     		    
    		    if(id!=0)
    		    {
    		    	 
    		    	result=supplierDaoObj.deleteSupplierUser(id); // Approve deleting - supplierconnect
    		    	
    		    if(result=true)
    		    	
    		    {
    		    	// send email to client - supplier has deleted your connection
    		    }
    		    
    		    
    		    }
     		  
    		    return "Added";
    		    
    		    }
        	
        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return "session expired Please logout and login again";
    } 	
	
    
 

    
    public String addSupplierUsers(SupplierBeans details){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
        
        HttpSession session = wc.getSession(false);
       
        try{
        	  
        	 if(session !=null) // checks for session
    		    {
        		 Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id  
        		 
        		 if(supplierId!=null)
        		 {
        		 details.setSupplierId(supplierId);
        		 supplierDaoObj.insertNewUser(details);
        		 
    		     return "Added";
        		 }
    		    }
        	 else
        	 {
        		 
        	 }
        	


        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return "session expired Please logout and login again";
    }
 
    public String updateSupplierUsers(SupplierBeans detailso){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
        
        HttpSession session = wc.getSession(false);
       
        try{
   		 System.out.println("details>>> "+detailso); 
        	 if(session !=null) // checks for session
    		    {
        		 Integer supplierId = (Integer) session.getAttribute("supplierId"); // Client Id  
        		 System.out.println("supplierId>>> "+supplierId);
        		 if(supplierId!=null)
        		 {
        		 detailso.setSupplierId(supplierId);
        		 supplierDaoObj.updateSupplierUser(detailso);
        		 
    		     return "Added";
        		 }
    		    }
        	 else
        	 {
        		 
        	 }
        	


        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return "session expired Please logout and login again";
    }
 
    
    public String assignReviewertoAccountant(String reviewerId, int accountantId){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
        
        HttpSession session = wc.getSession(false);
       
        try{
        	  
        	 if(session !=null) // checks for session
    		    {
        		 Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id  
        		 
        		 if(supplierId!=null)
        		 {
        		  
        			 supplierDaoObj.assignReviewertoAccountant(reviewerId,accountantId,supplierId);
        		 
    		     return "Added";
        		 }
    		    }
        	 else
        	 {
        		 
        	 }
        	


        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return "session expired Please logout and login again";
    }
 
    
    
    
    public String assignAccoutantToPartners(int clientId,int partnerId,String accountantIds){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
        
        HttpSession session = wc.getSession(false);
       
        try{
        	  
        	 if(session !=null) // checks for session
    		    {
        		 Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id  
        		 
        		 if(supplierId!=null)
        		 {
        			 
        			 
        		  
        			 supplierDaoObj.assignAccoutantToPartners(supplierId, clientId, partnerId, accountantIds);
        		
        			 
        			 
        			 
    		     return "Added";
        		 }
    		    }
        	 else
        	 {
        		 
        	 }
        	


        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return "session expired Please logout and login again";
    }
    

// delete Accountants
    
    public String deleteAccountantDetails(int id){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get(); 
        HttpSession session = wc.getSession(false);
        boolean result=false; 
        try{
           
          if(session !=null) // checks for session
          {
          
          if(id!=0)
          {
            
           result=supplierDaoObj.deleteAccountants(id); // Approve deleting - Accountants
           
          if(result=true)
           
          {
           // send email to client - supplier has deleted your connection
          }
          
          
          }
         
          return "Added";
          
          }
         
        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return "session expired Please logout and login again";
    }
    
    
    //
    public String defaultAccountantToPartner(int clientId,int partnerId, int id){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get(); 
        HttpSession session = wc.getSession(false);
        boolean result=false; 
        try{
           
          if(session !=null) // checks for session
          {
        	  Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id  
          if(id!=0)
          {
            
           result=supplierDaoObj.defaultAccountantToPartner(supplierId, clientId, partnerId,  id); // update default accountant
           
          if(result=true)
           
          {
           // send email to client - supplier has deleted your connection
          }
          
          
          }
         
          return "Added";
          
          }
         
        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return "session expired Please logout and login again";
    }
    
    
    
    public String defaultReviewerToPartner(int reviewerId, int id){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get(); 
        HttpSession session = wc.getSession(false);
        boolean result=false; 
        try{
           
          if(session !=null) // checks for session
          {
        	  
          if(id!=0)
          {
            
           result=supplierDaoObj.defaultReviewerToPartner(reviewerId,  id); // update default accountant
           
          if(result=true)
           
          {
           // send email to client - supplier has deleted your connection
          }
          
          
          }
         
          return "Added";
          
          }
         
        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return "session expired Please logout and login again";
    }
    
    public ArrayList getPartnerList(int clientId){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get(); 
        HttpSession session = wc.getSession(false);
        HttpServletRequest request = wc.getHttpServletRequest();
        ArrayList partnerList=null; 
        try{
           
          if(session !=null) // checks for session
          {
          //Integer userId = (Integer) session.getAttribute("userId"); // user Id 
          // Integer clientId = (Integer) session.getAttribute("clientId"); // client  Id
           
          if(clientId!=0)
          {
        	  partnerList= new ArrayList(); 
        	  partnerList=clientDaoObj.getPartnerList(clientId); // get Partner List  
        	  if(partnerList.size()>0){
                  
                request.setAttribute("partnerList", partnerList);
            }
          
          
          }
         
          return partnerList;
          
          }
         
        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return partnerList;
    }
    
    
    public ArrayList getAccountantListForPartner(int clientId,int partnerId){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get(); 
        HttpSession session = wc.getSession(false);
        HttpServletRequest request = wc.getHttpServletRequest();
        ArrayList acountantList=null; 
        try{
           
          if(session !=null) // checks for session
          {
        	  Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id  
           
          if(clientId!=0)
          {
        	  acountantList= new ArrayList(); 
        	  acountantList = supplierDaoObj.getActiveAccountantForPartner(supplierId,clientId,partnerId);
				 
        	  if(acountantList.size()>0){
                  
                request.setAttribute("acountantList", acountantList);
            }
          
          
          }
         
          return acountantList;
          
          }
         
        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return acountantList;
    } 
    
    public ArrayList getReviewerListForAccountant(int clientId,int partnerId,int accountantId){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get(); 
        HttpSession session = wc.getSession(false);
        HttpServletRequest request = wc.getHttpServletRequest();
        ArrayList reviewerList=null; 
        try{
           
          if(session !=null) // checks for session
          {
        	  Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id  
           
          if(clientId!=0)
          {
        	  reviewerList= new ArrayList(); 
        	  reviewerList = supplierDaoObj.getConnectedReviewerForAccountant( supplierId, accountantId, clientId, partnerId);
				 
        	  if(reviewerList.size()>0){
                  
                request.setAttribute("reviewerList", reviewerList);
            }
          
          
          }
         
          return reviewerList;
          
          }
         
        }catch(NullPointerException ex){
        }catch(Exception e){
            e.printStackTrace();
        }
        return reviewerList;
    } 
    
    
	
    public String checko(String ak){
    org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
    ParameterParser prsObj =null;
    HttpSession session = wc.getSession(false);
    HttpServletRequest request = wc.getHttpServletRequest();
   
    try{
    	  
    	 if(session !=null) // checks for session
		    {
    		 prsObj = new ParameterParser(request); 
    		  int ClientId=prsObj.getIntParameter("clientList",0); // Client Id
    	      String fileName=prsObj.getStringParameter("fileName",""); //File Name;
    	      
    	      System.out.println("ClientId>>xcxc> "+ClientId);
    	      System.out.println("fileName>>> "+fileName);
		    
 		  
 		  
 		  
		    return "Added";
		    
		    }
    	
    	
    	
     

    }catch(NullPointerException ex){
    }catch(Exception e){
        e.printStackTrace();
    }
    return "session expired Please logout and login again";
}
    
    
  //UPDATE JOB STATUS
        public String updateJobsStatus(int jobStatus, int id){
            org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
            
            HttpSession session = wc.getSession(false);
           
            try{
               
              if(session !=null) // checks for session
              {
               Integer supplierId = (Integer) session.getAttribute("supplierId"); // Client Id  
               
               if(supplierId!=null)
               {
                System.out.println("jobStatus>>> "+jobStatus);  System.out.println("id>>> "+id);
                daoObj.updateJobStatus(jobStatus,id);
               
               return "Added";
               }
              }
              else
              {
               
              }
             


            }catch(NullPointerException ex){
            }catch(Exception e){
                e.printStackTrace();
            }
            return "session expired Please logout and login again";
        }
    
    
        
        
      //  UPDATE coverSheet CheckList STATUS
        public String updateCoverSheetCheckList(int jobId, String fieldName, boolean fieldValue){
            org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
            
            HttpSession session = wc.getSession(false);
           
            try{
               
              if(session !=null) // checks for session
              {
               //Integer supplierId = (Integer) session.getAttribute("supplierId"); // Client Id  
               
               if(jobId!=0)
               {
               // System.out.println("jobId>>> "+jobId);  System.out.println("fieldName>>> "+fieldName); System.out.println("fieldValue>>> "+fieldValue);
                 daoObj.updateCoverSheetCheckList(jobId,fieldName,fieldValue);
               
               return "Added";
               }
              }
              else
              {
               
              }
             


            }catch(NullPointerException ex){
            }catch(Exception e){
                e.printStackTrace();
            }
            return "session expired Please logout and login again";
        }
        
        
        //Start TimeSheet
        public String startTimesheet(TimeSheetBeans details){
            org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
            
            HttpSession session = wc.getSession(false);
           
            try{
               
              if(session !=null) // checks for session
              {

               supplierDaoObj.startAccountantTimeSheet(details);
               
               return "Added";
               
              }
              else
              {
               
              }
             


            }catch(NullPointerException ex){
            }catch(Exception e){
                e.printStackTrace();
            }
            return "session expired Please logout and login again";
        }
        
        public String stopTimesheet(TimeSheetBeans details){
            org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
            
            HttpSession session = wc.getSession(false);
           
            try{
               
              if(session !=null) // checks for session
              {
                 
           //    System.out.println("I'm in stopTimesheet" );
               if(details.getId()!=0)
               {
               
               supplierDaoObj.stopAccountantTimeSheet(details);
               
               return "Added";
               }
              }
              else
              {
               
              }
             


            }catch(NullPointerException ex){
            }catch(Exception e){
                e.printStackTrace();
            }
            return "session expired Please logout and login again";
        }
    
        
        //Insert Leave form - paid , sick, break etc
        
         
        public String insertLeaveForm(TimeSheetBeans details){
            org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
            
            HttpSession session = wc.getSession(false);
           
            try{
               
              if(session !=null) // checks for session
              {
                 
            	  System.out.println("I'm in insertLeaveForm" );
               if(details.getUserId()!=0)
               {
            	   System.out.println("I'm in insertLeaveForm" );
               supplierDaoObj.insertLeaveForm(details);
               
               return "Updated";
               }
              }
              else
              {
               
              }
             


            }catch(NullPointerException ex){
            }catch(Exception e){
                e.printStackTrace();
            }
            return "session expired Please logout and login again";
        }
        
   //************************************************** Menu Load ************************************
    
    public String getInclude()throws ServletException, IOException   {
    org.directwebremoting.WebContext wctx =  org.directwebremoting.WebContextFactory.get();
     
    return wctx.forwardToString("/pages/Supplier/SupplierMenu.jsp");
       
    }
   
   
    
    
 
	public Map getPage(String forwardpage,String[] values){
        org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
        HttpServletRequest request = wc.getHttpServletRequest();
        HttpServletResponse response = wc.getHttpServletResponse();
        ServletConfig config = wc.getServletConfig();
        ServletContext context = wc.getServletContext();
        Map frdPage = new HashMap();
        try{
        	
            if(forwardpage.compareToIgnoreCase("getAccountantList") == 0){
                String clientId=values[0]; // client id
                String partnerId=values[1]; // partner id
                frdPage.put(PAGE,wc.forwardToString("/getAccountantListPageForPartner.html?clientId="+clientId+"&partnerId="+partnerId));
                }
            else   if(forwardpage.compareToIgnoreCase("editJob") == 0){
                String jobId=values[0]; // job id
                System.out.println("getPage>>> "+jobId);
                frdPage.put(PAGE,wc.forwardToString("/editJob.html?jobId="+jobId));
                }
        	
        	
       }catch(NullPointerException ex){
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return frdPage;
    }
    
    
}
