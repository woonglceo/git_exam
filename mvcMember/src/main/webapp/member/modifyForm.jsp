<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%! String name = "홍길동"; %> <!-- 전역변수 -->

<% int age = 25; %>  		 <!-- 지역변수 --> --%>
    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원정보 수정</title>
	<style type="text/css">
		#modifyForm div {
			color: red;
			font-size: 8px;
			font-weight: bold;
		}
	</style>
	
</head>
<body>
	<form name="modifyForm" id="modifyForm" method="post" action="/mvcMember/member/modify.do">
		<table border="1" cellspacing="0" cellpadding="5">
			<tr>
				<td width="100" align="center">이름</td>
				<td>
					<input type="text" name="name" id="name" placeholder="이름 입력">
					<!-- <div id="nameDiv" style="border: 1px solid red; font-size: 8pt; color: red;"></div> -->
					<div id="nameDiv"></div>
				</td>	
			</tr>
			
			<tr>
				<td width="100" align="center">아이디</td>
				<td>
					<input type="text" name="id" id="id" readonly>
				</td>	
			</tr>
			
			<tr>
				<td width="100" align="center">비밀번호</td>
				<td>
					<input type="password" name="pwd" id="pwd" size="30" placeholder="비밀번호 입력">
					<div id="pwdDiv"></div>
				</td>	
			</tr>
			
			<tr>
				<td width="100" align="center">재확인</td>
				<td>
					<input type="password" name="repwd" id="repwd" size="30" placeholder="비밀번호 입력">
					<div id="repwdDiv"></div>
				</td>	
			</tr>
			
			<tr>
				<td width="100" align="center" >성별</td>
				<td>
					<input type="radio" name="gender" value="0">남
					<input type="radio" name="gender" value="1">여
				</td>
			</tr>
			
			<tr>
				<td width="100" align="center">이메일</td>
				<td>
					<input type="text" name="email1" id="email1">
					@
					<input type="text" name="email2"  id="email2" list="email2" placeholder="직접입력">
					<datalist id="email2">
						<option value="naver.com">naver.com
						<option value="daum.net">daum.net
						<option value="gmail.com">gmail.com
					</datalist>
				</td>
			</tr>
			
			<tr>
				<td width="100" align="center">핸드폰</td>
				<td>
					<select name="tel1">
						<option value="010" selected>010</option>
						<option value="011" >011</option>
						<option value="019" >019</option>
					</select>
					-
					<input type="text" name="tel2" size="6" maxlength="4"  id="tel2">
					-
					<input type="text" name="tel3" size="6" maxlength="4" id="tel3">
				</td>
			</tr>
			
			<tr>
				<td width="100" align="center">주소</td>
				<td>
					<input type="text" name="zipcode" id="zipcode" readonly>
					<input type="button" value="우편번호 검색" onclick="checkPost()"><br>
					<input type="text" name="addr1" id="addr1" size="60" placeholder="주소" readonly><br>
					<input type="text" name="addr2" id="addr2" size="60" placeholder="상세주소">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="modifyBtn" value="정보 수정">
					<!-- <input type="reset" value="다시작성"> -->
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		//$(function(){});  =>  window.onload=function(){}
		$(document).ready(function(){
			console.log('${memberDTO}'); 
			$('#name').val('${memberDTO.name}'); 
			$('#id').val("${memberDTO.id}");
			$('#pwd').val("${memberDTO.pwd}");
			document.modifyForm.gender['${memberDTO.gender}'].checked = true;
			$('#email1').val("${memberDTO.email1}");
			$('#email2').val("${memberDTO.email2}");
			$('#tel1').val("${memberDTO.tel1}");
			$('#tel2').val("${memberDTO.tel2}");
			$('#tel3').val("${memberDTO.tel3}");
			$('#zipcode').val("${memberDTO.zipcode}");
			$('#addr1').val("${memberDTO.addr1}");
			$('#addr2').val("${memberDTO.addr2}");
		});//end ready
		
		$('#modifyBtn').click(function(){
			$('#nameDiv').empty();
			$('#idDiv').empty();
			$('#pwdDiv').empty();
			$('#repwdDiv').empty();
			
			let a = $('#idDiv').val();
			console.log("text: " + a);
			
			if($('#name').val() == '') $('#nameDiv').text('이름 입력');
			else if($('#id').val() == '') $('#idDiv').text('아이디 입력');
			else if($('#pwd').val() == '') $('#pwdDiv').text('비밀번호 입력');
			else if($('#pwd').val() != $('#repwd').val()) $('#repwdDiv').text('비밀번호가 맞지 않습니다.');
			else document.modifyForm.submit();
		});//end click
	</script>
</body>
</html>