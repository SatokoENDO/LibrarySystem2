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
		request.setAttribute("user", user);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();


//		if(isValid(request, messages) == true) {

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
			int userId = new UserService().getUserId()+1;   //まだDB登録されていないから最後に登録されている人の次のidを付加する
			DecimalFormat dformat = new DecimalFormat("0000000");
			int libraryNumber = Integer.parseInt(request.getParameter("libraryId")) ;
			int cardNumber = Integer.parseInt(libraryNumber + dformat.format(userId));
			session.setAttribute("userCardNumber", cardNumber);

			response.sendRedirect("user-confirm");

//		}
	}

}
