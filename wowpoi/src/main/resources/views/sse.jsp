<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>sse</title>
</head>
<body>
    <div id="msgPush"></div>
</body>
</html>
<script src="assest/js/jquery.min.js">
</script>
<script>
    if(window.eventSource){
        var source = new EventSource('push');
        s = '' ;
        source.addEventListener('message',function (e) {
            s+=e.data+"<br/>";
            $("#msgPush").html(s);
        });
        source.addEventListener('open',function (e) {
            console.log("链接打开")
        },false);

        source.addEventListener('',function (e) {
            if(e.readyState==EventSource.CLOSED){
                console.log("链接关闭");
            }else{
                console.log(e.readyState);
            }
        },false)
    }else{
        console.log("您的浏览器不支持SSE");
    }

</script>