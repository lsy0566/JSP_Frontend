package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DB에 접근하기 위한 클래스, 즉 db에 연결 호스트 설정이나 db에 실행할 쿼리문들을 여기에 작성한다. or DB에 있는 값과 비교하기 위함
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

	// 로그인을 위한 db 정보와 비교 result의 반환값을 userLoginAction에 넘겨줌에 따라 나머지 처리를 하기 위함
	public int login(String email, String password) {

		String SQL = "SELECT password FROM USER WHERE email = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(password))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 틀림
			}

			return -1; // 아이디 없음
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -2; // 데이터베이스 오류
	}

	// 회원가입을 위한 db 연동
	public int join(UserDTO user) {
		// email, username, password, phoneNumber, secondPhoneNumber, isMember, isAdmin
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, false, false)";	// 회원탈퇴여부와 관리자 여부는 일단 false

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getEmail());		// DTO에 담겨 있는 정보를 불러와서 담기 위함
			pstmt.setString(2, user.getUserName());		// 즉 ? 안에 DTO에 있는 데이터를 get으로 가져와서 담는 것
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getPhoneNumber());
			pstmt.setString(5, user.getSecondPhoneNumber());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1; // 회원가입 실패
	}
	
	// 일단 지금은 사용하지 않음
	public String getUserEmail(String userID) {

		String SQL = "SELECT userEmail FROM USER WHERE userID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				return rs.getString(1); // 이메일 주소 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null; // 데이터베이스 오류
	}

	
	// 지금은 안쓴다고 생각하자.
	public boolean getUserEmailChecked(String userID) {

		String SQL = "SELECT userEmailChecked FROM USER WHERE userID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				return rs.getBoolean(1); // 이메일 등록 여부 반환
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // 데이터베이스 오류
	}

	// 원래라면 아이디를 매칭해서 db에 있는 false로 되어있는 emailchecked 값을 true로 바꿔주는 메서드
	public boolean setUserEmailChecked(String userID) {

		String SQL = "UPDATE USER SET userEmailChecked = true WHERE userID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();

			return true; // 이메일 등록 설정 성공

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; // 이메일 등록 설정 실패

	}

}
