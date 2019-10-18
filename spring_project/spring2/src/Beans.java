import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import a.First;

@Configuration
public class Beans {
/*	@Bean
	public Second second() {
		return new Second();
		//return new Second1();
		//return new Second2();
		//어떤값이 주입될지~~?
	}
*/
	@Bean
	public First first() { //id에 해당하는 메서드 이름
		//생성자를 통한 주입
		//First f = new First(second());
		
		First f = new First();
		f.setMsg("금요일입니다.(Java)");
		
		return f;
		//return new First();
	}
	
}
