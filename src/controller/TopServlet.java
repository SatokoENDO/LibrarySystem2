package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Kind;
import beans.Library;
import service.KindService;
import service.LibraryService;


@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Kind> kinds = new KindService().getKindList();
		List<Library> libraries = new LibraryService().getLibraryList();

		request.setAttribute("kinds", kinds);
		request.setAttribute("libraries", libraries);
		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

}
