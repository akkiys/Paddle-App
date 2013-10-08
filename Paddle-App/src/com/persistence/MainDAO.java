package com.persistence;

import java.util.ArrayList;

public interface  MainDAO {
	
	
	public boolean  addUsers(String username,String password) throws DAOException;
	
	public ArrayList  getUsers() throws DAOException;

}
