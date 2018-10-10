package org.lq.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lq.bean.Book;
import org.lq.bean.PageBean;
import org.lq.exception.ServerException;
import org.lq.service.BookService;
import org.lq.service.UserService;

/**
 * Servlet implementation class ListServlset
 */
@WebServlet("/showProductByPage")
public class ListServlset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookService service;
	
	public void init() throws ServletException {
		System.out.println("fetch the userService....");
		
		service=(BookService)getServletContext().getAttribute("bookService");
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageIndexString = request.getParameter("pageIndex");
		String sizeString = request.getParameter("size");
		System.out.println(pageIndexString);
		int pageIndex = Integer.parseInt(pageIndexString);
		int size = Integer.parseInt(sizeString);
		
		Map<String,Book> map = null;
		try {
			map = service.page(pageIndex,size);
			Integer totals = service.getTotals();
			System.out.println("totals="+totals);
			PageBean pageBean = new PageBean();
			pageBean.setMap(map);
			pageBean.setSize(size);
			pageBean.setPageIndex(pageIndex);
			pageBean.setTotals(totals);
			
			request.getSession().setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("product_list.jsp").forward(request, response);	
		} catch (ServerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}

}
