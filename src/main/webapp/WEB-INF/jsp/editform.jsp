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
                        <li><a href="/itemform">Додати товар.</a></li>
                        <li><a href="/users">Користувачі.</a></li>
                    </sec:authorize>
                    <form:form action="/logout" method="post">
                        <button type="submit">${username}, logout</button>
                    </form:form>
                    <li><a name="verh"></a></li>
                </ul>
            </nav>
        </header>

        <div class="conteiner">
            <div id="link1">
                <div class="title_item_add">
                    <div>
                        <h1>Редагований товар: ${edit.count}; ${edit.name}; ${edit.description}; ${edit.number}; ${edit.price}; ${edit.type}</h1>
                        <form:form action="/editsave" method="post">
                            <input hidden type="text" name="id" value="${edit.id}">
                            <input type="text" name="count" value="${edit.count}">
                            <input type="text" name="name" value="${edit.name}">
                            <input type="text" name="description" value="${edit.description}">
                            <input type="text" name="number" value="${edit.number}">
                            <input type="text" name="price" value="${edit.price}">
                            <input type="text" name="creationDate" value="${edit.creationDate}">
                            <select name="type">
                                <option value="phone">Телефон</option>
                                <option value="console">Консоль</option>
                                <option value="laptop">Ноутбук</option>
                                <option value="pc">Комп'ютер</option>
                            </select>
                            <button type="submit" class="btn btn-warning">Save</button>
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
                        <li><a href="/itemform">Додати товар.</a></li>
                    </sec:authorize>
                    <li><a href="#verh"> Вгору.</a></li>
                </ul>
            </div>
        </footer>
    </body>
</html>
