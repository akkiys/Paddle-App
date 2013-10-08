package com.webapp.dwr;

 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import com.persistence.ClientDAO;
import com.persistence.PaddleDAO;
import com.persistence.mysql.MYSQLClientDAO;
import com.persistence.mysql.MYSQLPaddleDAO;
import com.webapp.beans.ClientBeans;
 
 

public class ClientDWR {

	PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
	ClientDAO clientDaoObj= new MYSQLClientDAO(); // Client DAO object
	
	
	
	
	
    public String approvehandShaking(int supplierId){
    org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get(); 
    HttpSession session = wc.getSession(false);
    boolean result=false; 
    try{
    	  
    	 if(session !=null) // checks for session
		    {
    		//Integer userId = (Integer) session.getAttribute("userId"); // user Id	
    	    Integer clientId = (Integer) session.getAttribute("clientId"); // client  Id
 		    //System.out.println("clientId>>> "+clientId);
		    if(supplierId!=0)
		    {
		    	 
		    	result=clientDaoObj.approveSupplierConnect(clientId, supplierId); // Approve handshaking - client_supplier_connect
		    	
		    if(result=true)
		    	
		    {
		    	// send email to supplier -  Client has accepted your Handshaking
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
    		    	// send email to Supplier - client has deleted your connection
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
    
	
//************* Delete Client User Details ***************
        public String deleteClientUserdetails(int id){
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
    		    	 
    		    	result=clientDaoObj.deleteClientUser(id); // Approve deleting - clientconnect
    		    	
    		    if(result=true)
    		    	
    		    {
    		    	// send email to Supplier - client has deleted your connection
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
    
	
        
        
        public String addClientUsers(ClientBeans details){
            org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
            
            HttpSession session = wc.getSession(false);
           
            try{
            	  
            	 if(session !=null) // checks for session
        		    {
            		 Integer clientId = (Integer) session.getAttribute("clientId"); // Client Id  
            		 
            		 if(clientId!=null)
            		 {
            		 details.setClientId(clientId);
            		 clientDaoObj.insertNewUser(details);
            		 
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
     

        
        public String updateClientUsers(ClientBeans details){
            org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
            
            HttpSession session = wc.getSession(false);
           
            try{
            	  
            	 if(session !=null) // checks for session
        		    {
            		 Integer clientId = (Integer) session.getAttribute("clientId"); // Client Id  
            		 
            		 if(clientId!=null)
            		 {
            		 details.setClientId(clientId);
            		 clientDaoObj.updateClientUser(details);
            		 
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
     

        
        
        public String assignPartnerstoManager(String partnerId, int mangerId){
            org.directwebremoting.WebContext wc =  org.directwebremoting.WebContextFactory.get();
            
            HttpSession session = wc.getSession(false);
           
            try{
            	  
            	 if(session !=null) // checks for session
        		    {
            		 Integer clientId = (Integer) session.getAttribute("clientId"); // Client Id  
            		 
            		 if(clientId!=null)
            		 {
            		  
            		 clientDaoObj.assignPartnerstoManager(partnerId,mangerId,clientId);
            		 
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
     
        
        public String deletePartnerdetails(int id){
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
               
              result=clientDaoObj.deletePartners(id); // Approve deleting - Partners
              
             if(result=true)
              
             {
              // send email to Supplier - client has deleted your connection
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
        
        
        
      //UPDATE coverSheet CheckList STATUS
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
    
        
        
        //************************************************** Menu Load ************************************
         
         public String getInclude()throws ServletException, IOException   {
         org.directwebremoting.WebContext wctx =  org.directwebremoting.WebContextFactory.get();
         //System.out.println("I'm Menu Load!!!");
         return wctx.forwardToString("/pages/Client/ClientMenu.jsp");
            
         }
}
