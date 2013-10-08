package com.common;

public class ServiceLocatorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception exception;

    /**
     * Default Constructor
     */ 
    public ServiceLocatorException() 
    {
        super();  
    }

    public ServiceLocatorException(String message, Exception ex)
    {
        super(message);
        exception = ex;
    }

    public ServiceLocatorException(String message)
    {
        this(message, null);
    }

    public ServiceLocatorException(Exception ex)
    {
        this(null, ex);
    }

    public Exception getException()
    {
        return exception;
    }

    public Exception getRootCause()
    {
        if (exception instanceof ServiceLocatorException)
        {
            return ((ServiceLocatorException) exception).getRootCause();
        }
        return (exception == null) ? this : exception;
    }

    public String toString()
    {
        if (exception instanceof ServiceLocatorException)
        {
            return exception.toString();
        }
        return (exception == null) ? this.toString() : exception.toString();
    }
}
