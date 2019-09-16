Created by IntelliJ IDEA.
User: Aizada
Date: 9/13/2019
Time: 6:15 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h3> User Information </h3>
<table>
    <c:if test="${fn:length(requestScope.allUsers) gt 0}">
    <p>
        <c:forEach items="${requestScope.allUsers}" var="user">
        <tr>
            <td><a>Login: </a><c:out value="${user.login}"/></td>
            <td> <a>, Name: </a><c:out value="${user.name}"/></td>
            <td><a>, SecondName : </a><c:out value="${user.secondName}"/></td>
            <td><a href="${pageContext.request.contextPath}/UpdateServlet?edit=${user.id}">edit</a></td>
            <td>
                <a href="${pageContext.request.contextPath}/DeleteServlet?delete=${user.id}">delete</a></td>
            </td>
        </tr>
        </c:forEach>
        </c:if>
</table>
<input type="button" value="Create User" onclick="window.location='create.jsp'" style="left: 10px;">


