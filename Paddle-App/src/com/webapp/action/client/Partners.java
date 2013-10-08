package com.webapp.action.client;

 import java.util.ArrayList;

 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.persistence.ClientDAO;
import com.persistence.SupplierDAO;
import com.persistence.mysql.MYSQLClientDAO;
import com.persistence.mysql.MYSQLSupplierDAO;
import com.webapp.beans.ClientBeans;
import com.webapp.beans.SupplierBeans;
  
  

 public class Partners extends AbstractController{
  protected ModelAndView handleRequestInternal(HttpServletRequest request,
    HttpServletResponse response) throws Exception {
       ModelAndView model =null; 
       HttpSession session = request.getSession(false); // don't create if it doesn't exist
       ClientDAO clientDaoObj=null;
       ArrayList<ClientBeans> partnerDetails =null;
       if(session !=null) // checks for session
       {
       Integer clientId = (Integer) session.getAttribute("clientId"); // client Id
       Integer userId = (Integer) session.getAttribute("userId"); // user Id
       clientDaoObj=new MYSQLClientDAO(); // Client DAO object 
       partnerDetails=new ArrayList<ClientBeans>();     
     //  System.out.println("clientId>>> "+clientId);
       partnerDetails=clientDaoObj.getPartnersListForManagers(clientId,userId); // gets user details
       if(partnerDetails.size()>0){
           //System.out.println("Sizzeee>>> "+partnerDetails.size());
              request.setAttribute("partnerDetails", partnerDetails); 
      }
       
       model = new ModelAndView("/Client/Partners");
    
  }
       
   else
       {
        
        // session expired
       }
      
   
       return model;
  } 
 }