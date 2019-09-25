<%--
  Created by IntelliJ IDEA.
  User: Aizada
  Date: 9/24/2019
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h3> User Information </h3>
<table>
        <tr>
            <td><a>Login: </a><c:out value="${requestScope.user.login}"/></td>
            <td> <a>, Name: </a><c:out value="${requestScope.user.name}"/></td>
            <td><a>, SecondName : </a><c:out value="${requestScope.user.secondName}"/></td>
        </tr>
</table>