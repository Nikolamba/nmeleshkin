<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        <%@include file="validateData.js"%>
    </script>
    <style>
        <%@include file="style.css"%>
        .btn-primary {
            margin-left: 200px;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Create new user</title>
</head>
<body>
<h1>Creating new user</h1>
<c:if test="${applicationScope.error != ''}">
    <div style="background-color: red; margin-left: 200px; margin-right: 200px">
        <c:out value="${applicationScope.error}"/>
    </div>
</c:if>
<c:remove var="error" scope="application"></c:remove>

<form id="form" action = "${pageContext.servletContext.contextPath}/newuser" method = "post">
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Zipcode</th>
            <th>City</th>
            <th>Street</th>
            <th>House number</th>
            <th>Autorization</th>
            <th>Music Types</th>
        </tr>
        <tr>
            <td><input type = "text" name = "id" id="id" title="Enter user's ID"/></td>
            <td><input type = "text" name = "name" id="name" title="Enter user's name"/></td>
            <td><input type = "text" name = "zipcode" id="zipcode" title="Enter correct zipcode"/></td>
            <td><input type = "text" name = "city" id="city" title="Enter user's city"/></td>
            <td><input type = "text" name = "street" id="street" title="Enter user's street"/></td>
            <td><input type = "text" name = "housenum" id="housenum" title="Enter user's house number"/></td>
            <td>
                <select id="autorization" name="autorization" title="Enter user's role" required>
                    <c:forEach items="${requestScope.roles}" var="role">
                        <option value="${role.id}"><c:out value="${role.role}"></c:out></option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="music_types[]" multiple size="5">
                    <c:forEach items="${requestScope.music_types}" var="music_type">
                        <option value="${music_type.id}"><c:out value="${music_type.type}"></c:out></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input class="btn btn-primary" type="submit" value="Create new user" onclick="return validate()">
</form>

</body>
</html>
