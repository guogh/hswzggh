<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="webResources/css/hswz.css" />
		<title>登录</title>
	</head>

	<body>
		<script>
            function register(){
                window.location.href='register.html';
            }
		</script>

		<form name="input" action="webApi/loginDo" method="get">
			用户名: <input class="input-Line" type="text" name="name"> <br>
			密  码: <input class="input-Line" type="password" name="password"> <br>
			<input class="btn-line" type="submit" value="登录">
		</form>

		<button class="btn-line"  onclick="register()"> 去注册</button>
	</body>

</html>

