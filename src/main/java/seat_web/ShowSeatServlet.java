package seat_web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//컨트롤러 역할. 컨트롤러 역할을 하게 하기 위해 MemberServlet 안에
//연결 객체(MemberDAO)가 필요함.
@WebServlet("/showseat")
public class ShowSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		SeatDAO dao = new SeatDAO();
		PrintWriter out = response.getWriter();

		List<SeatVO> seatList = dao.getSeatList();
		int row = 16;
		int col = 5;
		int cellIdx = 1;
		int voIdx = 0;
		SeatVO vo = null;
//		vo = (SeatVO) seatList.get();
		
		out.print("<html>" + "<head>" + "<style>" + "</style>" + "</head>" + "<body>");
		out.print("<table border='1' style='text-align:center'>");
		out.print("<tr><td colspan='5'> 버스 앞면 </td></tr>");

	    for (int i = 0; i < row; i++) {
	        out.print("<tr>");
	        if (i == 0) {
	            //첫번째 열일때는 5열중 3번째 열에서 rowspan 걸어야 하므로.
	            for (int idx = 0; idx < col; idx++) {
	                if (idx == 2) {
	                    out.print("<td width='50px' rowspan='16'>");
	                } else {
	                    out.print("<td width='50px'>" + cellIdx + "</td>");
	                    cellIdx++;
	                }
	            }
	        } else {
	            //두번째열부터는 4열씩 생성하여 복도있는 차량모양 완성.
	            for (int j = 0; j < col - 1; j++) {
	                if (i % 2 == 1) {
	                    vo = seatList.get(voIdx); // voIdx는 루프가 시작될 때마다 초기화
	                    System.out.println(vo);
	                    if (vo.isReserved()) {
	                        out.print("<td width='60px' height='40px' bgcolor='yellow'>예약</td>");
	                    } else {
	                        out.print("<td width='60px' height='40px' bgcolor='green'>빈좌석</td>");
	                    }
	                    voIdx++;
	                } else {
	                    out.print("<td width='50px'>" + cellIdx + "</td>");
	                    cellIdx++;
	                }
	            }
	        }
	        out.print("</tr>");
	    }

		out.print("</table></body></html>");
		out.print("<a href='/seat_web/menu.html'>초기 화면으로</a>");
	}

}
