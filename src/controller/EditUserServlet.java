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

@WebServlet(urlPatterns = { "/edituser"})
public class EditUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		User user = new User();

		List<Library> libraries = new LibraryService().getLibraryList();
		session.setAttribute("libraries", libraries);

		request.setAttribute("user", user);
		request.getRequestDispatcher("edituser.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();


//		if(isValid(request, messages) == true) {

			session.setAttribute("userCardNumber", Integer.parseInt(request.getParameter("cardNumber")));
			session.setAttribute("userName", request.getParameter("name"));
			System.out.println(request.getParameter("name"));
			session.setAttribute("userAddress", request.getParameter("address"));
			session.setAttribute("userTel", request.getParameter("tel"));
			session.setAttribute("userMail", request.getParameter("mail"));
			session.setAttribute("userPassword", request.getParameter("password"));
			session.setAttribute("userLibraryId", request.getParameter("libraryId"));
			session.setAttribute("userIsAdmin", request.getParameter("isAdmin"));

			response.sendRedirect("update-confirm");

//		}
	}

}
