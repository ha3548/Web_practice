package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.service.ZipService;

public class ZipController {
	private ZipService service;
	static private ZipController controller = new ZipController();
	private ZipController() {
		service = new ZipService();
	}
	public static ZipController getInstance() {
		return controller;
	}
	public String searchZip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doro = request.getParameter("doro");
		String result = service.searchZip(doro);
		
		request.setAttribute("result", result);
		String path = "/result.jsp";
		return path;	
	}
}
