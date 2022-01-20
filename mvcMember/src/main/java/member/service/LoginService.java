package member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class LoginService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 데이터
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		// DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		
		String name = memberDAO.login(id, pwd);
		
		// 응답
		if (name == null) {
			return "/member/loginFail.jsp";
		} else {
			// 쿠키
			/*
			//Cookie cookie = new Cookie("쿠키명", 값);
			Cookie cookie = new Cookie("memName", name);
			cookie.setPath("/mvcMember");
			cookie.setMaxAge(3);
			response.addCookie(cookie);  // 클라이언트 보내기
			
			Cookie cookie2 = new Cookie("memId", id);
			cookie2.setPath("/mvcMember");
			cookie2.setMaxAge(3*60);
			response.addCookie(cookie2);  // 클라이언트 보내기
			*/
			
			// 세션
			HttpSession session = request.getSession(); // 인터페이스라 new가 안된다. 세션 생성방법
			session.setAttribute("memName", name);
			session.setAttribute("memId", id);
			
			return "/member/loginOk.jsp";
		}
	}

}
