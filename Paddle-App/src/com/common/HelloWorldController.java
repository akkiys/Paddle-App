package com.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList; 

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.webapp.beans.Jdemo;
import com.persistence.MainDAO;
import com.persistence.mysql.MYSQLMainDAO;
 
public class HelloWorldController extends AbstractController{
 
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
        System.out.println("I'm herererererer");
        MainDAO jDemo= new MYSQLMainDAO();
        ArrayList test = new ArrayList();
        
        test=jDemo.getUsers();
        if(test.size()>0){
        	 System.out.println("test.size()>>> "+test.size());
            request.setAttribute("test", test);
        }
        
        
        
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
 
		return model;
	}
}