package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// dept 테이블의 모든 내용을 출력하시오.
// SELECT deptno, dname, loc FROM dept
public class DBEx05 {
	public static void main(String[] args) {
		try {
			// 1. connection 연결 (세션생성) port, ip, id, password, protocol
			Connection conn = DriverManager.getConnection // conn = socket
			("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			// 2. 버퍼 달아서 통신 (ALL:SELECT * FROM emp)
			String sql = "SELECT deptno, dname, loc FROM dept"; // 끝에 세미콜론 필요없음.
			PreparedStatement pstmt = conn.prepareStatement(sql); // 프로토콜이 적용된 버퍼
			ResultSet rs = pstmt.executeQuery(); // SELECT (flush 개념과 같음)

			List<Dept> depts = new ArrayList<>();
			while (rs.next()) {
				Dept dept = new Dept(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc"));
				depts.add(dept);
			}

			// for each 문 사용해서 출력
			for (Dept dept : depts) {
				System.out.println("deptno : " + dept.getDeptno());
				System.out.println("dname : " + dept.getDname());
				System.out.println("loc : " + dept.getLoc());
				System.out.println("==============");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
