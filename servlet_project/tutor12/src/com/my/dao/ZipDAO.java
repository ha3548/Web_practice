package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.exception.NotFoundException;

public class ZipDAO {
	public List<Map<String,String>> selectByDoro(String doro) throws NotFoundException{
		List<Map<String,String>> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##ora_user";
			String password = "yeo";
			con = DriverManager.getConnection(url, user, password);
			String selectDoroSQL = "SELECT zipcode,  \r\n" + 
			        "buildingno, "+
					"sido||' ' \r\n" + 
					"|| sigungu ||NVL2(sigungu,' ', '')\r\n" + 
					"|| eupmyun ||NVL2(eupmyun,' ', '')\r\n" + 
					"|| doro ||' ' \r\n" + 
					"|| building1\r\n" + 
					"|| DECODE(building2,'0', '', '-'||building2) ||' ' \r\n" + 
					"|| '('|| dong || ri || DECODE(building, '', '', ',' ||building) ||')'\r\n" + 
					" AS addrdoro" + //"||chr(13)||chr(10) \r\n" +
					","+
					"sido ||' ' \r\n" + 
					"|| sigungu ||NVL2(sigungu,' ', '')\r\n" + 
					"|| eupmyun ||NVL2(eupmyun,' ', '')\r\n" + 
					"|| dong || ri ||' ' ||  zibun1 || DECODE(zibun2, '0', '',  '-'|| zibun2)    ||' ' || DECODE(building, '', '', '(' ||building ||')')     "
					+ " AS addrzibun  \r\n" + 
					
					"FROM post\r\n" + 
					"WHERE (doro || ' ' || building1 || DECODE(building2,'0', '', '-'||building2)) LIKE ? ";
			pstmt = con.prepareStatement(selectDoroSQL);
			pstmt.setString(1, "%" + doro + "%");
			rs  = pstmt.executeQuery();
			while(rs.next()) { 
				HashMap<String,String> map = new HashMap<>();
				String zipcode = rs.getString("zipcode");
				String addrdoro = rs.getString("addrdoro");
				String addrzibun = rs.getString("addrzibun");
				String buildingno = rs.getString("buildingno");
				map.put("zipcode", zipcode);
				map.put("addrdoro", addrdoro);
				map.put("addrzibun", addrzibun);
				map.put("buildingno", buildingno);
				list.add(map);
			}
			if(list.size() == 0) {
				throw new NotFoundException("검색결과가 없습니다.");
			}
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();		
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
		return list;
	}
}
