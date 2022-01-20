<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>${requestScope.id}는 사용 가능</h3>
<input type="button" value="사용하기" onclick="checkIdClose('${requestScope.id}')">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function checkIdClose(id){
		var isIdChecked = true;
		
		//opener.document.writeForm.id.value = id;
		//opener.document.getElementById("id").value = id;
		$('#id', opener.document).val(id);
		
		window.close();
		//opener.document.writeForm.pwd.focus();
		$('#pwd', opener.document).focus();
		$('#isIdChecked', opener.document).val('true');
		$('#previousId', opener.document).val(id);
	}
</script>
</body>
</html>