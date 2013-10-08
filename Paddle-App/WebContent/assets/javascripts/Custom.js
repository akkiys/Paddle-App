function showHide(type)
{

 
	if(type=="Customer")
	{
	 
 document.getElementById("businessSignup").style.display="none";

document.getElementById("customerSignup").style.display="";

	}
else if(type=="Business")
{
	 
	 document.getElementById("businessSignup").style.display="";
document.getElementById("customerSignup").style.display="none";
 

 
}
}



   function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
       }
   
   
   
   
   
   
// show - Hide >> NewJob , AssistJob, Others in   TimeSheet

   function showHideAccTimeSheet(type)
   {


   	if(type=="NewJob")
   	{
   		 
      document.getElementById("assistJob").style.display="none";
      document.getElementById("others").style.display="none";
      document.getElementById("newJob").style.display="";
      document.getElementById("StartStop").style.display="";
      document.getElementById("leaveForm").style.display="none";  
      $("#selectedradiovalue").val("NewJob");
   	}
   else if(type=="AssistJob")
   {
   	 
   	  document.getElementById("newJob").style.display="none";
   	   document.getElementById("others").style.display="none";
   	  document.getElementById("assistJob").style.display="";
      document.getElementById("StartStop").style.display="";
      document.getElementById("leaveForm").style.display="none";  
      $("#selectedradiovalue").val("AssistJob");
   }
   else if(type=="Others")
   {
   	 
   	  document.getElementById("newJob").style.display="none";
   	  document.getElementById("assistJob").style.display="none";
   	  document.getElementById("others").style.display="";
      document.getElementById("StartStop").style.display="";
      document.getElementById("leaveForm").style.display="none";  
      $("#selectedradiovalue").val("Others");
   } 	
   	
   }
   
    // disable  all  NewJob , AssistJob, Others on TimeSheet Start
   
   function disableFunctionOnStartTimeSheet()
   {
	   
	   document.getElementById("timeSheetMainFunction").style.display="none";  
	   document.getElementById("showStatusOfTimeSheet").style.display="";  
	   
	   
   }
   
   
   function enableFunctionOnStartTimeSheet()
   {
	   
	   document.getElementById("timeSheetMainFunction").style.display="";  
	   document.getElementById("showStatusOfTimeSheet").style.display="none";  
	   
	   
   }
   
   
   function accountantOthers(value) // shows/hides leave form
   {
	    
	   if(value>=10)
		   {
		   document.getElementById("leaveForm").style.display="";  
		   document.getElementById("StartStop").style.display="none";  
		   
		   }
	   else{
		   
		   document.getElementById("leaveForm").style.display="none";  
		   document.getElementById("StartStop").style.display="";  
		   
	   }
	   
	   
   }
   