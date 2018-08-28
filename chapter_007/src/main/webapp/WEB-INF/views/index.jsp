<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>

<c:if test="${applicationScope.error != ''}">
    <div style="background-color: red">
        <c:out value="${applicationScope.error}"/>
    </div>
</c:if>
<c:remove var="error" scope="application"></c:remove>
    <table cellspacing="15" bgcolor="#a9a9a9">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>E-Mail</th>
            <th>Created data</th>
            <th>Role</th>

        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out> </td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.password}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td><c:out value="${user.role.name}"></c:out></td>
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
        <tr>
            <td>
                <form action="${pageContext.servletContext.contextPath}/signout" method="get">
                    <input type="submit" value="Signout">
                </form>
            </td>
        </tr>
</body>
</html>
