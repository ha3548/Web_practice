import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.vo.Customer;

public class Test {

	public static void main(String[] args) {
		String resource = "mybatis-config.xml";
		//cmd에서는 mybatis1>javac -d bin src \Test.java
		//이클립스는 mybatis1>java -cp bin Test
		//따라서, 현재경로(기본경로)는 mybatis1를 뜻함
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);//bin폴더(클래스패스)에서 파일을 찾는다.
			//클래스패스와 다른 위치에서 자원을 로드하는 것을 좀더 쉽게 해주는 'Resources'라는 유틸성 클래스를 가지고 있다.
			
			//inputStream = new FileInputStream(resource);
			//위처럼 파일 경로나 file:// URL로부터 만들어진 InputStream인스턴스를 사용할 수도 있다.
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			//Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
			
//			-----UPDATE-----
/*			session.update("com.my.vo.Customer.updateName", "id1");
			HashMap<String, String> map = new HashMap<>();
			map.put("id","id1");
			map.put("name","엘줴엠");
			session.update("com.my.vo.Customer.updateName", map);
			session.commit();
			session.close();*/
			
//			-----INSERT-----
/*			Customer c = new Customer();
			c.setId("idtest");
			c.setPwd("ptest");
			c.setName("name테스트");
			c.setAddr("상세주소1");
			session.insert("com.my.vo.Customer.insert", c);
			session.commit();
			session.close();*/
			
//			-----DELETE-----
/*			session.delete("com.my.vo.Customer.delete", "idtest");
			session.commit();
			session.close();*/
			
//			-----SELECT-----
/*			Customer c = session.selectOne("com.my.vo.Customer.selectById", "id1");
			System.out.println(c.getName() + ":" + c.getPwd());
			
			int cnt = session.selectOne("com.my.vo.Customer.selectByCount");
			System.out.println("총 고객수: " + cnt);
			
			HashMap map = session.selectOne("com.my.vo.Customer.selectGroup");
			System.out.println(map.get("C1") + ":" + map.get("C2")); //※오라클은 MAP의 KEY(헤딩)를 무조건 대문자로 표기한다!!
			
			List<Customer> list = session.selectList("com.my.vo.Customer.selectAll");
			System.out.println("총  고객 행수:" + list.size());
			for(Customer c1:list) {
				System.out.println(c1.getId() + ":" + c1.getName());
			}*/
			
			Customer c2 = session.selectOne("com.my.vo.Customer.selectZipcodeById","id2");
			System.out.println(c2.getPost());
			System.out.println(c2.getId() + ":" + c2.getPost().getZipcode());
			
			session.close();//종료한다는 의미, 마지막에 한번만 닫는다(중간에 닫으면 오류발생)
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
