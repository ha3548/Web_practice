package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.NotFoundException;
import com.my.service.ZipService;

public class ZipController {
	private ZipService service;
	static private ZipController controller = new ZipController();
	private ZipController() {
		service = new ZipService();
	}
	static public ZipController getInstance() {
		return controller;
	}
	
	public String searchZip(HttpServletRequest request, HttpServletResponse response) {
		String doro = request.getParameter("doro");
		String str = null;
		try {
			str = service.searchZip(doro);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("result", str);
		String path = "result";
		return path;
	}
}
