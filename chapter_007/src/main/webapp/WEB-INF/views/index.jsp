<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="style.css"%>
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>User list</title>
</head>
<body>

<c:if test="${applicationScope.error != ''}">
    <div style="background-color: red; margin-left: 200px; margin-right: 200px">
        <c:out value="${applicationScope.error}"/>
    </div>
</c:if>
<c:remove var="error" scope="application"></c:remove>
    <h1>Users list</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Password</th>
            <th>E-Mail</th>
            <th>Created data</th>
            <th>Role</th>
            <th>Country</th>
            <th>City</th>
            <th></th>
            <th></th>

        </tr>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out> </td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.password}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td><c:out value="${user.role.name}"></c:out></td>
                <td><c:out value="${user.country}"></c:out></td>
                <td><c:out value="${user.city}"></c:out></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <button class="btn btn-primary" type="submit">Edit</button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/" method="post">
                        <input type = "hidden" name = "id" value = "${user.id}"/>
                        <button class="btn btn-primary" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <form action = "${pageContext.servletContext.contextPath}/create" method="get">
                    <button class="btn btn-primary" type="submit">Create new user</button>
                </form>
            </td>
        </tr>
        <tr>
            <td>
                <form action="${pageContext.servletContext.contextPath}/signout" method="get">
                    <button class="btn btn-primary" type="submit">Signout</button>
                </form>
            </td>
        </tr>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
