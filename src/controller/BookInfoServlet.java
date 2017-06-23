package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.Kind;
import beans.Library;
import beans.Shelf;
import service.BookService;
import service.KindService;
import service.LibraryService;
import service.ShelfService;


@WebServlet(urlPatterns = { "/bookInfo" })
public class BookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bookId = Integer.parseInt(request.getParameter("id"));
		BookService bookService = new BookService();
		Book selectedBook = bookService.getBook(bookId);
		List<Library> libraries = new LibraryService().getLibraryList();
		List<Shelf> shelves = new ShelfService().getShelfList();
		List<Kind> kinds = new KindService().getKindList();

		request.setAttribute("libraries", libraries);
		request.setAttribute("book", selectedBook);
		request.setAttribute("shelf", shelves);
		request.setAttribute("kind", kinds);

		request.getRequestDispatcher("bookinfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
