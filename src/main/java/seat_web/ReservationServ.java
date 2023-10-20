package seat_web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reservation")
public class ReservationServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int seatNo = Integer.parseInt(request.getParameter("seatNo"));
		int reservationNum = Integer.parseInt(request.getParameter("reservationNum"));
		System.out.println("받아온 좌석번호 : " + seatNo);
		System.out.println("받아온 전화번호 : " + reservationNum);
		SeatDAO dao = new SeatDAO();
		
		List<SeatVO> list = dao.getSeatList();
		list.stream().forEach((c)->{
		if(seatNo == c.getSeatNo()) {
			if(!c.isReserved()) {
				//reservationstatus가 false일 때 = 빈좌석일 때
				dao.changeReservationStatus(seatNo, reservationNum);
				out.print(seatNo + " 번 좌석이 성공적으로 예약되었습니다.<br>");
				out.print("<a href='reservation.jsp'>예약화면으로 돌아가기</a><br>");
				out.print("<a href='menu.html'>홈으로 돌아가기</a><br>");
			}else if(c.isReserved()) {
				//reservationstatus가 true일 때 = 이미 예약석일 때
				out.print("죄송합니다." + seatNo + " 번 좌석은 이미 예약석입니다.<br>");
				out.print("<a href='reservation.jsp'>예약화면으로 돌아가기</a><br>");
				out.print("<a href='menu.html'>홈으로 돌아가기</a><br>");
			}
		}
		}
		);		
		
		
		
		SeatVO vo = list.get(seatNo-1);
		
		System.out.println(vo.getSeatNo() + "번 좌석이 "+vo.getReservationNum()+"예약번호로 "+vo.isReserved()+" 예약상태임을 확인함");
		
		
	}

}
