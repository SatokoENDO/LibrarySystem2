package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BookService;


@WebServlet(urlPatterns = { "/return" })
public class ReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("return.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		int bookId = Integer.parseInt(request.getParameter("bookId"));

		int reservationNumber = new BookService().getBook(bookId).getReservationNumber();
		if(reservationNumber>0){
			new BookService().returnBookToFront(bookId);
			System.out.println("reservation aruyo");

		}else{
			new BookService().returnBookToShelf(bookId);
		}



		response.sendRedirect("admin");
	}
}