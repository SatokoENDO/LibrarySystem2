package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Book;
import beans.User;
import service.BookService;

@WebServlet(urlPatterns = { "/reservationlist" })
public class ReservationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");

		if(request.getParameter("bookId") != null){
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new BookService().getBook(bookId);
		}
		request.getRequestDispatcher("reservationlist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
