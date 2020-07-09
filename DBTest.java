package ljh.java.exam01;

//1단계 : 필요한 클래스들을 임포트.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {
	public static void main(String[] args) {
		try { // 2단계 : 드라이버 검색.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 검색 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("드라이버 검색 실패");
		} 

		// 3단계 : 데이터베이스 커넥션 연결.
		Connection conn = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
			conn = DriverManager.getConnection(url,"mytest","mytest");
			System.out.println("데이터베이스 연결 성공");
		}catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("데이터베이스 연결 실패");
		}

		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 4단계, 5단계 : 정적 쿼리
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery("select * from mytable");
			
//			// 4단계, 5단계 : 동적 쿼리
//			pstmt = conn.prepareStatement("select * from mytable where age > ?"); 
//			pstmt.setInt(1, 30);
//			rs = pstmt.executeQuery();
			
			// 화면 출력. 
			while(rs.next()) {
				int num = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt("age");
				String address = rs.getString("address");
				if (address == null) address = " ";
				java.sql.Date date = rs.getDate("birth");
				java.util.Date d = new java.util.Date(date.getTime()); // 자바에서 계산하기 위해 자바의 Date형으로 바꾼다.
				System.out.println(num + "\t" + name + "\t" + age + "\t" + address + "\t" + date.toString() + "\t" + d.toString());
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("쿼리문 오류일 가능성이 높다.");
		} finally {
			// 6단계 : 자원을 반납한다.
			try { if (rs != null) rs.close();} catch (SQLException e) {}
			try { if (stmt != null) stmt.close();} catch (SQLException e) {}
			try { if (pstmt != null) pstmt.close();} catch (SQLException e) {}
			try { if (conn != null) conn.close();} catch (SQLException e) {}
		}
	}
}
