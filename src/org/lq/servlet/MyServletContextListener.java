package org.lq.servlet;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;

import org.lq.service.BookService;
import org.lq.service.OrderService;
import org.lq.service.UserService;

public class MyServletContextListener implements javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent ev) {
		String usClassName = ev.getServletContext().getInitParameter("usClassName");
    	String bookClassName = ev.getServletContext().getInitParameter("bookClassName"); 
    	String orderClassName = ev.getServletContext().getInitParameter("orderClassName");
		System.out.println(bookClassName);
    	UserService userService;
		BookService bookService;
		OrderService orderService;
		try {
			userService = (UserService)Class.forName(usClassName).newInstance();
			ev.getServletContext().setAttribute("userService", userService);
			
			bookService = (BookService) Class.forName(bookClassName).newInstance();
			ev.getServletContext().setAttribute("bookService", bookService);
			
			orderService = (OrderService) Class.forName(orderClassName).newInstance();
			ev.getServletContext().setAttribute("orderService", orderService);
			System.out.println("create userService....");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
