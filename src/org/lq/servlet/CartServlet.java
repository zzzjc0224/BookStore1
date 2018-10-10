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
 * Servlet implementation class CartServlet
 */
@WebServlet("/addToCart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		PageBean pageBean = (PageBean)session.getAttribute("pageBean");
		Book book = pageBean.getMap().get(id);
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		cart.addBook(book);
		
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

}
