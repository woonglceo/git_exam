<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<style type="text/css">
		#loginForm div {
			color: red;
			font-size: 8px;
			font-weight: bold;
			
		}
	</style>
</head>
<body>
	<form id="loginForm" method="post" action="/mvcMember/member/login.do">
		<input type="text" name="id" id="id">
		<div id="idDiv"></div>
		<input type="password" name="pwd" id="pwd">
		<div id="pwdDiv"></div>
		
		<input type="button" id="loginBtn" value="로그인">
		<input type="button" id="registerBtn" value="회원가입" onclick="location.href='/mvcMember/member/writeForm.do'">
	</form>
	
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	$('#loginBtn').click(function(){
		$('#idDiv').empty();
		$('#pwdDiv').empty();
		
		if ($('#id').val() == '') {
			$('#idDiv').text('아이디를 입력하세요');
		}
		
		if ($('#pwd').val() == '') {
			$('#pwdDiv').text('패스워드를 입력하세요');
		}
		else $('#loginForm').submit();
	});
	</script>
	
</body>
</html>