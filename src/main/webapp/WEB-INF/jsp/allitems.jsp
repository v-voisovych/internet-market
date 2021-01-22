<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

        <div class="conteiner">
            <div id="link1">
                <div class="title_item">
                    <sec:authorize access="hasRole('ADMIN')">
                        <div>
                            <h1>Додайте товар.</h1>
                            <h1>${error}</h1>
                            <form:form action="/save" method="post">
                                <input type="text" name="count" placeholder="Count">
                                <input type="text" name="name" placeholder="Name">
                                <input type="text" name="description" placeholder="Description">
                                <input type="text" name="number" placeholder="Number">
                                <input type="text" name="price" placeholder="Price">
                                <select name="type">
                                    <option value="phone">Телефон</option>
                                    <option value="console">Консоль</option>
                                    <option value="laptop">Ноутбук</option>
                                    <option value="pc">Комп'ютер</option>
                                </select>
                                <button type="submit">Add</button>
                            </form:form>
                        </div>
                    </sec:authorize>
                    <div id="cont">
                        <h1>Наші товари.</h1>
                        <table>
                            <tr><th>Номер</th><th>Найменування</th><th>Опис</th><th>Кількість</th><th>Ціна</th><th>Категорія товару</th><th>Дата створення</th>
<%--                                <sec:authorize access="hasAnyRole('ADMIN', 'SELLER')">--%>
                                    <th>Редагувати</th>
<%--                                </sec:authorize>--%>

                                <sec:authorize access="hasRole('ADMIN')">
                                    <th>Видалити</th>
                                </sec:authorize>
                            </tr>

                            <c:forEach var="el" items="${list}">
                                <tr>
                                    <td>${el.count}</td>
                                    <td>${el.name}</td>
                                    <td>${el.description}</td>
                                    <td>${el.number}</td>
                                    <td>${el.price}</td>
                                    <td>${el.type}</td>
                                    <td>${el.creationDate}</td>
<%--                                    <sec:authorize access="hasAnyRole('ADMIN', 'SELLER')">--%>
                                        <td>
                                            <form:form action="/edit">
                                                <input hidden value="${el.id}" name="id">
                                                <button type="submit">edit</button>
                                            </form:form>
                                        </td>
<%--                                    </sec:authorize>--%>

                                    <sec:authorize access="hasRole('ADMIN')">
                                        <td>
                                            <form:form action="/delete" method="post">
                                                <input hidden value="${el.id}" name="id">
                                                <button type="submit">delete</button>
                                            </form:form>
                                        </td>
                                    </sec:authorize>
                                </tr>
                            </c:forEach>
                        </table>

                        <form:form action="/search" method="post">
                            <input type="text" name="count" placeholder="Сount">
                            <input type="text" name="name" placeholder="Name">
                            <input type="text" name="description" placeholder="Description">
                            <input type="text" name="number" placeholder="Number">
                            <input type="text" name="price" placeholder="Price">
                            <input type="text" name="type" placeholder="Type">
                            <input type="text" name="creationDate" placeholder="CreationDate">
                            <button type="submit" class="btn btn-warning">find</button>
                        </form:form>

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
                        <li><a href="#verh">Додати товар.</a></li>
                    </sec:authorize>
                    <li><a href="#verh"> Вгору.</a></li>
                </ul>
            </div>
        </footer>

    </body>
</html>