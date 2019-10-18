package listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MySessionAttributeListener implements HttpSessionAttributeListener {

    int loginedCnt = 0;
    public MySessionAttributeListener() {
        // TODO Auto-generated constructor stub
    }

    public void attributeAdded(HttpSessionBindingEvent e)  { 
        //HttpSession session = e.getSession();
        String attrName = e.getName();
        Object attrValue = e.getValue();
        if(attrName.equals("loginInfo")) {//로그인된 경우
        	loginedCnt++;
        	System.out.println("지금 로그인한 고객: " + attrValue);
        	System.out.println("총 로그인된 고객 수 :" + loginedCnt);
        }
    }

	
    public void attributeRemoved(HttpSessionBindingEvent e)  { 
    	String attrName = e.getName();
    	Object attrValue = e.getValue();
    	if(attrName.equals("loginInfo")) { //로그아웃된 경우
    		loginedCnt--;
    		System.out.println("지금 로그아웃한 고객: " + attrValue);
        	System.out.println("총 로그인된 고객 수 :" + loginedCnt);
    	}
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    }
	
}
