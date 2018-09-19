<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Create new user</title>
    <style>
        <%@include file="style.css"%>
        .btn-primary {
            margin-left: 200px;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script>
        <%@include file="validate.js"%>
    </script>
</head>

<body>
    <h1>Creating new user</h1>
    <c:if test="${applicationScope.error != ''}">
        <div style="background-color: red; margin-left: 200px; margin-right: 200px">
            <c:out value="${applicationScope.error}"/>
        </div>
    </c:if>
    <c:remove var="error" scope="application"></c:remove>
    <form id="form" action = "${pageContext.servletContext.contextPath}/create" method = "post">
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Password</th>
                <th>Email</th>
                <th>Date</th>
                <th>Autorization</th>
                <th>Country</th>
                <th>City</th>
            </tr>
            <tr>
                <td><input type = "text" name = "id" id="id" title="Enter user's ID"/></td>
                <td><input type = "text" name = "name" id="name" title="Enter user's name"/></td>
                <td><input type = "text" name = "login" id="login" title="Enter user's login"/></td>
                <td><input type = "password" name = "password" id="password" title="Enter user's password"/></td>
                <td><input type = "text" name = "email" id="email" title="Enter user's email"/></td>
                <td><input type = "text" name = "created"/></td>
                <td><select name="autorization">
                    <option value="administrator">aministrator</option>
                    <option value="user">user</option>
                </select></td>
                <td>
                    <select id="country" name="country" title="Select user's country">
                        <option value="error">Select value</option>
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
        <input class="btn btn-primary" type="submit" value="Create new user" onclick="return validateData()">
    </form>


</body>
</html>
