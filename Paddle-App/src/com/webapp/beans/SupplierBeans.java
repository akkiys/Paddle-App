package com.webapp.beans;

import java.util.ArrayList;

public class SupplierBeans implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyName;
	private String email;
	private String clientName;
	private String password;
	private String mobile;
	private int id;
	private String SupplierName;
	private boolean isClient;
	private int clientId;
	private int supplierId;
	private int userId;
	private String userName;
	private String status;
	private String userType;
	private String name;
	private String type;
	private String date;
	private boolean isAccountant  ;
	private boolean isReviewer   ;
	private boolean isAdmin   ;
	private ArrayList reviewerList;
	private boolean isConnected;
	private boolean isDefault;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public boolean getIsClient() {
		return isClient;
	}
	public void setIsClient(boolean isClient) {
		this.isClient = isClient;
	}
	public String getSupplierName() {
		return SupplierName;
	}
	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsAccountant() {
		return isAccountant;
	}
	public void setIsAccountant(boolean isAccountant) {
		this.isAccountant = isAccountant;
	}
	public boolean getIsReviewer() {
		return isReviewer;
	}
	public void setIsReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}
	 
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public ArrayList getReviewerList() {
		return reviewerList;
	}
	public void setReviewerList(ArrayList reviewerList) {
		this.reviewerList = reviewerList;
	}
	 
	public boolean getIsConnected() {
		return isConnected;
	}
	public void setIsConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	 



}
