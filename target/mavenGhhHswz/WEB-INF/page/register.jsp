<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="webResources/css/hswz.css" />
		<title>注册</title>
	</head>

	<body>
		<form name="input" action="webApi/registerDo" method="post">
			用户名: <input type="text" name="name"> <br> 
			密 码: <input type="password" name="password"> <br>
			邀请码: <input type="text" name="invitationCode"> <br>
			<input type="submit" value="注册">
		</form>
	</body>
</html>