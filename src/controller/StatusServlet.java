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
import service.ReservationService;
import service.StatusService;

@WebServlet(urlPatterns = { "/status" })
public class StatusServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");

		List<Book> borrowBookList = null;
		List<Book> reservedBookList = new ArrayList<Book>();
		List<List<Integer>> reservedBookNumber = new ArrayList<List<Integer>>();
		List<Integer> reservationNumber = new ArrayList<Integer>();

		if(new StatusService().getBookInfo(loginUser) != null){
			borrowBookList = new StatusService().getBookInfo(loginUser);
		}

		if(new StatusService().getReservedBookId(loginUser.getId()) != null){
			List<Integer> reservedBookIdList = new StatusService().getReservedBookId(loginUser.getId());

			if(reservedBookIdList != null){
				for(int i = 0 ; i < reservedBookIdList.size() ; i++ ){

					Book book = new BookService().getBook(reservedBookIdList.get(i));

					List<Integer> eachReservedBookNumber =new StatusService().getReservationNumber(book.getId());
					reservedBookNumber.add(eachReservedBookNumber) ;
					reservedBookList.add(book);

					int reservation = new StatusService().getReservationNumberOf(loginUser.getId(), book.getId());

					reservationNumber.add(eachReservedBookNumber.indexOf(reservation));
				}
			}
		}

		List<Integer> deliverBookIdList = new ArrayList<Integer>();
		List<Book> deliverBookList = new ArrayList<Book>();

		if(new StatusService().getDeliverBookId(loginUser.getId()) != null){
			deliverBookIdList = new StatusService().getDeliverBookId(loginUser.getId());

			for(int i = 0 ; i < deliverBookIdList.size() ; i++ ){

				Book book = new BookService().getBook(deliverBookIdList.get(i));
				deliverBookList.add(book);
			}
		}

		request.setAttribute("reservedBookList", reservedBookList);
		request.setAttribute("borrowBooks", borrowBookList);
		request.setAttribute("reservationNumber", reservationNumber);
		request.setAttribute("deliverBookList", deliverBookList);

		request.getRequestDispatcher("status.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		User loginUser = (User) session.getAttribute("loginUser");

		new ReservationService().delete(loginUser.getId(), bookId);

		response.sendRedirect("status");
	}
}
