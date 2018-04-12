<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>defer</title>
</head>
<body>
Hello World!<br/>
<div id="pushMsg"></div>
<button type="button" onclick="send()" >开始接收</button>
</body>
<%@include file="common/footer.jsp"%>
</html>
<script>
    var  s = "";
    function send() {
        $.ajax({
            url:"/defer",type:"post",contentType:"application/json",
            success:function (data) {
                s+=data.result+"<br/>"
                $("#pushMsg").html(s);
            }
        });
    }
</script>
