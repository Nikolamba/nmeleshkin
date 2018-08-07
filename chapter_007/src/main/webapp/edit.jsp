<%@ page import="ru.job4j.http.User" %>
<%@ page import="ru.job4j.http.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
    <% String idUser = request.getParameter("id"); %>
    <% User user = ValidateService.getInstance().findById(Integer.valueOf(idUser)); %>
    <form action="<%=request.getContextPath()%>/edit" method="post">
        id : <input type = "text" name = "id" value="<%=user.getId()%>"/>
        Name : <input type = "text" name = "name" value = "<%=user.getName()%>"/>
        Login : <input type = "text" name = "login" value = "<%=user.getLogin()%>"/>
        EMail : <input type = "text" name = "email" value = "<%=user.getEmail()%>"/>
        <input type = "submit" value = "Edit user"/>
    </form>
</body>
</html>
