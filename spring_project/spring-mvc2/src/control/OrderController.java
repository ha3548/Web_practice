package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.exception.NotFoundException;
import com.my.service.OrderService;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

@Controller
public class OrderController {
	@Autowired
	private OrderService service;

	@PostMapping("/addorder")
	public ModelAndView addOrder(String[] prod_noArray, HttpSession session) {
	  ModelAndView mnv = new ModelAndView();
	  JSONObject obj = new JSONObject( );
	  
	  String id = (String) session.getAttribute("loginInfo");
	  if(session.getAttribute("loginInfo") == null) { //로그아웃상태
	    obj.put("status", 0);
	    mnv.addObject("result", obj.toString());
	  } else {
	    Map<Product, Integer> cart = (Map)session.getAttribute("cart");
	    System.out.println(cart);
	    
		Customer customer = new Customer();
		customer.setId(id);
		OrderInfo info = new OrderInfo();
		info.setCustomer(customer); //주문자
		
		List<OrderLine> lines = new ArrayList<>();
		info.setOrderLines(lines);
        if(cart!=null) {
            for(Product p: cart.keySet()) {
               for(String prod_no: prod_noArray) {	//변수: 배열
                  if(p.getProd_no().equals(prod_no)) {
                     OrderLine line = new OrderLine();
                     line.setProduct(p);
                     line.setOrder_quantity(cart.get(p));
                     lines.add(line);
                     break;
                  }
                  cart.remove(p);
               }
            }
         }
         
		String result = service.add(info);

	    //정상적으로 처리되면 cart비우기
	    //session.removeAttribute("cart");
	    
	    mnv.addObject("result", result);
	  }
	  mnv.setViewName("/result.jsp");
	  return mnv;
	}
	
	@GetMapping("/orderlist")
	public ModelAndView list(HttpSession session) {
		String id = (String)session.getAttribute("loginInfo");
		ModelAndView mnv = new ModelAndView();
		if(session.getAttribute("loginInfo") == null) { //로그아웃상태
		    JSONObject obj = new JSONObject( );
		    obj.put("status", 0);
		    mnv.addObject("result", obj.toString());
		} else {
			List<OrderInfo> list;
			try {
				list = service.findById(id);
				mnv.addObject("list", list);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
		mnv.setViewName("/orderlistresult.jsp");
		return mnv;
	}

}
