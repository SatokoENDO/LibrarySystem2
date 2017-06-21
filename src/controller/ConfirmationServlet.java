package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Library;
import beans.User;
import service.LibraryService;
import service.UserService;

@WebServlet(urlPatterns = { "/user-confirm"})
public class ConfirmationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		HttpSession session = request.getSession();

		session.getAttribute("userLibraryId");
		List<Library> libraries = new LibraryService().getLibraryList();
		session.setAttribute("libraries", libraries);

		request.getRequestDispatcher("confirmation.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = new User();
		user.setName((String) session.getAttribute("userName"));
		user.setAddress((String) session.getAttribute("userAddress"));
		user.setTel((String) session.getAttribute("userTel"));
		user.setMail((String) session.getAttribute("userMail"));
		user.setPassword((String) session.getAttribute("userPassword"));
		String libraryId = (String) session.getAttribute("userLibraryId");
		user.setLibraryId(Integer.parseInt(libraryId));
		String isAdmin = (String) session.getAttribute("userIsAdmin");
		user.setIsAdmin(Integer.parseInt(isAdmin));

		System.out.println(session.getAttribute("userCardNumber"));

		String cardNumber = (String) session.getAttribute("userCardNumber");
		user.setCardNumber(Integer.parseInt(cardNumber));

		new UserService().register(user);

		session.invalidate();

		response.sendRedirect("admin");



	}

}
