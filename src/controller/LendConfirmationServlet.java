package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Book;
import beans.Kind;
import service.BookService;
import service.KindService;

@WebServlet(urlPatterns = { "/lend-confirm" })
public class LendConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		String bookId = (String)session.getAttribute("bookId");

		Book bookInfo = new BookService().getBook(Integer.parseInt(bookId));
		session.setAttribute("bookName", bookInfo.getName());
		session.setAttribute("author", bookInfo.getAuthor());
		session.setAttribute("publisher", bookInfo.getPublisher());
		session.setAttribute("kindId", bookInfo.getKind());

		List<Kind> kinds = new KindService().getKindList();
		session.setAttribute("kinds", kinds);

		System.out.println(session.getAttribute("kinds"));

		request.getRequestDispatcher("confirmation.jsp").forward(request, response);

	}
}
