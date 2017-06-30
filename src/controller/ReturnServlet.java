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
import beans.User;


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
		User loginUser = (User) session.getAttribute("loginUser");


		if(isValid(request, messages) == true) {


			int id = Integer.parseInt(request.getParameter("bookId"));
			Book book =  new BookService().getBook(id);

			//System.out.println(book.getId());

			int libraryId = book.getLibraryId();
			int reservationNumber = book.getReservationNumber();
			System.out.println(loginUser.getLibraryId());

			//返却された本のidがadminのidと一致しない場合
			if(libraryId != loginUser.getLibraryId()){

				//予約がある場合
				if(reservationNumber>0){
					new BookService().returnBookToFront(book);

					String validationMessage = "他図書館の所蔵資料です。予約者がいるため、カウンターで保管の上、配送の必要性を確認してください。";

					session.setAttribute("validationMessage", validationMessage);

					response.sendRedirect("return");
				}else{
					new BookService().returnBookToFront(book);
					String validationMessage = "他図書館の所蔵資料です。所蔵元図書館に配送してください。";

					session.setAttribute("validationMessage", validationMessage);
					response.sendRedirect("return");
				}
			}else if (libraryId==loginUser.getLibraryId()){
				if(reservationNumber>0){
					new BookService().returnBookToFront(book);

					String validationMessage = "予約者がいるため、カウンターで保管してください。";

					session.setAttribute("validationMessage", validationMessage);

					response.sendRedirect("return");
				}else{
					new BookService().returnBookToShelf(book);
					String validationMessage = "書棚に戻してください。";

					session.setAttribute("validationMessage", validationMessage);
					response.sendRedirect("return");
				}
			}else{
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("return");
			}
		}
	}



	//int reservationNumber = book.getReservationNumber();

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String bookId =(request.getParameter("bookId"));



		if (StringUtils.isBlank(bookId) || bookId.length()==0) {
			messages.add("一連番号を入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}