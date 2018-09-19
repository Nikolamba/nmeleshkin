<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style>
        <%@include file="style.css"%>
        .btn-primary {
            margin-left: 200px;
        }
    </style>
    <script>
        <%@include file="validate.js"%>
    </script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Edit user</title>
</head>
<body>
    <h1>Users list</h1>
    <c:if test="${applicationScope.error != ''}">
        <div style="background-color: red; margin-left: 200px; margin-right: 200px">
            <c:out value="${applicationScope.error}"/>
        </div>
    </c:if>
    <c:remove var="error" scope="application"></c:remove>
    <form action="${pageContext.servletContext.contextPath}/edit" method="post">
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Email</th>
                <th>Autorization</th>
                <th>Country</th>
                <th>City</th>
            </tr>
            <tr>
                <td><input type = "text" name = "id" id="id" title="Enter user's id" value="${requestScope.user.id}" readonly/></td>
                <td><input type = "text" name = "login" id="login" title="Enter user's login" value = "${requestScope.user.login}" readonly/></td>
                <td><input type = "text" name = "name" id="name" title="Enter user's name" value = "${requestScope.user.name}"/></td>
                <td><input type = "text" name = "email" id="email" title="Enter user's email" value = "${requestScope.user.email}"/></td>
                <td><select name = "autorization">
                    <option value="administrator">administrator</option>
                    <option value="user">user</option>
                </select></td>
                <td>
                    <select id="country" name="country" on>
                        <option value="russia">Russia</option>
                        <option value="belarus">Belarus</option>
                        <option value="ukraine">Ukraine</option>
                    </select>
                </td>
                <td>
                    <select id="city" name="city">
                    </select>
                </td>
            </tr>
        </table>
        <input class="btn btn-primary" type="submit" value="Edit user" onclick="return validateData()">
    </form>
</body>
</html>
