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
import service.SearchService;
import beans.Book;
import beans.Kind;
import beans.Library;

@WebServlet("/searched")
public class SearchedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();

<<<<<<< HEAD
		if(!StringUtils.isEmpty(request.getParameter("kinds"))){
			int kindId = Integer.parseInt(request.getParameter("kinds"));
			request.setAttribute("kindId", kindId);
			System.out.println("kindId:" + kindId);
		}else{
			int kindId = 0;
			request.setAttribute("kindId", kindId);
			System.out.println("kindId:" + kindId);
		}

		if(!StringUtils.isEmpty(request.getParameter("libraryId"))){
			int libraryId = Integer.parseInt(request.getParameter("libraryId"));
			request.setAttribute("libraryId", libraryId);
			System.out.println("libraryId:" + libraryId);
		}else{
			int libraryId = 0;
			request.setAttribute("libraryId", libraryId);
			System.out.println("libraryId:" + libraryId);
		}
=======

		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		int kind = Integer.parseInt(request.getParameter("kindId")) ;
		int libraryId = Integer.parseInt(request.getParameter("libraryId"));


		List<Book> searchedBooks = new SearchService().getBookFromName(bookName, author, publisher, libraryId, kind);
		request.setAttribute("books", searchedBooks);
>>>>>>> ae6e2eda80ec9a41f103c2c419d34caf1849903d

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