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

import beans.Book;
import beans.User;
import service.BookService;
import service.StatusService;

@WebServlet(urlPatterns = { "/status" })
public class StatusServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");

		List<Book> borrowBookList = new StatusService().getBookInfo(loginUser);
		List<Integer> reservedBookIdList = new StatusService().getReservedBookId(loginUser.getId());
		List<Book> reservedBookList = new ArrayList<Book>();

		for(int i = 0 ; i < reservedBookIdList.size() ; i++ ){
			Book book = new BookService().getBook(reservedBookIdList.get(i));
			reservedBookList.add(book);
		}

		System.out.println(reservedBookList.get(0).getName());
		request.setAttribute("reservedBookList", reservedBookList);
		request.setAttribute("borrowBooks", borrowBookList);

		request.getRequestDispatcher("status.jsp").forward(request, response);
	}
}
