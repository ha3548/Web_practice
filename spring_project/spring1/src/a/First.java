package a;

import java.util.List;
import java.util.Map;

public class First {
	private int num;
	private boolean flag;
	
	private String msg;
	private Second second;
	
	private List<String>list;
	private Map<String, String>map;
	
	public First() {
		super();
		System.out.println("First()생성자");
	}
	public First(int num, boolean flag, String msg, Second second) {
		super();
		this.num = num;
		this.flag = flag;
		this.msg = msg;
		this.second = second;
		System.out.println("First(매개변수)생성자");
	}
	public int getNum() {
		System.out.println("getNum(); 호출됨");
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		System.out.println("setNum(" + num + "); 호출됨");
	}
	public boolean isFlag() { //boolean타입은 get이 아니라 is
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Second getSecond() {
		return second;
	}
	public void setSecond(Second second) {
		this.second = second;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
}
