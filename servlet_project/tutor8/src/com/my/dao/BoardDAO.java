package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.my.exception.NotFoundException;
import com.my.vo.Board;

public class BoardDAO {
	public int count() throws NotFoundException{
		Connection con = null;		PreparedStatement pstmt = null;		ResultSet rs = null;		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##ora_user";
			String password = "yeo";
			con = DriverManager.getConnection(url, user, password);
			String selectCountSQL = "SELECT COUNT(*)  FROM board";
			pstmt = con.prepareStatement(selectCountSQL);			
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);			
		}catch(ClassNotFoundException| SQLException e) {
			throw new NotFoundException(e.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {}
			}
			if(con != null) {
				try {			
					con.close();
				}catch(SQLException e) {}
			}
		}
	}
	public List<Board> select(int startRow, int endRow) throws NotFoundException{
		List<Board> list = new ArrayList<Board>();
		Connection con = null;		PreparedStatement pstmt = null;		ResultSet rs = null;		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##ora_user";
			String password = "yeo";
			con = DriverManager.getConnection(url, user, password);
			String selectSQL = "SELECT a.*\r\n" + 
					"FROM\r\n" + 
					" (SELECT rownum rn, level, board.*\r\n" + 
					"  FROM board \r\n" + 
					"  START WITH parent_no IS NULL \r\n" + 
					"  CONNECT BY PRIOR board_no = parent_no\r\n" + 
					"  ORDER SIBLINGS BY board_no DESC) a\r\n" + 
					"WHERE  a.rn BETWEEN ? AND ?";
			pstmt = con.prepareStatement(selectSQL);//SQL구문 송신			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();//SQL구문 실행			
			while(rs.next()) {
				int board_no = rs.getInt("board_no");
				int parent_no = rs.getInt("parent_no");
				int level = rs.getInt("level");
				String board_subject = rs.getString("BOARD_SUBJECT");
				String board_content = rs.getString("BOARD_CONTENT");
				String board_writer = rs.getString("BOARD_WRITER");
				Date board_time = rs.getDate("BOARD_TIME");
				String board_pwd = rs.getString("BOARD_PWD");
				Board board = new Board(board_no, parent_no, level, 
						board_subject, board_writer, board_content, board_time, board_pwd);
				list.add(board);
			}
			if(list.size() == 0) {
				throw new NotFoundException("게시목록이 없습니다.");
			}
		}catch(ClassNotFoundException| SQLException e) {
			throw new NotFoundException(e.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e) {}
			}
			if(con != null) {
				try {			
					con.close();
				}catch(SQLException e) {}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		try {
			System.out.println(dao.count()); //7
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

}
