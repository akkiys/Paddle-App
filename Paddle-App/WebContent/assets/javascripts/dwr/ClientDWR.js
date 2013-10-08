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


function loadClientMenu()
{
	dwr.util.useLoadingMessage();
	  
	ClientDWR.getInclude(function(data) {
		    dwr.util.setValue("loadMenu", data, { escapeHtml:false });
			 
		    dwr.util.setValue("loadMenu1", data, { escapeHtml:false });	    
	
	});
	

}


function loadEditJobPage(jobId)
{
	dwr.util.useLoadingMessage();	
 
	  window.location.href = "ClientEditJob.html?jobId="+jobId;  
	
 
}


//****************************************************************************




function approvehandShaking(supplierID)
{ 
	dwr.util.useLoadingMessage();
 
    if(supplierID=="")
	  {
	  alert("Invalid Operation");
	  return false;
	  }
   
   
   ClientDWR.approvehandShaking(supplierID, function(details){  
	   
	    window.location.href = "/ClientHandShaking.html";
	 }); 
   
   
}


//delete handShaking
function deleteHandShaking(id)
{ 
	dwr.util.useLoadingMessage();
	
    if(id=="")
	  {
	  alert("Invalid Operation");
	  return false;
	  }
    
    ClientDWR.deleteClientSupplierConnection(id, function(details){  
	  
	   window.location.href = "/ClientHandShaking.html";
	 }); 
   
   
}


//************** delete Client Users ***********************
function deleteClientUsers(id)
{ 
	dwr.util.useLoadingMessage();
	 
    if(id=="")
	  {
	  alert("Invalid Operation");
	  return false;
	  }
    
    ClientDWR.deleteClientUserdetails(id, function(details){  
	  
	    window.location.href = "ClientUsers.html";
	 }); 
  
   
}


function addClientUsers()
{ 
	dwr.util.useLoadingMessage();
 

	var ClientBeans = {
			  name: dwr.util.getValue("UserName"),
			  email:dwr.util.getValue("UserEmail"),
			  password:dwr.util.getValue("UserPassword"),
			  userType:dwr.util.getValue("userType"),
			  isAdmin: dwr.util.getValue("isAdmin"),
			}
	


	if(ClientBeans.name=="")
	  {
	  alert("Enter the name");
	  return false;
	  }
   if(ClientBeans.email=="")
	  {
	   alert("Enter the  email");
	   return false;
	  }
   if(ClientBeans.password=="")
	  {
	   alert("Enter the password");
	   return false;
	  }
    if(ClientBeans.userType=="" &&  ClientBeans.isAdmin=="")
	  {
	   alert("Select the Usertype");
	   return false;
	  }
 
   //alert(ClientBeans.name);    alert(ClientBeans.email);    alert(ClientBeans.password);    alert(ClientBeans.userType);    alert(ClientBeans.isAdmin);
    
   ClientDWR.addClientUsers(ClientBeans, function(details){  
	  
	    window.location.href = "ClientUsers.html";
	 });  
   
}



function editClientUsers(editUserId,editUserName,editIsAdmin,editIsPartner,editIsManager)
{
	//alert("");
	//alert(editUserId);	alert(editUserName); alert(editIsAdmin); alert(editIsPartner); alert(editIsManager);
document.getElementById("editDiv").style.display="";
document.getElementById("addDiv").style.display="none";

dwr.util.setValue('editUserID', editUserId); // edit user Id
dwr.util.setValue('editUserName', editUserName); // user Name
 
if(editIsPartner=="true")
{ 
$("#editIsPartner").prop("checked", true);
	 
}
if(editIsManager=="true")
{
 $("#editIsManager").prop("checked", true);
}

if(editIsAdmin=="true")
{
 $("#editIsAdmin").prop("checked", true);  
}

}


// update user

function updateUsers()
{
	dwr.util.useLoadingMessage();
	var ClientBeans = {
			  id: dwr.util.getValue("editUserID"),
			  userType:dwr.util.getValue("editUserType"),
			  isAdmin: dwr.util.getValue("editIsAdmin"),
			  name: dwr.util.getValue("editUserName"),
			  userId: dwr.util.getValue("editUserID"),
			}
	if(ClientBeans.id=="")
	  {
	  alert("Invalid Id");
	  return false;
	  }
 if(ClientBeans.userType=="")
	  {
	   alert("Select the usertype");
	   return false;
	  }
 
 
	
 ClientDWR.updateClientUsers(ClientBeans, function(details){  
	  
	    window.location.href = "ClientUsers.html";
	 });   


}


function assignPartnerstoManager(id,managerId){
	dwr.util.useLoadingMessage();
	  if(id!="" ||  managerId!="")
	  {
	$(id).each(function(idx, val)
			{
			 
			var partnerIds= $(val).val() ;// partner Ids 
			 
			ClientDWR.assignPartnerstoManager(String(partnerIds),managerId, function(details){  
				  
			   // window.location.href = "ClientUsers.html";
			 }); 

			});
	
	  }} 


//delete Partners
function deletePartners(id)
{ 
 dwr.util.useLoadingMessage();
  
    if(id=="")
   {
   alert("Invalid Operation");
   return false;
   }
    
    ClientDWR.deletePartnerdetails(id, function(details){  
   
     window.location.href = "Partners.html";
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
	ClientDWR.updateCoverSheetCheckList(jobId,fieldName,fieldValue, function(details){ 
		   
	     // window.location.href = "SupplierJobs.html";
	   });
	 
	 
		}

}

function UploadFileDiv()
{
	 
	dwr.util.useLoadingMessage();		
	document.getElementById("uploadFile").style.display="";
	 
	 
}


function test( )
{
	dwr.util.useLoadingMessage();
	alert("Hello World!!! ");
 
}