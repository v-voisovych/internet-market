<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <link href="/css/viewitemstile.css" rel="stylesheet" type="text/css">
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
                    </sec:authorize>
                    <li><a href="#verh"> Вгору.</a></li>
                    <form action="/logout" method="post">
                        <button type="submit">Logout</button>
                    </form>
                    <li><a name="verh"></a></li>
                </ul>
            </nav>
        </header>

        <div class="conteiner">
            <div id="link1">
                <div class="title_item">
                    <div id="cont">
                        <h1>${type}
                            ${nothing}.
                        </h1>
                        <table>
                            <tr><th>Номер</th><th>Найменування</th><th>Опис</th><th>Кількість</th><th>Ціна</th><th>Редагувати</th><th>Видалити</th></tr>
                                <c:forEach var="el" items="${list}">
                                    <tr>
                                        <td>${el.count}</td>
                                        <td>${el.name}</td>
                                        <td>${el.description}</td>
                                        <td>${el.number}</td>
                                        <td>${el.price}</td>
                                        <td><button type="button"><a href="/edit?id=${el.id}">edit</a></button></td>
                                        <td><button type="button"><a href="/delete?id=${el.id}">delete</a></button></td>
                                    </tr>
                                </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <footer>
            <div>
                <ul>
                    <li><a href="/">Наші товари.</a></li>
                    <li><a href="/type?type=phone">Телефони.</a></li>
                    <li><a href="/type?type=console">Консолі.</a></li>
                    <li><a href="/type?type=laptop">Ноутбуки.</a></li>
                    <li><a href="/type?type=pc">Комп'ютери.</a></li>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li><a href="/itemform">Додати товар.</a></li>
                    </sec:authorize>
                    <li><a href="#verh"> Вгору.</a></li>
                    <li><a href="#verh"> Вгору.</a></li>
                </ul>
            </div>
        </footer>

    </body>
</html>