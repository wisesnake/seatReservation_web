package seat_web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// Value Object, 밸류값 객체.
@Data // @Getter @Setter @ToString 대체
@NoArgsConstructor
@AllArgsConstructor
public class SeatVO {
	private int seatNo; // 좌석 PK
	private int seatNum; // 좌석번호
	private boolean reserved; // 예약여부
	private int reservationNum; // 예약번호
}
