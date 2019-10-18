package jacksontest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	public static void main(String[] args) {
		A a = new A();
		a.setStr("test");
		a.setNum(123);
		a.setFlag(true);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			//POJO to JSONString
			String jsonStr = mapper.writeValueAsString(a);
			System.out.println(jsonStr);
			
			//JSONString to POJO
			A a1 = mapper.readValue(jsonStr, A.class);
			System.out.println(a1.getStr()+":"+a1.getNum()+":"+a1.isFlag());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//-------------------------------------------------------------------------------------
		List<A> list = new ArrayList();
		for(int i=1;i<=5;i++) {
			A a2 = new A();
			a2.setNum(i);
			list.add(a2);
		}
		try {
			String list_jsonStr = mapper.writeValueAsString(list);
			System.out.println(list_jsonStr);
			
			//List<A> list1 = mapper.readValue(list_jsonStr, java.util.ArrayList.class);
			//System.out.println(list1);
			
			List<A> list1 = mapper.readValue(list_jsonStr, new TypeReference<List<A>>(){});
			for(A a3:list1) {
				System.out.println(a3.getNum());
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
