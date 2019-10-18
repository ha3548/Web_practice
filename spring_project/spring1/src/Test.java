import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import a.First;

public class Test {

	public static void main(String[] args) {
		String path = "beans.xml";
		ApplicationContext ctx; // Spring Container : Spring에서 쓰일 객체관리자
		ctx = new ClassPathXmlApplicationContext(path);
		
		//해당 bean태그의 class 타입으로 맞춰야한다.
		a.First first = ctx.getBean("first-set", a.First.class);
		System.out.println(first);
		System.out.println(first.getNum());	
		System.out.println(first.getSecond().info());
		a.First first1 = ctx.getBean("first-set", a.First.class);
		System.out.println(first1);
		System.out.println(first1.getNum());	
		System.out.println(first1.getSecond().info());
		//결과: 같은객체(미리만들어진 객체 1개를 계속사용)
		
		First first2 = ctx.getBean("first-con", a.First.class);
		System.out.println(first2.getNum());
		System.out.println(first2.getMsg());
		System.out.println(first1==first2);//false
		System.out.println(first1.getSecond()==first2.getSecond());
		//결과: TRUE (미리만들어진 객체 1개, 즉 같은객체를 계속사용)
		
		First first3 = ctx.getBean("first-list", a.First.class);
		System.out.println(first3.getList());

		First first4 = ctx.getBean("first-map", a.First.class);
		System.out.println(first4.getMap());
	}

}
