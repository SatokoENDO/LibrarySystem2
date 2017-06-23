package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import service.KindService;
import service.LibraryService;
import beans.Kind;
import beans.Library;

@WebServlet("/searched")
public class SearchedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!StringUtils.isEmpty(request.getParameter("kindId"))){
			int kindId = Integer.parseInt(request.getParameter("kindId"));
			request.setAttribute("kindId", kindId);
		}else{
			int kindId = 0;
			request.setAttribute("kindId", kindId);
			System.out.println("ないよkindId:" + kindId);
		}

		if(!StringUtils.isEmpty(request.getParameter("libraryId"))){
			int libraryId = Integer.parseInt(request.getParameter("libraryId"));
			request.setAttribute("libraryId", libraryId);
			System.out.println("libraryId:" + libraryId);
		}else{
			int libraryId = 0;
			request.setAttribute("libraryId", libraryId);
			System.out.println("libraryId:" + libraryId);
		}

		List<Kind> kinds = new KindService().getKindList();
		List<Library> libraries = new LibraryService().getLibraryList();

		request.setAttribute("kinds", kinds);
		request.setAttribute("libraries", libraries);
		request.getRequestDispatcher("/searched.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}