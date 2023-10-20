package seat_web.members;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			//jndi에 접근하기 위해 기본 경로를 지정
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			//톰캣 context.xml에 설정한 resource의 name값인 jdbc/oracle을 이용해
			//톰캣이 미리 연결한 DataSource를 받아옴
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> getMembersList() {
		List<MemberVO> list = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			//DataSource를 이용해 데이터베이스에 연결합니다.
			String query = "select * from member ";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int userType = rs.getInt("usertype");
				int userNo = rs.getInt("userNo");
				String user_id = rs.getString("user_id");
				String user_pw = rs.getString("user_pw");
				String phoneNum = rs.getString("phone");
				String name = rs.getString("name");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO();
				vo.setUserType(userType);
				vo.setUserNo(userNo);
				vo.setUser_id(user_id);
				vo.setUser_pw(user_pw);
				vo.setPhoneNum(phoneNum);
				vo.setName(name);
				vo.setJoinDate(joinDate);
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
	
	public void addMember(MemberVO custVO) {
		int userNo = giveUserNo();
		System.out.println(userNo);
		try {
			Connection con = dataFactory.getConnection();
			int userType = 2;
			String id = custVO.getUser_id();
			String pwd = custVO.getUser_pw();
			String phoneNum = custVO.getPhoneNum();
			String query = "insert into member";
			String name = custVO.getName();
			query += " (usertype,userNo,user_id,user_pw,phone,name)";
			query += " values(?,?,?,?,?,?)";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userType);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, id);
			pstmt.setString(4, pwd);
			pstmt.setString(5, phoneNum);
			pstmt.setString(6, name);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int giveUserNo() {
		System.out.println("메서드 호출");
		int userNo = 0;
		try {
			Connection con = dataFactory.getConnection();
			String query = "SELECT MAX(userNo) + 1 FROM member";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			userNo=rs.getInt("userNo");
			System.out.println("다음 회원등록 유저번호 : " + userNo);
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userNo;
	}
	
	public void modUserInfo(MemberVO memberVO) {
		
	}

	public MemberVO searchMemberInfo(String _id) {
		MemberVO userInfo = null;
		try {
			Connection con = dataFactory.getConnection();
			String query = "select * from member where user_id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int userType = rs.getInt("usertype");
			int userNo = rs.getInt("userNo");
			String user_id = rs.getString("user_id");
			String user_pw = rs.getString("user_pw");
			String phoneNum = rs.getString("phone");
			String name = rs.getString("name");
			Date joinDate = rs.getDate("joinDate");
			
			userInfo = new MemberVO(userNo, userType, name, user_id, user_pw, phoneNum, joinDate);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	
}
