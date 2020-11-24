<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
        <title></title>
    </head>

    <body>
        <div class="container bgcont center-block">
            <form:form method="POST" modelAttribute="user">
                <h2>Add new User</h2>
                <div>
                    <form:input type="text" path="username" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                        ${usernameError}
                </div>
                <div>
                    <form:input type="password" path="password" placeholder="Password"></form:input>
                </div>
                <div>
                    <form:input type="password" path="passwordConfirm"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="password"></form:errors>
                        ${passwordError}
                </div>
                <div>
                    <select name="role">
                        <option value="admin">Адміністратор</option>
                        <option value="seller">Продавець</option>
                        <option value="user">Користувач</option>
                    </select>
                </div>
                <button type="submit">Sing Up</button>
            </form:form>
        </div>
        <div class="container bgcont center-block">
        <h2>All Users</h2>
            <table>
                <thead>
                <th>UserName</th>
                <th>Roles</th>
                <th>Delete</th>
                </thead>
                <c:forEach items="${allUsers}" var="user">
                    <tr>
                        <td>${user.username}</td>
                        <td>
                            <c:forEach items="${user.roles}" var="role">${role.name}</c:forEach>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/deleteusers" method="post">
                                <input type="hidden" name="userId" value="${user.id}"/>
                                <input type="hidden" name="action" value="delete"/>
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <form action="/">
                <button type="submit">Home</button>
            </form>
        </div>
    </body>
</html>