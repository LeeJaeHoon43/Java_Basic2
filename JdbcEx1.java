package ljh.java.exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcEx1 { // INSERT 예제
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try { 
			// jdbc 드라이브 메모리에 로드하기.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 객체 얻기.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "mytest", "mytest");
			// Statement 객체 얻기.
			stmt = conn.createStatement();
			// SQL 작성하기.
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO department ");
			sql.append("VALUES (203,'컴퓨터공학과', 200, '7호관')");
			// 쿼리 실행시키기.
			int result = stmt.executeUpdate(sql.toString());
			System.out.println(result + "개의 행이 추가되었습니다.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원 반납 처리.
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
