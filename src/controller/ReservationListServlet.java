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
import service.LibraryService;
import service.ReservationService;
import service.UserService;
import beans.Book;
import beans.Library;
import beans.User;

@WebServlet(urlPatterns = { "/reservationlist" })
public class ReservationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");
		User reservedUser = null;
		int bookId = 0;
		if(request.getParameter("bookId") != null){
			bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new BookService().getBook(bookId);
			System.out.println(new ReservationService().select(bookId));
			if((new ReservationService().select(book.getId()) != 0) ){
				reservedUser = new UserService().getUser(new ReservationService().select(book.getId()));
				request.setAttribute("reservedUser", reservedUser);
			}
			request.setAttribute("book", book);
		}
		List<Library> libraries = new LibraryService().getLibraryList();
		request.setAttribute("libraries", libraries);
		request.setAttribute("loginUser", loginUser);
		request.setAttribute("bookId", bookId);

		request.getRequestDispatcher("reservationlist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//連絡日時更新
		if(request.getParameter("reservationBookId") != null){
			int bookId = Integer.parseInt(request.getParameter("reservationBookId"));
			new ReservationService().updateNotificationTime(bookId);
			response.sendRedirect("reservationlist");
		}else if(request.getParameter("reservationBookIdTo") != null){
			//配送確認
			int bookId = Integer.parseInt(request.getParameter("reservationBookIdTo"));
			new ReservationService().update(bookId);
			response.sendRedirect("reservationlist");
		}else if(request.getParameter("returnBookId") != null){
			//受取確認
			int bookId = Integer.parseInt(request.getParameter("returnBookId"));
			Book book = new BookService().getBook(bookId);
			new BookService().returnBookToShelf(book);
			response.sendRedirect("reservationlist");
		}else if(request.getParameter("deleteReservation") != null){
			//予約削除
			int bookId = Integer.parseInt(request.getParameter("deleteReservation"));
			int reservedUserId = Integer.parseInt(request.getParameter("reservedUser"));
			new ReservationService().delete(reservedUserId, bookId);
			response.sendRedirect("reservationlist");
		}

	}

}