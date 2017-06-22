package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/admin" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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

		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

}