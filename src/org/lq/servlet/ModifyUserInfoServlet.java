package org.lq.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lq.bean.User;
import org.lq.exception.NoSuchGenderException;

import org.lq.service.UserService;
import org.lq.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ModifyUserInfoServlet
 */
@WebServlet("/modifyUserInfo.logined.do")
public class ModifyUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService service =new UserServiceImpl();

	@Override
	public void init() throws ServletException {
		service = (UserService)getServletContext().getAttribute("userService");
	}
   
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("bbbbbbbbbbbbbb...");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String telephone = req.getParameter("telephone");
		User user = (User) req.getSession(false).getAttribute("user"); 
		
		
		
		
		user.setPassword(password);
		try {
			user.setGender(gender);
		} catch (NoSuchGenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setTelephone(telephone);
		
		
			try {
				System.out.println(user);
				service.update(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.getSession(false).removeAttribute("user");
			req.getRequestDispatcher("modifyUserInfoSuccess.jsp").forward(req, resp);
		
		
		
	
	}

	
}
