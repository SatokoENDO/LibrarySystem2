package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.ReservationService;

@WebServlet(urlPatterns = { "/reservation" })
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		User loginUser = (User)session.getAttribute("loginUser");
		int bookId = Integer.parseInt(request.getParameter("bookId"));

		new ReservationService().reservation(loginUser.getId(), bookId );
	}

}
