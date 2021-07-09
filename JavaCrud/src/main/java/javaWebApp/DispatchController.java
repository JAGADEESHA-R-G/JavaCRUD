package javaWebApp;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class DispatchController extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub\
		return new Class[] {};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
	
		return new Class[] { ConfigMVC.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		
		System.out.println("flow Here");
				
		return new String[] {"/"};    // accepts request from all paths 
		
	}
	
}