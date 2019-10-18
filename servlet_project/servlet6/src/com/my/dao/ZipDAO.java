package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.my.exception.NotFoundException;

public class ZipDAO {
	public List<Map<String,String>> selectByDoro(String doro) throws NotFoundException {
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		String result="["; 
		try {
			String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			String DB_USER = "c##ora_user";
			String DB_PW = "bong";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		String SQL = "SELECT zipcode,  \r\n" + 
		        "buildingno, "+
				"sido||' ' \r\n" + 
				"|| sigungu ||NVL2(sigungu,' ', '')\r\n" + 
				"|| eupmyun ||NVL2(eupmyun,' ', '')\r\n" + 
				"|| doro ||' ' \r\n" + 
				"|| building1\r\n" + 
				"|| DECODE(building2,'0', '', '-'||building2) ||' ' \r\n" + 
				"|| '('|| dong || ri || DECODE(building, '', '', ',' ||building) ||')'\r\n" + 
				" AS addrdoro,"+
				"sido ||' ' \r\n" + 
				"|| sigungu ||NVL2(sigungu,' ', '')\r\n" + 
				"|| eupmyun ||NVL2(eupmyun,' ', '')\r\n" + 
				"|| dong || ri ||' ' ||  zibun1 || DECODE(zibun2, '0', '',  '-'|| zibun2)    ||' ' || DECODE(building, '', '', '(' ||building ||')')     "
				+ " AS addrzibun  \r\n" + 
				"FROM post\r\n" + 
				"WHERE (doro || ' ' || building1 || DECODE(building2,'0', '', '-'||building2)) LIKE ? ";
		
		try {
			ptmt = conn.prepareStatement(SQL);
			ptmt.setString(1, "%" + doro + "%");
			rs  = ptmt.executeQuery();
			int cnt = 0;
			while(rs.next()) { 
				String zipcode = rs.getString("zipcode");
				String addrdoro = rs.getString("addrdoro");
				String addrzibun = rs.getString("addrzibun");
				String buildingno = rs.getString("buildingno");
				if(cnt != 0) {
					result += ",";
				}
				
				result += "{\"zipcode\":\""+ zipcode +"\",\"addrdoro\":\""+ addrdoro+"\",\"addrzibun\":\"" + addrzibun + "\", \"buildingno\":\""+ buildingno+"\"}";
				cnt++;
			}
			
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
		result +="]";
		
		return null;//자료형맞춰주기!!!!!!!
	};
}
