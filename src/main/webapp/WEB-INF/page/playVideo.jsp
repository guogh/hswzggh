<%--
  Created by IntelliJ IDEA.
  User: guogh
  Date: 2017/9/1
  Time: 下午6:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<link type="text/css" rel="stylesheet" href="webResources/css/hswz.css" />
<script src="webResources/JavaScriptLib/jquery-3.2.1/jquery-3.2.1.js" type="text/javascript"></script>

<!DOCTYPE html>
<html lang="en">
	<head>
		<link href="http://vjs.zencdn.net/5.10.4/video-js.css" rel="stylesheet">
		<!-- 如果要支持ie8，引入下面的代码 -->
		<script src="http://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>

		<script src="webResources/JavaScriptLib/videojs-contrib-hls.js"></script>
		<script src="webResources/JavaScriptLib/videojs-contrib-hls.min.js"></script>

	</head>

	<body>
		<div id="" class="video-play">
			<video id="my-video" class="video-js vjs-big-play-centered" controls preload="auto" width="640" height="480" data-setup="{}">
				<source src="movies/${movie.name}" type='video/mp4'>
				<%--<source src="movies/21/playlist.m3u8"  type="application/x-mpegURL>--%>


				<p class="vjs-no-js">
					欢迎来到 HSWZ
					<a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
				</p>
			</video>
		</div>
		<script src="http://vjs.zencdn.net/6.2.7/video.js"></script>
	</body>

</html>