package com.webapp.action.client;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.webapp.beans.ClientBeans;
import com.webapp.beans.JobBeans;
import com.webapp.beans.SupplierBeans;
import com.common.ParameterParser;
import com.persistence.PaddleDAO;
import com.persistence.SupplierDAO;
import com.persistence.mysql.MYSQLClientDAO;
import com.persistence.mysql.MYSQLPaddleDAO;
import com.persistence.mysql.MYSQLSupplierDAO;


	public class ClientEditJob extends AbstractController{

		private HttpSession session;

		@Override
		protected ModelAndView handleRequestInternal(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			ModelAndView model =null;
			HttpSession session = request.getSession(false); // don't create if it // doesn't exist
			PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
			ParameterParser prsObj = new ParameterParser(request); 
			SupplierDAO supplierDaoObj = null;
			JobBeans details=null;
			MYSQLClientDAO clientDao=null;
			MYSQLSupplierDAO supplierDao=null;
			ArrayList<ClientBeans> partnerDetails = null;
			ArrayList<SupplierBeans> accountantDetails = null,reviewerDetails=null;
			if (session != null) // checks for session
			{
				details=new JobBeans();
				int jobId=prsObj.getIntParameter("jobId",0); // Job Id
				  Integer clientId = (Integer) session.getAttribute("clientId"); // clientId 
				System.out.println("EditJob>>> "+jobId);
			 
				if(jobId!=0)
				{
				details= daoObj.getJobDetails(jobId) ;
				request.setAttribute("jobDetails", details); 
				} 
				
				//Partner List
				clientDao= new MYSQLClientDAO();
				partnerDetails=new ArrayList<ClientBeans>();
				partnerDetails=clientDao.getPartnerList(daoObj.getClientIDForJob(jobId));
				if (partnerDetails.size() > 0) {

					request.setAttribute("partnerDetails", partnerDetails);
				}
				
				
				 //Accountant List
				/*supplierDao= new MYSQLSupplierDAO();
				accountantDetails=new ArrayList<SupplierBeans>();
				accountantDetails=supplierDao.getSupplierAccountantList(daoObj.getSupplierIDForJob(jobId));
				if (accountantDetails.size() > 0) {

					request.setAttribute("accountantDetails", accountantDetails);
				}
				
				
				 	//Reviewer List
				supplierDao= new MYSQLSupplierDAO();
				reviewerDetails=new ArrayList<SupplierBeans>();
				reviewerDetails=supplierDao.getSupplierReviewerList(daoObj.getSupplierIDForJob(jobId));
				if (reviewerDetails.size() > 0) {

					request.setAttribute("reviewerDetails", reviewerDetails);
				}
				*/
				 
				model = new ModelAndView("/Client/EditJob");
			}
			return model;
		
		
		
		}

	}