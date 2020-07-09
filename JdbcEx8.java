package ljh.java.exam09;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ljh.java.common.ConnUtil;

public class JdbcEx8 {
	public static void main(String[] args) {
		// transaction : 논리적인 작업단위.
		// insert, delete, update 등의 작업들을 하나의 논리적인 작업단위로 묶어서 쿼리 실행 시 모든 작업이 정상 처리된 경우에는
		// commit을 실행해서 db에 반영하고, 쿼리 실행 중 하나라도 정상처리 되지 않는 경우 rollback을 실행해서 작업 단위 내 모든 작업을 취소한다.
		StringBuffer sql1 = new StringBuffer();
		sql1.append("INSERT INTO department ");
		sql1.append("VALUES(?,?,?,?) ");
		StringBuffer sql2 = new StringBuffer();
		sql2.append("UPDATE department ");
		sql2.append("SET dname = ?, loc = ? ");
		sql2.append("WHERE deptno = ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// transaction 시작.
			// 하나의 논리적인 작업 단위 시작
			conn = ConnUtil.getConnection();
			// autoCommit기능 비활성화 시키기.
			conn.setAutoCommit(false);
			// 1번 작업 시작.
			pstmt = conn.prepareStatement(sql1.toString());
			pstmt.setInt(1, 255);
			pstmt.setString(2, "핵물리학과");
			pstmt.setInt(3, 200);
			pstmt.setString(4, "9호관");
			pstmt.executeUpdate();	
			// 1번 작업 종료.
			// 2번 작업 시작.
			pstmt = conn.prepareStatement(sql2.toString());
			pstmt.setString(1, "생명공학과");
			pstmt.setString(2, "8호관");
			pstmt.setInt(3, 255);
			pstmt.executeUpdate();
			// 2번 작업 종료.
			// 쿼리가 정상적으로  실행된 경우  db에 반영.
			conn.commit();
			System.out.println("db 반영 성공");			
		} catch (SQLException e) {
			try {
				conn.rollback();
				System.out.println("db 반영 실패");
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
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
			// transaction 종료
		}
	}
}
