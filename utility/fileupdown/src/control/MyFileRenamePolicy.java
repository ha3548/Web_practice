package control;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File file) {
		//System.out.println("rename(" + file + ")호출");
		// 1.파일이름 얻기
		System.out.println(file.getName() + ", " + file.getAbsoluteFile());
		//String filePath = file.getAbsolutePath();
		String parent = file.getParent();
		String oldname = file.getName();
		
		// 2.파일이름바꾸기 ex)파일명-yyMMddHHmmss.txt
		int index = oldname.indexOf(".");// 3
		
		// 2-1) 확장자 제외한 파일이름
		String now = oldname.substring(0, index);// a
		// 2-2) yyMMddHHmmss
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String uploadtime = sdf.format(date);// 190917111258
		// 2-3) 확장자
		String extension = oldname.substring(index);// .PNG

		String newName = now +"-"+ uploadtime + extension;//aaa-190917111459.PNG
		
		// 3.새파일 생성후 변환
		File f = new File(parent, newName);
		return f;
	}

}
