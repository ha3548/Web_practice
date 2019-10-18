package control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.ProductService;
import com.my.vo.Product;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping("/productlist")
	public ModelAndView list() {
		List<Product> list = service.productList();
		
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("list", list);
		mnv.setViewName("/productlistresult.jsp");
		return mnv;
	}
	
	@GetMapping("/productdetail")
	public ModelAndView productDetail(String prod_no) {
		String str = service.productDatail(prod_no);
		
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);
		mnv.setViewName("/productdetailresult.jsp");
		return mnv;
	}
}
