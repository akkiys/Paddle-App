package com.persistence;

import java.util.ArrayList;

import com.webapp.beans.JobBeans;
 
import com.webapp.beans.SupplierBeans;
import com.webapp.beans.TimeSheetBeans;
 

public interface SupplierDAO {
	
	public int  clientSupplierConnect(int supplierId, int clientId,int addedBy) throws DAOException; // inserts connection b/w supplier and Client

	public ArrayList <SupplierBeans>  getClientConnections(int supplierId) throws DAOException; // gets list of all clients connected

	public int insertNewUser(SupplierBeans details) throws DAOException; // adds new user - admin, accountant, reviewer
	
	public ArrayList <SupplierBeans>  getSupplierUsers(int supplierId) throws DAOException; // gets list of all users
	
	public ArrayList<SupplierBeans> getSupplierAccountantList(int supplierId) throws DAOException; //gets accountant List
	
	public ArrayList<SupplierBeans> getSupplierReviewerList(int supplierId) throws DAOException; //gets accountant List
	
	public boolean updateSupplierUser(SupplierBeans details) throws DAOException; // update user - Acc, Rev, Admin
	
	public boolean  deleteSupplierUser(int id) throws DAOException; // delete Supplier Users
	
	public boolean assignReviewertoAccountant(String partnerIds, int mangerId,int clientId)throws DAOException; // assign accountant to one or more reviewer
	
	public ArrayList<SupplierBeans> getAccountantsListForReviewer(int supplierId,int reviewerId) throws DAOException;
	
	public boolean  deleteAccountants(int id) throws DAOException; // delete Accountants
	
	public ArrayList<SupplierBeans> getAccountantsListForPartner(int supplierId,int clientId,int partnerId) throws DAOException ;// get list of accountant for partner
	
	public boolean assignAccoutantToPartners(int supplierId,int clientId,int partnerId,String accountantIds)throws DAOException; // assign partner to one or more accountant
	
	public ArrayList<SupplierBeans> getActiveAccountantForPartner(int supplierId,int clientId,int partnerId) throws DAOException;
	
	public boolean  defaultAccountantToPartner(int supplierId ,int clientId,int partnerId, int id) throws DAOException; //  update default accountant
	
	public boolean  defaultReviewerToPartner(int reviewerId, int id) throws DAOException; //  update default rev to Partner

	public ArrayList<SupplierBeans> getConnectedReviewerForAccountant(int supplierId,int accountantId,int clientId,int partnerId) throws DAOException;
	
	public ArrayList<SupplierBeans> getReviewerListForAccountant(int supplierId,int accountantId) throws DAOException;
	
    public int  insertNewJob(JobBeans details) throws DAOException; // inserts New Job by Supplier
    
	public ArrayList<JobBeans> getJobListForAccountant(int accountantId) throws DAOException; // list of job for an accountant
	
	public ArrayList<JobBeans> getAssistJobList() throws DAOException; // assist job list
	
	public  JobBeans  getCurrentAccountantJob(int accountantId, boolean isAccountant) throws DAOException; //get current job working on
	
	public ArrayList<JobBeans> getJobStatusForAccountant(int supplierId) throws DAOException; // get accountant jobStatus
	
	 public int  startAccountantTimeSheet(TimeSheetBeans details) throws DAOException; // insert accountant timeSheet details
	 
	 public boolean stopAccountantTimeSheet(TimeSheetBeans details) throws DAOException; // update TimeSheet
	 
	 public int insertLeaveForm(TimeSheetBeans details)  throws DAOException;  //Insert Leave form - paid , sick, break etc
	
}
