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
                    <form action="/logout" method="post">
                        <button type="submit">${username}, logout</button>
                    </form>
                    <li><a name="verh"></a></li>
                </ul>
            </nav>
        </header>

        <div class="conteiner">
            <div id="link1">
                <div class="title_item_add">
                    <div>
                        <h1>Додайте товар.</h1>
                        <h1>${error}</h1>
                            <form action="/save" method="post">
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
                            </form>
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
                    <li><a href="/itemform">Додати товар.</a></li>
                    <li><a href="#verh"> Вгору.</a></li>
                </ul>
            </div>
        </footer>
    </body>
</html>