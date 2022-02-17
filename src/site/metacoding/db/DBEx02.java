package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// dept ���̺��� ��� ������ ����Ͻÿ�.
// SELECT deptno, dname, loc FROM dept
public class DBEx02 {
	public static void main(String[] args) {
		try {
			// 1. connection ���� (���ǻ���) port, ip, id, password, protocol
			Connection conn = DriverManager.getConnection // conn = socket
			("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB����Ϸ�");

			// 2. ���� �޾Ƽ� ��� (ALL:SELECT * FROM emp)
			String sql = "SELECT * FROM dept"; // ���� �����ݷ� �ʿ����.
			PreparedStatement pstmt = conn.prepareStatement(sql); // ���������� ����� ����
			ResultSet rs = pstmt.executeQuery(); // SELECT (flush ����� ����)

			while (rs.next()) {
				System.out.println("deptno : " + rs.getInt("deptno")); // �ڹ�Ÿ��(�÷���)
				System.out.println("dname : " + rs.getString("dname")); // �ڹ�Ÿ��(�÷���)
				System.out.println("loc : " + rs.getString("loc")); // �ڹ�Ÿ��(�÷���)
				System.out.println("==============");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
