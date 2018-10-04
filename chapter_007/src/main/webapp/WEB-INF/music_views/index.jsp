<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Music Court</title>
    <style>
        <%@include file="style.css"%>
    </style>
    <script>
        <%@include file="validateData.js"%>
    </script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<h1>Filters</h1>
<table>
    <tr>
        <td> <input name="filter" type="radio" id="addres_filter" value="adress">Adress filter </td>
        <td>
            <select id="adresses" name="adresses" disabled title="Select address ">
                <option value="error">Select address</option>
                <c:forEach items="${requestScope.adresses}" var="adress">
                    <option value="${adress.id}"><c:out value="${adress.zipCode}, ${adress.city}, ${adress.street}, ${adress.houseNum}"></c:out></option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td> <input name="filter" type="radio" id="role_filter" value="role">Role Filter </td>
        <td>
            <select id="roles" name="roles" disabled title="Select role">
                <option value="error">Select role</option>
                <c:forEach items="${requestScope.roles}" var="role">
                    <option value="${role.id}"><c:out value="${role.role}"></c:out></option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td> <input name="filter" type="radio" id="music_type_filter" value="music">Music type filter </td>
        <td>
            <select id="music_types" name="music_types" disabled title="Select music type">
                <option value="error">Select Music Type</option>
                <c:forEach items="${requestScope.musicTypes}" var="musicType">
                    <option value="${musicType.id}"><c:out value="${musicType.type}"></c:out></option>
                </c:forEach>
            </select>
        </td>
    </tr>
</table>
<h1>Users list</h1>
<c:if test="${applicationScope.error != ''}">
    <div style="background-color: red; margin-left: 200px; margin-right: 200px">
        <c:out value="${applicationScope.error}"/>
    </div>
</c:if>
<c:remove var="error" scope="application"></c:remove>
<table id="usersTable">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>City</th>
    <th>Role</th>
    <th>Music types</th>

</tr>
<c:forEach items="${requestScope.users}" var="user">
    <tr>
    <td><c:out value="${user.user.id}"></c:out> </td>
    <td><c:out value="${user.user.name}"></c:out></td>
    <td><c:out value="${user.adress.zipCode}, ${user.adress.city}, ${user.adress.street}, ${user.adress.houseNum}"></c:out></td>
    <td><c:out value="${user.role.role}"></c:out></td>
        <td><c:forEach items="${user.musicTypeList}" var="musicType">
            <c:out value="${musicType.type}, "></c:out>
        </c:forEach> </td>
    </tr>
    </c:forEach>
    <tr>
        <td>
            <form action = "${pageContext.servletContext.contextPath}/newuser" method="get">
            <button class="btn btn-primary" type="submit">Create new user</button>
            </form>
        </td>
    </tr>
</table>

</body>
</html>
