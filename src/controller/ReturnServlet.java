package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
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


		int id = Integer.parseInt(request.getParameter("bookId"));
		Book book =  new BookService().getBook(id);

		System.out.println(book.getId());

		int reservationNumber = book.getReservationNumber();
		if(reservationNumber>0){
			new BookService().returnBookToFront(book);
			System.out.println("reservation aruyo");

		}else{
			new BookService().returnBookToShelf(book);
		}



		response.sendRedirect("admin");
	}
}