package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBEx06 {

	public static void main(String[] args) {
		try {
			// 1. connection 연결 (세션생성) port, ip, id, password, protocol
			Connection conn = DriverManager.getConnection // conn = socket
			("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			// 2. 버퍼 달아서 통신 (ALL:SELECT * FROM emp)
			String sql = "INSERT INTO userTbl(id, username, password, gender) VALUES(?,?,?,?)"; // 끝에 세미콜론 필요없음.
			PreparedStatement pstmt = conn.prepareStatement(sql); // 프로토콜이 적용된 버퍼
			pstmt.setInt(1, 7); // 물음표의 순서, 값
			pstmt.setString(2, "there");
			pstmt.setString(3, "1234");
			pstmt.setString(4, "남");
			// 에러 : -1, 성공 : 수행된(생성, 삭제, 수정) row 개수, 아무 변화가 없으면 0
			int result = pstmt.executeUpdate(); // DELETE, UPDATE, INSERT (내부에 commit 존재)

			if (result > 0) {
				System.out.println("성공"); // 1
			} else {
				System.out.println("실패"); // 0
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
