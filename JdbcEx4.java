package ljh.java.exam05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcEx4 { // SELECT 예제.
	public static void main(String[] args) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT deptno, dname, college, loc");
		sql.append(" FROM department ");
		Connection conn = null;
		Statement stmt = null;
		// SELECT 쿼리의 수행결과 집합과 결과 집합에서 데이터를 추출할 수 있는 메서드를 가지고 있는 객체.
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "mytest", "mytest");
			stmt = conn.createStatement();
			// 실행하고자 하는 sql이 SELECT 쿼리일 경우 쿼리실행의 결과 집합을 리턴 받을 수 있는 executeQuery()를 사용한다.
			rs = stmt.executeQuery(sql.toString());
			// resultset객체로부터 데이터 추출하기.
			// resultset의 next()메소드는 커서를 다음 행으로 이동시킴.
			// 이동된 위치에 row가 존재한다면 true, 존재하지 않으면 false를 반환.
			// rs.next()가 참인 동안, 즉 결과집합에 행이 존재하는 동안 ResultSet으로부터 데이터 추출.
			while(rs.next()) {
				// 커서가 위치한 row의 각각의 column에서 값 추출하기.
				// resultset의 get___(컬럼위치) , get____(컬럼이름)
				int i = rs.getInt(1); // rs.getInt("deptno");
				String s1 = rs.getString(2);
				int j = rs.getInt("college");
				String s2 = rs.getString("loc");
				System.out.println(i + "\t" + s1 + "\t" + j + "\t" + s2);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {}
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
