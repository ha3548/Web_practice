package a;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class First {
	private String msg;

	@Autowired(required = false)
	// 자동주입: first에 second를 묶는다 -> second를 first에 자동주입
	// 생성자주입, setter주입 선언안함
	// (required = false)면 해당객체가 없을때 null값, Exception발생X

	@Qualifier("s1") // s1,s2 가능
	private Second second;

	// Second 객제가 없으면, NoSuchBeanDefinitionException 발생
	// 해결: XML파일에서 bean태그 선언

	// Second 객체가 여러개면, NoUniqueBeanDefinitionException 발생
	// 해결: Qualifier annotation 선언

	private boolean flag;

	@PostConstruct
	public void init() {
		System.out.println("init()호출됨");
		flag = true;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toString() {
		return "msg= " + msg + ", second.Info()= " + second.info();
	}
}
