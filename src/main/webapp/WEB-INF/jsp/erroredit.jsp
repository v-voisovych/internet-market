<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <link href="/css/editstile.css" rel="stylesheet" type="text/css">
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
                </ul>
            </nav>
        </header>

        <div class="conteiner">
            <div id="link1">
                <img src="/img/ps7.jpg">
            </div>
            <div class="title_item">
                <div>
                    <h1>${error} <a href="/edit/${id}">Спробувати ще.</a></h1>
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
                    <li><a href="#verh"> Наверх.</a></li>
                </ul>
            </div>
        </footer>
    </body>
</html>
