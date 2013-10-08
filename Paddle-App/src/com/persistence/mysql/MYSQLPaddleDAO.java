package com.persistence.mysql;
import com.persistence.PaddleDAO;
import com.persistence.DAOException;
import com.webapp.beans.CoverSheetBeans;
import com.webapp.beans.JobBeans;
//import com.webapp.beans.Jdemo;
import com.webapp.beans.PaddleBeans;
import com.webapp.beans.SupplierBeans;
import com.webapp.util.PINGenerator;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MYSQLPaddleDAO extends MYSQLConnectionDAO implements PaddleDAO{
private Log log = LogFactory.getLog(MYSQLPaddleDAO.class);
PINGenerator pin = new PINGenerator(); 
private final String DIR = java.util.ResourceBundle.getBundle("PaddleApp").getString("DIR"); 
private File file=null,file2=null;
@Override
public boolean isEmailAvaliable(String email) throws DAOException {
	 
	boolean isadded=false;
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    ResultSet rs=null;  
    boolean result = false;  
      try{
          con = getConnection();
         
         
          SQL = "select id from users where email=?";
          pstmt = con.prepareStatement(SQL);
          pstmt.setString(1, email);   // email ID         
          rs= pstmt.executeQuery();
          if(rs.next()) {
         
        	  result = false;	  
          }
          else
          {
        	  result=true;
          }
          rs.close();
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred  while checking EmailId - isEmailAvaliable()" + ex.getMessage());
          throw new DAOException(ex);
      }catch(Exception e){
          e.printStackTrace();
      }finally{
      
          
          try{
              if(pstmt!=null){   pstmt.close();}
          }catch(Exception e){
              pstmt = null;
            
          }
          
          try{
              if(con!=null){   con.close();}
          }catch(Exception e){
              con = null;
          }
      }

	return result;
	 
	
}

@Override
public int insertNewClient(PaddleBeans details) throws DAOException {
	 
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    ResultSet rs=null,rs1=null;  
   int clientId=0, userid=0;
      try{
          con = getConnection();
          String newPin ="";
          //inserts new user 
          SQL = "insert into users(name,email,password,isClient,uniqueCode) values (?,?,?,?,?)";
          pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
          pstmt.setString(1, details.getClientName());           
          pstmt.setString(2, details.getEmail());
          pstmt.setString(3, details.getPassword());
          pstmt.setBoolean(4, true);
          pstmt.setString(5, pin.generate15DigitNumber()); // generates random security number
          pstmt.executeUpdate();
          rs= pstmt.getGeneratedKeys();
          if(rs.next())
          {
        	  
           userid =rs.getInt(1); // gets inserted new userID
            
         if(userid!=0)
         {
        	 newPin= pin.generate15DigitNumber();
        	 // inserts new company - client
        	 SQL = "insert into client(companyName,email,adminId,uniqueCode) values (?,?,?,?)";
             pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
             pstmt.setString(1, details.getCompanyName());           
             pstmt.setString(2, details.getEmail());
             pstmt.setInt(3, userid); //new user ID
             pstmt.setString(4,newPin);  // generates random security number
             pstmt.executeUpdate(); 
             rs1= pstmt.getGeneratedKeys();
             if(rs1.next())
             {
            	 clientId=rs1.getInt(1);// gets insertd new Client Id (company Id)
            	 
            	 // Create a New Dir
            	 
            	 System.out.println("Directory is "+DIR); 
            	 
            	 

            	   file = new File(DIR+"\\"+details.getCompanyName()+"_"+newPin);
            	if (!file.exists()) {
            	if (file.mkdir()) {
            	System.out.println("Directory is created!");
            	} else {
            	System.out.println("Failed to create directory!");
            	}
            	 SQL = "update client  set Dir=? where id=?";
                 pstmt = con.prepareStatement(SQL);
                 pstmt.setString(1,details.getCompanyName()+"_"+newPin); //  Client folder name
                 pstmt.setInt(2,clientId); //  table id
                 pstmt.executeUpdate();    
                 pstmt.close(); 
             
            	}  
             
               
             
             }  
            
        	 
            // Connects between user and company
             SQL = "insert into clientconnect(ClientId,UserId,isAdmin) values (?,?,?)";
             pstmt = con.prepareStatement(SQL);
             pstmt.setInt(1, clientId);           
             pstmt.setInt(2, userid); 
             pstmt.setBoolean(3, true); // admin for company
             pstmt.executeUpdate(); 
             pstmt.close();
         }
           
           
           
          
          
          }
          rs.close();
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred in insertNewClient() " + ex.getMessage());
          throw new DAOException(ex);
      }catch(Exception e){
          e.printStackTrace();
      }finally{
     
          try{
              if(pstmt!=null){   pstmt.close();}
          }catch(Exception e){
              pstmt = null;
            
          }
          
          try{
              if(con!=null){   con.close();}
          }catch(Exception e){
              con = null;
          }
      }

	return userid;
}

@Override
 
public int insertNewSupplier(PaddleBeans details) throws DAOException {
 
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    ResultSet rs=null,rs1=null;  
   int SupplierId=0, userid=0;
      try{
          con = getConnection();
          
          //inserts new user 
          SQL = "insert into users(name,email,password,isClient,uniqueCode) values (?,?,?,?,?)";
          pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
          pstmt.setString(1, details.getSupplierName());           
          pstmt.setString(2, details.getEmail());
          pstmt.setString(3, details.getPassword());
          pstmt.setBoolean(4, false);
          pstmt.setString(5, pin.generate15DigitNumber()); // generates random security number
          pstmt.executeUpdate();
          rs= pstmt.getGeneratedKeys();
          if(rs.next())
          {
           
           userid =rs.getInt(1); // gets inserted new userID
            
         if(userid!=0)
         {
            // inserts new company - Supplier
             SQL = "insert into supplier(supplierName,email,adminId,uniqueCode) values (?,?,?,?)";
             pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
             pstmt.setString(1, details.getCompanyName());           
             pstmt.setString(2, details.getEmail());
             pstmt.setInt(3, userid); //new user ID
             pstmt.setString(4, pin.generate15DigitNumber());  // generates random security number
             pstmt.executeUpdate(); 
             rs1= pstmt.getGeneratedKeys();
             if(rs1.next())
             {
              SupplierId=rs1.getInt(1);// gets inserted new supplier Id (company Id)
              
             } rs1.close();
             pstmt.close();
            
          
            // Connects between user and company
             SQL = "insert into supplierconnect(UserId,SupplierId,isAdmin) values (?,?,?)";
             pstmt = con.prepareStatement(SQL);
             pstmt.setInt(1, userid);
             pstmt.setInt(2, SupplierId);           
             pstmt.setBoolean(3, true); // admin for company
             pstmt.executeUpdate(); 
         
         }
       
          }
          rs.close();
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred in insertNewSupplier() " + ex.getMessage());
          throw new DAOException(ex);
      }catch(Exception e){
          e.printStackTrace();
      }finally{
     
          try{
              if(pstmt!=null){   pstmt.close();}
          }catch(Exception e){
              pstmt = null;
            
          }
          
          try{
              if(con!=null){   con.close();}
          }catch(Exception e){
              con = null;
          }
      }

 return userid;
}

@Override
public PaddleBeans loginCheck(String email, String password) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	PaddleBeans details =null;
	 
	try{
		details = new PaddleBeans();
	    con = getConnection();
	    SQL = "select id,isClient from users where email=? and password=?  ";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setString(1, email);           
        pstmt.setString(2, password);
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	        details.setId(rs.getInt("id")); // user Id
	        details.setIsClient(rs.getBoolean("isClient")); // true = client; false = supplier
	        details.setResult(true);
	    }
	    else
	    {
	    	 details.setResult(false);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getUsers" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return details;
}

 
@Override
public PaddleBeans getClientUserDetails(int userId, boolean isClient)
		throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null, rs1=null;
	PaddleBeans details =null;
	 
	try{
		details = new PaddleBeans();
	    con = getConnection();
	    SQL = "select ClientId,isAdmin,isPartner,isManager from clientconnect where UserId=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, userId);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	details.setClientName(this.getCompanyName(rs.getInt("ClientId"))); // gives Client name  - Silver levene
	    	details.setClientId(rs.getInt("ClientId")); // Client Id
	    	details.setUserId(userId); // user ID
	    	details.setIsAdmin(rs.getBoolean("isAdmin")); //Admin
	    	details.setIsPartner(rs.getBoolean("isPartner")); //Partner
	    	details.setIsManager(rs.getBoolean("isManager")); //Manager
	    	
	    	 
	        SQL = "select Name,email from users where Id=?";
	  	    pstmt = con.prepareStatement(SQL);
	  	    pstmt.setInt(1, userId);           
	  	    rs1= pstmt.executeQuery();
	  	    if(rs1.next()) {
	  	    	
	  	    	details.setUserName(rs1.getString("Name")); // user name
	  	    	details.setEmail(rs1.getString("email"));  // user Email Id
	  	    	
	  	    }
	  	    rs1.close();
	  	    pstmt.close();
	  	    
	    	
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getClientUserDetails" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return details;
}


public String getSecureCodeForUser(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,uniqueCode="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select uniqueCode from users where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	uniqueCode = rs.getString("uniqueCode");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSecureCodeForUser" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return uniqueCode;
}

 


public String getSecureCodeForClient(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,uniqueCode="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select Dir from client where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	uniqueCode = rs.getString("Dir");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSecureCodeForClient" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return uniqueCode;
}



public String getSecureCodeForJobs(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,uniqueCode="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select SecureCode from jobs where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	uniqueCode = rs.getString("SecureCode");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSecureCodeForJobs" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return uniqueCode;
}
public String getFolderJob(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,uniqueCode="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select Dir from jobs where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	uniqueCode = rs.getString("Dir");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getFolderJob" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return uniqueCode;
}

public String getSecureCodeForPartner(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,uniqueCode="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select partnerDir from clientconnect where UserId=? and isPartner=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);  
	    pstmt.setBoolean(2, true);       
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	uniqueCode = rs.getString("partnerDir");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSecureCodeForPartner" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return uniqueCode;
}


public String getPrincipalActivity(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,PrincipalActivity="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select PrincipalActivity from client_principal_activity where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	PrincipalActivity = rs.getString("PrincipalActivity");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getPrincipalActivity" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return PrincipalActivity;
}


public String getJobStatus(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,Status="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select Status from job_status1 where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	Status = rs.getString("Status");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getJobStatus" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return Status;
}


public String getAccountantJobStatus(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,Status="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select Status from accountant_jobstatus where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	Status = rs.getString("Status");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getAccountantJobStatus" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return Status;
}

public String getReviewerJobStatus(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,Status="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select Status from reviewer_jobstatus where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	Status = rs.getString("Status");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getReviewerJobStatus" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return Status;
}

public String getFileName(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,FileName="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select FileName from jobs where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	FileName = rs.getString("FileName");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getFileName" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return FileName;
}

public String getJobType(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,JobType="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select JobType from client_job_type where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	JobType = rs.getString("JobType");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getJobType" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return JobType;
}


public String getJobDifficulty(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,JobDifficulty="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select Levels from client_job_difficulty where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	JobDifficulty = rs.getString("Levels");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getJobDifficulty" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return JobDifficulty;
}

@Override
public PaddleBeans getSupplierUserDetails(int userId, boolean isClient) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null, rs1=null;
	PaddleBeans details =null;
	 
	try{
		details = new PaddleBeans();
	    con = getConnection();
	    SQL = "select SupplierId,isReviewer,isAccountant,isAdmin from supplierconnect where UserId=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, userId);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	details.setSupplierName(this.getSupplierName(rs.getInt("SupplierId"))); // gives supplier name  - Infosense
	    	details.setSupplierId(rs.getInt("SupplierId")); // Supplier Id
	    	details.setUserId(userId); // user ID
	    	details.setIsAccountant(rs.getBoolean("isAccountant"));
	    	details.setIsReviewer(rs.getBoolean("isReviewer"));
	    	details.setIsAdmin(rs.getBoolean("isAdmin"));
	        SQL = "select Name,email from users where Id=?";
	  	    pstmt = con.prepareStatement(SQL);
	  	    pstmt.setInt(1, userId);           
	  	    rs1= pstmt.executeQuery();
	  	    if(rs1.next()) {
	  	    	
	  	    details.setUserName(rs1.getString("Name")); // user name
	  	    details.setEmail(rs1.getString("email"));  // user Email Id
	  	    	
	  	    }
	  	    rs1.close();
	  	    pstmt.close();
	  	    
	    	
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSupplierUserDetails" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return details;
}



public String getCompanyName(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,companyName="";
	ResultSet rs=null;
	
	 
	try{
	    con = getConnection();
	    SQL = "select companyName from client where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	companyName = rs.getString("companyName");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getCompanyName" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return companyName;
}


public String getSupplierName(int id) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,supplierName="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select supplierName from supplier where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	supplierName = rs.getString("supplierName");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSupplierName" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return supplierName;
}

@Override
 public int isClientExists(String email) throws DAOException {
	int isClientExists=0;
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    ResultSet rs=null;  
    
      try{
          con = getConnection();
          SQL = "select id from client where email=?";
          pstmt = con.prepareStatement(SQL);
          pstmt.setString(1, email);   // email ID         
          rs= pstmt.executeQuery();
          if(rs.next()) {
         
        	  isClientExists = rs.getInt("id"); // returns ID
          }
          rs.close();
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred  in  - isClientExists()" + ex.getMessage());
          throw new DAOException(ex);
      }catch(Exception e){
          e.printStackTrace();
      }finally{
      
          
          try{
              if(pstmt!=null){   pstmt.close();}
          }catch(Exception e){
              pstmt = null;
            
          }
          
          try{
              if(con!=null){   con.close();}
          }catch(Exception e){
              con = null;
          }
      }

	return isClientExists;
	 
}

@Override
public boolean deleteClientSupplierConnection(int id) throws DAOException {
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    
    boolean result=false;
      try{
          con = getConnection();
          SQL = "update client_supplier_connect  set active=? where id=?";
          pstmt = con.prepareStatement(SQL);
          pstmt.setBoolean(1,false);
          pstmt.setInt(2,id); //  table id
          pstmt.executeUpdate();    
          result=true;
          pstmt.close(); 
          
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred  in  - deleteClientSupplierConnection()" + ex.getMessage());
          throw new DAOException(ex);
      }catch(Exception e){
          e.printStackTrace();
      }finally{
      
          
          try{
              if(pstmt!=null){   pstmt.close();}
          }catch(Exception e){
              pstmt = null;
            
          }
          
          try{
              if(con!=null){   con.close();}
          }catch(Exception e){
              con = null;
          }
      }

	return result;
}

 

 

//Returns user name
public String getUserName(int userId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL,userName="";
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select Name from users where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, userId);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	userName = rs.getString("Name");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getUserName" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return userName;
}



public int insertNewJob(JobBeans details)  throws DAOException {
	 
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    ResultSet rs=null;  
   int jobId=0;
      try{
          con = getConnection();
          
          //inserts new user 
          SQL = "insert into jobs(ClientId,SupplierId,PartnerId,accountantId,ReviewerId,FileName,"
          		+ "FileReference,DateRecieved,DateExptected,DateReturned,YearEnding,BudgetedTime,FileStorage,PrincipalActivity,"
          		+ "Comments,Urgency,addedBy,isFollowLeadSchedule,SecureCode,jobStatus) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
          pstmt.setInt(1,details.getClientId() );           
          pstmt.setInt(2, details.getSupplierId());
          pstmt.setInt(3, details.getPartnerId());
          pstmt.setInt(4, details.getAccountantId());
          pstmt.setInt(5, details.getReviewerId());
          pstmt.setString(6, details.getFileName());
          pstmt.setString(7, details.getFileReference());
          pstmt.setString(8, details.getDateReceived());
          pstmt.setString(9, details.getExpectedDate());
          pstmt.setString(10, details.getDateReturned());
          pstmt.setString(11, details.getYearEnding());
          pstmt.setString(12, details.getBudgetedTime());
          pstmt.setString(13, details.getFileStorage());
          pstmt.setInt(14, details.getPrincipalActivityId());
          pstmt.setString(15, details.getComments());
          pstmt.setString(16, details.getUrgency());
          pstmt.setInt(17, details.getUserId());
          pstmt.setBoolean(18, details.getIsFollowLeadSchedules());
          pstmt.setString(19, pin.generate15DigitNumber()); // generates random security number
          pstmt.setInt(20, 0);
          pstmt.executeUpdate();
          rs= pstmt.getGeneratedKeys();
          if(rs.next())
          {
           
           jobId =rs.getInt(1); // gets inserted new userID
           
           // Creates year ending folder
           file = new File(DIR+"\\"+this.getSecureCodeForClient(details.getClientId() )+"\\"+this.getSecureCodeForPartner(details.getPartnerId())+"\\"+details.getYearEnding()+"_"+this.getSecureCodeForJobs(jobId));
           // Creates year job folder inside Year Ending
           file2 = new File(DIR+"\\"+this.getSecureCodeForClient(details.getClientId() )+"\\"+this.getSecureCodeForPartner(details.getPartnerId())+"\\"+details.getYearEnding()+"_"+this.getSecureCodeForJobs(jobId)+"\\"+details.getFileName()+"_"+this.getSecureCodeForJobs(jobId));
           //+"\\"+details.getFileName()+"_"+this.getSecureCodeForJobs(jobId))   	
           if (!file.exists()) {
          	if (file.mkdir()) {
          		file2.mkdir();	
          	System.out.println("Directory is created!");
          	} else {
          	System.out.println("Failed to create directory!");
          	}
           SQL = "update jobs  set Dir=? where id=?";
           pstmt = con.prepareStatement(SQL);
           pstmt.setString(1,details.getFileName()+"_"+this.getSecureCodeForJobs(jobId)); //  Client folder name
           pstmt.setInt(2,jobId); //  table id
           pstmt.executeUpdate();    
           pstmt.close(); 
       
           SQL = "update jobs  set YearEndingFolder=? where id=?";
           pstmt = con.prepareStatement(SQL);
           pstmt.setString(1,details.getYearEnding()+"_"+this.getSecureCodeForJobs(jobId)); //  Year Ending folder name
           pstmt.setInt(2,jobId); //  table id
           pstmt.executeUpdate();    
           pstmt.close(); 
          	}  
           
      //  Inserts new Cover Sheet
           SQL = "insert into jobs_coversheet(ClientId,JobId)values (?,?)";
           pstmt = con.prepareStatement(SQL);
           pstmt.setInt(1,details.getClientId() );           
           pstmt.setInt(2, jobId);
           pstmt.executeUpdate();
           pstmt.close();
           
           SQL = "insert into jobs_coversheet_checklist(ClientId,JobId)values (?,?)";
           pstmt = con.prepareStatement(SQL);
           pstmt.setInt(1,details.getClientId() );           
           pstmt.setInt(2, jobId);
           pstmt.executeUpdate();
           pstmt.close();
           
       
          }
          rs.close();
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred in insertNewJob() " + ex.getMessage());
          throw new DAOException(ex);
      }catch(Exception e){
          e.printStackTrace();
      }finally{
     
          try{
              if(pstmt!=null){   pstmt.close();}
          }catch(Exception e){
              pstmt = null;
            
          }
          
          try{
              if(con!=null){   con.close();}
          }catch(Exception e){
              con = null;
          }
      }

 return jobId;
}


public int updateJob(JobBeans details)  throws DAOException {
	 
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    ResultSet rs=null;  
   int jobId=0;
      try{
          con = getConnection();
          
          //inserts new user  SQL = "update jobs  set Dir=? where id=?";
          SQL = "update jobs set ClientId=?,SupplierId=?,PartnerId=?,accountantId=?,ReviewerId=?,FileName=?,"
          		+ "FileReference=?,DateRecieved=?,DateExptected=?,DateReturned=?,YearEnding=?,BudgetedTime=?,FileStorage=?,PrincipalActivity=?,"
          		+ "Comments=?,Urgency=?,addedBy=?,isFollowLeadSchedule=? where id=?";
          pstmt = con.prepareStatement(SQL);
          pstmt.setInt(1,details.getClientId() );           
          pstmt.setInt(2, details.getSupplierId());
          pstmt.setInt(3, details.getPartnerId());
          pstmt.setInt(4, details.getAccountantId());
          pstmt.setInt(5, details.getReviewerId());
          pstmt.setString(6, details.getFileName());
          pstmt.setString(7, details.getFileReference());
          pstmt.setString(8, details.getDateReceived());
          pstmt.setString(9, details.getExpectedDate());
          pstmt.setString(10, details.getDateReturned());
          pstmt.setString(11, details.getYearEnding());
          pstmt.setString(12, details.getBudgetedTime());
          pstmt.setString(13, details.getFileStorage());
          pstmt.setInt(14, details.getPrincipalActivityId());
          pstmt.setString(15, details.getComments());
          pstmt.setString(16, details.getUrgency());
          pstmt.setInt(17, details.getUserId());
          pstmt.setBoolean(18, details.getIsFollowLeadSchedules());
          pstmt.setInt(19, details.getId());
          pstmt.executeUpdate();
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred in updateJob() " + ex.getMessage());
          throw new DAOException(ex);
      }catch(Exception e){
          e.printStackTrace();
      }finally{
     
          try{
              if(pstmt!=null){   pstmt.close();}
          }catch(Exception e){
              pstmt = null;
            
          }
          
          try{
              if(con!=null){   con.close();}
          }catch(Exception e){
              con = null;
          }
      }

 return jobId;
}

public ArrayList<JobBeans> getJobDetails(int id, String type) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<JobBeans> jobDetails =null;
	JobBeans details = null; 
	try{
		jobDetails = new ArrayList<JobBeans>();
	    con = getConnection();
	    
	  SQL = "select id,clientId,SupplierId,accountantId,ReviewerId,FileName,FileReference,DateRecieved,jobStatus,PrincipalActivity from jobs where "+type+"=?  ORDER BY id DESC";
	   
	     
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, id);   
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	details = new JobBeans(); 
	        details.setClientId(rs.getInt("clientId")); // Client Id
	        details.setSupplierId(rs.getInt("SupplierId"));
	        details.setFileName(rs.getString("FileName"));
	        details.setFileReference(rs.getString("FileReference"));
	        details.setClientName(this.getCompanyName(rs.getInt("clientId")));
	        details.setSupplierName(this.getSupplierName(rs.getInt("SupplierId")));
	        details.setDateReceived(rs.getString("DateRecieved"));
	        details.setJobstatusList(this.getJobStatusForJob(rs.getInt("jobStatus"))); //get jobStatus list with selected status
	        details.setId(rs.getInt("id"));
	        details.setPrincipalActivityId(rs.getInt("PrincipalActivity")); 
	        details.setPrincipalActivity(this.getPrincipalActivity(rs.getInt("PrincipalActivity")));
	      
	        jobDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getJobDetails" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return jobDetails;
}



public ArrayList<JobBeans> getJobStatusForJob(int jobId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<JobBeans> jobStatusDetails =null;
	JobBeans details = null; 
	try{
		 
		jobStatusDetails = new ArrayList<JobBeans>();
	    con = getConnection();
	    SQL = "select id,Status  from job_status where   active=?   ";
        pstmt = con.prepareStatement(SQL);
        
        pstmt.setBoolean(1, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new JobBeans(); 
	    	details.setJobStatus(rs.getString("Status"));
	    	details.setJobStatusId(rs.getInt("id"));
	         
	        if( rs.getInt("id")==jobId)
		       {
		        details.setStatus("selected");  //jobstatus connected
		         
		       }

	        details.setId(rs.getInt("id"));
	        jobStatusDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getJobStatusForJob" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return jobStatusDetails;
}


public  JobBeans  getClientPartnerDetailsForJob(int jobId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	 
	JobBeans details = null; 
	try{
		 
		 
	    con = getConnection();
	    SQL = "select id,ClientId,PartnerId,DIR,YearEndingFolder  from jobs where   id=?   ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, jobId); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new JobBeans(); 
	    	details.setClientId(rs.getInt("ClientId"));
	    	details.setPartnerId(rs.getInt("PartnerId"));
	    	details.setPartnerFolder(this.getSecureCodeForPartner(rs.getInt("PartnerId"))); 
	    	details.setClientFolder(this.getSecureCodeForClient(rs.getInt("ClientId")));
	    	details.setJobFolder(rs.getString("DIR"));
	    	details.setYearEnding(rs.getString("YearEndingFolder")); 
	        details.setId(rs.getInt("id"));
	      
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getClientPartnerDetailsForJob" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return details;
}

public  JobBeans  getJobDetails(int jobId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	 
	JobBeans details = null; 
	try{
		 
		System.out.println("jobId>>> "+jobId);
	    con = getConnection();
	    SQL = "select ClientId,SupplierId,PartnerId,accountantId,ReviewerId,FileName,"
          		+ "FileReference,DateRecieved,DateExptected,DateReturned,YearEnding,BudgetedTime,FileStorage,PrincipalActivity,"
          		+ "Comments,Urgency,addedBy,isFollowLeadSchedule,SecureCode,jobStatus  from jobs where   id=?   ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, jobId); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	//getCompanyName
	    	details = new JobBeans(); 
	    	details.setClientId(rs.getInt("ClientId"));
	    	details.setClientName(this.getCompanyName(rs.getInt("ClientId")));
	    	details.setPartnerId(rs.getInt("PartnerId"));
	    	details.setPartnerName(this.getUserName(rs.getInt("PartnerId")));
	    	details.setSupplierId(rs.getInt("SupplierId"));
	    	details.setSupplierName(this.getSupplierName(rs.getInt("SupplierId")));
	    	details.setAccountantId(rs.getInt("accountantId"));
	    	details.setAccountantName(this.getUserName(rs.getInt("accountantId")));
	    	details.setReviewerId(rs.getInt("ReviewerId"));
	    	details.setReviewerName(this.getUserName(rs.getInt("ReviewerId")));
	    	details.setFileName(rs.getString("FileName"));
	    	details.setFileReference(rs.getString("FileReference"));
	    	details.setDateReceived(rs.getString("DateRecieved"));
	    	details.setDateReturned(rs.getString("DateReturned"));
	    	details.setExpectedDate(rs.getString("DateExptected"));
	    	details.setYearEnding(rs.getString("YearEnding"));
	    	details.setBudgetedTime(rs.getString("BudgetedTime"));
	    	details.setFileStorage(rs.getString("FileStorage"));
	        details.setPrincipalActivityId(rs.getInt("PrincipalActivity"));
	    	details.setComments(rs.getString("Comments"));
	    	details.setUrgency(rs.getString("Urgency"));
	    	details.setIsFollowLeadSchedules(rs.getBoolean("isFollowLeadSchedule"));
	    	details.setId(jobId);
	    	  
	      
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getJobDetails" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return details;
}




public boolean updateJobStatus(int jobStatus, int id) throws DAOException {
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 String SQL = "";
	  
	 boolean result=false;
	   try{
	       con = getConnection();
	       // update Job Status in jobs table
	       SQL = "update jobs set jobStatus=? where  Id=?";
	       pstmt = con.prepareStatement(SQL);
	       pstmt.setInt(1, jobStatus);  
	       pstmt.setInt(2,id);
	       pstmt.executeUpdate(); 
	       result=true;
	       pstmt.close();
	       
	       
	       
	   }catch (SQLException ex) {
	       log.error("SQLException Occurred  in  - updateJobStatuses()" + ex.getMessage());
	       throw new DAOException(ex);
	   }catch(Exception e){
	       e.printStackTrace();
	   }finally{
	   
	       
	       try{
	           if(pstmt!=null){   pstmt.close();}
	       }catch(Exception e){
	           pstmt = null;
	         
	       }
	       
	       try{
	           if(con!=null){   con.close();}
	       }catch(Exception e){
	           con = null;
	       }
	   }

	  return result;
	}


public int insertFileDetailsForJob( String jobId,String fileName) throws DAOException {
	 Connection con = null;
	 PreparedStatement pstmt = null,pstmt1 = null;
	 String SQL = "";
	 ResultSet rs=null; 
	 int result=0;
	   try{
	       con = getConnection();
	       
	       
	           SQL = "insert into job_files(JobId,FileName) values (?,?)";
	          pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	          pstmt.setInt(1, Integer.parseInt(jobId.trim()));           
	          pstmt.setString(2, fileName);
	          pstmt.executeUpdate();
	          rs= pstmt.getGeneratedKeys();
	          if(rs.next())
	          {
	           
	        	  result =rs.getInt(1); // 
	       
	      /* SQL = "update jobs set jobStatus=? where  Id=?";
	       pstmt1 = con.prepareStatement(SQL);
	       pstmt1.setInt(1,  Integer.parseInt(jobStatusId.trim()));  
	       pstmt1.setInt(2, Integer.parseInt(jobId.trim()));
	       pstmt1.executeUpdate(); 
	       pstmt1.close();*/
	       
	          } 
	       
	   }catch (SQLException ex) {
	       log.error("SQLException Occurred  in  - insertFileDetailsForJob()" + ex.getMessage());
	       throw new DAOException(ex);
	   }catch(Exception e){
	       e.printStackTrace();
	   }finally{
	   
	       
	       try{
	           if(pstmt!=null){   pstmt.close();}
	       }catch(Exception e){
	           pstmt = null;
	         
	       }
	       
	       try{
	           if(con!=null){   con.close();}
	       }catch(Exception e){
	           con = null;
	       }
	   }

	  return result;
	}
	

public int getClientIDForJob(int jobId) throws DAOException{
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL="";
	int clientId=0;
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select clientId from jobs where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, jobId);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	clientId = rs.getInt("clientId");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getClientIDForJob" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return clientId;
}




public int getPartnerIDForJob(int jobId) throws DAOException{
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL="";
	int partnerId=0;
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select partnerId from jobs where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, jobId);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	partnerId = rs.getInt("partnerId");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getPartnerIDForJob" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return partnerId;
}

public int getSupplierIDForJob(int jobId) throws DAOException{
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL="";
	int supplierId=0;
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select supplierId from jobs where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, jobId);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	supplierId = rs.getInt("supplierId");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSupplierIDForJob" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return supplierId;
}

public int getAccoutantIDForJob(int jobId) throws DAOException{
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL="";
	int accountantId=0;
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select accountantId from jobs where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, jobId);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	accountantId = rs.getInt("accountantId");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getAccountantIDForJob" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return accountantId;
}


public int getReviewerIDForJob(int jobId) throws DAOException{
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL="";
	int reviewerId=0;
	ResultSet rs=null;
	try{
	    con = getConnection();
	    SQL = "select reviewerId from jobs where Id=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, jobId);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	reviewerId = rs.getInt("reviewerId");
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getReviewerIDForJob" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return reviewerId;
}

 


public int updateCoverSheetJob(CoverSheetBeans details)  throws DAOException {
	 
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
      
   int jobId=0;
      try{
          con = getConnection();
          // SQL = "update jobs set jobStatus=? where  Id=?";
          //inserts new user 
         // System.out.println("details.getBankStatements() "+details.getBankStatements());
          //System.out.println("details.getJobId() "+details.getJobId());
          SQL = "update jobs_coversheet set BankStatements=?,BankStatementsOther=?,BankReconciliation=?,BankPayments=?,ChequeBook=?,Other=?,PurchaseDayEmail=?,"
          		+ "PurchaseDaySummary=?,PurchaseInvoices=?,ExpenseInvoices=?,SalesDay=?,SalesDaySummary=?,SalesInvoices=?,salesInvoiceSummary=?,RentalIncomeSummary=?,"
          		+ "Salaries=?,P35=?,Wages=?,Stocks=?,PettyCashControl=?,PettyCashBook=?,PettyCashReceipt=?,CreditCardAnalysis=?,CreditCardStatements=?,"
          		+ "VATReturns=?,VatCalculations=?,FaltRate=?,StandardRate=?,CompanyProfile=?,Leases=?,ClientLetter=?,CyLeadSchedules=?,AnnualAccounts=?,finalTrialBalance=?,"
          		+ "LeadSchedules=?,accountancyFee=?,Disbursements=?,TimeBudget=?,JobType=?,jobDifficulty=? where JobId=?";
          pstmt = con.prepareStatement(SQL);
          pstmt.setString(1, details.getBankStatements() );           
          pstmt.setString(2,  details.getBankStatementsOther());
          pstmt.setString(3,  details.getBankReconciliation());
          pstmt.setString(4,  details.getBankPayments());
          pstmt.setString(5,  details.getChequeBook());
          pstmt.setString(6, details.getOther());
          pstmt.setString(7,  details.getPurchaseDayEmail());
          
          pstmt.setString(8, details.getPurchaseDaySummary());
          pstmt.setString(9,  details.getPurchaseInvoices());
          pstmt.setString(10, details.getExpenseInvoices());
          pstmt.setString(11, details.getSalesDay());
          pstmt.setString(12,  details.getSalesDaySummary());
          pstmt.setString(13, details.getSalesInvoices());
          pstmt.setString(14,  details.getSalesInvoiceSummary());
          pstmt.setString(15, details.getRentalIncomeSummary());
          
          pstmt.setString(16,  details.getSalaries());
          pstmt.setString(17, details.getP35());
          pstmt.setString(18, details.getWages());
          pstmt.setString(19, details.getStocks());
          pstmt.setString(20,  details.getPettyCashControl());
          pstmt.setString(21, details.getPettyCashBook());
          pstmt.setString(22, details.getPettyCashReceipt());
          pstmt.setString(23,details.getCreditCardAnalysis());
          pstmt.setString(24, details.getCreditCardStatements());
          
          pstmt.setString(25, details.getVatReturns());
          pstmt.setString(26,  details.getVatCalculations());
          pstmt.setString(27, details.getFlatRate());
          pstmt.setString(28, details.getStandardRate());
          pstmt.setString(29, details.getCompanyProfile());
          pstmt.setString(30, details.getLeases());
          pstmt.setString(31, details.getClientLetter());
          pstmt.setString(32, details.getCyLeadSchedules());
          pstmt.setString(33, details.getAnnualAccounts());
          pstmt.setString(34, details.getFinalTrialBalance());     
          
          pstmt.setString(35,  details.getLeadSchedules());
          pstmt.setString(36, details.getAccountancyFee());
          pstmt.setString(37,  details.getDisbursements());
          pstmt.setString(38, details.getTimeBudget());
          pstmt.setInt(39, details.getJobTypeId()); 
          pstmt.setInt(40, details.getJobDifficultyId()); 
          pstmt.setInt(41, details.getJobId()); 
        
          pstmt.executeUpdate();
          
          
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred in updateCoverSheetJob() " + ex.getMessage());
          throw new DAOException(ex);
      }catch(Exception e){
          e.printStackTrace();
      }finally{
     
          try{
              if(pstmt!=null){   pstmt.close();}
          }catch(Exception e){
              pstmt = null;
            
          }
          
          try{
              if(con!=null){   con.close();}
          }catch(Exception e){
              con = null;
          }
      }

 return jobId;
}

@Override
public CoverSheetBeans getCoverSheetJob(int jobId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL="";
	 
	ResultSet rs=null;
	CoverSheetBeans details=null;
	try{
	    con = getConnection();
	    SQL = "select * from jobs_coversheet where jobId=?";
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, jobId);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
 
	             
	    	details =new CoverSheetBeans();
	    	details.setBankStatements(rs.getString("BankStatements"));
	    	  details.setBankStatementsOther(rs.getString("BankStatementsOther")) ;
	    	  details.setBankReconciliation(rs.getString("BankReconciliation"));
	    	  details.setBankPayments(rs.getString("BankPayments"));
	    	   details.setChequeBook(rs.getString("ChequeBook"));
	    	 details.setOther(rs.getString("Other"));
	    	  details.setPurchaseDayEmail(rs.getString("PurchaseDayEmail"));
	    	  details.setPurchaseDaySummary(rs.getString("PurchaseDaySummary"));
	    	  details.setPurchaseInvoices(rs.getString("PurchaseInvoices"));
	    	  details.setExpenseInvoices(rs.getString("ExpenseInvoices"));
	    	 details.setSalesDay(rs.getString("SalesDay"));
	    	   details.setSalesDaySummary(rs.getString("SalesDaySummary"));
	    	  details.setSalesInvoices(rs.getString("SalesInvoices"));
	    	  details.setSalesInvoiceSummary(rs.getString("salesInvoiceSummary"));
	    	  details.setRentalIncomeSummary(rs.getString("RentalIncomeSummary"));
	    	   details.setSalaries(rs.getString("Salaries"));
	    	 details.setP35(rs.getString("P35"));
	    	  details.setWages(rs.getString("Wages"));
	    	  details.setStocks(rs.getString("Stocks"));
	    	  details.setPettyCashControl(rs.getString("PettyCashControl"));
	    	  details.setPettyCashBook(rs.getString("PettyCashBook"));
	    	  details.setPettyCashReceipt(rs.getString("PettyCashReceipt"));
	    	 details.setCreditCardAnalysis(rs.getString("CreditCardAnalysis"));
	    	 details.setCreditCardStatements(rs.getString("CreditCardStatements"));
	    	  details.setVatReturns(rs.getString("VATReturns"));
	    	  details.setVatCalculations(rs.getString("VatCalculations"));
	    	  details.setFlatRate(rs.getString("FaltRate"));
	    	 details.setStandardRate(rs.getString("StandardRate"));
	    	  details.setCompanyProfile(rs.getString("CompanyProfile"));
	    	  details.setLeases(rs.getString("Leases"));
	    	 details.setClientLetter(rs.getString("ClientLetter"));
	    	 details.setCyLeadSchedules(rs.getString("CyLeadSchedules"));
	    	  details.setAnnualAccounts(rs.getString("AnnualAccounts"));
	    	  details.setFinalTrialBalance(rs.getString("finalTrialBalance"))  ; 
	    	  details.setLeadSchedules(rs.getString("LeadSchedules"));
	    	  details.setAccountancyFee(rs.getString("accountancyFee"));
	    	   details.setDisbursements(rs.getString("Disbursements"));
	    	 details.setTimeBudget(rs.getString("TimeBudget"));
	    	  details.setJobTypeId(rs.getInt("JobType"));
	    	  details.setJobDifficultyId(rs.getInt("jobDifficulty"));
	    	  details.setJobType(this.getJobType(rs.getInt("JobType")));
	    	  details.setJobDifficulty(this.getJobDifficulty(rs.getInt("jobDifficulty")));
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getAccountantIDForJob" + ex.getMessage());
	    throw new DAOException(ex);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    try{
	        if(rs!=null){   rs.close();}
	    }catch(Exception e){
	        rs = null;
	    }
	    try{
	        if(pstmt!=null){   pstmt.close();}
	    }catch(Exception e){
	        pstmt = null;
	    }
	    try{
	        if(con!=null){   con.close();}
	    }catch(Exception e){
	        con = null;
	    }
	}
	return details;
}

@Override
public boolean updateCoverSheetCheckList(int jobId, String fieldName,boolean fieldValue) throws DAOException {
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 String SQL = "";
	  
	 boolean result=false;
	   try{
	       con = getConnection();
	       
           //System.out.println("jobId7>>> "+jobId);  System.out.println("fieldName7>>> "+fieldName); System.out.println("fieldValue7>>> "+fieldValue);

	       // update   in jobs_coversheet_checklist table
	       SQL = "update jobs_coversheet_checklist set "+fieldName+"=? where  jobId=?";
	       pstmt = con.prepareStatement(SQL);
	       pstmt.setBoolean(1,fieldValue);
	       pstmt.setInt(2, jobId);  
	       pstmt.executeUpdate(); 
	       result=true;
	       pstmt.close();
	       
	       
	       
	   }catch (SQLException ex) {
	       log.error("SQLException Occurred  in  - updateCoverSheetCheckList()" + ex.getMessage());
	       throw new DAOException(ex);
	   }catch(Exception e){
	       e.printStackTrace();
	   }finally{
	   
	       
	       try{
	           if(pstmt!=null){   pstmt.close();}
	       }catch(Exception e){
	           pstmt = null;
	         
	       }
	       
	       try{
	           if(con!=null){   con.close();}
	       }catch(Exception e){
	           con = null;
	       }
	   }

	  return result;
	}
 

 public CoverSheetBeans getCoverSheetCheckListJob(int jobId) throws DAOException {
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 String SQL="";
	  
	 ResultSet rs=null;
	 CoverSheetBeans details=null;
	 try{
	     con = getConnection();
	     SQL = "select * from jobs_coversheet_checklist where jobId=?";
	     pstmt = con.prepareStatement(SQL);
	     pstmt.setInt(1, jobId);           
	     rs= pstmt.executeQuery();
	     if(rs.next()) {
	 
	              
	      details =new CoverSheetBeans();
	      
	     if(rs.getBoolean("isBankStatements")==true){details.setBankStatements("CHECKED");}
	     if(rs.getBoolean("IsBankStatementOther")==true){details.setBankStatementsOther("CHECKED");}
	     if(rs.getBoolean("IsBankReconciliation")==true){details.setBankReconciliation("CHECKED");}
	     if(rs.getBoolean("IsBankPayments")==true){details.setBankPayments("CHECKED");}
	     if(rs.getBoolean("IsChequeBook")==true){details.setChequeBook("CHECKED");}
	     if(rs.getBoolean("IsOther")==true){details.setOther("CHECKED");}
	     if(rs.getBoolean("IsPurchaseDayEmail")==true){details.setPurchaseDayEmail("CHECKED");}
	     if(rs.getBoolean("IsPurchaseDaySummary")==true){details.setPurchaseDaySummary("CHECKED");}
	     if(rs.getBoolean("IsPurchaseInvoices")==true){details.setPurchaseInvoices("CHECKED");}
	     if(rs.getBoolean("IsExpenseInvoices")==true){details.setExpenseInvoices("CHECKED");}
	     if(rs.getBoolean("IsSalesDay")==true){details.setSalesDay("CHECKED");}
	     if(rs.getBoolean("IsSalesDaySummary")==true){details.setSalesDaySummary("CHECKED");}
	     if(rs.getBoolean("IsSalesInvoices")==true){details.setSalesInvoices("CHECKED");}
	     if(rs.getBoolean("IsSalesInvoiceSummary")==true){details.setSalesInvoiceSummary("CHECKED");}
	     if(rs.getBoolean("IsRentalIncomeSummary")==true){details.setRentalIncomeSummary("CHECKED");}
	     if(rs.getBoolean("IsSalaries")==true){details.setSalaries("CHECKED");}
	     if(rs.getBoolean("IsP35")==true){details.setP35("CHECKED");}
	     if(rs.getBoolean("IsWages")==true){details.setWages("CHECKED");}
	     if(rs.getBoolean("IsStocks")==true){details.setStocks("CHECKED");}
	     if(rs.getBoolean("IsPettyCashControl")==true){details.setPettyCashControl("CHECKED");}
	     if(rs.getBoolean("IsPettyCashBook")==true){details.setPettyCashBook("CHECKED");}
	     if(rs.getBoolean("IsPettyCashReceipt")==true){details.setPettyCashReceipt("CHECKED");}
	     if(rs.getBoolean("IsCreditCardAnalysis")==true){details.setCreditCardAnalysis("CHECKED");}
	     if(rs.getBoolean("IsCreditCardStatements")==true){details.setCreditCardStatements("CHECKED");}
	     if(rs.getBoolean("IsVATReturns")==true){details.setVatReturns("CHECKED");}
	     if(rs.getBoolean("IsVatCalculations")==true){details.setVatCalculations("CHECKED");}
	     if(rs.getBoolean("IsFlatRate")==true){details.setFlatRate("CHECKED");}
	     if(rs.getBoolean("IsStandardRate")==true){details.setStandardRate("CHECKED");}
	     if(rs.getBoolean("IsCompanyProfile")==true){details.setCompanyProfile("CHECKED");}
	     if(rs.getBoolean("IsLeases")==true){details.setLeases("CHECKED");}
	     if(rs.getBoolean("IsClientLetter")==true){details.setClientLetter("CHECKED");}
	     if(rs.getBoolean("IsCyLeadSchedules")==true){details.setCyLeadSchedules("CHECKED");}
	     if(rs.getBoolean("IsannualAccounts")==true){details.setAnnualAccounts("CHECKED");}
	     if(rs.getBoolean("IsFinalTrialBalance")==true){details.setFinalTrialBalance("CHECKED");}
	     if(rs.getBoolean("IsLeadSchedules")==true){details.setLeadSchedules("CHECKED");}
	    // if(rs.getBoolean("IsAccountancyFee")==true){details.setAccountancyFee("CHECKED");}
	    // if(rs.getBoolean("IsDisbursements")==true){details.setDisbursements("CHECKED");}
	    // if(rs.getBoolean("IsTimeBudget")==true){details.setTimeBudget("CHECKED");}
	 
	     
	     
	        
	     
	     }
	     rs.close();
	     pstmt.close();
	 }catch (SQLException ex) {
	     log.error("SQLException Occurred  getAccountantIDForJob" + ex.getMessage());
	     throw new DAOException(ex);
	 }catch(Exception e){
	     e.printStackTrace();
	 }finally{
	     try{
	         if(rs!=null){   rs.close();}
	     }catch(Exception e){
	         rs = null;
	     }
	     try{
	         if(pstmt!=null){   pstmt.close();}
	     }catch(Exception e){
	         pstmt = null;
	     }
	     try{
	         if(con!=null){   con.close();}
	     }catch(Exception e){
	         con = null;
	     }
	 }
	 return details;
	}
/* 
  details.setBankStatements()          
   details.setBankStatementsOther() 
  details.setBankReconciliation(
  details.setBankPayments()
   details.setChequeBook()
 details.setOther()
  details.setPurchaseDayEmail()
  details.setPurchaseDaySummary()
  details.setPurchaseInvoices()
  details.setExpenseInvoices()
 details.setSalesDay()
   details.setSalesDaySummary()
  details.setSalesInvoices()
  details.setSalesInvoiceSummary()
  details.setRentalIncomeSummary()
   details.setSalaries()
 details.setP35()
  details.setWages()
  details.setStocks()
  details.setPettyCashControl()
  details.setPettyCashBook()
  details.setPettyCashReceipt()
 details.setCreditCardAnalysis()
 details.setCreditCardStatements()
  details.setVatReturns()
  details.setVatCalculations()
  details.setFlatRate()
 details.setStandardRate()
  details.setCompanyProfile()
  details.setLeases()
 details.setClientLetter()
 details.setCyLeadSchedules()
  details.setAnnualAccounts()
  details.setFinalTrialBalance()   
  details.setLeadSchedules()
  details.setAccountancyFee()
   details.setDisbursements()
 details.setTimeBudget()
  details.setJobType()
  details.setJobDifficulty()
  details.setJobDifficulty()
*/
}
