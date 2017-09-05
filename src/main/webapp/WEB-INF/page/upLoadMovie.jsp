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
		<title> 视频上传 </title>
	</head>

	<body>
		<h1> 上传 </h1>

		<form id='myupload' action='upLoadMovieDo.html' method='post' enctype='multipart/form-data'>
			<div class="demo">
				<div class="btn">
					<span>添加附件</span>
					<input id="fileupload" type="file" name="movie">
				</div>
				<div class="">
					网络链接 : <input type="text" name="netUrl" /> <br>
				</div>
				<div class="">
					视频名称 : <input type="text" name="name" /> <br>
				</div>

				<div class="progress">
					<span class="bar"></span><span class="percent">0%</span>
				</div>
				<!-- 显示已上传的文件名 -->
				<div class="files"></div>
				<!-- 显示已上传的图片-->
				<div class="showimg"></div>
			</div>
			<input type="submit" onclick="gosubmit2()" />
		</form>
		
		<script src="//oss.maxcdn.com/jquery.form/3.50/jquery.form.min.js"></script>
		<script type="text/javascript">
			var bar = $('.bar'); //进度条
			var percent = $('.percent'); //获取上传百分比
			var showimg = $('.showimg'); //显示图片的div
			var progress = $('.progress'); //显示进度的div
			var files = $('.files'); //文件上传控件的input元素
			var btn = $('.btn span'); //按钮文本
			function gosubmit2() {
				$("#myupload").ajaxSubmit({
					dataType: 'json', //返回数据类型
					beforeSend: function() {
						showimg.empty();
						progress.show();
						var percentVal = '0%';
						bar.width(percentVal);
						percent.html(percentVal);
						btn.html('上传中..');
					},
					//更新进度条事件处理代码
					uploadProgress: function(event, position, total, percentComplete) {
						var percentVal = percentComplete + '%';
						bar.width(percentVal);
						percent.html(percentVal);
					},
					success: function(data) { //图片上传成功时
						//获取服务器端返回的文件数据
						alert(data.name + "," + data.pic + "," + data.size);
					},
					error: function(xhr) {
						btn.html(上传失败);
						bar.width('0');
						files.html(xhr.responseText);
					}
				});
			}
		</script>

	</body>

</html>