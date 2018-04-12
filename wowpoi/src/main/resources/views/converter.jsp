<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta  http-equiv="content-type" content="text/html;" charset="utf-8">
    <title>upload</title>
</head>
<body>
<div id="respon">
</div>
<input type="button" onclick="send()" value="请求"/>
</body>
</html>
<script src="assest/js/jquery.min.js"></script>
<script>
    function send() {
        $.ajax({
            url: "convert", data: "1-zhangsan", type: "post"
            , contentType: "application/x-wisely",
            success: function (data) {
                $("#respon").html(data);
            }
        });
    }
</script>