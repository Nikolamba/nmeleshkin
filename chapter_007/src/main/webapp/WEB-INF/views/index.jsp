<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
    <table>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out> </td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input type="submit" value="Edit" />
                    </form>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/" method="post">
                        <input type = "hidden" name = "id" value = "${user.id}"/>
                        <input type = "submit" value = "Delete"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <form action = "${pageContext.servletContext.contextPath}/create" method="get">
                    <input type = "submit" value="Create new User"/>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
