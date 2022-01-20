package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class modifyFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("modifyFormService 실행");
		
		// request 변수들 가져오기
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		
		// DB
		MemberDTO m = MemberDAO.getInstance().selectOne(id);
		System.out.println(m.toString());
		
		// 응답
		request.setAttribute("memberDTO", m);
		return "/member/modifyForm.jsp";
	}

}
