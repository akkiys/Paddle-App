package com.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
 
	public class LogOutController extends AbstractController{

		private HttpServletRequest request;
		private HttpSession session ;

		@Override
		protected ModelAndView handleRequestInternal(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			ModelAndView model =null;
			session=request.getSession();
	 
			 session.invalidate();
			
			 model = new ModelAndView("index"); 
			
			return model;
		}
}
