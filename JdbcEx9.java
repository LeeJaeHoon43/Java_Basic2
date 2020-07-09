package ljh.java.exam9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import ljh.java.common.ConnUtil;

public class JdbcEx9 {
	public static void main(String[] args) throws Exception {
		// keyboard와 연결된 스트림 얻기.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("SQL 입력 : ");
		String sql = br.readLine();
		Connection conn = ConnUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		// 쿼리 수행 결과 집합의 부가 정보를 가지고 있는 ResultSetMetaData 얻기.
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println("컬럼의 갯수 : " + rsmd.getColumnCount());

		// 컬럼 이름.
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.println(rsmd.getColumnName(i) + "\t");	
		}
		System.out.println();

		// 컬럼 타입.
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.println(rsmd.getColumnTypeName(i) + "\t");
		}
		System.out.println();

		int columnCount = rsmd.getColumnCount();
		while(rs.next()) {
			for (int colnum = 1; colnum < columnCount; colnum++) {
				int colType = rsmd.getColumnType(colnum);
				switch (colType) {
				case Types.NUMERIC:
					System.out.print(rs.getInt(colnum) + "\t");
					break;
				case Types.VARCHAR:
					System.out.print(rs.getString(colnum) + "\t");
					break;
				case Types.TIMESTAMP:
					System.out.print(rs.getDate(colnum) + "\t");
					break;
				}
			}
			System.out.println();
		}
		try {
			if (br != null) {
				br.close();
			}
		} catch (Exception e) {}
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
