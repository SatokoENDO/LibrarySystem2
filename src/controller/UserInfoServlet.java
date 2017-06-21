package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LibraryService;
import beans.Library;
import beans.User;

@WebServlet(urlPatterns = { "/userinfo" })
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");
		List<Library> libraries = new LibraryService().getLibraryList();
		session.setAttribute("libraries", libraries);

		session.setAttribute("editUser", loginUser);

		request.getRequestDispatcher("userinfo.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		session.setAttribute("bookId", request.getParameter("bookId"));
		session.setAttribute("cardNumber", request.getParameter("cardNumber"));

		response.sendRedirect("userinfo");

	}
}