package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LibraryService;
import service.UserService;
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

		List<Library> libraries = new LibraryService().getLibraryList();

		session.setAttribute("libraries", libraries);
		request.setAttribute("user", user);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = new User();
//		if(isValid(request, messages) == true) {


			user.setName(request.getParameter("name"));
			user.setAddress(request.getParameter("address"));
			user.setTel(request.getParameter("tel"));
			user.setMail(request.getParameter("mail"));
			user.setPassword(request.getParameter("password"));
			user.setLibraryId(Integer.parseInt(request.getParameter("libraryId")));
			if(request.getParameter("isAdmin") == null){
				user.setIsAdmin(0);
			}else{
			user.setIsAdmin(Integer.parseInt(request.getParameter("isAdmin")));
			}
			new UserService().register(user);

			int userId = new UserService().getUserId();
			DecimalFormat dformat = new DecimalFormat("0000000");
			int libraryNumber = user.getLibraryId();
			int cardNumber = Integer.parseInt(libraryNumber + dformat.format(userId));
			new UserService().registerCardNumber(cardNumber);
			System.out.println(cardNumber);

			response.sendRedirect("admin");

//		}
	}

}
