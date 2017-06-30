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
import beans.Library;
import beans.User;
import service.BookService;
import service.LibraryService;
import service.ReservationService;
import service.UserService;


@WebServlet(urlPatterns = { "/demand" })
public class GetBookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Library> libraries = new LibraryService().getLibraryList();
		HttpSession session = request.getSession();

		User loginUser = (User)session.getAttribute("loginUser");

		if(new ReservationService().getReservationUserList() == null){
			List<User> reservedUsers = new ArrayList<User>();
			List<Book> reservedBooks = new ArrayList<Book>();
			for(int i = 0 ; i < new ReservationService().getReservationUserList().size(); i++){
				List<Integer> reservedUserId = new ReservationService().getReservationBookList();
				List<Integer> reservedBookId = new ReservationService().getReservationUserList();
				User reservedUser = new UserService().getUser(reservedUserId.get(i));
				Book reservedBook = new BookService().getBook(reservedBookId.get(i));
				if(reservedUser.getLibraryId() == loginUser.getLibraryId()){
					reservedUsers.add(reservedUser);
					reservedBooks.add(reservedBook);
				}
			}
			request.setAttribute("reservedUsers", reservedUsers);
			request.setAttribute("reservedBooks", reservedBooks);
		}
		request.setAttribute("libraries", libraries);
		request.getRequestDispatcher("getbooklist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
