package org.lq.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.lq.bean.User;
import org.lq.exception.LoginFailedException;
import org.lq.exception.NoSuchEmailUrlException;
import org.lq.exception.ServerException;
import org.lq.service.UserService;
import org.lq.service.impl.UserServiceImpl;
import org.lq.util.EmailUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserService service;
	@Override
	public void init() throws ServletException {
		System.out.println("fetch the userService....");
		service = (UserService) getServletContext().getAttribute("userService");

	}
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 
	
		try {
			User user = service.login(username,password);
			System.out.println(user.getActiveCode());
			if(user.getActiveCode().equals("0")) {
				//用户没激活
				//发一封激活邮件
				String emailContent ="<html><head><meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"></head><body><h1>网上书城激活链接</h1><a href='http://localhost:8080/BookStore/active?id="+user.getId()+"&time="+System.currentTimeMillis()+"'>点此链接激活帐户</a></body></html>";
				
				try {
					EmailUtil.send(emailContent, user.getEmail());
					String emailUrl = EmailUtil.getEmailPage(user.getEmail());
					request.setAttribute("emailUrl", emailUrl);
					request.getRequestDispatcher("to_active.jsp").forward(request, response);

				} catch (EmailException | NoSuchEmailUrlException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else {
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("product_list.jsp").forward(request, response);
			}
			
		} 
		 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

}
