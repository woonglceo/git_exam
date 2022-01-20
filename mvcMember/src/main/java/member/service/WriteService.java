package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class WriteService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("WriteService 실행");
		
		MemberDTO m = new MemberDTO();
		m.setName(request.getParameter("name"));
		m.setId(request.getParameter("id"));
		m.setPwd(request.getParameter("pwd"));
		m.setGender(request.getParameter("gender"));
		m.setEmail1(request.getParameter("email1"));
		m.setEmail2(request.getParameter("email2"));
		m.setTel1(request.getParameter("tel1"));
		m.setTel2(request.getParameter("tel2"));
		m.setTel3(request.getParameter("tel3"));
		m.setZipcode(request.getParameter("zipcode"));
		m.setAddr1(request.getParameter("addr1"));
		m.setAddr2(request.getParameter("addr2"));
//		System.out.printf("%s %s %s %s %s %s %s %s %s %s %s %s\n", name, id, pwd, gender, email1, email2, tel1, tel2, tel3, zipcode, addr1, addr2);
		
		int result = new MemberDAO().write(m);
		
		System.out.println("WriteService 종료");
		if (result < 0) {
			return "/member/writeFail.jsp";
		} else {
			return "/member/writeOk.jsp";
		}
		
	}

}
