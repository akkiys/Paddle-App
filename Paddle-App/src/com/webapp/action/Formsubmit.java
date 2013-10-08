 
package com.webapp.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.persistence.MainDAO;
import com.persistence.mysql.MYSQLMainDAO;;


public class Formsubmit extends AbstractController{
 
	 

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
  System.out.println("I'm FormsubmitFormsubmitFormsubmitFormsubmitFormsubmit");
 
 String username=request.getParameter("username");
 String password = request.getParameter("password");
 MainDAO jDemo= new MYSQLMainDAO();
 jDemo.addUsers(username, password);
 
 
 ArrayList test = new ArrayList();
 
 test=jDemo.getUsers();
 if(test.size()>0){
 	 System.out.println("test.size()>>> "+test.size());
     request.setAttribute("test", test);
 }
 
 
 
 
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "Successfullllllll "+username);
 
		return model;
	}
}