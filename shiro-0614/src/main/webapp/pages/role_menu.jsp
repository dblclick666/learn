<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>

<form action="/role_menu.html?act=add" method="post">
    角色id<input name="roleId"><br/>
    <c:forEach items="${menuList}" var="menu">
        <input name="menuIds" type="checkbox" value="${menu.id}">${menu.name}  ||
    </c:forEach>
    <br/><input type="submit" value="添加">
</form>
</body>
</html>
