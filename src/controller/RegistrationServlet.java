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

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		if(isValid(request, messages) == true) {

		session.setAttribute("bookLibraryId", request.getParameter("libraryId"));
		session.setAttribute("shelfId", request.getParameter("shelfId"));
		session.setAttribute("ISBN", request.getParameter("ISBN"));
		session.setAttribute("bookName", request.getParameter("name"));
		session.setAttribute("author", request.getParameter("author"));
		session.setAttribute("publisher", request.getParameter("publisher"));
		session.setAttribute("kindId", request.getParameter("kind"));

		int bookId = new BookService().getBookId()+1;   //まだDB登録されていないから最後に登録されている人の次のidを付加する
		session.setAttribute("bookId", bookId);

		response.sendRedirect("book-confirm");

		}else {
		Book errorBook  = new Book();
		errorBook.setISBN(request.getParameter("ISBN"));
		errorBook.setName(request.getParameter("name"));
		errorBook.setAuthor(request.getParameter("author"));
		errorBook.setPublisher(request.getParameter("publisher"));
		errorBook.setLibraryId(Integer.parseInt(request.getParameter("libraryId")));
		session.setAttribute("errorBooks", errorBook);
		session.setAttribute("errorMessages", messages);
		response.sendRedirect("registration");
	}
}
private boolean isValid(HttpServletRequest request, List<String> messages) {
	String ISBN = request.getParameter("ISBN");
	String name = request.getParameter("name");
	String author = request.getParameter("author");
	String publisher = request.getParameter("publisher");


	if (ISBN.length() ==0) {
		messages.add("ISBNを入力してください");
	}

	if (name.length() ==0) {
		messages.add("資料名を入力してください");
	}

	if (author.length() ==0) {
		messages.add("著者名を入力してください");
	}

	if (publisher.length() ==0) {
		messages.add("出版社名を入力してください");
	}

	if(!ISBN.matches("[-0-9X]+$") || !ISBN.matches(".*-.*") || ISBN.length() > 17){
		messages.add("ISBNは-(ハイフン)を含む半角数字17文字以内で入力してください");
	}

	if (messages.size() == 0) {
		return true;
	} else {
		return false;
	}
}


}

