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
import service.KindService;
import service.LibraryService;


@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Kind> kinds = new KindService().getKindList();
		List<Library> libraries = new LibraryService().getLibraryList();

		HttpSession session = request.getSession();

		//図書館情報のセッション削除
		session.removeAttribute("libraries");
		session.removeAttribute("shelves");
		session.removeAttribute("kinds");
		session.removeAttribute("books");

		//利用者情報のセッション削除
		session.removeAttribute("userAddress");
		session.removeAttribute("userName");
		session.removeAttribute("userTel");
		session.removeAttribute("userMail");
		session.removeAttribute("userPassword");
		session.removeAttribute("userLibraryId");
		session.removeAttribute("userIsAdmin");
		session.removeAttribute("userCardNumber");

		//本情報のセッション削除
		session.removeAttribute("bookLibraryId");
		session.removeAttribute("shelfId");
		session.removeAttribute("ISBN");
		session.removeAttribute("bookName");
		session.removeAttribute("author");
		session.removeAttribute("publisher");
		session.removeAttribute("kindId");
		session.removeAttribute("bookId");

		request.setAttribute("kinds", kinds);
		request.setAttribute("libraries", libraries);
		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

}
