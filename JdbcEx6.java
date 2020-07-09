package ljh.java.exam07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ljh.java.common.ConnUtil;

public class JdbcEx6 {
	public static void main(String[] args) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE professor ");
		sql.append("SET sal = ? ");
		sql.append("WHERE name = ? ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, 500);
			pstmt.setString(2, "홍길동");
			int i = pstmt.executeUpdate();
			System.out.println(i + "개의 행이 변경되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
