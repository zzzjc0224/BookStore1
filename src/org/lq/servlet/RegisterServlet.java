package org.lq.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.lq.bean.*;
import org.lq.exception.NoSuchEmailUrlException;
import org.lq.exception.NoSuchGenderException;
import org.lq.exception.UserRegisterFailedException;
import org.lq.service.UserService;
import org.lq.util.EmailUtil;
import org.lq.util.FormUtil;

/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	private UserService service;
	
	public void init(ServletConfig config) throws ServletException {
		String usClassName = config.getServletContext().getInitParameter("usClassName");
		try {
			service = (UserService) Class.forName(usClassName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FormBean rfb= new FormBean();
		try {
		
			FormUtil.populate(request.getParameterMap(),rfb);
			
			Map<String, String> errors = FormUtil.validate(rfb);
			
			if(rfb.getImageCode()==null || 
					request.getSession(false)==null
					||request.getSession(false).getAttribute("imageCode")==null 
					|| !rfb.getImageCode().equals(request.getSession(false).getAttribute("imageCode") ) ){
			
				if( errors==null) {
					errors = new HashMap<>();
				}
				errors.put("imageCode", "校验码错误");
			}
			//如果出现错误，将错误信息errors塞进请求中，转发到register.jsp中，在register.jsp中取出这些错误信息
			if(errors!=null) {
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}else {
				//调service...
				System.out.println("call service...");
				User user=new User();
				try {
					FormUtil.populate(rfb, user);
				} catch (NoSuchGenderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.setActiveCode("0");
				user.setRegistTime(new Date());
				user.setRole("vip");
				user.setState(1);
				try {
					service.register(user);
					
					//发激活邮件
					String emailContent ="<html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"></head><body><h1>网上书城激活链接</h1><a href='http://localhost:8080/BookStore1/active?id="+user.getId()+"&time="+System.currentTimeMillis()+"'>点此链接激活帐户</a></body></html>";
					
					EmailUtil.send(emailContent, user.getEmail());
					String emailUrl = EmailUtil.getEmailPage(user.getEmail());
					request.setAttribute("emailUrl", emailUrl);
					request.getRequestDispatcher("registersuccess.jsp").forward(request, response);

				} catch (UserRegisterFailedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchEmailUrlException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("rfb", rfb);
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
				request.getRequestDispatcher("registersuccess.jsp").forward(request, response);
			}
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
