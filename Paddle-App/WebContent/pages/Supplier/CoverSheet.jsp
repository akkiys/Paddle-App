<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cover Sheet</title>

<link rel="stylesheet" href="/assets/stylesheets/main.css" />
<!-- imports all the CSS Files -->
<script src="/assets/javascripts/vendor/custom.modernizr.js"></script>
<script src="/assets/javascripts/Custom.js"></script>

</head>
<body onload="loadSupplierMenu();">
	<input type="hidden" name="clientId"
		value='<c:out value="${sessionScope.supplierId}"/>' />
	<input type="hidden" name="userId"
		value='<c:out value="${sessionScope.userId}"/>' />
	<div class="wrap-menu">
		<div class="row">
			<nav class="top-bar">

			<ul class="title-area">
				<!-- Title Area -->
				<li class="name">
					<h1>
						<a href="#">PaddleApp</a>
					</h1>
				</li>
				<!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
				<li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
			</ul>

			<section class="top-bar-section"> <!-- Left Nav Section -->
			<ul class="left">
				<li><a href="#"> <span class="color-red"><c:out
								value="${sessionScope.supplierName}" /></span></a></li>
				<!-- <li class="has-form"><a class="app-menu-button button" href="#pricing">Switch</a></li> -->
			</ul>
			<ul class="show-for-small" id="loadMenu">

			</ul>
			<!-- Right Nav Section -->
			<ul class="right">
				<li><a href="#">Welcome <span class="color-red"><c:out
								value="${sessionScope.userName}" />, <c:if
								test="${sessionScope.isAdmin == true}">Admin</c:if> <c:if
								test="${sessionScope.isAccountant == true}">Accountant</c:if> <c:if
								test="${sessionScope.isReviewer == true}">Reviewer</c:if></span></a></li>
				<li class="has-form"><a href="/Logout.html"
					class="app-menu-button button-logout button">Logout</a></li>
			</ul>
			</section> </nav>
		</div>
	</div>



<!-- JOB DETAILS -->
	<div class="wrap-app">
		<div class="row app-body">
			<div class="large-2 columns wrap-side-nav">
				<ul class="side-nav hide-for-small" id="loadMenu1">

				</ul>
			</div>
			<div class="large-10 columns">
				
					<fieldset>
						<legend>Job</legend>

     <div class="row">
     <div class="large-4 columns">
        <label>Client Code</label>
        <input type="text" placeholder="Enter Client Code" name="clientCode"  disabled id="clientCode" value="<c:out value='${jobDetails.fileReference}'/>">
      </div>
      <div class="large-8 columns">
        <label>Client Name</label>
        <input type="text" placeholder="Enter Client Name" name="clientName" disabled id="clientName" value="<c:out value='${jobDetails.fileName}'/>"">
      </div>
    </div>

    <div class="row">
      <div class="large-4 columns">
        <label>Year End</label>
        <input type="text" placeholder="enter year end date" name="yearEnd" disabled id="yearEnd" value="<c:out value='${jobDetails.yearEnding}'/>">
      </div>
      <div class="large-4 columns">
        <label>Date of incorporation</label>
        <input type="text" placeholder="mention if applicable" name="incorporationDate" disabled id="incorporationDate">
      </div>
       <div class="large-4 columns">
        <label>Principal Activity</label>
        <input type="text" placeholder="enter principal Activity" name="principalActivity" disabled id="principalActivity"  value="<c:out value='${jobDetails.principalActivity}'/>">
      </div>
          
        </div>
     

    <div class="row">
      <div class="large-6 columns">
        <label>Urgency/Reasons</label>
        <textarea placeholder="Enter a reason or description " name="reason" id="reason" disabled  ><c:out value='${jobDetails.urgency}'/></textarea>
      </div>
       <div class="large-6 columns">
        <label>Additional Information</label>
        <textarea placeholder="Enter Comments " name="Comments" id="Comments" disabled  ><c:out value='${jobDetails.comments}'/></textarea>
      </div>
    </div>
     
     <div class="row">
     <span class="label">All documents are scanned in the order shown below. A cover page separates categories.</span>
     <span class="label"> If a document is not legible or file has not been scanned to a high enough quality
      please email ScanMan@silverlevene.co.uk</span>
      </div>
   



					</fieldset>
					
	<!--******************************************* COVER SHEET DETAILS -->				
					
					<fieldset>
<legend>Job Details</legend>
            
            
          <!-- upload Job  -->
        <div class="row">
	  
  <form class="custom"  action="/UploadFilesForJob.html" method="post" enctype="multipart/form-data"> 
  <input type="hidden" name="uploadJobId" id="uploadJobId" value="<c:out value='${jobDetails.id}'/>">
  <div id="uploadFile" style="display:none;" >
  	 
   <div class="row" >
 <div class="large-4 columns">
					 <input type="file"   name="uploadFile" id="uploadFile"> 
					
				</div>
				<div class="large-8 columns" style="margin-top:-17px;">
				  <input type="submit" class="button-add" value="Upload" />
				</div>
			</div>
 </div>
   </form>            
           </div> 
            
            
            
            <!-- ************************* -->
            <form class="custom" action="/UpdateCoverJob.html" method="post"> <!-- FORM -->
 
          

          
          
          
          <div class="section-container auto" data-section>
            <section>
                <p class="title" data-section-title><a href="#p1">Current Year</a></p>
                <div class="content" data-section-content>
                
                
                 <div class="row">
	 
 <input type="hidden" name="jobId" value="<c:out value='${jobDetails.id}'/>">
			      <div class="large-6 columns">
			       <label for="JobType">Job Type</label>
				      <select   name="JobType" id="JobType" class="medium">
				         <option value="<c:out value='${coverdetails.jobTypeId}'/>"><c:out value='${coverdetails.jobType}'/></option>  
				        <option value="1">SLC Bookkeeping Job</option>
				        <option value="2">SLC Payroll/Fees Job</option>
				        <option value="3">Sage Job</option>
				      </select>
			      </div>
 
 
                       <div class="large-6 columns">
			       <label for="JobDifficulty">Job Difficulty</label>
				      <select   class="medium" name="JobDifficulty" id="JobDifficulty">
				         <option value="<c:out value='${coverdetails.jobDifficultyId}'/>"><c:out value='${coverdetails.jobDifficulty}'/></option>
				        <option value="1">A</option>
				        <option value="2">B</option>
				        <option value="3">C</option>
				        <option value="4">D</option>
				      </select>
			      </div>
			      
          </div>
          
                
                
                    <p>Books & Records.</p>
                    
                              <table class="table-transactions">
			 
				<tbody>
				
				<!-- CASHBOOK -->
				
					<tr>
						<td>1. CASHBOOK</td>
					</tr>
					
					<tr>
						<td>Bank Statements- Current Account</td>
						<td><class="large-01 columns"><input type="text" placeholder="Additional Information" name="txtCurrentAccount" id="txtCurrentAccount"  value="<c:out value='${coverdetails.bankStatements}'/>"></td>
						<td><label for="IsBankStatements"><input type="checkbox" id="IsBankStatements" <c:out value='${coverCheckdetails.bankStatements}'/>  name="IsBankStatements" onchange="javascript:updateCoverSheetCheckList('IsBankStatements');"   style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td> <i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Bank Statements- Other Account</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.bankStatementsOther}'/>" placeholder="Additional Information" name="txtOtherAccount" id="txtOtherAccount"></td>
						<td><label for="IsBankStatementOther"><input type="checkbox" id="IsBankStatementOther"  <c:out value='${coverCheckdetails.bankStatementsOther}'/> name="IsBankStatementOther" onchange="javascript:updateCoverSheetCheckList('IsBankStatementOther');"  style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Bank Reconciliation</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.bankReconciliation}'/>" placeholder="Additional Information" name="txtBankReconciliation" id="txtBankReconciliation"></td>
						<td><label for="IsBankReconciliation"><input type="checkbox" id="IsBankReconciliation" <c:out value='${coverCheckdetails.bankReconciliation}'/> name="IsBankReconciliation" onchange="javascript:updateCoverSheetCheckList('IsBankReconciliation');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Bank Payments & Receipts</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.bankPayments}'/>" placeholder="Additional Information" name="IsBankPayments" id="txtBankPayments"></td>
						<td><label for="IsBankPayments"><input type="checkbox" id="IsBankPayments" <c:out value='${coverCheckdetails.bankPayments}'/> name="IsBankPayments" onchange="javascript:updateCoverSheetCheckList('IsBankPayments');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Cheque Book Details</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.chequeBook}'/>" placeholder="Additional Information" name="txtChequeBookDetails" id="txtChequeBookDetails"></td>
						<td><label for="IsChequeBook"><input type="checkbox" id="IsChequeBook" <c:out value='${coverCheckdetails.chequeBook}'/> name="IsChequeBook" onchange="javascript:updateCoverSheetCheckList('IsChequeBook');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Other......</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.other}'/>"  placeholder="Additional Information" name="txtOther" id="txtOther"></td>
						<td><label for="IsOther"><input type="checkbox" id="IsOther" <c:out value='${coverCheckdetails.other}'/> name="IsOther" onchange="javascript:updateCoverSheetCheckList('IsOther');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					
					
					<!-- PURCHASES -->
					
					<tr>
						<td>2. PURCHASES</td>
					</tr>
					
					<tr>
						<td>Purchase Day Book (via email)</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.purchaseDayEmail}'/>" placeholder="Additional Information" name="txtPurchaseDayBook" id="txtPurchaseDayBook"></td>
						<td><label for="IsPurchaseDayEmail"><input type="checkbox" id="IsPurchaseDayEmail" <c:out value='${coverCheckdetails.purchaseDayEmail}'/> name="IsPurchaseDayEmail" onchange="javascript:updateCoverSheetCheckList('IsPurchaseDayEmail');" style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Purchase Day Book Summary</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.purchaseDaySummary}'/>" placeholder="Additional Information" name="txtPurchaseDayBookSummary" id="txtPurchaseDayBookSummary"></td>
						<td><label for="IsPurchaseDaySummary"><input type="checkbox" id="IsPurchaseDaySummary" <c:out value='${coverCheckdetails.purchaseDaySummary}'/> name="IsPurchaseDaySummary" onchange="javascript:updateCoverSheetCheckList('IsPurchaseDaySummary');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Purchase Invoices</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.purchaseInvoices}'/>" placeholder="Additional Information" name="txtPurchaseInvoices" id="txtPurchaseInvoices"></td>
						<td><label for="IsPurchaseInvoices"><input type="checkbox" id="IsPurchaseInvoices" <c:out value='${coverCheckdetails.purchaseInvoices}'/> name="IsPurchaseInvoices" onchange="javascript:updateCoverSheetCheckList('IsPurchaseInvoices');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Expense invoices/receipts</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.expenseInvoices}'/>" placeholder="Additional Information" name="txtExpenseInvoice" id="txtExpenseInvoice"></td>
						<td><label for="IsExpenseInvoices"><input type="checkbox" id="IsExpenseInvoices" <c:out value='${coverCheckdetails.expenseInvoices}'/> name="IsExpenseInvoices" onchange="javascript:updateCoverSheetCheckList('IsExpenseInvoices');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					
					<!-- SALES -->
					
					<tr>
						<td>3. SALES</td>
					</tr>
					
					<tr>
						<td>Sales Day Book </td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.salesDay}'/>" placeholder="Additional Information" name="txtSalesDayBook" id="txtSalesDayBook"></td>
						<td><label for="IsSalesDay"><input type="checkbox" id="IsSalesDay" <c:out value='${coverCheckdetails.salesDay}'/>
						 name="IsSalesDay" onchange="javascript:updateCoverSheetCheckList('IsSalesDay');" style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Sales Day Book Summary</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.salesDaySummary}'/>" placeholder="Additional Information" name="txtSalesDayBookSummary" id="txtSalesDayBookSummary"></td>
						<td><label for="IsSalesDaySummary"><input type="checkbox" id="IsSalesDaySummary"
						<c:out value='${coverCheckdetails.salesDaySummary}'/> name="IsSalesDaySummary" onchange="javascript:updateCoverSheetCheckList('IsSalesDaySummary');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Sales Invoices</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.salesInvoices}'/>" placeholder="Additional Information" name="txtSalesInvoices" id="txtSalesInvoices"></td>
						<td><label for="IsSalesInvoices"><input type="checkbox" id="IsSalesInvoices"
						 <c:out value='${coverCheckdetails.salesInvoices}'/> name="IsSalesInvoices" onchange="javascript:updateCoverSheetCheckList('IsSalesInvoices');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Sales Invoice Summary</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.salesInvoiceSummary}'/>" placeholder="Additional Information" name="txtSalesInvoiceSummary" id="txtSalesInvoiceSummary"></td>
						<td><label for="IsSalesInvoiceSummary"><input type="checkbox" id="IsSalesInvoiceSummary"
						<c:out value='${coverCheckdetails.salesInvoiceSummary}'/> name="IsSalesInvoiceSummary" onchange="javascript:updateCoverSheetCheckList('IsSalesInvoiceSummary');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Rental Income Summary</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.rentalIncomeSummary}'/>" placeholder="Additional Information" name="txtRentalIncomeSummary" id="txtRentalIncomeSummary"></td>
						<td><label for="IsRentalIncomeSummary"><input type="checkbox" id="IsRentalIncomeSummary" 
						<c:out value='${coverCheckdetails.rentalIncomeSummary}'/> name="IsRentalIncomeSummary" onchange="javascript:updateCoverSheetCheckList('IsRentalIncomeSummary');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					
					<!-- WAGES -->
					
					<tr>
						<td>4. WAGES</td>
					</tr>
					
					<tr>
						<td>Salaries</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.salaries}'/>" placeholder="Additional Information" name="txtSalaries" id="txtSalaries"></td>
						<td><label for="IsSalaries"><input type="checkbox" id="IsSalaries"
						<c:out value='${coverCheckdetails.salaries}'/> name="IsSalaries" onchange="javascript:updateCoverSheetCheckList('IsSalaries');" style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>P35's/P11D's</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.p35}'/>" placeholder="Additional Information" name="txtP35" id="txtP35"></td>
						<td><label for="IsP35"><input type="checkbox" <c:out value='${coverCheckdetails.p35}'/>
						 id="IsP35" name="IsP35" onchange="javascript:updateCoverSheetCheckList('IsP35');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Wages/Deduction Sheets</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.wages}'/>" placeholder="Additional Information" name="txtWages" id="txtWages"></td>
						<td><label for="IsWages"><input type="checkbox" id="IsWages"
						<c:out value='${coverCheckdetails.wages}'/>  name="IsWages" 
						onchange="javascript:updateCoverSheetCheckList('IsWages');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					
					<!-- STOCKS -->
					
					<tr>
						<td>5. STOCKS</td>
					</tr>
					
					<tr>
						<td>Stocks & WIP Sheets</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.stocks}'/>" placeholder="Additional Information" name="txtStocks" id="txtStocks"></td>
						<td><label for="IsStocks"><input type="checkbox" id="IsStocks" <c:out value='${coverCheckdetails.stocks}'/>
						 name="IsStocks" onchange="javascript:updateCoverSheetCheckList('IsStocks');"  style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					
	                <!-- PETTY CASH -->
					
					<tr>
						<td>6. PETTY CASH</td>
					</tr>
					
					<tr>
						<td>Petty Cash Control Account</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.pettyCashControl}'/>" placeholder="Additional Information" name="txtPettyCash" id="txtPettyCash"></td>
						<td><label for="IsPettyCashControl"><input type="checkbox" id="IsPettyCashControl" <c:out value='${coverCheckdetails.pettyCashControl}'/> name="IsPettyCashControl"  onchange="javascript:updateCoverSheetCheckList('IsPettyCashControl');"  style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Petty Cash Book</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.pettyCashBook}'/>" placeholder="Additional Information" name="txtPettyCashBook" id="txtPettyCashBook"></td>
						<td><label for="IsPettyCashBook"><input type="checkbox" id="IsPettyCashBook" <c:out value='${coverCheckdetails.pettyCashBook}'/>
						 name="IsPettyCashBook"  onchange="javascript:updateCoverSheetCheckList('IsPettyCashBook');"  style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Petty Cash Receipts</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.pettyCashReceipt}'/>" placeholder="Additional Information" name="txtPettyCashReceipts" id="txtPettyCashReceipts"></td>
						<td><label for="IsPettyCashReceipt"><input type="checkbox" id="IsPettyCashReceipt" <c:out value='${coverCheckdetails.pettyCashReceipt}'/> 
						name="IsPettyCashReceipt"  onchange="javascript:updateCoverSheetCheckList('IsPettyCashReceipt');"  style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					
					 <!-- CREDIT CARD PAYMENTS -->
					
					<tr>
						<td>7. CREDIT CARD PAYMENTS</td>
					</tr>
					
					<tr>
						<td>Credit Card Analysis</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.creditCardAnalysis}'/>" placeholder="Additional Information" name="txtCreditCardAnalysis" id="txtCreditCardAnalysis"></td>
						<td><label for="IsCreditCardAnalysis"><input type="checkbox" id="IsCreditCardAnalysis" <c:out value='${coverCheckdetails.creditCardAnalysis}'/>
						name="IsCreditCardAnalysis"  onchange="javascript:updateCoverSheetCheckList('IsCreditCardAnalysis');" style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Credit Card Statements</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.creditCardStatements}'/>" placeholder="Additional Information" name="txtCreditCardStatements" id="txtCreditCardStatements"></td>
						<td><label for="IsCreditCardStatements"><input type="checkbox" id="IsCreditCardStatements" <c:out value='${coverCheckdetails.creditCardStatements}'/>
						 name="IsCreditCardStatements"  onchange="javascript:updateCoverSheetCheckList('IsCreditCardStatements');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					
					<!-- VAT RETURNS -->
					
					<tr>
						<td>8. VAT RETURNS</td>
					</tr>
					
					<tr>
						<td>VAT Returns</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.vatReturns}'/>" placeholder="Additional Information" name="txtVatReturns" id="txtVatReturns"></td>
						<td><label for="IsVATReturns"><input type="checkbox" id="IsVATReturns" <c:out value='${coverCheckdetails.vatReturns}'/>
						 name="IsVATReturns"  onchange="javascript:updateCoverSheetCheckList('IsVATReturns');" style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>VAT Returns/Calculations</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.vatCalculations}'/>" placeholder="Additional Information" name="txtVatCalculations" id="txtVatCalculations"></td>
						<td><label for="IsVatCalculations"><input type="checkbox" id="IsVatCalculations" <c:out value='${coverCheckdetails.vatCalculations}'/>
						 name="IsVatCalculations"  onchange="javascript:updateCoverSheetCheckList('IsVatCalculations');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Falt Rate</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.flatRate}'/>" placeholder="%" name="txtFileRate" id="txtFileRate"></td>
						<td><label for="IsFlatRate"><input type="checkbox" id="IsFlatRate" <c:out value='${coverCheckdetails.flatRate}'/>
						 name="IsFlatRate"  onchange="javascript:updateCoverSheetCheckList('IsFlatRate');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Standard Rate</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.standardRate}'/>" placeholder="20%" name="txtStandardRate" id="txtStandardRate"></td>
						<td><label for="IsStandardRate"><input type="checkbox" id="IsStandardRate" <c:out value='${coverCheckdetails.standardRate}'/>
						 name="IsStandardRate"  onchange="javascript:updateCoverSheetCheckList('IsStandardRate');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					
					<!-- OTHER ITEMS -->
					
					<tr>
						<td>9. OTHER ITEMS</td>
					</tr>
					
					<tr>
						<td>Company Profile</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.companyProfile}'/>" placeholder="Additional Information" name="txtCompanyProfile" id="txtCompanyProfile"></td>
						<td><label for="IsCompanyProfile"><input type="checkbox" id="IsCompanyProfile" <c:out value='${coverCheckdetails.companyProfile}'/>
						 onchange="javascript:updateCoverSheetCheckList('IsCompanyProfile');" name="IsCompanyProfile" style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Leases and Loan Agreements</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.leases}'/>" placeholder="Additional Information" name="txtLeases" id="txtLeases"></td>
						<td><label for="IsLeases"><input type="checkbox" id="IsLeases" <c:out value='${coverCheckdetails.leases}'/>
						 onchange="javascript:updateCoverSheetCheckList('IsLeases');" name="IsLeases" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Client Letter</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.clientLetter}'/>" placeholder="Additional Information" name="txtClientLetter" id="txtClientLetter"></td>
						<td><label for="IsClientLetter"><input type="checkbox" id="IsClientLetter" <c:out value='${coverCheckdetails.clientLetter}'/>
						 onchange="javascript:updateCoverSheetCheckList('IsClientLetter');" name="IsClientLetter" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>C/Y lead schedules- Via email</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.cyLeadSchedules}'/>" placeholder="Additional Information" name="txtLeadSchedules" id="txtLeadSchedules"></td>
						<td><label for="IsCyLeadSchedules"><input type="checkbox" id="IsCyLeadSchedules" <c:out value='${coverCheckdetails.cyLeadSchedules}'/>
						 onchange="javascript:updateCoverSheetCheckList('IsCyLeadSchedules');" name="IsCyLeadSchedules" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
				</tbody>
			</table>
                    
                </div>
            </section>
            
            <section>
                <p class="title" data-section-title><a href="#p2">Previous Year</a></p>
                <div class="content" data-section-content>
                   
                 <p>Books & Records.</p>
                    
               <table class="table-transactions">
			 
				<tbody>
				
				<!-- PREVIOUS YEAR -->
				
					<tr>
						<td>10. PREVIOUS YEAR</td>
					</tr>
					
					<tr>
						<td>Annual Accounts- Via email</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.annualAccounts}'/>" placeholder="Additional Information" name="txtAnnualAccount" id="txtAnnualAccount"></td>
						<td><label for="IsannualAccounts"><input type="checkbox" id="IsannualAccounts" <c:out value='${coverCheckdetails.annualAccounts}'/>
						 name="IsannualAccounts"  onchange="javascript:updateCoverSheetCheckList('IsannualAccounts');" style="display: none;" >
                        <span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Final Trial Balance- Via email</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.finalTrialBalance}'/>" placeholder="Additional Information" name="txtFinalTrialBalance" id="txtFinalTrialBalance"></td>
						<td><label for="IsFinalTrialBalance"><input type="checkbox" id="IsFinalTrialBalance" <c:out value='${coverCheckdetails.finalTrialBalance}'/>
						name="IsFinalTrialBalance"  onchange="javascript:updateCoverSheetCheckList('IsFinalTrialBalance');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>
					
					<tr>
						<td>Lead Schedules- Via email</td>
						<td><class="large-01 columns"><input type="text" value="<c:out value='${coverdetails.leadSchedules}'/>" placeholder="Additional Information" name="txtLeadSchedules" id="txtLeadSchedules"></td>
						<td><label for="IsLeadSchedules"><input type="checkbox" id="IsLeadSchedules" <c:out value='${coverCheckdetails.leadSchedules}'/>
						name="IsLeadSchedules"  onchange="javascript:updateCoverSheetCheckList('IsLeadSchedules');" style="display: none;">
						<span class="custom checkbox"></span></label></td>
						<td><i class="small button" onClick="javascript:UploadFileDiv()">Upload</i></td>
					</tr>

                    </tbody>
			</table>

                </div>
            </section>
            
            
             <section>
                <p class="title" data-section-title><a href="#p2">Accountancy Fee</a></p>
                <div class="content" data-section-content>
                   
               
   <div class="row">
      <div class="large-4 columns">
        <label>Accountancy Fee</label>
        <input type="text" placeholder="Accountancy Fee" value="<c:out value='${coverdetails.accountancyFee}'/>" name="accountancyFee" id="accountancyFee">
      </div>
      <div class="large-4 columns">
        <label>Disbursements</label>
        <input type="text" placeholder="Disbursements" value="<c:out value='${coverdetails.disbursements}'/>" name="disbursements" id="disbursements">
      </div>
       <div class="large-4 columns">
        <label>Time Budget</label>
        <input type="text" placeholder="Enter Time Budget" value="<c:out value='${coverdetails.timeBudget}'/>" name="timeBudget" id="timeBudget">
      </div>
          
        </div>
            
          
  
      <!-- <div class="row">
        
        <div class="large-4 columns">
        <label for="right-label" class="right inline">Accountancy Fee</label>
          <input type="text" id="right-label" placeholder="Enter Accountancy Fee" name="accountancyFee" id="accountancyFee">
        </div>
      </div> -->
   
 
             </div>
            </section>
            
            
        </div>      <!-- End of TAB div  -->
 
 
 <div class="switch small-4">
  <input id="x" name="switch-x" type="radio"  >
  <label for="x" onclick="">Incomplete</label>

  <input id="x1" name="switch-x" type="radio" checked>
  <label for="x1" onclick="">Complete</label>

  <span></span>
  
 
</div>
<div class="row">	
 <div class="small-4 columns">
			     		<input type="submit" class="button-submit" value="Submit" />
			     		 	     	<input type="submit" class="button-submit" value="Cancel" />
	</div>
	
	<!-- <div class="small-4 columns">
			    
	</div> -->
</div>	
 
 
  </form>         
        
</fieldset>        <!-- End of TAB fieldset  -->
					
	

            </div>


 
		</div>
		
	</div>




  
	 
	<div class="wrap-footer">
		<div class="row">
			<div class="small-10 small-centered columns">
				<ul class="inline-list">
					<li><a href="#">Copyright &copy; PaddleApp 2013</a></li>
					<li><a href="#">Terms & Conditions</a></li>
					<li><a href="#">Privacy Policy</a></li>
					<li><a href="#">Contact</a></li>
					<li><a href="#">Twitter</a></li>
					<li><a href="#">Facebook</a></li>
				</ul>
			</div>
		</div>
	</div>


	<script>
  document.write('<script src=' +
  ('__proto__' in {} ? '/assets/javascripts/vendor/zepto' : '/assets/javascripts/vendor/jquery') +
  '.js><\/script>')
  </script>

	<script src="assets/javascripts/foundation/foundation.js"></script>

	<script src="/assets/javascripts/foundation/foundation.alerts.js"></script>

	<script src="/assets/javascripts/foundation/foundation.clearing.js"></script>

	<script src="/assets/javascripts/foundation/foundation.cookie.js"></script>

	<script src="/assets/javascripts/foundation/foundation.dropdown.js"></script>

	<script src="/assets/javascripts/foundation/foundation.forms.js"></script>

	<script src="/assets/javascripts/foundation/foundation.joyride.js"></script>

	<script src="/assets/javascripts/foundation/foundation.magellan.js"></script>

	<script src="/assets/javascripts/foundation/foundation.orbit.js"></script>

	<script src="/assets/javascripts/foundation/foundation.placeholder.js"></script>

	<script src="/assets/javascripts/foundation/foundation.reveal.js"></script>

	<script src="/assets/javascripts/foundation/foundation.section.js"></script>

	<script src="/assets/javascripts/foundation/foundation.tooltips.js"></script>

	<script src="/assets/javascripts/foundation/foundation.topbar.js"></script>

	<!-- DWR CONFIGURATION -->
	<script type='text/javascript'
		src="/assets/javascripts/dwr/SupplierDWR.js"></script>
	<script type='text/javascript' src='/dwr/engine.js'></script>
	<script type='text/javascript' src='/dwr/interface/SupplierDWR.js'></script>
	<script type='text/javascript' src='/dwr/util.js'></script>




	<script>
    $(document).foundation();
    $('table').stacktable();
    
    $(document).ready(function() {
        $("a[href='#p1']").bind("click", function(){
            alert(" panel 1"); //panel 1 is about to be opened by user
    });

    $("a[href='#p2']").bind("click", function(){
        alert(" panel 2"); // panel 2 is about to be opened
    });

});
 
  </script>



</body>
</html>