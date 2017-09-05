<%--
  Created by IntelliJ IDEA.
  User: guogh
  Date: 2017/9/4
  Time: 下午12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link type="text/css" rel="stylesheet" href="webResources/css/hswz.css" />


<html>
<head>
    <title>播放列表 </title>
</head>
<body>
<h1 >播放列表</h1>

<table border="1" align="center" cellspacing="" cellpadding="">
	<tr><td >序号 </td><td>名称</td><td>路径</td><td>网络链接</td><td>状态</td></tr>
	
	<c:forEach items="${list}" var="item" varStatus="status">
    <tr >
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.path}</td>
        <td>${item.netUrl}</td>
        <td><a href='playVideo.html?name=${item.name}'>播放</a></td>
    </tr>
	</c:forEach>
	
</table>


</body>
</html>
