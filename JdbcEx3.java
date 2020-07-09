package ljh.java.exam04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcEx3 { // DELETE 예제.
	public static void main(String[] args) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE department ");
		sql.append("WHERE dname = '전자공학과' ");
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "mytest", "mytest");
			stmt = conn.createStatement();
			int i = stmt.executeUpdate(sql.toString());
			System.out.println(i + "개의 행이 삭제되었습니다.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// Connection, Statement 자원 반납 처리.
			try {
				if (stmt != null) {
					stmt.close();
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
