<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <link href="/css/userpagestile.css" rel="stylesheet" type="text/css">
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
                    <form action="/logout" method="post">
                        <button type="submit"> ${username}, logout</button>
                    </form>
                </li>
                <li><a name="verh"></a></li>
            </ul>
        </nav>
    </header>
        <div class="editUserForm">
            ${editUser.username}, ${editUser.status}, <c:forEach items="${editUser.roles}" var="role">${role.name}</c:forEach>
            <form action="/saveEditUsers" method="post">
                <input hidden type="text" name="id" value="${editUser.id}">
                <input hidden type="text" name="username" value="${editUser.username}">
                <input hidden type="text" name="password" value="${editUser.password}">
                <select name="status">
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="BANNED">BANNED</option>
                </select>
                <select name="role">
                    <option value="admin">Адміністратор</option>
                    <option value="seller">Продавець</option>
                    <option value="user">Користувач</option>
                </select>
                <button type="submit" class="btn btn-warning">Save</button>
            </form>
        </div>

    </body>
</html>