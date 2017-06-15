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
import service.KindService;
import service.LibraryService;
import service.ShelfService;
import beans.Book;
import beans.Kind;
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


		List<Kind> kinds = new KindService().getKindList();
		session.setAttribute("kinds", kinds);


		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {
			Book book = new Book();
			book.setLibraryId(Integer.parseInt(request.getParameter("libraryId")));
			book.setShelfId(Integer.parseInt(request.getParameter("shelfId")));
			book.setISBN(request.getParameter("ISBN"));
			book.setName(request.getParameter("name"));
			book.setAuthor(request.getParameter("author"));
			book.setPublisher(request.getParameter("publisher"));
			book.setKind(Integer.parseInt(request.getParameter("kind")));
			new BookService().register(book);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("registration");
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		if (StringUtils.isEmpty(name) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (StringUtils.isEmpty(author) == true) {
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


