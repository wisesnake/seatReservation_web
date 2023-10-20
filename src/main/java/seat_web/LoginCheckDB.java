package seat_web;

import java.util.List;

import seat_web.members.*;

public class LoginCheckDB {

	public static MemberVO login(String id, String pwd) {
		System.out.println("로그인 시도");
		System.out.println(id + " : " + pwd);
		MemberDAO dao = new MemberDAO();
		List<MemberVO> userList = dao.getMembersList();
		MemberVO foundUser = null;

		for (int i = 0; i < userList.size(); i++) {
			System.out.println(i + "번째 유저까지 찾는중");
			MemberVO vo = userList.get(i);
			if (vo.getUser_id().equals(id)) {
				System.out.println(id + "유저 아이디 발견");
				if (vo.getUser_pw().equals(pwd)) {
					System.out.println("아이디, 비밀번호가 일치: " + vo.getUser_id());
					System.out.println("로그인을 성공하였습니다.");
					foundUser = vo;
					break;
				} else {
					System.out.println("유저가 입력한 비밀번호 :" + pwd + "DB 비밀번호 :" + vo.getUser_pw());
				}
			} else {
				System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
				foundUser = null;
			}
		}
		return foundUser;
	}
}
