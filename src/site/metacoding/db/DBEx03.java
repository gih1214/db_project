package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// PrepareStatement ���� ���ε� �ϱ�
public class DBEx03 {

	public static void login(String username, String password) {
		try {
			// 1. connection ���� (���ǻ���)
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB����Ϸ�");

			// 2. ���� �޾Ƽ� ���
			String sql = "SELECT * FROM userTbl WHERE username = ? AND password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql); // ���������� ����� ����
			// ?�� ���۹����� 1�̴�.
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery(); // SELECT (flush ����� ����)

			while (rs.next()) {
				// System.out.println("�α��� �Ǿ����ϴ�.");
				Session.isLogin = true; // ��� ���Ͽ����� Ȯ�� ����
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
