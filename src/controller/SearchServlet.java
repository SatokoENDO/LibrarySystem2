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

		request.setAttribute("kinds", kinds);
		request.setAttribute("libraries", libraries );

		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*HttpSession session = request.getSession();

		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		int libraryId = Integer.parseInt(request.getParameter("libraryId"));
		int kindId = Integer.parseInt(request.getParameter("kindId")) ;

		List<Book> searchedBooks = new SearchService().getBookFromName(bookName, author, publisher, libraryId, kindId);
		session.setAttribute("books", searchedBooks);*/


		response.sendRedirect("searched");
	}

}