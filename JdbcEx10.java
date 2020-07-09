package ljh.java.exam10;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import ljh.java.common.ConnUtil;

public class JdbcEx10 {
	public static void main(String[] args) throws IOException{
		Properties pro = new Properties();
		pro.load(new FileInputStream("src/department.properties")); // 이클립스라면.
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnUtil.getConnection();
			// department.properties에 정의된 sql을 가져와서 preparedstatement 만들기.
			pstmt = conn.prepareStatement(pro.getProperty("department_insert"));
			// parameter(바인딩 변수에 값 할당하기) 세팅하기.
			pstmt.setInt(1, 100);
			pstmt.setString(2, "산업공학과");
			pstmt.setInt(3, 200);
			pstmt.setString(4, "5호관");
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
