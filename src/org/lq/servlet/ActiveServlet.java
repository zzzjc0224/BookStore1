package org.lq.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lq.exception.UserActiveFailedException;
import org.lq.service.UserService;

/**
 * Servlet implementation class ActiveServlet
 */
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String time = request.getParameter("time");
		if(id==null || time==null) {
			return;
		}
		long tim = Long.parseLong(time);
		if(System.currentTimeMillis()-tim>300000) {
			return;
		}
		try {
			service.active1(id);
			request.getRequestDispatcher("activesuccess.jsp").forward(request, response);
		} catch (UserActiveFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
