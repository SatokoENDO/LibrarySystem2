package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Book;
import service.BookService;

@WebServlet(urlPatterns = { "/book-confirm"})
public class BookConfirmationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		request.getRequestDispatcher("confirmation.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		HttpSession session = request.getSession();

		Book book = new Book();
		String libraryId = (String) session.getAttribute("bookLibraryId");
		book.setLibraryId(Integer.parseInt(libraryId));
		String shelfId = (String) session.getAttribute("shelfId");
		book.setShelfId(Integer.parseInt(shelfId));
		book.setISBN((String) session.getAttribute("ISBN"));
		book.setName((String) session.getAttribute("bookName"));
		book.setAuthor((String) session.getAttribute("author"));
		book.setPublisher((String) session.getAttribute("publisher"));
		String kind = (String) session.getAttribute("kindId");
		book.setKind(Integer.parseInt(kind));

		new BookService().register(book);

		session.invalidate();

		response.sendRedirect("admin");


	}
}
