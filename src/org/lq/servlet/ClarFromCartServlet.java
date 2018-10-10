package org.lq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lq.bean.Book;
import org.lq.bean.Cart;
import org.lq.bean.PageBean;

/**
 * Servlet implementation class ClarFromCartServlet
 */
@WebServlet("/clearFromCart")
public class ClarFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
		
		HttpSession session = req.getSession();
		PageBean pageBean = (PageBean)session.getAttribute("pageBean");
		Book book = pageBean.getMap().get(id);
		Cart cart = (Cart) session.getAttribute("cart");
		cart.setNumberOfBook(book, 0);
		
		
		req.getRequestDispatcher("cart.jsp").forward(req, resp);
	}

}
