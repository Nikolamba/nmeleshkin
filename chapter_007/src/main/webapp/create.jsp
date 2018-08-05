<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new user</title>
</head>
<body>
    <form action = "<%=request.getContextPath()%>/create" method = "post">
        ID : <input type = "text" name = "id"/>
        Name : <input type = "text" name = "name"/>
        Login : <input type = "text" name = "login"/>
        EMail : <input type = "text" name = "email"/>
        Create Date : <input type = "text" name = "created"/>
        <input type = "submit" value = "Create new user"/>
    </form>

</body>
</html>
