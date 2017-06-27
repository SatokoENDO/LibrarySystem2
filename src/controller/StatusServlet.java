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

					System.out.println(reservation);
					reservationNumber.add(eachReservedBookNumber.indexOf(reservation));
				}
			}
		}

		System.out.println(reservedBookNumber);
		System.out.println(reservationNumber);
		request.setAttribute("reservedBookList", reservedBookList);
		request.setAttribute("borrowBooks", borrowBookList);
		request.setAttribute("reservationNumber", reservationNumber);

		request.getRequestDispatcher("status.jsp").forward(request, response);
	}
}
