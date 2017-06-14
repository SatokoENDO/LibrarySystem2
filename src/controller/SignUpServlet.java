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

@WebServlet(urlPatterns = { "/signup"})
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		User user = new User();

		List<Library> libraries = new LibraryService().getLibraries();

		session.setAttribute("libraries", libraries);
		request.setAttribute("user", user);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = new User();
		if(isValid(request, messages) == true) {
			user.setCardNumber(Integer.parseInt("cardNumber"));
			user.setName("name");
			user.setAddress("address");
			user.setTel(Integer.parseInt("tel"));
		}
	}

}
