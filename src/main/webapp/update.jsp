<%--
  Created by IntelliJ IDEA.
  User: Aizada
  Date: 9/14/2019
  Time: 6:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h3> User Information </h3>
<form name="Update" action="UpdateServlet" method="post">
    <input type="hidden" name="id" value="${requestScope.user.id}" />


    <label>
        Role:
        <input type="text" name="role" value="${requestScope.user.role}" />
    </label>

    <label>
        Login:
        <input type="text" name="login" value="${requestScope.user.login}" />
    </label>

    <label>
        Password:
        <input type="text" name="password" value="${requestScope.user.password}" />
    </label>

    <label>
        Name:
        <input type="text" name="name" value="${requestScope.user.name}" />
    </label>

    <label>
        SecondName:
        <input type="text" name="second_name" value="${requestScope.user.secondName}" />
    </label>

    <input type="submit" name="submit" value="Submit" />
</form>