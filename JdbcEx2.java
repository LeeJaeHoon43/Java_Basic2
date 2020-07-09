package ljh.java.exam03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcEx2 { // UPDATE 예제.
	public static void main(String[] args) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE department ");
		sql.append("set dname = '전자공학과' ");
		sql.append("WHERE deptno = 203 ");
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "mytest", "mytest");
			stmt = conn.createStatement();
			// UPDATE문은 executeUpdate()를 사용한다.
			int i = stmt.executeUpdate(sql.toString());
			System.out.println(i + "개의 행이 변경되었습니다.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
