package com.webapp.beans;

public class PaddleBeans  implements java.io.Serializable {
	
private String companyName;
private String email;
private String clientName;
private String password;
private String mobile;
private int id;
private String supplierName;
private boolean isClient;
private int clientId;
private int supplierId;
private int userId;
private String userName;
private boolean result;
private boolean isAccountant  ;
private boolean isReviewer   ;
private boolean isAdmin   ;
private boolean isPartner ;
private boolean isManager   ;
 
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
	return supplierName;
}
public void setSupplierName(String supplierName) {
	this.supplierName = supplierName;
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
public boolean  getResult() {
	return result;
}
public void setResult(boolean result) {
	this.result = result;
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
 
public boolean getIsPartner() {
	return isPartner;
}
public void setIsPartner(boolean isPartner) {
	this.isPartner = isPartner;
}
public boolean getIsManager() {
	return isManager;
}
public void setIsManager(boolean isManager) {
	this.isManager = isManager;
}



}
