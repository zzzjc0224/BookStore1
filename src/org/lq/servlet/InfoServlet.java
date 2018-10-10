package org.lq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lq.bean.Book;
import org.lq.exception.ServerException;
import org.lq.service.BookService;

/**
 * Servlet implementation class InfoServlet
 */
@WebServlet("/product_info")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 BookService service;   
    /**
     * @see HttpServlet#HttpServlet()
     */
   @Override
   	public void init() throws ServletException {
	   System.out.println("fetch the userService....");
		service = (BookService) getServletContext().getAttribute("bookService");

}

	protected void service(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
			String id = req.getParameter("id");
			if(id==null) {
				return ;
			}
			Book book;
			try {
				book= service.getBook(id);
				req.setAttribute("book", book);
				req.getRequestDispatcher("product_info.jsp").forward(req, resp);
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

}
