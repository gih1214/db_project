package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBEx06 {

	public static void main(String[] args) {
		try {
			// 1. connection ���� (���ǻ���) port, ip, id, password, protocol
			Connection conn = DriverManager.getConnection // conn = socket
			("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB����Ϸ�");

			// 2. ���� �޾Ƽ� ��� (ALL:SELECT * FROM emp)
			String sql = "INSERT INTO userTbl(id, username, password, gender) VALUES(?,?,?,?)"; // ���� �����ݷ� �ʿ����.
			PreparedStatement pstmt = conn.prepareStatement(sql); // ���������� ����� ����
			pstmt.setInt(1, 7); // ����ǥ�� ����, ��
			pstmt.setString(2, "there");
			pstmt.setString(3, "1234");
			pstmt.setString(4, "��");
			// ���� : -1, ���� : �����(����, ����, ����) row ����, �ƹ� ��ȭ�� ������ 0
			int result = pstmt.executeUpdate(); // DELETE, UPDATE, INSERT (���ο� commit ����)

			if (result > 0) {
				System.out.println("����"); // 1
			} else {
				System.out.println("����"); // 0
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
