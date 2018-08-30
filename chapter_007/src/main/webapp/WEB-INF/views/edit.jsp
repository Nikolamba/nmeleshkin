<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
    <form action="${pageContext.servletContext.contextPath}/edit" method="post">
        id : <input type = "text" name = "id" value="${requestScope.user.id}"/>
        Name : <input type = "text" name = "name" value = "${requestScope.user.name}"/>
        Login : <input type = "text" name = "login" value = "${requestScope.user.login}"/>
        EMail : <input type = "text" name = "email" value = "${requestScope.user.email}"/>
        Autorization : <select name = "autorization">
                    <option value="administrator">administrator</option>
                    <option value="user">user</option>
                </select>
        <input type = "submit" value = "Edit user"/>
    </form>
</body>
</html>
