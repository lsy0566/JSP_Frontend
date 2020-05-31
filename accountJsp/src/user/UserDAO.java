package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB�� �����ϱ� ���� Ŭ����, �� db�� ���� ȣ��Ʈ �����̳� db�� ������ ���������� ���⿡ �ۼ��Ѵ�. or DB�� �ִ� ���� ���ϱ� ����
public class UserDAO {

	private Connection conn;
	private ResultSet rs;

	public UserDAO() {

		try {
			String dbURL = "jdbc:mysql://localhost:3306/LectureEvaluation?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "mysql";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �α����� ���� db ������ �� result�� ��ȯ���� userLoginAction�� �Ѱ��ܿ� ���� ������ ó���� �ϱ� ����
	public int login(String email, String password) {

		String SQL = "SELECT password FROM USER WHERE email = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(password))
					return 1; // �α��� ����
				else
					return 0; // ��й�ȣ Ʋ��
			}

			return -1; // ���̵� ����
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -2; // �����ͺ��̽� ����
	}

	// ȸ�������� ���� db ����
	public int join(UserDTO user) {
		// email, username, password, phoneNumber, secondPhoneNumber, isMember, isAdmin
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, false, false)";	// ȸ��Ż�𿩺ο� ������ ���δ� �ϴ� false

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getEmail());		// DTO�� ��� �ִ� ������ �ҷ��ͼ� ��� ����
			pstmt.setString(2, user.getUserName());		// �� ? �ȿ� DTO�� �ִ� �����͸� get���� �����ͼ� ��� ��
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getPhoneNumber());
			pstmt.setString(5, user.getSecondPhoneNumber());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1; // ȸ������ ����
	}
	
	// �ϴ� ������ ������� ����
	public String getUserEmail(String userID) {

		String SQL = "SELECT userEmail FROM USER WHERE userID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				return rs.getString(1); // �̸��� �ּ� ��ȯ
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null; // �����ͺ��̽� ����
	}

	
	// ������ �Ⱦ��ٰ� ��������.
	public boolean getUserEmailChecked(String userID) {

		String SQL = "SELECT userEmailChecked FROM USER WHERE userID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				return rs.getBoolean(1); // �̸��� ��� ���� ��ȯ
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // �����ͺ��̽� ����
	}

	// ������� ���̵� ��Ī�ؼ� db�� �ִ� false�� �Ǿ��ִ� emailchecked ���� true�� �ٲ��ִ� �޼���
	public boolean setUserEmailChecked(String userID) {

		String SQL = "UPDATE USER SET userEmailChecked = true WHERE userID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();

			return true; // �̸��� ��� ���� ����

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // �̸��� ��� ���� ����

	}

}
