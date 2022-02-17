package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// PrepareStatement 변수 바인딩 하기
public class DBEx03 {

	public static void login(String username, String password) {
		try {
			// 1. connection 연결 (세션생성)
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			// 2. 버퍼 달아서 통신
			String sql = "SELECT * FROM userTbl WHERE username = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql); // 프로토콜이 적용된 버퍼
			// ?의 시작번지는 1이다.
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery(); // SELECT (flush 개념과 같음)

			while (rs.next()) {
				// System.out.println("로그인 되었습니다.");
				Session.isLogin = true; // 어느 파일에서든 확인 가능
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		login("ssar", "1234");

		System.out.println(Session.isLogin);
	}

}
