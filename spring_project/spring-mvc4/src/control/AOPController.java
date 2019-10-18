package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dao.AccountDAO;

@Controller
public class AOPController {
// 	controller가 dao 작업을 직접함, test는 이런식으로 간단하게 진행할 수 있다

//	private DataSource dataSource;
	private AccountDAO dao;
	
	//@Transactional(propagation = Propagation.REQUIRED)	// 선언적 트랜잭션의 annotation
	@GetMapping("/account")
	@ResponseBody	// view단을 안거치고 controller가 직접 응
	public String account(){
		dao.account();
		return "계좌이체";
	}
/*		// service.XX();
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			// 출금
			String sql1 = "UPDATE account SET balance=balance-10 WHERE no=?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setString(1, "101");// 101번 계좌에서 10원 출금
			// 처리건수
			int rowcnt1 = pstmt1.executeUpdate();// 성공, AutoCommit

			// 입금
			String sql2 = "UPDATE account SET balance=balance+10 WHERE no=?";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, "999");// 102번 계좌에 20원 입금
			int rowcnt2 = pstmt2.executeUpdate();// 무시, 수행되지않음

			if (rowcnt1 == 1 && rowcnt2 == 1) {
				con.commit();	//입금&출금 둘다 성공인 경우만 commit
			} else {
				con.rollback();	//하나라도 실패시 rollback
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return "계좌이체";
	}
*/
	
}
