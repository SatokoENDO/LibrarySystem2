package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DemandService;
import service.KindService;
import service.LibraryService;
import beans.Book;
import beans.Kind;
import beans.Library;
import beans.User;


@WebServlet(urlPatterns = { "/demand" })
public class DemandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		List<Book> books = new DemandService().getDelayedBook();
		List<Kind> kinds = new KindService().getKindList();
		List<Library> libraries = new LibraryService().getLibraryList();
		List<User> users = new DemandService().getUserInfo();
		//request.setAttribute("users", users);

		request.setAttribute("users", users);
		request.setAttribute("books", books);
		request.setAttribute("kinds", kinds);
		request.setAttribute("libraries", libraries );


		request.getRequestDispatcher("/demand.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{

		int userId = Integer.parseInt(request.getParameter("userId"));

		System.out.println("aaaa"+userId);

		new DemandService().updateDemandTime(userId);

		response.sendRedirect("demand");
	}

}
