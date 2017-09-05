<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="webResources/css/hswz.css" />
		<title>登录</title>
	</head>

	<body>
		<form name="input" action="webApi/loginDo" method="get">
			用户名: <input type="text" name="name"> <br>
			密 码: <input type="password" name="password"> <br>
			<input type="submit" value="登录">
		</form>
	</body>

</html>