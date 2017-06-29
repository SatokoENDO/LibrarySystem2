package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BookService;
import service.KindService;
import service.LendService;
import service.UserService;
import beans.Book;
import beans.Kind;
import beans.User;

@WebServlet(urlPatterns = { "/lend-confirm" })
public class LendConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		String bookId = (String) session.getAttribute("bookId");

		Book bookInfo = new BookService().getBook(Integer.parseInt(bookId));
		session.setAttribute("bookName", bookInfo.getName());
		session.setAttribute("author", bookInfo.getAuthor());
		session.setAttribute("publisher", bookInfo.getPublisher());
		session.setAttribute("kindId", bookInfo.getKind());

		List<Kind> kinds = new KindService().getKindList();
		session.setAttribute("kinds", kinds);

		String cardNumber = (String) session.getAttribute("cardNumber");
		User userInfo = new UserService().getUser(Long.parseLong(cardNumber));
		session.setAttribute("userName", userInfo.getName());

		request.getRequestDispatcher("confirmation.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		Book book = new Book();
		String bookId = (String) session.getAttribute("bookId");
		book.setId(Integer.parseInt(bookId));

		String cardNumber = (String) session.getAttribute("cardNumber");
		User userCardNumber = new UserService().getUser(Long.parseLong(cardNumber));

		book.setBorrower(userCardNumber.getId());

		new LendService().lend(book);

		// 図書館情報のセッション削除
		session.removeAttribute("libraries");
		session.removeAttribute("shelves");
		session.removeAttribute("kinds");

		// 利用者情報のセッション削除
		session.removeAttribute("userAddress");
		session.removeAttribute("userName");
		session.removeAttribute("userTel");
		session.removeAttribute("userMail");
		session.removeAttribute("userPassword");
		session.removeAttribute("userLibraryId");
		session.removeAttribute("userIsAdmin");
		session.removeAttribute("userCardNumber");

		// 本情報のセッション削除
		session.removeAttribute("bookLibraryId");
		session.removeAttribute("shelfId");
		session.removeAttribute("ISBN");
		session.removeAttribute("bookName");
		session.removeAttribute("author");
		session.removeAttribute("publisher");
		session.removeAttribute("kindId");
		session.removeAttribute("bookId");

		response.sendRedirect("admin");
	}
}
