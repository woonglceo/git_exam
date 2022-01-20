package member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

public class LogoutService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 쿠키
		// 내가 원하는 특정 쿠키를 얻는 방법이 없으므로 모든 쿠키를 가져온다
		Cookie[] ar = request.getCookies();
		if(ar != null){
			for (int i = 0; i < ar.length; i++){
				if (ar[i].getName().equals("memName")) {
					ar[i].setPath("/mvcMember");
					ar[i].setMaxAge(0); 	// 시간을 0을주면 쿠키 삭제가 된다.
					response.addCookie(ar[i]); // 클라이언트에게 보내기
				}
				
				if (ar[i].getName().equals("memId")) {
					ar[i].setPath("/mvcMember");
					ar[i].setMaxAge(0); 	
					response.addCookie(ar[i]); 				
				}
			}//for
		}//if
		
		// 세션
		HttpSession session = request.getSession();
//		session.removeAttribute("memName");
//		session.removeAttribute("memId");
		
		session.invalidate(); // 모든 세션을 다 무효화(제거)
		
		return "/member/logout.jsp";
	}

}
