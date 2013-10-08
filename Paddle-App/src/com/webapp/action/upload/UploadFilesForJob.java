package com.webapp.action.upload;
import java.util.List;
 



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 



import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.common.ParameterParser;

 


import javax.servlet.http.HttpSession;

import com.persistence.mysql.MYSQLPaddleDAO; 
import com.persistence.PaddleDAO;

import java.io.File;
import java.util.Iterator;

import com.webapp.beans.JobBeans;

import org.apache.commons.fileupload.*;

import com.webapp.util.PINGenerator;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 *
 * @author  Ak
 */
public class UploadFilesForJob extends AbstractController   {


public UploadFilesForJob() {
}
protected ModelAndView handleRequestInternal(
HttpServletRequest request, HttpServletResponse response)
throws Exception {
HttpSession session = null;
session = request.getSession(false);
String DIR = java.util.ResourceBundle.getBundle("PaddleApp").getString("DIR"); 
JobBeans jobDetails= null;
ParameterParser prsObj =null;
PaddleDAO daoObj=null;
String jobId="";
int cjobID=0;
String fileName="";
try{
 if(session != null){
     
    //  String maileusId=session.getAttribute("MaileusId").toString();   // get Userid  
    //  String userid=session.getAttribute("Id").toString();   // get Userid
	 daoObj=new MYSQLPaddleDAO(); 
	
     
      
     jobDetails=  new JobBeans();
     
    // prsObj = new ParameterParser(request);  
     //jobId=prsObj.getIntParameter("uploadJobId",0);
     //jobStatusId=prsObj.getIntParameter("jobstatusId",2);
     
    // System.out.println("jobId>>>>>>>"+jobId);
     
     
     
      boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
                System.out.println("isMultipart>>>>>>>"+isMultipart);
              if (isMultipart) { 
                   System.out.println("isMultipart>>>>>>>"+isMultipart);
                  // Create a factory for disk-based file items
                  FileItemFactory factory = new DiskFileItemFactory();
                   System.out.println("isMultipar111t>>>>>>>"+isMultipart);
                  // Create a new file upload handler 
                  ServletFileUpload upload = new ServletFileUpload(factory);
                   System.out.println("isMultipar111t222222>>>>>>>"+isMultipart);
                  try { 
                      // Parse the request 
                	  System.out.println("Parse the request >>>>>>>");
                      List  items = upload.parseRequest(request);
                      System.out.println("List>>>>>>>"+items);
                      // System.out.println("items>>>>>>>"+items);
                      Iterator iterator = items.iterator();
                      while (iterator.hasNext()) {
                    	  System.out.println("iterator>>>>>>>"+iterator);
                       FileItem item = (FileItem) iterator.next(); 
                      if (!item.isFormField()) { 
                         // Process form file field (input type="file").  
                         fileName = item.getName();
                       System.out.println("fileName>>>>>>>"+fileName);
                        String contentType = item.getContentType();
                        if(item.getFieldName().equals("uploadJobId"))
                        {
                  		  jobId=item.getString().toString();
                  		 System.out.println("jobIdimmooooo>>>>>>>"+jobId);
                        }
                        System.out.println("jobIdfgfgfgfg>>>>>>>"+jobId);
                          cjobID=Integer.parseInt(jobId.trim());
                        jobDetails=daoObj.getClientPartnerDetailsForJob(cjobID);
                       
                       
                       File uploadedFile = new File(DIR+"//"+jobDetails.getClientFolder()+"//"+jobDetails.getPartnerFolder()+"//"+jobDetails.getYearEnding()+"//"+jobDetails.getJobFolder()+"//"+fileName);
                       System.out.println("uploadedFile>>>>>>>"+uploadedFile);
                       
                       item.write(uploadedFile); // writes the file to folder
                       
                        
                      //  maileus.addDocumentDetails(userid, fileName,uniqueFileName,sizeInBytes,contentType); // insert upload document deatils to DB
                      
                      }
                      else if(item.isFormField())  {
                    	  
                // Process regular form field (input type="text|radio|checkbox|etc", select, etc)
              
                     
                    	  
                    	   if(item.getFieldName().equals("uploadJobId"))
                          {
                    		  jobId=item.getString();
                    		  System.out.println("jobId>>>>>>>"+jobId); 
                          } 
                    	  
                    	  
                    	 
                    	  System.out.println("jobId>>>>>>>"+jobId); 
                    	  System.out.println("fileName>>>>>>>"+fileName); 
                    	  
                    	  // insert job details 
                    	  
                  
                      }
                      
                     
                      
                      } 
                      
                      daoObj.insertFileDetailsForJob(jobId,fileName); 
                  
                  } catch (FileUploadException e)
                      { e.printStackTrace(); 
                      }
                  catch (Exception e) 
                  { e.printStackTrace(); 
                  }
              } 
     
     
     
     
     
     
    
 
     
 
   
   
 }
 else
 {
     
     return new ModelAndView("SessionExpired");    //Session Expired
     
 }

}
catch(Exception e) {
e.printStackTrace();
}
 
return new ModelAndView("redirect:CoverSheet.html?jobId="+cjobID);           
}  
}
