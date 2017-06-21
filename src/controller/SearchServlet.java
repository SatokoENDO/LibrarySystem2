package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.KindService;
import service.LibraryService;
import beans.Kind;
import beans.Library;


@WebServlet(urlPatterns = { "/search" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Kind> kinds = new KindService().getKindList();
		List<Library> libraries = new LibraryService().getLibraryList();

		/*できないよー
		String bookName = request.getParameter("bookName");
		List<Book> searchedBooks = new BookService().getBookFromName(bookName);
		request.setAttribute("books", searchedBooks);*/

		request.setAttribute("kinds", kinds);
		request.setAttribute("libraries", libraries);
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

}
