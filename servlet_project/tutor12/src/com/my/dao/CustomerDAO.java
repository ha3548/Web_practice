package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.my.vo.Customer;
import com.my.vo.Post;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
public class CustomerDAO {
	//고객정보: id,pwd,name,buildingno,addr

	public Customer  selectById(String id) throws NotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##ora_user";
			String password = "yeo";
			con = DriverManager.getConnection(url, user, password);
			
			String selectIdSQL = "SELECT * FROM customer WHERE id=?";
			pstmt = con.prepareStatement(selectIdSQL);
			pstmt.setString(1, id);
			
			rs  = pstmt.executeQuery();
			if(rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getString("id"));
				c.setPwd(rs.getString("pwd"));
				c.setName(rs.getString("name"));
				c.setAddr(rs.getString("addr"));
				Post p = new Post();
				p.setBuildingno(rs.getString("buildingno"));
				c.setPost(p);
				return c;
			}
			throw new NotFoundException("아이디가 존재하지 않습니다.");
		//}catch(ClassNotFoundException | SQLException e) {
		}catch(Exception e) {
			throw new NotFoundException(e.getMessage());
		}finally {
			if(rs != null) 
				try {
					rs.close();
				}catch(SQLException e) {}
			if(pstmt != null) 
				try {
				pstmt.close();
				}catch(SQLException e) {}
			if(con != null) 
				try {
					con.close();
				}catch(SQLException e) {}
		}		
	}

	public void insert(Customer c) throws AddException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##ora_user";
			String password = "yeo";
			con = DriverManager.getConnection(url, user, password);
			
			String insertSQL = 
		"INSERT INTO customer(id,pwd,name,buildingno, addr)"
		+ " VALUES (?,?,?,?,?)";
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getPwd());
			pstmt.setString(3, c.getName());
			pstmt.setString(4, c.getPost().getBuildingno());
			pstmt.setString(5, c.getAddr());
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new AddException(e.getMessage());
		}catch(SQLException e) {
			throw new AddException(e.getMessage());
		}finally {		
			if(pstmt != null) 
				try {
				pstmt.close();
				}catch(SQLException e) {}
			if(con != null) 
				try {
					con.close();
				}catch(SQLException e) {}
		}		
	}
}