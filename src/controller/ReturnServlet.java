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
import beans.Book;


@WebServlet(urlPatterns = { "/return" })
public class ReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("return.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();


		if(isValid(request, messages) == true) {


			int id = Integer.parseInt(request.getParameter("bookId"));
			Book book =  new BookService().getBook(id);

			System.out.println(book.getId());

			int reservationNumber = book.getReservationNumber();
			if(reservationNumber>0){
				new BookService().returnBookToFront(book);
				System.out.println("reservation aruyo");//
				session.setAttribute("validationMessages", messages);
				messages.add("予約者がいます。書棚には戻さず、カウンターで保管してください");

			}else{
				new BookService().returnBookToShelf(book);
			}
		}else {
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("return.jsp").forward(request, response);
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String id =(request.getParameter("bookId"));



		if (StringUtils.isBlank(id) || id.length()==0) {
			messages.add("一連番号を入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}