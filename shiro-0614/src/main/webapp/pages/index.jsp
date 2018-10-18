<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<body>qqq
<c:forEach items="${menuList}" var="menu">
    <a href="${menu.url}">${menu.name}</a><br/>
</c:forEach>
</body>
</html>
