package seat_web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seat_web.members.*;
@WebServlet("/login")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void init(ServletConfig config) throws ServletException {
		System.out.println("접근성공");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		System.out.println(id+pw);
		MemberVO user = LoginCheckDB.login(id, pw);
		System.out.println("로그인한 유저" + (String)user.getUser_id());
		if(user != null) {
			session.setAttribute("isLogon", true);
			session.setAttribute("user_id", user.getUser_id());
			session.setAttribute("userType", user.getUserType());
			session.setAttribute("PhoneNum", user.getPhoneNum());
			response.sendRedirect("menu.jsp");
		}else {
			out.print("로그인 정보가 일치하지 않습니다.<br>");
			out.print("<a href='seat_web/login.jsp'>로그인 화면으로</a>");
		}
	
	}
	


}
