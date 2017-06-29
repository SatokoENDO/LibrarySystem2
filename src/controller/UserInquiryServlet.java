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

import service.LibraryService;
import service.SearchService;
import beans.Library;
import beans.User;

@WebServlet(urlPatterns = { "/inquiry" })
public class UserInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/inquiry.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();


		if(isValid(request, messages) == true) {


			String name = request.getParameter("name");
			String address = request.getParameter("address");

			User searchedUser = new SearchService().getUser(name, address);
			List<Library> libraries = new LibraryService().getLibraryList();

			request.setAttribute("editUser", searchedUser);
			request.setAttribute("libraries", libraries);

			request.getRequestDispatcher("/userinfo.jsp").forward(request, response);
		}else {
			User editUser = new User();
			editUser.setName(request.getParameter("name"));
			editUser.setAddress(request.getParameter("address"));
			session.setAttribute("editUser", editUser);
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("inquiry");
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String address = request.getParameter("address");


		if (name.length() ==0) {
			messages.add("名前を入力してください");
		}

		if (address.length() ==0) {
			messages.add("住所を入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}
}