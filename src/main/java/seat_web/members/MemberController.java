package seat_web.members;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO dao;

	public void init(ServletConfig config) throws ServletException {
		dao = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		if (action == null || action.equals("/listMembers.do")) {
			//최초 혹은 회원정보 조회 요청일 시 DAO한테 회원목록 받아서 내보냄
			List<MemberVO> membersList = dao.getMembersList();
			request.setAttribute("membersList", membersList);
			nextPage = "/listMembers.jsp";
		}else if (action.equals("/modMemberForm.do")) {
			String id = request.getParameter("id");
			System.out.println(id);
			MemberVO userInfo = dao.searchMemberInfo(id);
			nextPage = "/modMemberForm.jsp";
			System.out.println(userInfo);
			request.setAttribute("userInfo", userInfo);
		}else if(action.equals("/modMember.do")) {
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			String phoneNum = request.getParameter("phone");
			String name = request.getParameter("name");
			
			MemberVO vo = new MemberVO(name, user_id, user_pw, phoneNum);
			request.setAttribute("msg", "modified");
			nextPage = "/member/listMembers.do";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
