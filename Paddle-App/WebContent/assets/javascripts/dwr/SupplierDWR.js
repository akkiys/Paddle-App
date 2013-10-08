function showHideDiv(typeu)
{
	// 1= open ; 0=close;   
	dwr.util.useLoadingMessage();
	if(typeu=="1")
	{
	 
 document.getElementById("addDiv").style.display="";
 document.getElementById("Addbutton").style.display="none";
 document.getElementById("editDiv").style.display="none";

	}
else if(typeu=="0")
{
	 
	 document.getElementById("addDiv").style.display="none";
	 document.getElementById("Addbutton").style.display="";
}
else if(typeu=="2")
{
	 
	 document.getElementById("editDiv").style.display="none";
	 
}	
}

function loadSupplierMenu()
{
	dwr.util.useLoadingMessage();
	//alert("hello ");
	SupplierDWR.getInclude(function(data) {
		    dwr.util.setValue("loadMenu", data, { escapeHtml:false });
			 
		    dwr.util.setValue("loadMenu1", data, { escapeHtml:false });	    
	
	
	
	});
	



}




//***************************************************************************************************

function handShaking()
{ 
	dwr.util.useLoadingMessage();
	var companyName = dwr.util.getValue("companyName");
	var email = dwr.util.getValue("email");
	
    if(companyName=="")
	  {
	  alert("Enter the company name");
	  return false;
	  }
   if(email=="")
	  {
	   alert("Enter the company email");
	   return false;
	  }
  
   SupplierDWR.handShaking(companyName,email, function(details){  
	  
	    window.location.href = "HandShaking.html";
	 }); 
   
   
}


// delete handShaking
function deleteHandShaking(id)
{ 
	dwr.util.useLoadingMessage();
	 
    if(id=="")
	  {
	  alert("Invalid Operation");
	  return false;
	  }
    
   SupplierDWR.deleteClientSupplierConnection(id, function(details){  
	  
	    window.location.href = "HandShaking.html";
	 }); 
   
   
}

function addSupplierUsers()
{ 
	dwr.util.useLoadingMessage();
 

	var SupplierBeans = {
			  name: dwr.util.getValue("UserName"),
			  email:dwr.util.getValue("UserEmail"),
			  password:dwr.util.getValue("UserPassword"),
			  userType:dwr.util.getValue("userType"),
			  isAccountant: dwr.util.getValue("isAccountant"),
			  isReviewer: dwr.util.getValue("isReviewer"),
			  isAdmin: dwr.util.getValue("isAdmin"),
			}
	
	
	if(SupplierBeans.name=="")
	  {
	  alert("Enter the name");
	  return false;
	  }
   if(SupplierBeans.email=="")
	  {
	   alert("Enter the  email");
	   return false;
	  }
   if(SupplierBeans.password=="")
	  {
	   alert("Enter the password");
	   return false;
	  }
    if(SupplierBeans.isAccountant=="" && SupplierBeans.isReviewer=="" && SupplierBeans.isAdmin=="")
	  {
	   alert("Select the Usertype");
	   return false;
	  }
 
    
   SupplierDWR.addSupplierUsers(SupplierBeans, function(details){  
	  
	    window.location.href = "SupplierUsers.html";
	 });   
   
}


// ************* delete Supplier Users *********************************
function deleteSupplierUsers(id)
{ 
	dwr.util.useLoadingMessage();
	 
    if(id=="")
	  {
	  alert("Invalid Operation");
	  return false;
	  }
    
   SupplierDWR.deleteSupplierUserdetails(id, function(details){  
	  
	    window.location.href = "SupplierUsers.html";
	 }); 
   
   
}


function editSupplierUsers(editUserId,editUserName,editIsAccountant,editIsReviewer,editIsAdmin)
{
	//alert("");
	// alert(editUserId);	alert(editUserName); alert(editIsAdmin); alert(editIsAccountant); alert(editIsReviewer);
document.getElementById("editDiv").style.display="";
document.getElementById("addDiv").style.display="none";

dwr.util.setValue('editUserID', editUserId); // edit user Id
dwr.util.setValue('editUserName', editUserName); // user Name
 
if(editIsAccountant=="true")
{ 
$("#editIsAccountant").prop("checked", true);
	 
}
if(editIsReviewer=="true")
{
 $("#editIsReviewer").prop("checked", true);
}

if(editIsAdmin=="true")
{
 $("#editIsAdmin").prop("checked", true);  
}

}

//update user

function updateSupplierUsers()
{
	dwr.util.useLoadingMessage();
	 
	var Userdetails = {
			  id: dwr.util.getValue("editUserID"),
			  isAccountant: dwr.util.getValue("editIsAccountant"),
			  isReviewer: dwr.util.getValue("editIsReviewer"),
			  isAdmin: dwr.util.getValue("editIsAdmin"),
			}

	if(Userdetails.id=="")
	  {
	  alert("Invalid Id");
	  return false;
	  }
	 
	   if(Userdetails.isAccountant=="" && Userdetails.isReviewer=="" && Userdetails.isAdmin=="")
		  {
		   alert("Select the Usertype");
		   return false;
		  }
	
 
 SupplierDWR.updateSupplierUsers(Userdetails, function(details){  
	  
	    window.location.href = "SupplierUsers.html";
	 });
}


function assignReviewerToAccountant(id,accountantId){
	 
	dwr.util.useLoadingMessage();
	  if(id!="" ||  accountantId!="")
	  {
	$(id).each(function(idx, val)
			{
			 
			var reviewerIds= $(val).val() ;// reviewer Ids 
			 
			SupplierDWR.assignReviewertoAccountant(String(reviewerIds),accountantId, function(details){  
				  
			   // window.location.href = "ClientUsers.html";
			 }); 

			});
	
	  }
	  

} 


//delete Accountants
function deleteAccountants(id)
{ 
 dwr.util.useLoadingMessage();
  
    if(id=="")
   {
   alert("Invalid Operation");
   return false;
   }
    
   SupplierDWR.deleteAccountantDetails(id, function(details){  
   
     window.location.href = "Accountants.html";
  }); 
   
   
}



function getPartnerList()
{
	 dwr.util.useLoadingMessage();
	 var clientId=dwr.util.getValue("clientList");
	 document.getElementById("loadPartner").style.display="";

	 
	 SupplierDWR.getPartnerList(clientId, function(details){  
		  
		 dwr.util.removeAllOptions("partnerList");
		// dwr.util.removeAllOptions("accountantList");
		// dwr.util.removeAllOptions("ReviewerList");
		 $('#partnerList').append(dwr.util.addOptions("partnerList",details,"userId","userName", {escapeHtml:false})).trigger('change');
	
			 
			 
     });
	

}
 

function getAccountantListForPartner()
{
	 
	 dwr.util.useLoadingMessage();
	 document.getElementById("loadAccountant").style.display="";
	 var clientId=dwr.util.getValue("clientList");
	 var partnerId=dwr.util.getValue("partnerList");
	 
	 
	  
	 SupplierDWR.getAccountantListForPartner(clientId,partnerId, function(details){  
		 dwr.util.removeAllOptions("accountantList");
		 dwr.util.removeAllOptions("ReviewerList");
		 
		 $('#accountantList').append(dwr.util.addOptions("accountantList",details,"userId","userName", {escapeHtml:false})).trigger('change');	 
		 
		//	dwr.util.addOptions("accountantList",details,"userId","userName", {escapeHtml:false}); 
	 
		  
     });


}

 
function getReviewerListForAccountant()
{
	 
	 dwr.util.useLoadingMessage();
	 document.getElementById("loadReviewer").style.display="";
	 var clientId=dwr.util.getValue("clientList");
	 var partnerId=dwr.util.getValue("partnerList");
	 var accountantId=dwr.util.getValue("accountantList");
	  
	 SupplierDWR.getReviewerListForAccountant(clientId,partnerId,accountantId, function(details){  
		 dwr.util.removeAllOptions("ReviewerList");
	
		 $('#ReviewerList').append(dwr.util.addOptions("ReviewerList",details,"userId","userName", {escapeHtml:false})).trigger('change');	 
	 
		 
		//	dwr.util.addOptions("ReviewerList",details,"userId","userName", {escapeHtml:false}); 
	 
		  
     });


}

function loadAccountantListPage()
{
	dwr.util.useLoadingMessage();	
	 var clientId=dwr.util.getValue("clientList");
	 var partnerId=dwr.util.getValue("partnerList");
	  
	 
 var values = new Array(clientId,partnerId);
	 SupplierDWR.getPage("getAccountantList",values,function(details){ 
		  
	dwr.util.setValue("loadAccountants",details.page,{escapeHtml:false});  
	 
		
	$(function(){
		$(".multiSelect").multiselect({header: false});
	});
 
	 
	}); 
}


function loadEditJobPage(jobId)
{
	dwr.util.useLoadingMessage();	
 
	  window.location.href = "SuppierEditJob.html?jobId="+jobId;  
	
	
	
	 
/* var values = new Array(String(jobId));
 
	 SupplierDWR.getPage("editJob", values,function(details){ 
		
	dwr.util.setValue("editJob",details.page,{escapeHtml:false});  
 
	 
	 
	});*/ 
}


function assignAccountantToPartners(){
	 
	dwr.util.useLoadingMessage();
	 
	$("#accountantList").each(function(idx, val)
			{
			 
			var accountantIds= $(val).val() ;// accountant Ids  12,87,34
			 var partnerId=dwr.util.getValue("partnerList");
			 var clientId=dwr.util.getValue("clientList");
			
			//alert(accountantIds);
			//alert(partnerId);
			//alert(clientList);
			
			SupplierDWR.assignAccoutantToPartners(clientId,partnerId,String(accountantIds), function(details){  
				  
			   // window.location.href = "ClientUsers.html";
				
			 }); 
			
			//loadAccountantListPage();

			});
 
	// loadAccountantListPage();

} 


function defaultAccountantToPartner()
{
	dwr.util.useLoadingMessage();	
	 var id=dwr.util.getValue("defaultAccountant");
	 var partnerId=dwr.util.getValue("partnerList");
	 var clientId=dwr.util.getValue("clientList");
	 
	 SupplierDWR.defaultAccountantToPartner(clientId,partnerId,id, function(details){  
		  
		   // window.location.href = "ClientUsers.html";
			
		 });
	


}


function defaultReviewerToPartner(id)
{
	
	dwr.util.useLoadingMessage();	
	 var reviewerId=dwr.util.getValue("reviewerList");
	 
  SupplierDWR.defaultReviewerToPartner(reviewerId,id, function(details){  
		  
		   // window.location.href = "ClientUsers.html";
		 
		 });
	 


}

function UploadFileDiv()
{
	 
	dwr.util.useLoadingMessage();		
	document.getElementById("uploadFile").style.display="";
	 
	 
}



//UPDATE JOB STATUS
function updateJobStatus(jobStatusId, jobId)
{
 /*alert(jobStatusId); alert(jobId);*/
 dwr.util.useLoadingMessage(); 
  
  SupplierDWR.updateJobsStatus(jobStatusId,jobId, function(details){ 
   
      window.location.href = "SupplierJobs.html";
   });  
}




function updateCoverSheetCheckList(fieldName)
{
	//alert("fieldName"+fieldName);
	dwr.util.useLoadingMessage();	

	var jobId=dwr.util.getValue("jobId");
	// alert("jobId "+ jobId);
	
	var fieldValue=dwr.util.getValue(fieldName);
	// alert("fieldValue "+ fieldValue);
	 
	if(jobId!="")
		{
	 SupplierDWR.updateCoverSheetCheckList(jobId,fieldName,fieldValue, function(details){ 
		   
	     // window.location.href = "SupplierJobs.html";
	   });
	 
		}


}





function startJob(type)
{
	dwr.util.useLoadingMessage();		
	 var TimeSheetBeans ="";
	if(dwr.util.getValue("selectedradiovalue")=="NewJob")
		{
		alert("NewJob");
		    TimeSheetBeans = {
					jobId: dwr.util.getValue("jobId"),
					jobstatusId:dwr.util.getValue("jobStatusId"),
					notes:dwr.util.getValue("Comments"),
					userType:dwr.util.getValue("usertType"),   
					userId:dwr.util.getValue("userId"),  
					}
      } 
	else if(dwr.util.getValue("selectedradiovalue")=="AssistJob")
		{
		alert("AssistJob");
		   TimeSheetBeans = {
					jobId: dwr.util.getValue("AssitjobId"),
					jobstatusId:dwr.util.getValue("AssitJobStatus"),
					notes:dwr.util.getValue("Comments"),
					userType:dwr.util.getValue("usertType"),   
					userId:dwr.util.getValue("userId"),  
					}
		
		}
	else if(dwr.util.getValue("selectedradiovalue")=="Others")
	{
		alert("Others");
		  TimeSheetBeans = {
					jobId: "0",
					jobstatusId:dwr.util.getValue("othersStatus"),
					notes:dwr.util.getValue("Comments"),
					userType:dwr.util.getValue("usertType"),   
					userId:dwr.util.getValue("userId"),  
					}
	}
	alert(TimeSheetBeans)
 	 SupplierDWR.startTimesheet(TimeSheetBeans, function(details){ 
		   
	    if(type="accountant")
	    	{
		 
		 window.location.href = "AccountantTimeSheet.html";
	    	}
	 
	 }); 
	 
}

function stopJob(type,timesheetId)
{
	dwr.util.useLoadingMessage();		
//alert("stopJob "+timesheetId);
	 
	
	var TimeSheetBeans = {
			 
		 
			id:timesheetId,   
			userId:dwr.util.getValue("userId"),  
			}
	
	
	 SupplierDWR.stopTimesheet(TimeSheetBeans, function(details){ 
		   
	    if(type="accountant")
	    	{
		 
		 window.location.href = "AccountantTimeSheet.html";
	    	}
	 
	 });
	 
}





function addLeaveForm()
{
	dwr.util.useLoadingMessage();		
	//alert("startJob" );
	// var jobId=dwr.util.getValue("jobId");
	// var jobStatusId=dwr.util.getValue("jobStatusId");
 //var comments=dwr.util.getValue("Comments");
	
	// alert(dwr.util.getValue("others"));	alert(dwr.util.getValue("LeaveComments"));	alert(dwr.util.getValue("usertType"));
	// alert(dwr.util.getValue("StartDate"));alert(dwr.util.getValue("StartTime"));alert(dwr.util.getValue("EndDate"));
	// alert(dwr.util.getValue("EndTime"));alert(dwr.util.getValue("userId"));
	var leaveForm = {
			 
			jobstatusId:dwr.util.getValue("othersStatus"),
			notes:dwr.util.getValue("LeaveComments"),
			userType:dwr.util.getValue("usertType"), 
			startDay:dwr.util.getValue("StartDate"), 
			startTime:"00:00:00", 
			endDay:dwr.util.getValue("EndDate"), 
			endTime:"00:00:00", 
			userId:dwr.util.getValue("userId"),  
			}
	
	
	 SupplierDWR.insertLeaveForm(leaveForm, function(details){ 
		   
	    if(type="accountant")
	    	{
		 alert(details);
		 window.location.href = "AccountantTimeSheet.html";
	    	}
	 
	 });
	 
}




