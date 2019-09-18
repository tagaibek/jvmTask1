<%--
  Created by IntelliJ IDEA.
  User: Aizada
  Date: 9/13/2019
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test.jsp</title>
</head>
<body>
create.jsp
</body>
</html>

<form name="Create" action="CreateServlet" method="Post">
    login:       <input type="text" name="login"/>
    password:   <input type="text" name="password"/>
    name:       <input type="text" name="name"/>
    secondName: <input type="text" name="secondName"/>
    mail:       <input type="text" name="mail"/>
    <input type="submit" value="Create"/>
</form>
