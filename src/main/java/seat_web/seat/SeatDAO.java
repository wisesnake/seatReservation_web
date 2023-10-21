package seat_web.seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//DB에 접근(Access)하여 생성/조회/수정/삭제(CRUD)
public class SeatDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public SeatDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			//jndi에 접근하기 위해 기본 경로를 지정
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle2");
			//톰캣 context.xml에 설정한 resource의 name값인 jdbc/oracle을 이용해
			//톰캣이 미리 연결한 DataSource를 받아옴
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	// 조회하는 메서드
	List<SeatVO> getSeatList() {
		List<SeatVO> list = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			//DataSource를 이용해 데이터베이스에 연결합니다.
			String query = "select * from seat ";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int seatNo = rs.getInt("seatno");
				int seatNumber = rs.getInt("seatNumber");
				boolean reservationStatus = (rs.getInt("reservationStatus") == 1);
				int reservationNumber = rs.getInt("reservationNumber");
				
				SeatVO vo = new SeatVO();
				vo.setSeatNo(seatNo);
				vo.setSeatNum(seatNumber);
				vo.setReserved(reservationStatus);
				vo.setReservationNum(reservationNumber);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	


	public void changeReservationStatus(int num, int reservationNum) {
		
		
		try {
			con = dataFactory.getConnection();
			String query = "update seat set ";
			query += "reservationStatus = ?, reservationNumber = ? ";
			query += "where seatNo = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, reservationNum);
			pstmt.setInt(3, num);
			//setString은 쿼리문에서 ? 넣은 부분에 순서대로 (인덱싱, 값) 순으로 대입함.
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cancelReservation(int num, int reservationNum) {
		try {
			con = dataFactory.getConnection();
			String query = "update seat set ";
			query += "reservationStatus = ?, reservationNumber = ? ";
			query += "where seatNo = ? and reservationNumber = ?";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, num);
			pstmt.setInt(4, reservationNum);
			//setString은 쿼리문에서 ? 넣은 부분에 순서대로 (인덱싱, 값) 순으로 대입함.
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}
