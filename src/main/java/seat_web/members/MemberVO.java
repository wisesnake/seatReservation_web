package seat_web.members;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// Value Object, 밸류값 객체.
@Data // @Getter @Setter @ToString 대체
@NoArgsConstructor
@AllArgsConstructor

public class MemberVO {
	private int userNo;
	private int userType;
	private String name;
	//1=관리자,2=일반,3=VIP
	private String user_id;
	private String user_pw;
	private String phoneNum;
	private Date joinDate;
	

	public MemberVO(String name, String user_id, String user_pw, String phoneNum) {
		this.name = name;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.phoneNum = phoneNum;
	}
	
	
}
