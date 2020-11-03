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
                    <li><a href="/phone">Телефони.</a></li>
                    <li><a href="/console">Консолі.</a></li>
                    <li><a href="/laptop">Ноутбуки.</a></li>
                    <li><a href="/pc">Комп'ютери.</a></li>
                    <li><a href="/itemform">Додати товар.</a></li>
                    <li><a name="verh"></a></li>
                </ul>
            </nav>
        </header>

        <div class="conteiner">
            <div id="link1">
                <div class="title_item">
                    <div id="cont">
                        <h1>Наші товари.</h1>
                        <table>
                            <tr><th>Id</th><th>Найменування</th><th>Опис</th><th>Кількість</th><th>Ціна</th><th>Категорія товару</th><th>Редагувати</th><th>Видалити</th></tr>
                            <c:forEach var="el" items="${list}">
                                <tr>
                                    <td>${el.count}</td>
                                    <td>${el.name}</td>
                                    <td>${el.description}</td>
                                    <td>${el.number}</td>
                                    <td>${el.price}</td>
                                    <td>${el.type}</td>
                                    <td><button type="button"><a href="/edit/${el.id}">edit</a></button></td>
                                    <td><button type="button"><a href="/delete/${el.id}">delete</a></button></td>
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
                    <li><a href="/phone">Телефони.</a></li>
                    <li><a href="/console">Консолі.</a></li>
                    <li><a href="/laptop">Ноутбуки.</a></li>
                    <li><a href="/pc">Комп'ютери.</a></li>
                    <li><a href="/itemform">Додати товар.</a></li>
                    <li><a href="#verh"> Вгору.</a></li>
                </ul>
            </div>
        </footer>

    </body>
</html>