package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Library;
import beans.User;
import service.LibraryService;
import service.SearchService;

@WebServlet(urlPatterns = { "/inquiry" })
public class UserInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/inquiry.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String address = request.getParameter("address");

		User searchedUser = new SearchService().getUser(name, address);
		List<Library> libraries = new LibraryService().getLibraryList();

		request.setAttribute("editUser", searchedUser);
		request.setAttribute("libraries", libraries);

		request.getRequestDispatcher("/userinfo.jsp").forward(request, response);
	}

}
