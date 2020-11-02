<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <link href="/css/addstile.css" rel="stylesheet" type="text/css">
    </head>

    <body>

        <header>
            <div class="logo">
                <img src="/img/logo.png">
            </div>
            <nav>
                <ul>
                    <li><a href="/">Наші товари.</a></li>
                    <li><a href="/itemform">Додати товар.</a></li>
                    <li><a name="verh"></a></li>
                </ul>
            </nav>
        </header>

        <div class="conteiner">
            <div id="link1">
                <img src="/img/ps7.jpg">
            </div>
            <div class="title_item">
                <div>
                    <h1>Додайте товар.</h1>
                        <form action="/save" method="post">
                            <input type="text" name="name" placeholder="Name">
                            <input type="text" name="description" placeholder="Description">
                            <input type="text" name="number" placeholder="Number">
                            <input type="text" name="price" placeholder="Price">
                            <button type="submit">Add</button>
                        </form>
                </div>
            </div>
        </div>

        <footer>
            <div>
                <ul>
                    <li><a href="/">Наші товари.</a></li>
                    <li><a href="/itemform">Додати товар.</a></li>
                    <li><a href="#verh"> Вгору.</a></li>
                </ul>
            </div>
        </footer>
    </body>
</html>