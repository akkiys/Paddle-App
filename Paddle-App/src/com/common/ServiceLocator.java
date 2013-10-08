package com.common;
import javax.naming.InitialContext;
//import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
//import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ServiceLocator {
	 //~ static fields / initializers ==========================================
    private static ServiceLocator sl;


    //~ private ===============================================================

    /**
     * InitialContext
     */
    private InitialContext ctx;

    /**
     * The <code>Log</code> instance for this class;
     */
    private Log log = LogFactory.getLog(ServiceLocator.class);

    // Singleton
    /*private ServiceLocator() throws ServiceLocatorException 
    {
        // populate the system properties with application
        // server specific Initial Context Factory and
       System.out.println("ServiceLocatorServiceLocatorServiceLocator");
        Properties p = System.getProperties();
        p.put(Context.INITIAL_CONTEXT_FACTORY, Constants.INITIAL_CONTEXT_FACTORY);
        p.put(Context.PROVIDER_URL, Constants.PROVIDER_URL);
        System.out.println("Properties>>> "+p);
        try
        {
            ctx = new InitialContext(p);
            System.out.println("ctx>>>> "+ctx);
        }
        catch (NamingException ne)
        {
            log.warn("Naming Exception Occurred while obtaining ServiceLocator");
            throw new ServiceLocatorException("Error Instantiating ServiceLocator", ne); 
        }
    }*/

    /**
     * Returns the ServiceLocator instance if present or creates
     * a new one.
     * @return ServiceLocator
     */
    public static ServiceLocator getInstance() throws ServiceLocatorException 
    {
        if (sl == null)
        {
            sl = new ServiceLocator();
        }
        return sl;
    }

    /**
     * Returns DataSource object of the database whose
     * JNDI name is passed as the argument.
     * @return DataSource
     */
    public DataSource getDataSource(String jndiName) throws ServiceLocatorException 
    {
        DataSource ds ;         
        try
        {
        	ctx = new InitialContext();
        	  //System.out.println("I'm in getDataSource>>>> "+jndiName);
            ds = (DataSource) ctx.lookup(jndiName);
           // System.out.println("ds>>>> "+ds);
        }
        catch (NamingException ne)
        {
            ne.printStackTrace();     
            log.warn("NamingException Occurred while obtaining DataSource");
            throw new ServiceLocatorException("Error while obtaining DataSource", ne); 
        }
        return ds;
    }	

}
