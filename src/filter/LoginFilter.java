package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

@WebFilter(urlPatterns = {"/admin", "/confirmation", "/demand", "/edituser", "/inquiry", "/lend", "/registration", "/reservation", "/return", "/signup", "/status", "/updateconfirmation", "/userinfo", "/usersearched"})
public class LoginFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		String target = ((HttpServletRequest)request).getServletPath();
		//サーブレットが呼ばれたときのURI（コンテキストパス、サーブレットパスの部分）を取得する
		String thisURI = ((HttpServletRequest)request).getRequestURI();
		User user = (User) session.getAttribute("loginUser");



		try{
			if(!thisURI.matches(".*.css") && (!target.equals("/login"))&& (!target.equals("/index.jsp"))&& (!target.equals("/top"))&& (!target.equals("/login"))&& (!target.equals("/search"))&& (!target.equals("/searched"))&& (!target.equals("/bookinfo")) && user == null)
			{
				String message = "ログインしてください";
				session.setAttribute("errorMessages", message);
				((HttpServletResponse)response).sendRedirect("login");
				return;
			}

			chain.doFilter(request, response);

		} catch (ServletException se){
		}catch (IOException e){
		}
	}


	public void init(FilterConfig arg0) throws ServletException {

	}


	public void destroy() {

	}


}