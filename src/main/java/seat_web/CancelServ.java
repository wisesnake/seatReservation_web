package seat_web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cancel")
public class CancelServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int seatNo = Integer.parseInt(request.getParameter("seatNo"));
		int reservationNum = Integer.parseInt(request.getParameter("reservationNum"));
		System.out.println("받아온 좌석번호 : " + seatNo);
		System.out.println("받아온 전화번호 : " + reservationNum);

		SeatDAO dao = new SeatDAO();

		List<SeatVO> list = dao.getSeatList();
		list.stream().forEach((c) -> {
			if (seatNo == c.getSeatNo()) {
				if (c.isReserved()) {
					if (reservationNum == c.getReservationNum()) {
						dao.cancelReservation(seatNo, reservationNum);
						out.print(seatNo + " 번 좌석의 예약이 취소되었습니다.<br>");
						out.print("<a href='menu.html'>홈으로 돌아가기</a><br>");
					} else {
						out.print("예약번호가 틀립니다. 예약번호는 전화번호 뒷자리 4자리입니다.<br>");
						out.print("<a href='menu.html'>홈으로 돌아가기</a><br>");
					}
				} else {
					out.print("해당 좌석은 빈 좌석입니다. 좌석번호를 다시 확인하시기 바랍니다.");
					out.print("<a href='menu.html'>홈으로 돌아가기</a><br>");
				}
			}

		});

		SeatVO vo = list.get(seatNo - 1);

		System.out.println(
				vo.getSeatNo() + "번 좌석의 상태"  + vo.isReserved() );

	}

}
