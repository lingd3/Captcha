<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeR(node) {
		//用于点击时长生不同的验证码
		node.src = "randomcode.jpg?time="+new Date().getTime();
	}
</script>
</head>
<body>
	<img alt="random" src="randomcode.jpg" onclick="changeR(this)" style="cursor:pointer;">
	<form action="check.jsp" method="post">
		<input type="text" name="r">
		<input type="submit" value="submit">		
	</form>
</body>
</html>