package com.persistence.mysql;
import com.persistence.DAOException;
import com.persistence.PaddleDAO;
import com.persistence.SupplierDAO;
 
import com.webapp.beans.JobBeans;
 
//import com.webapp.beans.Jdemo;
import com.webapp.beans.SupplierBeans;
import com.webapp.beans.TimeSheetBeans;
import com.webapp.util.PINGenerator;

import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MYSQLSupplierDAO extends MYSQLConnectionDAO implements SupplierDAO{
private Log log = LogFactory.getLog(MYSQLPaddleDAO.class);
PINGenerator pin = new PINGenerator();
PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object





@Override
public int clientSupplierConnect(int supplierId, int clientId,int addedBy) throws DAOException {
	int isClientExists=0;
    Connection con = null;
    PreparedStatement pstmt = null, pstmt1 = null;
    String SQL = "";
    ResultSet rs=null;  
    
      try{
          con = getConnection();
          SQL = "select id from client_supplier_connect where clientId=? and supplierId=? and addedBy=?";
          pstmt = con.prepareStatement(SQL);
          pstmt.setInt(1, clientId);   
          pstmt.setInt(2, supplierId);   
          pstmt.setInt(3, addedBy);    
          rs= pstmt.executeQuery();
          if(rs.next()) {
         
        	  isClientExists = rs.getInt("id"); // client Already Exists
          }
          else
          {
        	  
        	  // Connects between supplier and Client
              SQL = "insert into client_supplier_connect(clientId,supplierId,addedBy) values (?,?,?)";
              pstmt1 = con.prepareStatement(SQL);
              pstmt1.setInt(1, clientId);   
              pstmt1.setInt(2, supplierId);   
              pstmt1.setInt(3, addedBy);    
              pstmt1.executeUpdate(); 
              pstmt1.close();
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
 public ArrayList<SupplierBeans> getClientConnections(int supplierId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> clientDetails =null;
	SupplierBeans details = null; 
	try{
		clientDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
        SQL = "select id,clientId,supplierId,active from client_supplier_connect where  supplierId=? ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId);   
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	details = new SupplierBeans(); 
	        details.setClientId(rs.getInt("clientId")); // Client Id
	        details.setClientName(this.getClientName(rs.getInt("clientId"))); // Client Name - gets from internal method getClientName()
	        details.setId(rs.getInt("id"));
	        if(rs.getBoolean("active")==true) 
	        {
	        details.setStatus("Active"); // client Connected
	        }
	        else
	        {
	        details.setStatus("Pending"); // client Not Connected
	        }
	        
	        
	        
	        
	        clientDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getClientConnections" + ex.getMessage());
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
	return clientDetails;
}


//


public String getClientName(int id) throws DAOException {
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

 



@Override // insert new Account / Reviewer /Admin
public int insertNewUser(SupplierBeans details) throws DAOException {
	 
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    int result=0,userId=0;
    ResultSet rs=null;
      try{
          con = getConnection();
          
        //checks users - if not registered - add and usertype = Supplier
          userId= this.isUserRegisterd(details);

         if(userId!=0)
         {
   
        	    SQL = "select id from supplierconnect where UserId=? and SupplierId=?";
        	    pstmt = con.prepareStatement(SQL);
        	    pstmt.setInt(1, userId);
                pstmt.setInt(2,details.getSupplierId() );                 
        	    rs= pstmt.executeQuery();
        	    if(rs.next()) {  
        	    	 result=0; // already  exists
        	    }
        	    else
        	    {
        	        // Connects between user and company
                    SQL = "insert into supplierconnect(UserId,SupplierId,isAccountant,isReviewer,isAdmin) values (?,?,?,?,?)";
                    pstmt = con.prepareStatement(SQL);
                    pstmt.setInt(1, userId);
                    pstmt.setInt(2,details.getSupplierId() );           
                    pstmt.setBoolean(3, details.getIsAccountant()); //  
                    pstmt.setBoolean(4, details.getIsReviewer()); // 
                    System.out.println("details.getIsAdmin()>>>> "+ details.getIsAdmin());
                    pstmt.setBoolean(5, details.getIsAdmin()); //  
                    pstmt.executeUpdate(); 
                    result=1;
        	    	
        	    }
        	    rs.close(); 
        	    pstmt.close();
         
         
         }
           
           
           
          
          
    
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred in insertNewUser() " + ex.getMessage());
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

//checks users - if not registered - add and usertype = Supplier
public int isUserRegisterd(SupplierBeans details) throws DAOException {
	 
	 
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    ResultSet rs=null,rs1=null;  
    int result = 0;  
      try{
          con = getConnection();
         
          
          SQL = "select id from users where email=?";
          pstmt = con.prepareStatement(SQL);
          pstmt.setString(1, details.getEmail());   // email ID         
          rs= pstmt.executeQuery();
          if(rs.next()) {
         
        	  result = rs.getInt("id");	  
          }
          else
          {
        	   SQL = "insert into users(name,email,password,isClient,uniqueCode) values (?,?,?,?,?)";
               pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
               pstmt.setString(1, details.getName());           
               pstmt.setString(2, details.getEmail());
               pstmt.setString(3, details.getPassword());
               pstmt.setBoolean(4, false);
               pstmt.setString(5, pin.generate15DigitNumber()); // generates random security number
               pstmt.executeUpdate();
               rs1= pstmt.getGeneratedKeys();
               if(rs1.next())
               {
             	  
            	   result =rs1.getInt(1); // gets inserted new userID
               }
               rs1.close();
               pstmt.close();
          }
          rs.close();
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred in -  isUserRegisterd()" + ex.getMessage());
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
public ArrayList<SupplierBeans> getSupplierUsers(int supplierId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> userDetails =null;
	SupplierBeans details = null; 
	try{
		 
		 userDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
        SQL = "select id,UserId,isAdmin,isReviewer,isAccountant  from supplierconnect where  supplierId=? and active=? ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setBoolean(2, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	 
	    	details = new SupplierBeans(); 
	        details.setUserId(rs.getInt("UserId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("UserId"))); // User Name - gets from internal method getUserName()
	        details.setId(rs.getInt("id"));
	        details.setIsAccountant(rs.getBoolean("isAccountant"));
	    	details.setIsReviewer(rs.getBoolean("isReviewer"));
	    	details.setIsAdmin(rs.getBoolean("isAdmin"));
	        if(rs.getBoolean("isAccountant")==true)
	        {
	        details.setReviewerList(this.getReviewerListForAccountant(supplierId,rs.getInt("UserId"))); //
	       // System.out.println(" details.setPartnerList()>>>> "+ details.getReviewerList().size());
	        }
        
	    	userDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSupplierUsers" + ex.getMessage());
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
	return userDetails;
}

public ArrayList<SupplierBeans> getSupplierAccountantList(int supplierId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> userDetails =null;
	SupplierBeans details = null; 
	try{
		 
		 userDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
        SQL = "select id,UserId,isAdmin,isReviewer,isAccountant  from supplierconnect where  supplierId=? and active=? and isAccountant=? ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setBoolean(2, true); 
        pstmt.setBoolean(3, true);
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	 
	    	details = new SupplierBeans(); 
	        details.setUserId(rs.getInt("UserId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("UserId"))); // User Name - gets from internal method getUserName()
	    	userDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSupplierAccountant" + ex.getMessage());
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
	return userDetails;
}


public ArrayList<SupplierBeans> getSupplierReviewerList(int supplierId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> userDetails =null;
	SupplierBeans details = null; 
	try{
		 
		 userDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
        SQL = "select id,UserId,isAdmin,isReviewer,isAccountant  from supplierconnect where  supplierId=? and active=? and isReviewer=? ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setBoolean(2, true); 
        pstmt.setBoolean(3, true);
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	 
	    	details = new SupplierBeans(); 
	        details.setUserId(rs.getInt("UserId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("UserId"))); // User Name - gets from internal method getUserName()
	    	userDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSupplierReviewerList" + ex.getMessage());
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
	return userDetails;
}


public ArrayList<SupplierBeans> getReviewerListForAccountant(int supplierId,int accountantId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> reviewerDetails =null;
	SupplierBeans details = null; 
	try{
		 
		reviewerDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
	    SQL = "select id,UserId  from supplierconnect where  SupplierId=? and active=?  and isReviewer=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setBoolean(2, true); 
        pstmt.setBoolean(3, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new SupplierBeans(); 
	        details.setUserId(rs.getInt("UserId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("UserId"))); // User Name - gets from   method getUserName()
	        details.setIsConnected(this.isAccoutantReviewerConnected( rs.getInt("UserId"), accountantId,supplierId)); // checks whether the reviewer is connected to acc
	       if( details.getIsConnected()==true)
	       {
	        details.setStatus("selected");
	       }
	        
	        details.setId(rs.getInt("id"));
	         
	       
	        reviewerDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getReviewerListForAccountant" + ex.getMessage());
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
	return reviewerDetails;
}


public ArrayList<SupplierBeans> getReviewerListForAccountantPartner(int supplierId,int accountantId,int clientId,int partnerId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> reviewerDetails =null;
	SupplierBeans details = null; 
	try{
		 
		reviewerDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
	    SQL = "select id,UserId  from supplierconnect where  SupplierId=? and active=?  and isReviewer=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setBoolean(2, true); 
        pstmt.setBoolean(3, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new SupplierBeans(); 
	        details.setUserId(rs.getInt("UserId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("UserId"))); // User Name - gets from   method getUserName()
	        details.setIsConnected(this.isReviewerDefaultForAccountant( rs.getInt("UserId"), accountantId,supplierId,clientId, partnerId)); // checks whether the partner is connected to manager
	       if( details.getIsConnected()==true)
	       {
	        details.setStatus("selected");
	       }
	        
	        details.setId(rs.getInt("id"));
	         
	       
	        reviewerDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getReviewerListForAccountant" + ex.getMessage());
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
	return reviewerDetails;
}


public ArrayList<SupplierBeans> getConnectedReviewerForAccountant(int supplierId,int accountantId,int clientId,int partnerId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> reviewerDetails =null;
	SupplierBeans details = null; 
	try{
		 
		reviewerDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
	    SQL = "select id,ReviewerId  from supplier_accountants_reviewer where  SupplierId=? and active=?  and  accountantId=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setBoolean(2, true); 
        pstmt.setInt(3, accountantId); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new SupplierBeans(); 
	        details.setUserId(rs.getInt("ReviewerId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("ReviewerId"))); // User Name - gets from   method getUserName()
	        details.setIsConnected(this.isReviewerDefaultForAccountant( rs.getInt("ReviewerId"), accountantId,supplierId,clientId, partnerId)); // checks whether the partner is connected to manager
	       if( details.getIsConnected()==true)
	       {
	        details.setStatus("selected");
	       }
	        
	        details.setId(rs.getInt("id"));
	         
	       
	        reviewerDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getReviewerListForAccountant" + ex.getMessage());
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
	return reviewerDetails;
}

public boolean isAccoutantReviewerConnected(int reviewerId , int accountantId,int supplierId) throws DAOException {
Connection con = null;
PreparedStatement pstmt = null;
String SQL = "";
ResultSet rs=null;  
boolean result=false;
  try{
      con = getConnection();
	    SQL = "select id  from supplier_accountants_reviewer where supplierId=? and AccountantId=?  and  ReviewerId=? and active=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setInt(2, accountantId); 
        pstmt.setInt(3, reviewerId); 
        pstmt.setBoolean(4, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	
	    	result= true;	
	    	 //  System.out.println(" isPartnerClientConnected>>>> "+result);
	    }
	    
      pstmt.close(); 
      
      
  }catch (SQLException ex) {
      log.error("SQLException Occurred  in  - isAccoutantReviewerConnected()" + ex.getMessage());
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


public boolean isReviewerDefaultForAccountant(int reviewerId , int accountantId,int supplierId,int clientId,int partnerId) throws DAOException {
Connection con = null;
PreparedStatement pstmt = null;
String SQL = "";
ResultSet rs=null;  
boolean result=false;
  try{
      con = getConnection();
	    SQL = "select id  from supplier_acc_rev_client where SupplierId=? and clientId=?  and  accountantId=? and defaultReviewerId=? and partnerId =? and active=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setInt(2, clientId); 
        pstmt.setInt(3, accountantId); 
        pstmt.setInt(4, reviewerId); 
        pstmt.setInt(5, partnerId); 
        pstmt.setBoolean(6, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	
	    	result= true;	
	    	 //  System.out.println(" isPartnerClientConnected>>>> "+result);
	    }
	    
      pstmt.close(); 
      
      
  }catch (SQLException ex) {
      log.error("SQLException Occurred  in  - isAccoutantReviewerConnected()" + ex.getMessage());
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


//*************** Delete Supplier Users *********************** 
@Override
public boolean deleteSupplierUser(int id) throws DAOException {
  Connection con = null;
  PreparedStatement pstmt = null;
  String SQL = "";
  ResultSet rs=null;  
  boolean result=false;
    try{
        con = getConnection();
        SQL = "update supplierconnect  set active=? where id=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setBoolean(1,false);
        pstmt.setInt(2,id); //  table id
        pstmt.executeUpdate();    
        result=true;
        pstmt.close(); 
        
        
    }catch (SQLException ex) {
        log.error("SQLException Occurred  in  - deleteSupplierUserdetails()" + ex.getMessage());
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
public boolean updateSupplierUser(SupplierBeans details) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL = "";
	 
	boolean result=false;
	  try{
	      con = getConnection();
	      // sets old usertype false
	      SQL = "update supplierconnect  set isAdmin=?, isReviewer=?,isAccountant=?  where id=? and SupplierId=?";
	      pstmt = con.prepareStatement(SQL);
	      pstmt.setBoolean(1, false); //  
	      pstmt.setBoolean(2, false); //  
	      pstmt.setBoolean(3, false); //  
          pstmt.setInt(4,details.getId() );   
          pstmt.setInt(5,details.getSupplierId()); 
	      pstmt.executeUpdate();    
	      pstmt.close();
	      
	      // updates latest usertypes
	      SQL = "update supplierconnect  set isAccountant=?,isReviewer=?,isAdmin=?  where id=? and SupplierId=?";
	      pstmt = con.prepareStatement(SQL);
	      pstmt.setBoolean(1,details.getIsAccountant());
	      pstmt.setBoolean(2,details.getIsReviewer());
	      pstmt.setBoolean(3,details.getIsAdmin());
          pstmt.setInt(4,details.getId() );   
          pstmt.setInt(5,details.getSupplierId() ); 
	      pstmt.executeUpdate();    
	      result=true;
	      pstmt.close(); 
	      
	      
	  }catch (SQLException ex) {
	      log.error("SQLException Occurred  in  - updateSupplierUser()" + ex.getMessage());
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
public boolean assignReviewertoAccountant(String reviewerIds, int accountantId,int supplierId)throws DAOException {
	Connection con = null;
    PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
    String SQL = "";
    ResultSet rs=null;  
    boolean result = false;  
      try{
          con = getConnection();
          String[] temp;
          temp= reviewerIds.split(","); // Splitting ids (12,45,67,21)
         
         
          
       // update all partner active false
	    	  SQL = "update supplier_accountants_reviewer  set active=? where supplierId=? and AccountantId=? ";
	          pstmt = con.prepareStatement(SQL);
	          pstmt.setBoolean(1,false);
	          pstmt.setInt(2,supplierId);
	          pstmt.setInt(3,accountantId);
	          pstmt.executeUpdate();
	          result=true;
	          pstmt.close();
          
          
          
          for(int i =0; i < temp.length ; i++) //12,34,67,32,79
          {
        	  
             // System.out.println("mangerId>>>> "+mangerId);	  
            //  System.out.println("clientId>>>> "+clientId);	 
             // System.out.println("partnerId>>>> "+temp[i]);	
	  
  	    SQL = "select id  from supplier_accountants_reviewer where supplierId=? and AccountantId=?  and  ReviewerId=? ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setInt(2, accountantId); 
        pstmt.setInt(3, Integer.parseInt(temp[i])); 
        rs= pstmt.executeQuery();
	    if(rs.next()) {
	  
          SQL = "update supplier_accountants_reviewer  set active=? where supplierId=? and AccountantId=? and  ReviewerId=?";
          pstmt1 = con.prepareStatement(SQL);
          pstmt1.setBoolean(1,true);
          pstmt1.setInt(2,supplierId);
          pstmt1.setInt(3,accountantId);
          pstmt1.setInt(4,Integer.parseInt(temp[i]));
          pstmt1.executeUpdate();
          pstmt1.close();
	    } 
	    else
	    {
	 	   SQL = "insert into supplier_accountants_reviewer(supplierId,AccountantId,ReviewerId) values (?,?,?)";
           pstmt2 = con.prepareStatement(SQL);
           pstmt2.setInt(1, supplierId);           
           pstmt2.setInt(2,accountantId);
           pstmt2.setInt(3, Integer.parseInt(temp[i]));
           pstmt2.executeUpdate();	
           pstmt2.close();
	    }
	    result=true;
          }
      }catch (SQLException ex) {
          log.error("SQLException Occurred  assignReviewertoAccountant()" + ex.getMessage());
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


public ArrayList<SupplierBeans> getAccountantsListForReviewer(int supplierId,int reviewerId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> accountantDetails =null;
	SupplierBeans details = null; 
	try{
		 
		accountantDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
	    SQL = "select id,AccountantId  from supplier_accountants_reviewer where  SupplierId=?  and ReviewerId=? and active=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setInt(2, reviewerId); 
        pstmt.setBoolean(3, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new SupplierBeans(); 
	        details.setUserId(rs.getInt("AccountantId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("AccountantId"))); // User Name - gets from   method getUserName()
	        details.setId(rs.getInt("id"));
	        
	       
	        accountantDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getAccountantsListForReviewer" + ex.getMessage());
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
	return accountantDetails;
}

// Delete Accountants 
@Override
public boolean deleteAccountants(int id) throws DAOException {
Connection con = null;
PreparedStatement pstmt = null;
String SQL = "";
boolean result=false;
try{
    con = getConnection();
    SQL = "update supplier_accountants_reviewer  set active=? where id=?";
    pstmt = con.prepareStatement(SQL);
    pstmt.setBoolean(1,false);
    pstmt.setInt(2,id); //  table id
    pstmt.executeUpdate();    
    result=true;
    pstmt.close(); 
    
    
}catch (SQLException ex) {
    log.error("SQLException Occurred  in  - deleteAccountants()" + ex.getMessage());
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


public ArrayList<SupplierBeans> getAccountantsListForPartner(int supplierId,int clientId,int partnerId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> accountantDetails =null;
	SupplierBeans details = null; 
	try{
		 
		accountantDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
	    SQL = "select id,UserId  from supplierconnect where  SupplierId=? and active=?  and isAccountant=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setBoolean(2, true); 
        pstmt.setBoolean(3, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new SupplierBeans(); 
	        details.setUserId(rs.getInt("UserId")); // accountant  Id
	        details.setUserName(daoObj.getUserName(rs.getInt("UserId"))); // User Name - gets from   method getUserName()
	        details.setIsConnected(this.isPartnerAccountantConnected(clientId, supplierId,partnerId,rs.getInt("UserId"))); // checks whether partner and accountant connected
	      
	        if( details.getIsConnected()==true)
		       {
		        details.setStatus("selected");  //Accountant connected
		         
		       }
	        
	       
	        
	      
	        details.setId(rs.getInt("id"));
	        
	        
	        
 
	        accountantDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getAccountantsListForReviewer" + ex.getMessage());
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
	return accountantDetails;
}

public ArrayList<SupplierBeans> getActiveAccountantForPartner(int supplierId,int clientId,int partnerId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<SupplierBeans> accountantDetails =null;
	SupplierBeans details = null; 
	try{
		 
		accountantDetails = new ArrayList<SupplierBeans>();
	    con = getConnection();
	    SQL = "select id,accountantId  from supplier_acc_rev_client where ClientID=? and SupplierId=?  and  partnerId=? and active=?"; 
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, clientId); 
        pstmt.setInt(2, supplierId); 
        pstmt.setInt(3, partnerId); 
        pstmt.setBoolean(4, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new SupplierBeans(); 
	        details.setUserId(rs.getInt("accountantId")); // accountant  Id
	        details.setUserName(daoObj.getUserName(rs.getInt("accountantId"))); // User Name - gets from   method getUserName()
	       
	        
	        details.setIsDefault(this.isAccountantDefaultForPartner(clientId, supplierId,partnerId,rs.getInt("accountantId")));
	        if( details.getIsDefault()==true)
		       {
		        details.setType("checked"); // default accountant
		         
		       }
	        
	       
	        details.setReviewerList(this.getReviewerListForAccountantPartner(supplierId,rs.getInt("accountantId"),clientId,partnerId));
	        
	        details.setId(rs.getInt("id"));
	        
	        
	        
 
	        accountantDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getActiveAccountantForPartner" + ex.getMessage());
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
	return accountantDetails;
}



public boolean isPartnerAccountantConnected(int clientId, int supplierId,int partnerId,int accountantId) throws DAOException {
Connection con = null;
PreparedStatement pstmt = null;
String SQL = "";
ResultSet rs=null;  
boolean result=false;
  try{
      con = getConnection();
     // System.out.println("clientId "+clientId);
     // System.out.println("supplierId "+supplierId);
     // System.out.println("partnerId "+partnerId);
     // System.out.println("accountantId "+accountantId);
	    SQL = "select id  from supplier_acc_rev_client where ClientID=? and SupplierId=?  and  partnerId=? and accountantId=? and active=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, clientId); 
        pstmt.setInt(2, supplierId); 
        pstmt.setInt(3, partnerId); 
        pstmt.setInt(4, accountantId); 
        pstmt.setBoolean(5, true); 
        rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	
	    	result= true;	
	    	 //  System.out.println(" isPartnerClientConnected>>>> "+result);
	    }
	    
      pstmt.close(); 
      
      
  }catch (SQLException ex) {
      log.error("SQLException Occurred  in  - isPartnerAccountantConnected()" + ex.getMessage());
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


public boolean isAccountantDefaultForPartner(int clientId, int supplierId,int partnerId,int accountantId) throws DAOException {
Connection con = null;
PreparedStatement pstmt = null;
String SQL = "";
ResultSet rs=null;  
boolean result=false;
  try{
      con = getConnection();
     // System.out.println("clientId "+clientId);
     // System.out.println("supplierId "+supplierId);
     // System.out.println("partnerId "+partnerId);
     // System.out.println("accountantId "+accountantId);
	    SQL = "select id  from supplier_acc_rev_client where ClientID=? and SupplierId=?  and  partnerId=? and accountantId=? and active=? and isDefaultAccountant=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, clientId); 
        pstmt.setInt(2, supplierId); 
        pstmt.setInt(3, partnerId); 
        pstmt.setInt(4, accountantId); 
        pstmt.setBoolean(5, true); 
        pstmt.setBoolean(6, true); 
        rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	
	    	result= true;	
	    	 //  System.out.println(" isPartnerClientConnected>>>> "+result);
	    }
	    
      pstmt.close(); 
      
      
  }catch (SQLException ex) {
      log.error("SQLException Occurred  in  - isPartnerAccountantConnected()" + ex.getMessage());
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
public boolean assignAccoutantToPartners( int supplierId,int clientId,int partnerId,String accountantIds) throws DAOException {
	Connection con = null;
    PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
    String SQL = "";
    ResultSet rs=null;  
    boolean result = false;  
      try{
          con = getConnection();
          String[] temp;
          temp= accountantIds.split(","); // Splitting ids (12,45,67,21)
         
        // System.out.println("supplierId>>> "+supplierId);
        // System.out.println("clientId>>> "+clientId);
        // System.out.println("partnerId>>> "+partnerId);
        // System.out.println("accountantIds>>> "+accountantIds);
          
       // update all partner active false
	    	  SQL = "update supplier_acc_rev_client  set active=? where supplierId=? and clientId=? and partnerId=? ";
	          pstmt = con.prepareStatement(SQL);
	          pstmt.setBoolean(1,false);
	          pstmt.setInt(2,supplierId);
	          pstmt.setInt(3,clientId);
	          pstmt.setInt(4,partnerId);
	          pstmt.executeUpdate();
	          result=true;
	          pstmt.close();
          
          
          
          for(int i =0; i < temp.length ; i++) //12,34,67,32,79
          {
        	  
             
             //  System.out.println("accountantId######## "+temp[i]);	
	  
  	    SQL = "select id  from supplier_acc_rev_client where supplierId=? and clientId=?  and  partnerId=?  and  accountantId=? ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, supplierId); 
        pstmt.setInt(2, clientId); 
        pstmt.setInt(3, partnerId); 
        pstmt.setInt(4, Integer.parseInt(temp[i])); 
        rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	//  System.out.println("accountantId************* "+temp[i]);	
	    	 // System.out.println("partnerId*********** "+partnerId);
          SQL = "update supplier_acc_rev_client  set active=? where supplierId=? and clientId=? and partnerId=?  and accountantId=?";
          pstmt1 = con.prepareStatement(SQL);
          pstmt1.setBoolean(1,true);
          pstmt1.setInt(2,supplierId);
          pstmt1.setInt(3,clientId);
          pstmt1.setInt(4, partnerId); 
          pstmt1.setInt(5,Integer.parseInt(temp[i]));
          pstmt1.executeUpdate();
          pstmt1.close();
	    
	    
	    } 
	    else
	    {
	    	 // System.out.println("accountantId^^^^^^^^^^^^^^^^"+temp[i]);
	    	//  System.out.println("partnerId^^^^^^^^^^^^^^^^^^ "+partnerId);
	 	   SQL = "insert into supplier_acc_rev_client(supplierId,clientId,partnerId,accountantId) values (?,?,?,?)";
           pstmt2 = con.prepareStatement(SQL);
           pstmt2.setInt(1, supplierId);           
           pstmt2.setInt(2,clientId);
           pstmt2.setInt(3, partnerId); 
           pstmt2.setInt(4, Integer.parseInt(temp[i]));
           pstmt2.executeUpdate();	
           pstmt2.close();
	    }
	    result=true;
          }
      }catch (SQLException ex) {
          log.error("SQLException Occurred  assignAccoutantToPartners()" + ex.getMessage());
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

//// update default accountant
@Override
public boolean defaultAccountantToPartner(int supplierId ,int clientId,int partnerId, int id) throws DAOException {
Connection con = null;
PreparedStatement pstmt1 = null;
String SQL = "";
boolean result=false;
try{
 con = getConnection();
 SQL = "update supplier_acc_rev_client  set isDefaultAccountant=? where supplierId=? and clientId=? and partnerId=? ";
 pstmt1 = con.prepareStatement(SQL);
 pstmt1.setBoolean(1,false);
 pstmt1.setInt(2,supplierId);
 pstmt1.setInt(3,clientId);
 pstmt1.setInt(4, partnerId); 
 pstmt1.executeUpdate();
 pstmt1.close();
 
 SQL = "update supplier_acc_rev_client  set isDefaultAccountant=? where supplierId=? and clientId=? and partnerId=? and id=? ";
 pstmt1 = con.prepareStatement(SQL);
 pstmt1.setBoolean(1,true);
 pstmt1.setInt(2,supplierId);
 pstmt1.setInt(3,clientId);
 pstmt1.setInt(4, partnerId); 
 pstmt1.setInt(5, id); 
 pstmt1.executeUpdate();
 pstmt1.close();
 
 
}catch (SQLException ex) {
 log.error("SQLException Occurred  in  - defaultAccountantToPartner()" + ex.getMessage());
 throw new DAOException(ex);
}catch(Exception e){
 e.printStackTrace();
}finally{

 
 try{
     if(pstmt1!=null){   pstmt1.close();}
 }catch(Exception e){
     pstmt1 = null;
   
 }
 
 try{
     if(con!=null){   con.close();}
 }catch(Exception e){
     con = null;
 }
}

return result;
}




////update default reviewer to Partner
@Override
public boolean defaultReviewerToPartner(int reviewerId, int id) throws DAOException {
Connection con = null;
PreparedStatement pstmt1 = null;
String SQL = "";
boolean result=false;
try{
con = getConnection();
 
SQL = "update supplier_acc_rev_client  set defaultReviewerId=? where Id=? ";
pstmt1 = con.prepareStatement(SQL);
pstmt1.setInt(1,reviewerId);
pstmt1.setInt(2,id);
pstmt1.executeUpdate();
pstmt1.close();

}catch (SQLException ex) {
log.error("SQLException Occurred  in  - defaultReviewerToPartner()" + ex.getMessage());
throw new DAOException(ex);
}catch(Exception e){
e.printStackTrace();
}finally{


try{
if(pstmt1!=null){   pstmt1.close();}
}catch(Exception e){
pstmt1 = null;

}

try{
if(con!=null){   con.close();}
}catch(Exception e){
con = null;
}
}

return result;
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
	          		+ "Comments,Urgency,addedBy,isFollowLeadSchedule,SecureCode) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
	          pstmt.executeUpdate();
	          rs= pstmt.getGeneratedKeys();
	          if(rs.next())
	          {
	           
	           jobId =rs.getInt(1); // gets inserted new userID
	            
	      
	       
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


public ArrayList<JobBeans> getAssistJobList() throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<JobBeans> jobDetails =null;
	JobBeans details = null; 
	try{
		 
		jobDetails = new ArrayList<JobBeans>();
	    con = getConnection();
	    SQL = "select id,FileName,jobStatus  from jobs where  active=? and jobStatus IN (1,2,3,4)  ";
        pstmt = con.prepareStatement(SQL);
        
        pstmt.setBoolean(1, true); 
        
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new JobBeans(); 
	       details.setId(rs.getInt("id"));
	       details.setFileName(rs.getString("FileName"));
	       details.setJobStatus(rs.getString("jobStatus"));
	         
	       
	       jobDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getAssistJobList" + ex.getMessage());
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



public ArrayList<JobBeans> getJobListForAccountant(int accountantId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<JobBeans> jobDetails =null;
	JobBeans details = null; 
	try{
		 
		jobDetails = new ArrayList<JobBeans>();
	    con = getConnection();
	    SQL = "select id,FileName,jobStatus  from jobs where  accountantId=? and active=? and jobStatus<4  ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, accountantId); 
        pstmt.setBoolean(2, true); 
        
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new JobBeans(); 
	       details.setId(rs.getInt("id"));
	       details.setFileName(rs.getString("FileName"));
	       details.setJobStatus(rs.getString("jobStatus"));
	         
	       
	       jobDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getReviewerListForAccountant" + ex.getMessage());
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



public ArrayList<JobBeans> getJobStatusForAccountant(int supplierId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<JobBeans> jobStatusList =null;
	JobBeans details = null; 
	try{
		 
		jobStatusList = new ArrayList<JobBeans>();
	    con = getConnection();
	    SQL = "select id,Status  from accountant_jobstatus where  active=? and id<=5   ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setBoolean(1, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new JobBeans(); 
	       details.setId(rs.getInt("id"));
	       details.setJobStatus(rs.getString("Status"));

	       jobStatusList.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getReviewerListForAccountant" + ex.getMessage());
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
	return jobStatusList;
}
public JobBeans  getCurrentAccountantJob(int accountantId, boolean isAccountant) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	 
	JobBeans details = null; 
	try{
		 
	    con = getConnection();
	    details = new JobBeans(); 
	   
	    SQL = "select id,jobid,jobstatusid,startday,starttime  from timesheet where  userid=? and usertype=? and completed=?    ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, accountantId); 
        pstmt.setBoolean(2, isAccountant); // if false= Reviewer
        pstmt.setBoolean(3, false); // if false= not completed; true= completed
        
        rs= pstmt.executeQuery();
	    if(rs.next()) {
	     
	    	
	       details.setId(rs.getInt("id"));
	       details.setFileName(daoObj.getFileName(rs.getInt("jobid")));
	       details.setJobStatus(daoObj.getAccountantJobStatus(rs.getInt("jobstatusid")));
	       if(rs.getInt("jobid")==0)
	       {
	    	   details.setFileName(daoObj.getAccountantJobStatus(rs.getInt("jobstatusid"))); 
	       }
	       details.setIsCurrentJob(true); 
	       details.setStartJob("Checked");
	        
	    }
	    else
	    {     
	    	details.setStopJob("Checked");
	    	  details.setIsCurrentJob(false); 
	    
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getCurrentAccountantJob" + ex.getMessage());
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





 // Insert Accountant Time Sheet Details
public int startAccountantTimeSheet(TimeSheetBeans details)  throws DAOException {
  
     Connection con = null;
     PreparedStatement pstmt = null,pstmt1=null;
     String SQL = "";
     ResultSet rs=null;  
    int timeSheetId=0;
       try{
           con = getConnection();
          if(details.getJobstatusId()!=5) //still accountant is working
          {
           //inserts new user 
           SQL = "insert into timesheet(ClientId,jobid,userid,usertype,jobstatusid,startday,starttime,"
             + " notes) values (?,?,?,?,?,CURDATE(),CURTIME(),?)";
           pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
           pstmt.setInt(1,daoObj.getClientIDForJob(details.getJobId())) ;   // Client Id   
           pstmt.setInt(2,details.getJobId());           
           pstmt.setInt(3, details.getUserId());
           pstmt.setBoolean(4, details.getUserType()); //1= Accountant 0= reviewer
           pstmt.setInt(5, details.getJobstatusId());
           pstmt.setString(6, details.getNotes());
          
           pstmt.executeUpdate();
           rs= pstmt.getGeneratedKeys();
           if(rs.next())
           {
            
        	   timeSheetId =rs.getInt(1); // gets inserted new timeSheet Id
             // System.out.println("getMainStatusOfAccRev"+this.getMainStatusOfAccRev(details.getJobstatusId(), details.getUserType()));
              //System.out.println("details.getJobId() "+details.getJobId()); 
               SQL = "update jobs  set jobStatus=?  where id=?  ";
    	       pstmt1 = con.prepareStatement(SQL);
    	       pstmt1.setInt(1,this.getMainStatusOfAccRev(details.getJobstatusId(), details.getUserType()));      //details.getUserType() 1= Accountant 0= reviewer  
    	       pstmt1.setInt(2,details.getJobId());
    	     
    	       pstmt1.executeUpdate();       
    	       pstmt1.close();
        
           }
           rs.close();
           pstmt.close();
          }
          else if(details.getJobstatusId()==5) // sending to reviewer
          {
           SQL = "update jobs  set jobStatus=?  where id=?  ";
   	       pstmt1 = con.prepareStatement(SQL);
   	       pstmt1.setInt(1,this.getMainStatusOfAccRev(details.getJobstatusId(), details.getUserType()));      //details.getUserType() 1= Accountant 0= reviewer  
   	       pstmt1.setInt(2,details.getJobId());
   	       pstmt1.executeUpdate();       
   	       pstmt1.close();
        	  
          }
          
           
           
       }catch (SQLException ex) {
           log.error("SQLException Occurred in startAccountantTimeSheet() " + ex.getMessage());
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

  return timeSheetId;
 }




public boolean stopAccountantTimeSheet(TimeSheetBeans details) throws DAOException {
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 String SQL = "";
	  
	 boolean result=false;
	   try{
	       con = getConnection();
	       System.out.println("details.getId() "+details.getId());
	       System.out.println("details.getUserId() "+details.getUserId());
	       // update endDay and endTime
	       SQL = "update timesheet  set endday=CURDATE(), endtime=CURTIME(),completed=?  where id=? and userid=?  ";
	       pstmt = con.prepareStatement(SQL);
	       pstmt.setBoolean(1, true);
	       pstmt.setInt(2,details.getId());
	       pstmt.setInt(3,details.getUserId());       
	       pstmt.executeUpdate();    
	       result=true;
	       pstmt.close(); 
	       
	       
	   }catch (SQLException ex) {
	       log.error("SQLException Occurred  in  - stopAccountantTimeSheet()" + ex.getMessage());
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


//Insert Leave form - paid , sick, break etc
public int insertLeaveForm(TimeSheetBeans details)  throws DAOException {
 
    Connection con = null;
    PreparedStatement pstmt = null,pstmt1=null;
    String SQL = "";
    ResultSet rs=null;  
   int timeSheetId=0;
      try{
          con = getConnection();
          System.out.println("I'm in insertLeaveForm dao" );
          //inserts new user 
          SQL = "insert into timesheet(jobid,userid,usertype,jobstatusid,startday,starttime,endday,endtime, "
            + " notes,completed) values (?,?,?,?,?,?,?,?,?,?)";
          pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
          pstmt.setInt(1,0);           
          pstmt.setInt(2, details.getUserId());
          pstmt.setBoolean(3, details.getUserType()); //1= Accountant 0= reviewer
          pstmt.setInt(4, details.getJobstatusId());
          pstmt.setString(5, details.getStartDay());
          pstmt.setString(6, details.getStartTime());
          pstmt.setString(7, details.getEndDay());
          pstmt.setString(8, details.getEndTime());
          pstmt.setString(9, details.getNotes());
          pstmt.setBoolean(10, true);
          pstmt.executeUpdate();
          rs= pstmt.getGeneratedKeys();
          if(rs.next())
          {
           
       	   timeSheetId =rs.getInt(1); // gets inserted new timeSheet Id
    
       
          }
          rs.close();
          pstmt.close();
         
    
      }catch (SQLException ex) {
          log.error("SQLException Occurred in startAccountantTimeSheet() " + ex.getMessage());
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

 return timeSheetId;
}


public int getMainStatusOfAccRev(int id, boolean isAccountant) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	int MainStatus=0;
	try{
	    con = getConnection();
	    if(isAccountant==true)	
	    {
	    	 SQL = "select MainStatus from accountant_jobstatus where Id=?";
	    }
	    else
	    {
	    	 SQL = "select MainStatus from reviewer_jobstatus where Id=?";
	    }
	    
	    pstmt = con.prepareStatement(SQL);
	    pstmt.setInt(1, id);           
	    rs= pstmt.executeQuery();
	    if(rs.next()) {
	    	 
	    	MainStatus = rs.getInt("MainStatus");
	    
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
	return MainStatus;
}




}
