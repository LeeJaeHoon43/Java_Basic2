package ljh.java.exam08;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ljh.java.common.ConnUtil;

public class JdbcEx7 {
	public static void main(String[] args) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT a.name, a.profno, a.position, b.dname ");
		sql.append("FROM professor a, department b "); // , <-> JOIN
		sql.append("WHERE a.deptno = b.deptno "); // WHERE <-> ON
		sql.append("AND a.deptno = ? "); // AND <-> WHERE
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			// ?(바인딩 변수)에 대체할 실제값 지정.
			pstmt.setInt(1, 203);
			// 쿼리 실행시켜서 결과 집합 얻기.
			rs = pstmt.executeQuery();
			// 결과집합에서 값 추출하기.
			while(rs.next()) {
				System.out.print(rs.getString("name") + "\t");
				System.out.print(rs.getInt("profno") + "\t");
				System.out.print(rs.getString("dname") + "\t");
				System.out.println(rs.getString("position") + "\t");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {}
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
