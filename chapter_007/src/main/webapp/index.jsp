<%@ page import="ru.job4j.http.User" %>
<%@ page import="ru.job4j.http.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
    <table>
        <% for (User user : ValidateService.getInstance().findAll()) { %>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getLogin()%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getCreateDate()%></td>
                <td>
                    <form action="<%=request.getContextPath()%>/edit.jsp" method="get">
                        <input type="hidden" name="id" value="<%=user.getId()%>"/>
                        <input type="submit" value="Edit" />
                    </form>
                </td>
                <td>
                    <form action="<%=request.getContextPath()%>/list" method="post">
                        <input type = "hidden" name = "id" value = "<%=user.getId()%>"/>
                        <input type = "submit" value = "Delete"/>
                    </form>
                </td>
            </tr>
        <% } %>
        <tr>
            <td>
                <form action = "<%=request.getContextPath()%>/create.jsp" method="get">
                    <input type = "submit" value="Create new User"/>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
