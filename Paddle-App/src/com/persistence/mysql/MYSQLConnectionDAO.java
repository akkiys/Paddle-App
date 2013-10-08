package com.persistence.mysql;
import com.common.ServiceLocator;
import com.common.ServiceLocatorException;
import com.common.Constants;
import com.persistence.DAOException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException; 

public class MYSQLConnectionDAO {
 
	    /**
	     * The <code>Log</code> for this class.
	     */
	    private Log log = LogFactory.getLog(MYSQLConnectionDAO.class);
	    protected Connection getConnection() throws DAOException {
	        DataSource ds = null;
	        Connection con = null;
	        ServiceLocator sl = null;  
	        try {  
	            sl = ServiceLocator.getInstance();
	          //  System.out.println("sl>>>>> "+sl);
	            //System.out.println("JNDI_DATASOURCE>>>>> "+Constants.JNDI_DATASOURCE);
	            ds = sl.getDataSource(Constants.JNDI_DATASOURCE);
	           // System.out.println("ds>>>>> "+ds);
	            con = ds.getConnection();
	        }
	        catch (ServiceLocatorException se) {   
	            throw new DAOException(se);  
	        }
	        catch (SQLException ex) {
	            log.warn("Exception Obtaining Connection in MYSQLConnectionDAO " + ex.getMessage());
	            throw new DAOException(ex);
	        }
	        return con;
	    } 
	    
}
