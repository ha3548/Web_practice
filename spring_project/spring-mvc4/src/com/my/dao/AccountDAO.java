package com.my.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
	@Autowired
	private SqlSession sqlSession;
	public void account() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("no","101");
		map.put("amount",10);
		int rowcnt1 = sqlSession.update("com.my.vo.Account.withdraw",map);
		if(rowcnt1 == 0) {
			throw new RuntimeException("출금오류");
		}
		
		map = new HashMap<>();
		map.put("no","102");
		//map.put("no","'999");
		map.put("amount",10);
		int rowcnt2 = sqlSession.update("com.my.vo.Account.deposit",map);
		if(rowcnt2 == 0) {
			throw new RuntimeException("입금오류");
		}
		
	}
}
