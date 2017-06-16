package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/user-confirm"})
public class UserConfirmation extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

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
		int isAdmin = (Integer) session.getAttribute("userIsAdmin");
		user.setIsAdmin(isAdmin);
		int cardNumber = (Integer) session.getAttribute("userCardNumber");
		user.setCardNumber(cardNumber);

		new UserService().register(user);

		response.sendRedirect("admin");
	}

}
