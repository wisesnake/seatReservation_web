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
		}else if(action.equals("/signUpForm.do")) {
			//회원가입 버튼 눌렀을 때 회원가입 폼 jsp로 포워드
			System.out.println("회원가입 요청");
			nextPage = "/signUpForm.jsp";
		}else if(action.equals("/addMember.do")) {
			//회원가입 폼 jsp에서 리퀘스트를 받았을 때, id pw name 폰번호 DB등록
			String id = request.getParameter("user_id");
			String pw = request.getParameter("user_pw");
			String name = request.getParameter("name");
			String phoneNum = request.getParameter("phoneNum");
			MemberVO vo = new MemberVO(name, id, pw, phoneNum);
			dao.addMember(vo);
			request.setAttribute("msg", "addMember");
			nextPage = "/login.jsp";
		}else if (action.equals("/modMemberForm.do")) {
			//회원정보 관리페이지에서 수정할 시, id만 넘겨와서 dao에서 해당 id로 정보 찾은 후
			//회원 수정 페이지로 넘어감.
			String id = request.getParameter("user_id");
			System.out.println(id);
			MemberVO userInfo = dao.searchMemberInfo(id);
			nextPage = "/modMemberForm.jsp";
			System.out.println(userInfo);
			request.setAttribute("userInfo", userInfo);
		}else if(action.equals("/modMember.do")) {
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			String phoneNum = request.getParameter("phoneNum");
			String name = request.getParameter("name");
			MemberVO vo = new MemberVO(name, user_id, user_pw, phoneNum);
			dao.modUserInfo(vo);
			System.out.println(vo.getPhoneNum());
			request.setAttribute("msg", "modified");
			nextPage = "/member/listMembers.do";
		}else if(action.equals("/login.do")) {
			nextPage = "/login";
		}else if(action.equals("/adminDelMember.do")) {
			String user_id = request.getParameter("user_id");
			dao.delUser(user_id);
			request.setAttribute("msg", "deleted");
			nextPage = "/member/listMembers.do";
		}
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
