 
	package com.webapp.action.supplier;

	import java.util.ArrayList;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

	import org.springframework.web.servlet.ModelAndView;
	import org.springframework.web.servlet.mvc.AbstractController;

	import com.persistence.SupplierDAO;
	import com.persistence.mysql.MYSQLSupplierDAO;
	import com.webapp.beans.SupplierBeans;

	public class Accountants extends AbstractController{
			protected ModelAndView handleRequestInternal(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				    ModelAndView model =null;	
				    HttpSession session = request.getSession(false); // don't create if it doesn't exist
				    SupplierDAO supplierDaoObj=null;
				    ArrayList<SupplierBeans> acountantDetails =null;
				    if(session !=null) // checks for session
				    {
				    Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier Id
				    Integer userId = (Integer) session.getAttribute("userId"); // user Id
				    supplierDaoObj=new MYSQLSupplierDAO(); // supplier DAO object	
				    acountantDetails=new ArrayList<SupplierBeans>();   		
				  //  System.out.println("supplierId>>> "+supplierId);
				    acountantDetails=supplierDaoObj.getAccountantsListForReviewer(supplierId,userId); // gets user details
				    if(acountantDetails.size()>0){
			        //	System.out.println("Sizzeee>>> "+acountantDetails.size());
			            request.setAttribute("acountantDetails", acountantDetails); 
				    	
				    	 model = new ModelAndView("/Supplier/Accountants");	
				    }	
				    
				    
				    	
				  
				    	
				 		
				    	
				     
				    
			
				   
				  
			
			
			
			}
				    return model;		
				
			}
	}
				
				
 
 
			
