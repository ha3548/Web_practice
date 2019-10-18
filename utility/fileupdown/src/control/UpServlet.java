package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class UpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.getParameter("t"); //이렇게쓰면 안된다!
		// InputStream is = request.getInputStream();
		// InputStreamReader isr = new InputStreamReader(is); //바이트->문자로 변환해줌
		// int readValue = -1;
		// while((readValue = isr.read())!=-1) {
		// System.out.print((char)readValue);

		String saveDirectory = "files";
		String realPath = getServletContext().getRealPath(saveDirectory);
		System.out.println(realPath);
		
		int maxPostSize = 1000 * 1024;
		String encoding = "UTF-8";
		//FileRenamePolicy policy = new DefaultFileRenamePolicy();
		FileRenamePolicy policy = new MyFileRenamePolicy();
		
		MultipartRequest mr = new MultipartRequest(request, realPath, maxPostSize, encoding, policy);

	}

}
