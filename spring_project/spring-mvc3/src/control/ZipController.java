package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.ZipService;
@Controller
public class ZipController {
	@Autowired
	private ZipService service;
	
	@PostMapping("/searchzip")
	public ModelAndView searchZip(String doro){
		String result = service.searchZip(doro);
		
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", result);
		mnv.setViewName("/result.jsp");
		return mnv;
		
	}
/*	public String searchZip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doro = request.getParameter("doro");
		String result = service.searchZip(doro);
		
		request.setAttribute("result", result);
		String path = "/result.jsp";
		return path;	
	}*/
}
