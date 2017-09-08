<%--
  Created by IntelliJ IDEA.
  User: guogh
  Date: 2017/8/31
  Time: 上午11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="webResources/css/hswz.css" />

<html>
<head>
    <title>Title</title>
</head>
<body>

<script>
    function golist() {
        window.location.href='listMovie.html';
    }

    function livePlayList() {
        window.location.href='livePlayList.html';
    }

    function uploadfile() {
        window.location.href='upLoadMovie.html';
    }
</script>

    <h2> Hello World!   success </h2>

    <button class="btn-line" onclick="golist()"> 走你 </button>
    <button class="btn-line" onclick="livePlayList()"> 直播 </button>
    <button class="btn-line" onclick="uploadfile()"> 上传 </button>

</body>
</html>
