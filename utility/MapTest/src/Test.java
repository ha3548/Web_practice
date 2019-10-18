import java.util.HashMap;

import com.my.vo.Product;

public class Test {

	public static void main(String[] args) {
	      HashMap<Product, Integer> map = new HashMap<>();
	      Product p1 = new Product();
	      Product p2 = new Product();
	      Product p3 = new Product();
	      
	      //선택
	      p1.setProd_no("10001");
	      int quantity = 3; //수량
	      
	      //조회
	      p3.setProd_no("10001");
	      Integer inte = map.get(p3);
	      if(inte != null) {
	         quantity += inte;
	      }	      
	      //추가
	      map.put(p1, quantity); //p1: '10001'상품, 3개
	      
	      //선택
	      p2.setProd_no("10001");
	      quantity = 2;
	      
	      //조회
	      p3.setProd_no("10001");
	      inte = map.get(p3);
	      if(inte != null) {
	         quantity += inte;
	      }
	      //추가
	      map.put(p2,quantity); //p2: '10001'상품, 2개
	      
	      System.out.println(map);
	}

}
