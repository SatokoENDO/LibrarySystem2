package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Kind;
import beans.Library;
import beans.Shelf;
import service.KindService;
import service.LibraryService;
import service.ShelfService;

@WebServlet(urlPatterns = { "/registration" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		List<Library> libraries = new LibraryService().getLibraryList();
		session.setAttribute("libraries", libraries);

		List<Shelf> shelves = new ShelfService().getShelfList();
		session.setAttribute("shelves", shelves);


		List<Kind> kinds = new KindService().getKindList();
		session.setAttribute("kinds", kinds);


		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		session.setAttribute("bookLibraryId", request.getParameter("libraryId"));
		session.setAttribute("shelfId", request.getParameter("shelfId"));
		session.setAttribute("ISBN", request.getParameter("ISBN"));
		session.setAttribute("bookName", request.getParameter("name"));
		session.setAttribute("author", request.getParameter("author"));
		session.setAttribute("publisher", request.getParameter("publisher"));
		session.setAttribute("kind", request.getParameter("kind"));

		response.sendRedirect("book-confirm");
	}
}


