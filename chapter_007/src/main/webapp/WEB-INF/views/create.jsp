<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new user</title>
</head>
<body>
    <form action = "${pageContext.servletContext.contextPath}/create" method = "post">
        ID : <input type = "text" name = "id"/>
        Name : <input type = "text" name = "name"/>
        Login : <input type = "text" name = "login"/>
        Password: <input type = "password" name = "password"/>
        EMail : <input type = "text" name = "email"/>
        Create Date : <input type = "text" name = "created"/>
        Authorization: <select name="autorization">
                <option value="administrator">aministrator</option>
                <option value="user">user</option>

            </select>
        <input type = "submit" value = "Create new user"/>
    </form>

</body>
</html>
