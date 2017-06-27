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
		List<String> messages = new ArrayList<String>();


		if(isValid(request, messages) == true) {

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
		}else {
			User errorUser = new User();
			errorUser.setName(request.getParameter("name"));
			errorUser.setAddress(request.getParameter("address"));
			errorUser.setTel(request.getParameter("tel"));
			errorUser.setMail(request.getParameter("mail"));
			errorUser.setPassword(request.getParameter("password"));
			errorUser.setLibraryId(Integer.parseInt(request.getParameter("libraryId")));
			session.setAttribute("errorUser", errorUser);
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");


		if (StringUtils.isBlank(name) ||name.length() ==0 || name.length() > 255) {
			messages.add("名前は255文字以下で入力してください");
		}

		if (StringUtils.isBlank(address) ||address.length() ==0 || address.length() > 255) {
			messages.add("住所は255文字以下で入力してください");
		}

		if (StringUtils.isBlank(tel) ||tel.length() ==0 || tel.length() > 11) {
			messages.add("電話番号は11文字以下で入力してください");
		}

		if (!tel.matches("[0-9]+$")) {
			messages.add("電話番号は半角数字のみで入力してください");
		}

		if (mail.length() ==0 || mail.length() > 255) {
			messages.add("メールアドレスは255文字以下で入力してください");
		}

		if(!mail.matches("[-_.@A-Za-z0-9]+$") || !mail.matches(".*@.*")){
			messages.add("メールアドレスは@を含む半角英数字で入力してください");
		}

		if(!password.equals(checkPassword)){
			messages.add("パスワードが一致しません");
		}

		if(password.length() >= 255 || (password.length() < 8) && (password.length() > 0)){
			messages.add("パスワードは8文字以上255文字以下の半角文字で入力してください");
		}


		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}


}

