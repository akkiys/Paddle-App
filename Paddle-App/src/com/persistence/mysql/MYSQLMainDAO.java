package com.persistence.mysql;
 import com.persistence.MainDAO;
import com.persistence.DAOException;
import com.webapp.beans.Jdemo;
import java.sql.*;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MYSQLMainDAO extends MYSQLConnectionDAO implements MainDAO {
	private Log log = LogFactory.getLog(MYSQLMainDAO.class);
	
	
	public boolean addUsers(String username,String password) throws DAOException 
	
	{
	boolean isadded=false;
	
	 
	  Connection con = null;
      PreparedStatement pstmt = null;
      
      String SQL = "";
      
      
      try{
          con = getConnection();
          //System.out.println("con>>>>> "+con);
          SQL = "insert into user(username,password) values (?,?)";
          pstmt = con.prepareStatement(SQL);
          pstmt.setString(1, username);           
          pstmt.setString(2, password);
          pstmt.executeUpdate();
          pstmt.close();
          
      }catch (SQLException ex) {
          log.error("SQLException Occurred  while getting vehicle Id" + ex.getMessage());
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

	return isadded;
	}
	
	
	
public ArrayList getUsers()	throws DAOException 
{
 Connection con = null;
PreparedStatement pstmt = null;
String SQL;
ResultSet rs=null;
ArrayList userDetails =null;
Jdemo details = null; 
try{
	userDetails = new ArrayList();
    con = getConnection();
    SQL = "select username,password from user  ";
    pstmt = con.prepareStatement(SQL);
    rs= pstmt.executeQuery();
    while(rs.next()) {
    	details = new Jdemo(); 
        details.setName(rs.getString("username"));
        details.setPassword(rs.getString("password"));
        userDetails.add(details);
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
return userDetails;
}
	
	
	
	

}
