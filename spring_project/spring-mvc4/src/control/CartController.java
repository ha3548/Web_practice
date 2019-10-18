package control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.vo.Product;

@Controller
public class CartController {
	
	@PostMapping("/addcart")
	public ModelAndView addCart(Product p, int quantity, HttpSession session) {
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		if(cart==null) {//장바구니 없으면
			cart = new HashMap<>();	//장바구니 만들어서
			session.setAttribute("cart", cart);	//세션에추가
		}

		//수량조회
		Integer inte = cart.get(p);
		if(inte != null) {
	         quantity += inte;
	    }
		
		//추가
		cart.put(p, quantity);
	    
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 1);
		
	    ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", jsonObj.toString());
		mnv.setViewName("/result.jsp");
		return mnv;
	}
	
	@GetMapping("/cartlist")
	public String cartList(HttpSession session) {
		return "/cartlistresult.jsp";
		//jsp단에서 ${sessionScope.cart} 하면됨
	}

/*	public ModelAndView cartList(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("cart", session.getAttribute("cart"));
		mnv.setViewName("/cartlistresult.jsp");
		return mnv;
	}*/
}