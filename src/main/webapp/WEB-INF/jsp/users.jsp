<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
            <link href="/css/userpagestile.css" rel="stylesheet" type="text/css">
        <title></title>
    </head>

    <body>

        <header>
            <div class="logo">
                <img src="/img/logo.png">
            </div>
            <nav>
                <ul>
                    <li><a href="/">Наші товари.</a></li>
                    <li><a href="/type?type=phone">Телефони.</a></li>
                    <li><a href="/type?type=console">Консолі.</a></li>
                    <li><a href="/type?type=laptop">Ноутбуки.</a></li>
                    <li><a href="/type?type=pc">Комп'ютери.</a></li>

                    <sec:authorize access="hasRole('ADMIN')">
                        <li><a href="/itemform">Додати товар.</a></li>
                        <li><a href="/users">Користувачі.</a></li>
                    </sec:authorize>

                    <li>
                        <form:form action="/logout" method="post">
                            <button type="submit"> ${username}, logout</button>
                        </form:form>
                    </li>
                    <li><a name="verh"></a></li>
                </ul>
            </nav>
        </header>
        <div class="userpage">
            <div>
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
            <div>
            <h2>All Users</h2>
                <table>
                    <thead>
                    <th>UserName</th>
                    <th>Status</th>
                    <th>Roles</th>
                    <th>Delete</th>
                    </thead>
                    <c:forEach items="${allUsers}" var="user">
                        <td>
                            ${user.username}
                        </td>
                        <td>
                            <form:form action="/saveEditUsers" method="post">
                                <input hidden type="text" name="id" value="${user.id}">
                                <input hidden type="text" name="username" value="${user.username}">
                                <input hidden type="text" name="password" value="${user.password}">
                                ${user.status}
                                <c:forEach items="${user.roles}" var="role">
                                    <input hidden type="text" name="role" value="${role.name}">
                                </c:forEach>
                                <select name="status">
                                    <option value="ACTIVE">ACTIVE</option>
                                    <option value="BANNED">BANNED</option>
                                </select>
                                <button type="submit">Edit</button>
                            </form:form>
                        </td>
                        <td>
                            <form:form action="/saveEditUsers" method="post">
                                <input hidden type="text" name="id" value="${user.id}">
                                <input hidden type="text" name="username" value="${user.username}">
                                <input hidden type="text" name="password" value="${user.password}">
                                <input hidden type="text" name="status" value="${user.status}">
                                <c:forEach items="${user.roles}" var="role">${role.name}</c:forEach>
                                <select name="role">
                                    <option value="ROLE_ADMIN">Адміністратор</option>
                                    <option value="ROLE_SELLER">Продавець</option>
                                    <option value="ROLE_USER">Користувач</option>
                                </select>
                                <button type="submit">Edit</button>
                            </form:form>
                        </td>
                        <td>
                            <form:form action="/deleteusers" method="post">
                                <input type="hidden" name="userId" value="${user.id}"/>
                                <input type="hidden" name="action" value="delete"/>
                                <button type="submit">Delete</button>
                            </form:form>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>