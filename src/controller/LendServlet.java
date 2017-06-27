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

import org.apache.commons.lang.StringUtils;

@WebServlet(urlPatterns = { "/lend" })
public class LendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("lend.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		if(isValid(request, messages) == true) {


			session.setAttribute("bookId", request.getParameter("bookId"));
			session.setAttribute("cardNumber", request.getParameter("cardNumber"));

			response.sendRedirect("lend-confirm");
		}else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("lend");
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String id =(request.getParameter("bookId"));
		String cardNumber = request.getParameter("cardNumber");



		if (StringUtils.isBlank(id) || id.length()==0) {
			messages.add("一連番号を入力してください");
		}

		if (! cardNumber.matches("[0-9]{8}")) {
			messages.add("利用者証番号は半角数字8文字で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
