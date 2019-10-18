import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.my.dao.OrderDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class OrderTest {
	public static void main(String[] args) {
		OrderDAO dao = new OrderDAO();
		String id = "id1";
		try {
			List<OrderInfo> list = dao.selectById(id);
			for(OrderInfo info:list) {
				int order_no = info.getOrder_no();
				Timestamp order_time = info.getOrder_time();
				List<OrderLine> lines = info.getOrderLines();
				for(OrderLine line:lines) {
					//line.getOrder_no();
					String p_no = line.getProduct().getProd_no();
					String p_name = line.getProduct().getProd_name();
					int p_price = line.getProduct().getProd_price();
					int quantity = line.getOrder_quantity();
					System.out.println("<상세정보>  상품번호:" + p_no + ",\t상품이름:" + p_name + ",\t상품가격:" + p_price + ",\t주문수량:" + quantity);
				}
			}
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
/*		
		OrderInfo info = new OrderInfo();
		Customer customer = new Customer();
		customer.setId(id);
		info.setCustomer(customer); //주문자
		
		
		List<OrderLine> orderlines = new ArrayList<>();
//1)
		OrderLine line = new OrderLine();
		Product product = new Product();
		//주문상품번호
		product.setProd_no("10001");
		line.setProduct(product);
		//주문수량
		line.setOrder_quantity(2);
		//OrderLine테이블에 추가
		orderlines.add(line);
		
//2) line,product 재사용X, 새로만들어야한다!!
		line = new OrderLine();
		product = new Product();
		//주문상품번호
		product.setProd_no("10003");
		line.setProduct(product);
		//주문수량
		line.setOrder_quantity(3);
		//OrderLine테이블에 추가
		orderlines.add(line);
		
		info.setOrderLines(orderlines);
		
		try {
			dao.insert(info);//정상출력
			System.out.println("주문 추가 성공!");
		} catch (AddException e) {
			System.out.println(e.getMessage());
		}
*/
	}
}
