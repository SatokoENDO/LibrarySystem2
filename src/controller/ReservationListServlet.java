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
import service.UserService;

@WebServlet(urlPatterns = { "/reservationlist" })
public class ReservationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");
		List<Book> reservedBookList = new ArrayList<Book>();
		List<Integer> reservedBookIdList = new ArrayList<Integer>();
		List<User> reservedBookUserList = new ArrayList<User>();

		if (new StatusService().getReservedBookId() != null) {
			reservedBookIdList = new StatusService().getReservedBookId();

			for (int i = 0; i < reservedBookIdList.size(); i++) {
				Book book = new BookService().getBook(reservedBookIdList.get(i));
				if (loginUser.getLibraryId() == book.getLibraryId()) {
					User reservedUser = new UserService().getUser(book.getBorrower());
					System.out.println(book.getBorrower());
					reservedBookUserList.add(reservedUser);
					reservedBookList.add(book);
				}


			}

			request.setAttribute("reservedBookUserList", reservedBookUserList);
			request.setAttribute("reservedBookList", reservedBookList);
		}

		request.getRequestDispatcher("reservationlist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
