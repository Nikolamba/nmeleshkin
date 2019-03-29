<%--
  Created by IntelliJ IDEA.
  User: Kalampus
  Date: 25.10.2018
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload file on server</title>
</head>
<body>
<form action = "${pageContext.servletContext.contextPath}/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="description" />
    <input type="file" name="file" />
    <input type="submit" />
</body>
</html>
