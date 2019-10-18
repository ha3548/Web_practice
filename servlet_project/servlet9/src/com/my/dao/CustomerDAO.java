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

//고객정보: id pwd name buildingno addr
public class CustomerDAO {
	// DTO클래스, VO클래스: 자료전달용 객체, 자료를 담고있다
	// 변경될 가능성이 클때 유지보수가 어렵다, 분석설계가 완벽할때 사용
	public Customer selectById(String id) throws NotFoundException {
		// public String[] selectById(String id) {}; -> 변경될 가능성이 클때 사용
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ptmt = null;

		try {
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "c##ora_user";
			String DB_PW = "bong";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String SQL = "select * from customer where id = ?";

		try {
			ptmt = conn.prepareStatement(SQL);
			ptmt.setString(1, id);
			rs = ptmt.executeQuery();
			if (rs.next()) {
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
			throw new NotFoundException("아이디중복확인, 사용가능");
			
		} catch (Exception e) {
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

	};
	
	public Customer insert(Customer c) throws AddException{
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ptmt = null;

		try {
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "c##ora_user";
			String DB_PW = "bong";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String SQL = "insert into customer(id,pwd,name,buildingno,addr) values(?,?,?,?,?)";

		try {
			ptmt = conn.prepareStatement(SQL);
			ptmt.setString(1, c.getId());
			ptmt.setString(2, c.getPwd());
			ptmt.setString(3, c.getName());
			ptmt.setString(4, c.getPost().getBuildingno());
			ptmt.setString(5, c.getAddr());
			ptmt.executeUpdate();
			
			return c;
			
		} catch(Exception e) {
			throw new AddException(e.getMessage());
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
		
	};

}
