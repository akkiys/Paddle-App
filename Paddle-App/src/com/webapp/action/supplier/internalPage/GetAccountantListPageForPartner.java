 
	package com.webapp.action.supplier.internalPage;

	import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

	import com.webapp.beans.PaddleBeans;
import com.webapp.beans.SupplierBeans;
import com.common.ParameterParser;
import com.persistence.PaddleDAO;
import com.persistence.SupplierDAO;
import com.persistence.mysql.MYSQLPaddleDAO;
import com.persistence.mysql.MYSQLSupplierDAO;

	public class GetAccountantListPageForPartner extends AbstractController{

		private HttpSession session;

		@Override
		protected ModelAndView handleRequestInternal(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			ModelAndView model =null;
			HttpSession session = request.getSession(false); // don't create if it// doesn't exist
			PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
			ParameterParser prsObj = new ParameterParser(request); 
			SupplierDAO supplierDaoObj = null;
			ArrayList<SupplierBeans> accountantDetails = null,activeAccountantDetails = null;
			if (session != null) // checks for session
			{
				int clientId=prsObj.getIntParameter("clientId",0); // client Id
				int partnerId=prsObj.getIntParameter("partnerId",0);// // partner ID
				
				 
		 
				 supplierDaoObj = new MYSQLSupplierDAO(); // supplier DAO object
				 accountantDetails = new ArrayList<SupplierBeans>();
				 activeAccountantDetails = new ArrayList<SupplierBeans>();
				Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier
				if(clientId !=0 && partnerId!=0)
				{
					accountantDetails = supplierDaoObj.getAccountantsListForPartner(supplierId,clientId,partnerId);
				if (accountantDetails.size() > 0) {

					request.setAttribute("accountantDetails", accountantDetails);
				} 
				
				activeAccountantDetails = supplierDaoObj.getActiveAccountantForPartner(supplierId,clientId,partnerId);
				if (activeAccountantDetails.size() > 0) {

					request.setAttribute("activeAccountantDetails", activeAccountantDetails);
				} 
				}
				model = new ModelAndView("/Supplier/internal/getAccountantListPageForPartner");
			}
			return model;
		
		
		
		}

	}

