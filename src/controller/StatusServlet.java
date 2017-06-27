package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Book;
import beans.User;
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

		System.out.println(reservedBookIdList);

		session.setAttribute("borrowBooks", borrowBookList);



		response.sendRedirect("status.jsp");
	}
}
