 
	
	package com.webapp.action.coversheet;

 

	import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

	import com.common.ParameterParser;
import com.persistence.PaddleDAO;
 
import com.persistence.mysql.MYSQLPaddleDAO;
 
import com.webapp.beans.CoverSheetBeans;
 
 

	public class UpdateCoverJob extends AbstractController{
		protected ModelAndView handleRequestInternal(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			    ModelAndView model =null;	
			    HttpSession session = request.getSession(false); // don't create if it doesn't exist
			    PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
			    ParameterParser prsObj = new ParameterParser(request); 
				CoverSheetBeans details=null;
			    if(session !=null) // checks for session
			    {
			    	details=new CoverSheetBeans();
					int jobId=prsObj.getIntParameter("jobId",0); // Job Id
					  //Integer supplierId = (Integer) session.getAttribute("supplierId"); // supplier 
					   System.out.println("oooooEditJob>>> "+jobId);
					   details.setJobId(jobId);
					   details.setBankStatements(prsObj.getStringParameter("txtCurrentAccount",""));
					   details.setBankStatementsOther(prsObj.getStringParameter("txtOtherAccount",""));
					   details.setBankReconciliation(prsObj.getStringParameter("txtBankReconciliation",""));
					   details.setBankPayments(prsObj.getStringParameter("txtBankPayments",""));
					   details.setChequeBook(prsObj.getStringParameter("txtChequeBookDetails",""));
					   details.setOther(prsObj.getStringParameter("txtOther",""));
					   details.setPurchaseDayEmail(prsObj.getStringParameter("txtPurchaseDayBook",""));
					   details.setPurchaseDaySummary(prsObj.getStringParameter("txtPurchaseDayBookSummary",""));
					   details.setPurchaseInvoices(prsObj.getStringParameter("txtPurchaseInvoices",""));
					   details.setExpenseInvoices(prsObj.getStringParameter("txtExpenseInvoice",""));
					   details.setSalesDay(prsObj.getStringParameter("txtSalesDayBook",""));
					   details.setSalesDaySummary(prsObj.getStringParameter("txtSalesDayBookSummary",""));
					   details.setSalesInvoices(prsObj.getStringParameter("txtSalesInvoices",""));
					   details.setSalesInvoiceSummary(prsObj.getStringParameter("txtSalesInvoiceSummary",""));
					   details.setRentalIncomeSummary(prsObj.getStringParameter("txtRentalIncomeSummary",""));
					   details.setSalaries(prsObj.getStringParameter("txtSalaries",""));
					   details.setP35(prsObj.getStringParameter("txtP35",""));
					   details.setWages(prsObj.getStringParameter("txtWages",""));
					   details.setStocks(prsObj.getStringParameter("txtStocks",""));
					   details.setPettyCashControl(prsObj.getStringParameter("txtPettyCash",""));
					   details.setPettyCashBook(prsObj.getStringParameter("txtPettyCashBook",""));
					   details.setPettyCashReceipt(prsObj.getStringParameter("txtPettyCashReceipts",""));
					   details.setCreditCardAnalysis(prsObj.getStringParameter("txtCreditCardAnalysis",""));
					   details.setCreditCardStatements(prsObj.getStringParameter("txtCreditCardStatements",""));
					   details.setVatReturns(prsObj.getStringParameter("txtVatReturns",""));
					   details.setVatCalculations(prsObj.getStringParameter("txtVatCalculations",""));
					   details.setFlatRate(prsObj.getStringParameter("txtFileRate",""));
					   details.setStandardRate(prsObj.getStringParameter("txtStandardRate",""));
					   details.setCompanyProfile(prsObj.getStringParameter("txtCompanyProfile",""));
					   details.setLeases(prsObj.getStringParameter("txtLeases",""));
					   details.setClientLetter(prsObj.getStringParameter("txtClientLetter",""));
					   details.setCyLeadSchedules(prsObj.getStringParameter("txtLeadSchedules",""));
					   details.setAnnualAccounts(prsObj.getStringParameter("txtAnnualAccount",""));
					   details.setFinalTrialBalance(prsObj.getStringParameter("txtFinalTrialBalance",""));
					   details.setLeadSchedules(prsObj.getStringParameter("txtLeadSchedules",""));
					   details.setAccountancyFee(prsObj.getStringParameter("accountancyFee",""));
					   details.setDisbursements(prsObj.getStringParameter("disbursements",""));
					   details.setTimeBudget(prsObj.getStringParameter("timeBudget",""));
					  details.setJobTypeId(prsObj.getIntParameter("JobType",0));
					  details.setJobDifficultyId(prsObj.getIntParameter("JobDifficulty",0));
					  // details
					  // details
					  // details
					   
					   
					   daoObj.updateCoverSheetJob(details); // updates all textbox values
					
					
					
					
					
					
					
						if(session.getAttribute("supplierId")!=null)
						{
							 model = new  ModelAndView("redirect:SupplierJobs.html");
						}
						else if(session.getAttribute("clientId")!=null)
						{
							model = new  ModelAndView("redirect:ClientJobs.html");
						}
					
					
					
					
					
					
					 
			    	
			   // }	
			    
			    
			   
			  
		
		
		
		}
			    return model;		
			
		}
	}
			
			


		
