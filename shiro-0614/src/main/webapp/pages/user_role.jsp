<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>

<form action="/user_role.html?act=add" method="post">
    用户id<input name="userId"><br/>
    <c:forEach items="${roleList}" var="role">
        <input name="roleIds" type="checkbox" value="${role.id}">${role.name}  ||
    </c:forEach>
    <br/><input type="submit" value="添加">
</form>
</body>
</html>
