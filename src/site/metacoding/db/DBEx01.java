package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBEx01 {

	public static void main(String[] args) {
		try {
			// 1. connection 연결 (세션생성) port, ip, id, password, protocol
			Connection conn = DriverManager.getConnection // conn = socket
			("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			// 2. 버퍼 달아서 통신 (ALL:SELECT * FROM emp)
			String sql = "SELECT empno, ename FROM emp"; // 끝에 세미콜론 필요없음.
			PreparedStatement pstmt = conn.prepareStatement(sql); // 프로토콜이 적용된 버퍼
			ResultSet rs = pstmt.executeQuery(); // SELECT (flush 개념과 같음)
			// pstmt.executeUpdate(); // INSERT, UPDATE, DELETE
			// System.out.println(rs.next()); // 커서 한칸 내리기

			while (rs.next()) {
				System.out.println("empno : " + rs.getInt("empno")); // 자바타입(컬럼명)
				System.out.println("ename : " + rs.getString("ename")); // 자바타입(컬럼명)
				System.out.println("==============");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
