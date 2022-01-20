<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <%
	// 쿠키
	// 내가 원하는 특정 쿠키를 얻는 방법이 없으므로 모든 쿠키를 가져온다
	Cookie[] ar = request.getCookies();
	if (ar != null){
		for (int i = 0; i < ar.length; i++){
			String cookieName = ar[i].getName(); // 쿠키명
			String cookieValue = ar[i].getValue(); // 값
			
			System.out.println("쿠키명 = " + cookieName);
			System.out.println("쿠키값 = " + cookieValue);
			System.out.println();
		}//end for
	}//end if
	
	// 세션
	//session.getAttribute("memName");	// ${sessionScope.memName} 와 같다
%> --%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h3>${sessionScope.memName}님 로그인 성공~~~~~~</h3>
	
	<form id="modifyForm" method="POST" action="/miniProject/member/modifyForm.do">
	  <input type="button" value="회원정보 수정" onclick="location.href='/miniProject/member/modifyForm.do'">
	  <input type="button" value="로그아웃" onclick="location.href='/mvcMember/member/logout.do'">
	</form>
	
</body>
</html>