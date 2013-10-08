package com.persistence.mysql;
import com.persistence.ClientDAO;
import com.persistence.DAOException;
 
import com.persistence.PaddleDAO;
//import com.webapp.beans.Jdemo;
import com.webapp.beans.ClientBeans;
import com.webapp.beans.JobBeans;
import com.webapp.beans.SupplierBeans;
import com.webapp.util.PINGenerator;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MYSQLClientDAO extends MYSQLConnectionDAO implements ClientDAO{
private Log log = LogFactory.getLog(MYSQLClientDAO.class);
PINGenerator pin = new PINGenerator();
PaddleDAO daoObj= new MYSQLPaddleDAO(); // MYSQL Object
private final String DIR = java.util.ResourceBundle.getBundle("PaddleApp").getString("DIR"); 
private File file=null;

@Override
public ArrayList<ClientBeans> getSupplierConnections(int clientId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<ClientBeans> supplierDetails =null;
	ClientBeans details = null; 
	try{
		supplierDetails = new ArrayList<ClientBeans>();
	    con = getConnection();
        SQL = "select id, supplierId,active from client_supplier_connect where  clientId=? ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, clientId);   
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	details = new ClientBeans(); 
	        details.setSupplierId(rs.getInt("supplierId")); // Supplier Id
	        details.setSupplierName(this.getSupplierName(rs.getInt("supplierId"))); // Supplier Name - gets from internal method getSupplierName()
	        details.setId(rs.getInt("id"));
	        details.setIsActive(rs.getBoolean("active")); // supplier Connected /Not Connected
	        
	       
	        
	     supplierDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getSupplierConnections" + ex.getMessage());
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
	return supplierDetails;
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




public String getSecureCodeForClient(int id) throws DAOException {
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
@Override
public boolean approveSupplierConnect(int clientId, int supplierId)
		throws DAOException {
	 
    Connection con = null;
    PreparedStatement pstmt = null;
    String SQL = "";
    ResultSet rs=null;  
    boolean result = false;  
      try{
          con = getConnection();
          SQL = "update client_supplier_connect  set active=? where clientId=? and supplierId=?";
          pstmt = con.prepareStatement(SQL);
          pstmt.setBoolean(1,true);
          pstmt.setInt(2,clientId);
          pstmt.setInt(3,supplierId);
          pstmt.executeUpdate();
          result=true;
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred  approveSupplierConnect()" + ex.getMessage());
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





@Override // insert new Partner / Manager /Admin
public int insertNewUser(ClientBeans details) throws DAOException {
	 
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
   
        	    SQL = "select id from clientconnect where UserId=? and ClientId=?";
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
                    SQL = "insert into clientconnect(UserId,ClientId,"+details.getUserType()+",isAdmin) values (?,?,?,?)";
                    pstmt = con.prepareStatement(SQL);
                    pstmt.setInt(1, userId);
                    pstmt.setInt(2,details.getClientId() );           
                    pstmt.setBoolean(3, true); //  
                    pstmt.setBoolean(4, details.getIsAdmin()); //  
                    pstmt.executeUpdate(); 
                    
                 // Creates new Dir for Partner   
                    
             	   file = new File(DIR+"\\"+daoObj.getSecureCodeForClient(details.getClientId() )+"\\"+details.getName()+"_"+daoObj.getSecureCodeForUser(userId));
               	if (!file.exists()) {
               	if (file.mkdir()) {
               	System.out.println("Directory is created!");
               	} else {
               	System.out.println("Failed to create directory!");
               	}
                SQL = "update clientconnect  set partnerDir=? where UserId=?";
                pstmt = con.prepareStatement(SQL);
                pstmt.setString(1,details.getName()+"_"+daoObj.getSecureCodeForUser(userId)); //  Client folder name
                pstmt.setInt(2,userId); //  table id
                pstmt.executeUpdate();    
                pstmt.close(); 
            
               	}  
                
                  
                    
                    
                    
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








//checks users - if not registered - add and usertype = Client
public int isUserRegisterd(ClientBeans details) throws DAOException {
	 
	 
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
               pstmt.setBoolean(4, true);
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

//  Delete Client Users  
 
public boolean deleteClientUser(int id) throws DAOException {
Connection con = null;
PreparedStatement pstmt = null;
String SQL = "";
ResultSet rs=null;  
boolean result=false;
  try{
      con = getConnection();
      SQL = "update clientconnect  set active=? where id=?";
      pstmt = con.prepareStatement(SQL);
      pstmt.setBoolean(1,false);
      pstmt.setInt(2,id); //  table id
      pstmt.executeUpdate();    
      result=true;
      pstmt.close(); 
      
      
  }catch (SQLException ex) {
      log.error("SQLException Occurred  in  - deleteClientUserdetails()" + ex.getMessage());
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
public boolean updateClientUser(ClientBeans details) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL = "";
	 
	boolean result=false;
	  try{
	      con = getConnection();
	      // sets old usertype false
	      SQL = "update clientconnect  set isAdmin=?, isPartner=?,isManager=?  where id=? and ClientId=?";
	      pstmt = con.prepareStatement(SQL);
	      pstmt.setBoolean(1, false); //  
	      pstmt.setBoolean(2, false); //  
	      pstmt.setBoolean(3, false); //  
          pstmt.setInt(4,details.getId() );   
          pstmt.setInt(5,details.getClientId() ); 
	      pstmt.executeUpdate();    
	      pstmt.close();
	      
	      // updates latest usertypes
	      SQL = "update clientconnect  set "+details.getUserType()+"=?, isAdmin=?  where id=? and ClientId=?";
	      pstmt = con.prepareStatement(SQL);
	      pstmt.setBoolean(1, true); //  
          pstmt.setBoolean(2, details.getIsAdmin()); //  
          pstmt.setInt(3,details.getId() );   
          pstmt.setInt(4,details.getClientId() ); 
          pstmt.executeUpdate();    
	      result=true;
	      pstmt.close(); 
	      
	      if(details.getUserType()=="isPartner") // partner creates new DIr
	      {
	         file = new File(DIR+"\\"+daoObj.getSecureCodeForClient(details.getClientId() )+"\\"+details.getName()+"_"+daoObj.getSecureCodeForUser(details.getUserId()));
         	if (!file.exists()) {
         	if (file.mkdir()) {
         	System.out.println("Directory is created!");
         	} else {
         	System.out.println("Failed to create directory!");
         	}
         	
         	 SQL = "update clientconnect  set partnerDir=? where UserId=?";
             pstmt = con.prepareStatement(SQL);
             pstmt.setString(1,details.getName()+"_"+daoObj.getSecureCodeForUser(details.getUserId())); //  Partner folder name
             pstmt.setInt(2,details.getUserId()); //  table id
             pstmt.executeUpdate();    
             pstmt.close(); 
         	
         	
         	}  
          
            
	      
	      }
	      
	      
	      
	  }catch (SQLException ex) {
	      log.error("SQLException Occurred  in  - updateClientUser()" + ex.getMessage());
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
public ArrayList<ClientBeans> getClientUsers(int clientId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<ClientBeans> userDetails =null;
	ClientBeans details = null; 
	try{
		 
		 userDetails = new ArrayList<ClientBeans>();
	    con = getConnection();
        SQL = "select id,UserId,isAdmin,isPartner,isManager  from clientconnect where  ClientId=? and active=? ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, clientId); 
        pstmt.setBoolean(2, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new ClientBeans(); 
	        details.setUserId(rs.getInt("UserId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("UserId"))); // User Name - gets from internal method getUserName()
	        details.setId(rs.getInt("id"));
	        details.setIsPartner(rs.getBoolean("isPartner"));
	    	details.setIsManager(rs.getBoolean("isManager"));
	    	details.setIsAdmin(rs.getBoolean("isAdmin"));
	        if(rs.getBoolean("isManager")==true)
		        {
		        details.setPartnerList(this.getPartnerListForManager(clientId,rs.getInt("UserId"))); //
		        System.out.println(" details.setPartnerList()>>>> "+ details.getPartnerList().size());
		   }
	        
	       userDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getClientUsers" + ex.getMessage());
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



@Override
public ArrayList<ClientBeans> getPartnerList(int clientId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<ClientBeans> partnerDetails =null;
	ClientBeans details = null; 
	try{
		 
		 partnerDetails = new ArrayList<ClientBeans>();
	    con = getConnection();
        SQL = "select id,UserId  from clientconnect where  ClientId=? and active=?  and isPartner=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, clientId); 
        pstmt.setBoolean(2, true); 
        pstmt.setBoolean(3, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new ClientBeans(); 
	        details.setUserId(rs.getInt("UserId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("UserId"))); // User Name - gets from   method getUserName()
	        details.setId(rs.getInt("id"));
	      
	        partnerDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getPartnerList" + ex.getMessage());
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
	return partnerDetails;
}

 
public ArrayList<ClientBeans> getPartnerListForManager(int clientId,int managerId) throws DAOException {
	Connection con = null;
	PreparedStatement pstmt = null;
	String SQL;
	ResultSet rs=null;
	ArrayList<ClientBeans> partnerDetails =null;
	ClientBeans details = null; 
	try{
		 
		 partnerDetails = new ArrayList<ClientBeans>();
	    con = getConnection();
	    SQL = "select id,UserId  from clientconnect where  ClientId=? and active=?  and isPartner=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, clientId); 
        pstmt.setBoolean(2, true); 
        pstmt.setBoolean(3, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	     
	    	details = new ClientBeans(); 
	        details.setUserId(rs.getInt("UserId")); // user Id
	        details.setUserName(daoObj.getUserName(rs.getInt("UserId"))); // User Name - gets from   method getUserName()
	        details.setIsConnected(this.isPartnerManagerConnected( rs.getInt("UserId"), managerId,clientId)); // checks whether the partner is connected to manager
	       if( details.getIsConnected()==true)
	       {
	        details.setStatus("selected");
	       }
	        
	        details.setId(rs.getInt("id"));
	         
	       
	        partnerDetails.add(details);
	    }
	    rs.close();
	    pstmt.close();
	}catch (SQLException ex) {
	    log.error("SQLException Occurred  getPartnerListForManager" + ex.getMessage());
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
	return partnerDetails;
}
 

public boolean isPartnerManagerConnected(int partnerId, int mangerId,int clientId) throws DAOException {
Connection con = null;
PreparedStatement pstmt = null;
String SQL = "";
ResultSet rs=null;  
boolean result=false;
  try{
      con = getConnection();
	    SQL = "select id  from client_partners_manager where ClientID=? and ManagerId=?  and  PartnerId=? and active=?";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, clientId); 
        pstmt.setInt(2, mangerId); 
        pstmt.setInt(3, partnerId); 
        pstmt.setBoolean(4, true); 
        rs= pstmt.executeQuery();
	    while(rs.next()) {
	    	
	    	result= true;	
	    	 //  System.out.println(" isPartnerClientConnected>>>> "+result);
	    }
	    
      pstmt.close(); 
      
      
  }catch (SQLException ex) {
      log.error("SQLException Occurred  in  - isPartnerClientConnected()" + ex.getMessage());
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
public boolean assignPartnerstoManager(String partnerIds, int mangerId,int clientId)throws DAOException {
	Connection con = null;
    PreparedStatement pstmt = null,pstmt1 = null,pstmt2 = null;
    String SQL = "";
    ResultSet rs=null;  
    boolean result = false;  
      try{
          con = getConnection();
          String[] temp;
          temp= partnerIds.split(","); // Splitting ids (12,45,67,21)
         
         
          
       // update all partner active false
	    	  SQL = "update client_partners_manager  set active=? where ClientID=? and ManagerId=? ";
	          pstmt = con.prepareStatement(SQL);
	          pstmt.setBoolean(1,false);
	          pstmt.setInt(2,clientId);
	          pstmt.setInt(3,mangerId);
	          pstmt.executeUpdate();
	          result=true;
	          pstmt.close();
          
          
          
          for(int i =0; i < temp.length ; i++) //12,34,67,32,79
          {
        	  
             // System.out.println("mangerId>>>> "+mangerId);	  
            //  System.out.println("clientId>>>> "+clientId);	 
             // System.out.println("partnerId>>>> "+temp[i]);	
	  
  	    SQL = "select id  from client_partners_manager where ClientID=? and ManagerId=?  and  PartnerId=? ";
        pstmt = con.prepareStatement(SQL);
        pstmt.setInt(1, clientId); 
        pstmt.setInt(2, mangerId); 
        pstmt.setInt(3, Integer.parseInt(temp[i])); 
        rs= pstmt.executeQuery();
	    if(rs.next()) {
	  
          SQL = "update client_partners_manager  set active=? where ClientID=? and ManagerId=? and  PartnerId=?";
          pstmt1 = con.prepareStatement(SQL);
          pstmt1.setBoolean(1,true);
          pstmt1.setInt(2,clientId);
          pstmt1.setInt(3,mangerId);
          pstmt1.setInt(4,Integer.parseInt(temp[i]));
          pstmt1.executeUpdate();
          pstmt1.close();
	    } 
	    else
	    {
	 	   SQL = "insert into client_partners_manager(ClientID,ManagerId,PartnerId) values (?,?,?)";
           pstmt2 = con.prepareStatement(SQL);
           pstmt2.setInt(1, clientId);           
           pstmt2.setInt(2,mangerId);
           pstmt2.setInt(3, Integer.parseInt(temp[i]));
           pstmt2.executeUpdate();	
           pstmt2.close();
	    }
	    result=true;
          }
      }catch (SQLException ex) {
          log.error("SQLException Occurred  assignPartnerstoManager()" + ex.getMessage());
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


 //Delete Partners

public boolean deletePartners(int id) throws DAOException {
Connection con = null;
PreparedStatement pstmt = null;
String SQL = "";
boolean result=false;
try{
  con = getConnection();
  SQL = "update client_partners_manager  set active=? where id=?";
  pstmt = con.prepareStatement(SQL);
  pstmt.setBoolean(1,false);
  pstmt.setInt(2,id); //  table id
  pstmt.executeUpdate();    
  result=true;
  pstmt.close(); 
  
  
}catch (SQLException ex) {
  log.error("SQLException Occurred  in  - deletePartnerdetails()" + ex.getMessage());
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




 public ArrayList<ClientBeans> getPartnersListForManagers(int clientId,int managerId) throws DAOException {
	 Connection con = null;
	 PreparedStatement pstmt = null;
	 String SQL;
	 ResultSet rs=null;
	 ArrayList<ClientBeans> partnerDetails =null;
	 ClientBeans details = null; 
	 try{
	   
	  partnerDetails = new ArrayList<ClientBeans>();
	     con = getConnection();
	     SQL = "select id,PartnerId  from client_partners_manager where  clientId=?  and managerId=? and active=?";
	        pstmt = con.prepareStatement(SQL);
	        pstmt.setInt(1, clientId); 
	        pstmt.setInt(2, managerId); 
	        pstmt.setBoolean(3, true); 
	        rs= pstmt.executeQuery();
	     while(rs.next()) {
	      
	      details = new ClientBeans(); 
	         details.setUserId(rs.getInt("PartnerId")); // user Id
	         details.setUserName(daoObj.getUserName(rs.getInt("PartnerId"))); // User Name - gets from   method getUserName()
	         details.setId(rs.getInt("id"));
	         
	        
	         partnerDetails.add(details);
	     }
	     rs.close();
	     pstmt.close();
	 }catch (SQLException ex) {
	     log.error("SQLException Occurred  getPartnersListForManagers" + ex.getMessage());
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
	 return partnerDetails;
	}


 public int getDefaultAccountantForPartner(int partnerId,int ClientId) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null,pstmt1 = null;
		String SQL=null;
		int accountantId=0;
		ResultSet rs=null,rs1=null;
		try{
		    con = getConnection();
		    SQL = "select accountantId from supplier_acc_rev_client where partnerId=? and ClientId=? and active=? and isDefaultAccountant=?";
		    pstmt = con.prepareStatement(SQL);
		    pstmt.setInt(1, partnerId);     
		    pstmt.setInt(2, ClientId); 
		    pstmt.setBoolean(3, true);
		    pstmt.setBoolean(4, true);
		    rs= pstmt.executeQuery();
		    if(rs.next()) {
		    	 
		    	accountantId = rs.getInt("accountantId");
		    
		    }
		    else
		    {
		    	//if thr is no default accountatant
		    SQL = "select accountantId from supplier_acc_rev_client where partnerId=? and ClientId=? and active=? ";
		    pstmt1 = con.prepareStatement(SQL);
		    pstmt1.setInt(1, partnerId);     
		    pstmt1.setInt(2, ClientId); 
		    pstmt1.setBoolean(3, true);
		    rs1= pstmt1.executeQuery();
		    if(rs1.next()) {
		    	 
		    	accountantId = rs1.getInt("accountantId");
		    
		    }
		    rs1.close();
		    pstmt1.close();
		  
		    	
		    }
		    rs.close();
		    pstmt.close();
		}catch (SQLException ex) {
		    log.error("SQLException Occurred  getDefaultAccount" + ex.getMessage());
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


 
 public int getDefaultReviewerForPartner(int partnerId,int ClientId, int accoutantId) throws DAOException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String SQL=null;
		int reviewerId=0;
		ResultSet rs=null;
		try{
		    con = getConnection();
		    SQL = "select defaultReviewerId from supplier_acc_rev_client where partnerId=? and ClientId=? and active=? and accountantId=?";
		    pstmt = con.prepareStatement(SQL);
		    pstmt.setInt(1, partnerId);     
		    pstmt.setInt(2, ClientId); 
		    pstmt.setBoolean(3, true);
		    pstmt.setInt(4, accoutantId); 
		    rs= pstmt.executeQuery();
		    if(rs.next()) {
		    	 
		    	reviewerId = rs.getInt("defaultReviewerId");
		    
		    }
		    
		  
		    	
		 
		    rs.close();
		    pstmt.close();
		}catch (SQLException ex) {
		    log.error("SQLException Occurred  getDefaultReviewer" + ex.getMessage());
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


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

}
