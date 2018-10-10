package org.lq.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lq.bean.Cart;
import org.lq.bean.Order;
import org.lq.bean.User;
import org.lq.exception.ServerException;
import org.lq.service.OrderService;

/**
 * Servlet implementation class SubmitOrderServlet
 */
@WebServlet("/submitOrder")
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService service;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		System.out.println("fetch the userService666....");
		service = (OrderService) getServletContext().getAttribute("orderService");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String addr = req.getParameter("receiverAddress");
		String name = req.getParameter("receiverName");
		String phone = req.getParameter("receiverPhone");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		Order order = new Order();
		order.setUser(user);
		order.setReceiverAddress(addr);
		order.setReceiverName(name);
		order.setReceiverPhone(phone);
		
		try {
			service.fillAndSaveOrderInfo(order,(Cart)session.getAttribute("cart"));
			session.removeAttribute("cart");
			session.setAttribute("order", order); 
			req.getRequestDispatcher("orderInfo.jsp").forward(req, resp);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
