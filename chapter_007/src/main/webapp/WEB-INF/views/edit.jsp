<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
    <form action="${pageContext.servletContext.contextPath}/edit" method="post">
        id : <input type = "text" name = "id" value="${user.id}"/>
        Name : <input type = "text" name = "name" value = "${user.name}"/>
        Login : <input type = "text" name = "login" value = "${user.login}"/>
        EMail : <input type = "text" name = "email" value = "${user.email}"/>
        <input type = "submit" value = "Edit user"/>
    </form>
</body>
</html>
