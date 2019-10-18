import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import a.First;

public class Test {
	public static void main(String[] args) {
		//XML파일을 사용한 ctx 구동
		String path = "beans2.xml";
		ApplicationContext ctx = // Spring Container : Spring에서 쓰일 객체관리자
				new ClassPathXmlApplicationContext(path);
		
		//Java 클래스를 사용한 ctx 구동
//		ApplicationContext ctx = 
//				new AnnotationConfigApplicationContext(Beans.class);
		
		First first = ctx.getBean("first", a.First.class); //다운캐스팅
		System.out.println(first);
		//System.out.println(first.getMsg());

	}
}
