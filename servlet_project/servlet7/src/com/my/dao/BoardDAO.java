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
	public List<Board> select() throws NotFoundException{
		List<Board> list = new ArrayList<Board>();
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ptmt = null;

		try {
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "c##ora_user";
			String DB_PW = "bong";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
		
			
			String SQL = "SELECT a.*\r\n" + 
					"FROM\r\n" + 
					"    (SELECT rownum rn, level, board.*\r\n" + 
					"    FROM board\r\n" + 
					"    WHERE rownum BETWEEN 1 AND 10\r\n" + 
					"    START WITH parent_no IS NULL\r\n" + 
					"    CONNECT BY PRIOR board_no = parent_no\r\n" + 
					"    ORDER SIBLINGS BY board_no DESC) a\r\n" + 
					"WHERE a.rn BETWEEN 1 AND 10";
			
			ptmt = conn.prepareStatement(SQL);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				int board_no = rs.getInt("board_no");
				int parent_no = rs.getInt("parent_no");
				int level = rs.getInt("level");
				String board_subject = rs.getString("board_subject");
				String board_writer = rs.getString("board_writer");
				String board_content = rs.getString("board_content");
				Date board_time = rs.getDate("board_time");
				String board_pwd = rs.getString("board_pwd");
				
				Board board = new Board(board_no, parent_no, level, board_subject, board_writer, board_content, board_time, board_pwd);
				list.add(board);
			}
			
			if(list.size()==0) {
				throw new NotFoundException("게시물목록이 없습니다.");
			}
		
		} catch(Exception e) {
			throw new NotFoundException(e.getMessage());
				
		} finally {
			try {
				if (ptmt != null && !ptmt.isClosed())
					ptmt.close();
				if (rs != null && !rs.isClosed())
					ptmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	};
}
