<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function reloadedCode() {
		var time = new Date().getTime(); //防止由于缓存而不更新图片
		document.getElementById("imagecode").src="<%=request.getContextPath()%>/servlet/ImageServlet?d="+time;
	}
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/servlet/LoginServlet" method="get">
		验证码：<input type="text" name="checkcode"/>
		<img alt="验证码" id="imagecode" src="<%=request.getContextPath()%>/servlet/ImageServlet"/>
		<a href="javascript:reloadedCode();">换一张</a><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>