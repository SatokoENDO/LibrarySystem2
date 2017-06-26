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
import service.KindService;
import service.LibraryService;
import service.SearchService;

@WebServlet("/searched")
public class SearchedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		int kind = Integer.parseInt(request.getParameter("kindId")) ;
		int libraryId = Integer.parseInt(request.getParameter("libraryId"));


		List<Book> searchedBooks = new SearchService().getBookFromName(bookName, author, publisher, libraryId, kind);
		request.setAttribute("books", searchedBooks);


		List<Kind> kinds = new KindService().getKindList();
		List<Library> libraries = new LibraryService().getLibraryList();

		request.setAttribute("bookName",bookName);
		request.setAttribute("author",author);
		request.setAttribute("publisher",publisher);
		request.setAttribute("libraryId",libraryId);
		request.setAttribute("kindId",kind);

		request.setAttribute("kinds", kinds);
		request.setAttribute("libraries", libraries);
		request.getRequestDispatcher("/searched.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}