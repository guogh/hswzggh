<%--
  Created by IntelliJ IDEA.
  User: guogh
  Date: 2017/9/1
  Time: 下午6:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<link type="text/css" rel="stylesheet" href="webResources/css/hswz.css" />
		<script src="webResources/JavaScriptLib/jquery-3.2.1/jquery-3.2.1.js" type="text/javascript"></script>

		<link href="webResources/JavaScriptLib/video-js-5.20.2/video-js.css" rel="stylesheet">

		<!-- 如果要支持ie8，引入下面的代码 -->
		<script src="webResources/JavaScriptLib/video-js-5.20.2/ie8/videojs-ie8.js"></script>


		<%--<script src="webResources/JavaScriptLib/video-js-5.20.2/video.js"></script>--%>

	<%-- 判断媒体类型--%>
		<c:set var="mediaType"  value="rtmp/flv" scope="request"/>
		<c:if test= "${movieType == mediaType}">
			<%-- 6.X版本不支持 rtmp直播 --%>
			<script src="http://vjs.zencdn.net/5.5.3/video.js"></script>
			<%--<script src="http://vjs.zencdn.net/6.2.7/video.js"></script>--%>
		</c:if>
		<c:if test= "${movieType != mediaType}">
			<script src="webResources/JavaScriptLib/video-js-5.20.2/video.js"></script>
		</c:if>

		<%--m3u8 文件插件--%>
		<script src="webResources/JavaScriptLib/videojs-contrib-hls.js"></script>
		<%--<script src="webResources/JavaScriptLib/videojs-contrib-hls.min.js"></script>--%>

		<%-- flash 支持 --%>
		<script type="text/javascript">
			videojs.options.flash.swf = "webResources/JavaScriptLib/video-js-5.20.2/video-js.swf";
		</script>
	</head>

	<body>
		<div id="" class="video-play">
			<video id="my-video"
				   class="video-js vjs-default-skin vjs-big-play-centered"
				   controls preload="auto"
				   x5-video-player-fullscreen="true"
				   width="640"
				   height="480"
				   data-setup='{}'>

				<source src="${movieSrc}" type='${movieType}'>
				<%--<source src="${movieSrc}" type='video/mp4'>--%>
				<%--<source src="movies/21/playlist.m3u8"  type="application/x-mpegURL>--%>
				<%--<source src="rtmp://live.hkstv.hk.lxdns.com/live/hks" type="rtmp/flv">--%>
			</video>
		</div>

		<script type="text/javascript">
            var myPlayer = videojs('my-video',{
                bigPlayButton : true,
                textTrackDisplay : true,
                posterImage: true,
                errorDisplay : true,
                controlBar : true
            },function(){
                console.log(this)
                this.on('loadedmetadata',function(){
                    console.log('loadedmetadata');
                    //加载到元数据后开始播放视频
//                    startVideo();
                })

                this.on('ended',function(){
                    console.log('ended')
                })
                this.on('firstplay',function(){
                    console.log('firstplay')
                })
                this.on('loadstart',function(){
                    //开始加载
                    console.log('loadstart')
                })
                this.on('loadeddata',function(){
                    console.log('loadeddata')
                })
                this.on('seeking',function(){
                    //正在去拿视频流的路上
                    console.log('seeking')
                })
                this.on('seeked',function(){
                    //已经拿到视频流,可以播放
                    console.log('seeked')
                })
                this.on('waiting',function(){
                    console.log('waiting')
                })
                this.on('pause',function(){
                    console.log('pause')
                })
                this.on('play',function(){
                    console.log('play')
                })
            });

            var isVideoBreak;
            function startVideo() {
                myPlayer.play();

                //判断开始播放视频，移除高斯模糊等待层
                var isVideoPlaying = setInterval(function(){
                    var currentTime = myPlayer.currentTime();
                    if(currentTime > 0){
                        $('.vjs-poster').remove();
                        clearInterval(isVideoPlaying);
                    }
                },200)

                //判断视频是否卡住，卡主3s重新load视频
                var lastTime = -1,
                    tryTimes = 0;

                clearInterval(isVideoBreak);
                isVideoBreak = setInterval(function(){
                    var currentTime = myPlayer.currentTime();
                    console.log('currentTime'+currentTime+'lastTime'+lastTime);

                    if(currentTime == lastTime){
                        //此时视频已卡主3s
                        //设置当前播放时间为超时时间，此时videojs会在play()后把currentTime设置为0
                        myPlayer.currentTime(currentTime+10000);
                        myPlayer.play();

                        //尝试5次播放后，如仍未播放成功提示刷新
                        if(++tryTimes > 5){
                            alert('您的网速有点慢，刷新下试试');
                            tryTimes = 0;
                        }
                    }else{
                        lastTime = currentTime;
                        tryTimes = 0;
                    }
                },3000)
            }
		</script>

	</body>

</html>