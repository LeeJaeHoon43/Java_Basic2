package ljh.java.exam06;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ljh.java.common.ConnUtil;

public class JdbcEx5 {
	public static void main(String[] args) {
		// preparedStatement를 사용하는 경우.
		// sql쿼리에서 실제 값으로 대체될 부분을 ?로 처리한다.
		// ?는 db에서 sql실행 시에 실제 값으로 대체된다.
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO professor ");
		sql.append("VALUES(?, ?, ?, ?, ?, sysdate, ?, ?)");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnUtil.getConnection();
			// PreparedStatement 객체 얻기.
			pstmt = conn.prepareStatement(sql.toString());
			// 파라미터 셋팅.
			// 쿼리의 ? (바인딩 변수)에 대체될 실제값 지정하기.
			pstmt.setInt(1, 9920);
			pstmt.setString(2, "홍길동");
			pstmt.setString(3, "HongGilDong");
			pstmt.setString(4, "시간 강사");
			pstmt.setInt(5, 450);
			pstmt.setInt(6, 40);
			pstmt.setInt(7, 203);
			// 쿼리 실행.
			int i = pstmt.executeUpdate();
			System.out.println(i + "개의 행이 추가되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e2) {}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {}	
		}
	}
}
