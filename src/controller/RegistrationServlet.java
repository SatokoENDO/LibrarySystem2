package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import service.BookService;
import service.LibraryService;
import service.ShelfService;
import beans.Book;
import beans.Library;
import beans.Shelf;

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

		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {
			Book book = new Book();
			book.setLibraryId(Integer.parseInt(request.getParameter("name")));
			book.setShelfNumber(Integer.parseInt(request.getParameter("account")));
			book.setISBN(request.getParameter("ISBN"));
			book.setName(request.getParameter("name"));
			book.setAuthorName(request.getParameter("authorName"));
			book.setPublisherName(request.getParameter("publisherName"));
			book.setKind(Integer.parseInt(request.getParameter("kind")));
			new BookService().register(book);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("registration");
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}


