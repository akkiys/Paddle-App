package com.persistence;
import java.util.ArrayList;

import com.webapp.beans.CoverSheetBeans;
import com.webapp.beans.JobBeans;
import com.webapp.beans.PaddleBeans;;

public interface PaddleDAO {

	
	public boolean  isEmailAvaliable(String email) throws DAOException; // checks if email Id already Exists
	
	public int  insertNewClient(PaddleBeans details) throws DAOException; // inserts New client & Connect - SignUp
	
	public int  insertNewSupplier(PaddleBeans details) throws DAOException; // inserts New Supplier & Connect - SignUp
	
	public PaddleBeans  loginCheck(String email, String password) throws DAOException; // checks for login details And returns userId
	
	public PaddleBeans  getClientUserDetails(int id, boolean isClient) throws DAOException; // get user details and client details 

	public PaddleBeans getSupplierUserDetails(int userId, boolean isClient) throws DAOException; // get user details and supplier details 

	public int  isClientExists(String email) throws DAOException; // checks if client is registered using email Id
	
	public boolean  deleteClientSupplierConnection(int id) throws DAOException; // delete client_supplier_connect
	
	public String    getUserName(int userId) throws DAOException; //  get user name
	
    public int insertNewJob(JobBeans details)  throws DAOException ; // inert new JOB
	
	public ArrayList<JobBeans> getJobDetails(int id, String type) throws DAOException; // get JOB details
	
	public String getSecureCodeForUser(int id) throws DAOException;
	
	public String getSecureCodeForClient(int id) throws DAOException ;
	 
	public  JobBeans  getClientPartnerDetailsForJob(int jobId) throws DAOException;
	
	public String getFolderJob(int id) throws DAOException;
	
	public int getClientIDForJob(int jobId) throws DAOException;
	
	public int getPartnerIDForJob(int jobId) throws DAOException;
	
	public int getSupplierIDForJob(int jobId) throws DAOException;
	
	public int getAccoutantIDForJob(int jobId) throws DAOException;
	
	public int getReviewerIDForJob(int jobId) throws DAOException;
	
	public  JobBeans  getJobDetails(int jobId) throws DAOException;
	
	public boolean updateJobStatus(int jobStatus, int id) throws DAOException;
	
	public int insertFileDetailsForJob( String jobId,String fileName)throws DAOException;
	
	public int updateJob(JobBeans details)  throws DAOException;
	
	public String getPrincipalActivity(int id) throws DAOException;
	
	public String getJobStatus(int id) throws DAOException;
	
	public String getFileName(int id) throws DAOException;
	
	public int updateCoverSheetJob(CoverSheetBeans details)  throws DAOException;
	
	public CoverSheetBeans getCoverSheetJob(int jobId) throws DAOException ;
	
	public boolean updateCoverSheetCheckList(int jobId, String fieldName, boolean fieldValue) throws DAOException;
	
	public CoverSheetBeans getCoverSheetCheckListJob(int jobId) throws DAOException;
	
	public String getReviewerJobStatus(int id) throws DAOException ;
	
	public String getAccountantJobStatus(int id) throws DAOException ;
}
