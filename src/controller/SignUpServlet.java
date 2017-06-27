package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;

import beans.Library;
import beans.User;
import service.LibraryService;
import service.UserService;

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

		session.setAttribute("libraries", libraries);
		request.setAttribute("user", user);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();


		if(isValid(request, messages) == true) {

			String password = RandomStringUtils.randomAlphabetic(8);

			session.setAttribute("userName", request.getParameter("name"));
			session.setAttribute("userAddress", request.getParameter("address"));
			session.setAttribute("userTel", request.getParameter("tel"));
			session.setAttribute("userMail", request.getParameter("mail"));
			session.setAttribute("userPassword", password);
			session.setAttribute("userLibraryId", request.getParameter("libraryId"));

			//デフォルト値を0にすればif文を書かなくて済む！
			if(request.getParameter("isAdmin") == null){
				session.setAttribute("userIsAdmin",Integer.toString(0) );
			}else{
				session.setAttribute("userIsAdmin", request.getParameter("isAdmin"));
			}
			int userId = new UserService().getUserId()+1;
			//まだDB登録されていないから最後に登録されている人の次のidを付加する
			DecimalFormat dformat = new DecimalFormat("0000000");
			int libraryNumber = Integer.parseInt(request.getParameter("libraryId")) ;
			int cardNumber = Integer.parseInt(libraryNumber + dformat.format(userId));
			session.setAttribute("userCardNumber", cardNumber);

			response.sendRedirect("user-confirm");

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


		if (name.length() ==0) {
			messages.add("名前を入力してください");
		}

		if (address.length() ==0) {
			messages.add("住所を入力してください");
		}

		if (tel.length() ==0) {
			messages.add("電話番号を入力してください");
		}

		if (!tel.matches("[0-9]+$")) {
			messages.add("電話番号は半角数字のみで入力してください");
		}

		if (mail.length() >0) {
			if(!mail.matches("[-_.@A-Za-z0-9]+$") || !mail.matches(".*@.*")){
				messages.add("メールアドレスは@を含む半角英数字で入力してください");
			}
		}



		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}


}

