package com.persistence;

import java.util.ArrayList;

import com.webapp.beans.ClientBeans;
import com.webapp.beans.JobBeans;
import com.webapp.beans.SupplierBeans;
 

public interface ClientDAO {
	
	public ArrayList<ClientBeans>  getSupplierConnections(int clientId) throws DAOException; // gets list of all supplier connected
	
	public boolean  approveSupplierConnect(int clientId, int supplierId) throws DAOException; // approve supplier connected
	
	public int insertNewUser(ClientBeans details) throws DAOException; // adds new user - Partner, Manager, Admin
	
	public boolean updateClientUser(ClientBeans details) throws DAOException; // update user - Partner, Manager, Admin
	
	public ArrayList <ClientBeans>  getClientUsers(int clientId) throws DAOException; // gets list of all users
	
	public ArrayList <ClientBeans>  getPartnerList(int clientId) throws DAOException; // gets list of all partner
	
	public boolean  deleteClientUser(int id) throws DAOException; // delete Client Users

	public boolean assignPartnerstoManager(String partnerId, int mangerId,int clientId)throws DAOException; // assign manger to one or more partners
	
	public ArrayList<ClientBeans> getPartnersListForManagers(int clientId,int managerId) throws DAOException;// List of partners to mangers
	 
	 public boolean  deletePartners(int id) throws DAOException; // delete Partners
	 
	 public int getDefaultAccountantForPartner(int partnerId,int ClientId) throws DAOException;
	 
	 public int getDefaultReviewerForPartner(int partnerId,int ClientId, int accoutantId) throws DAOException;
	 
	 
	 public int insertNewJob(JobBeans details)  throws DAOException ;
	 
	 

}
