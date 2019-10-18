package a;

import org.springframework.stereotype.Component;

@Component(value = "s1")
public class Second1 implements Second {
	@Override
	public String info() {
		return "Second1객체입니다.";
	}
}
