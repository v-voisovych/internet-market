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
                    <li><a href="/type?type=phone">Телефони.</a></li>
                    <li><a href="/type?type=console">Консолі.</a></li>
                    <li><a href="/type?type=laptop">Ноутбуки.</a></li>
                    <li><a href="/type?type=pc">Комп'ютери.</a></li>
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
                    <h1>Редагований товар: ${edit.name}; ${edit.description}; ${edit.number}; ${edit.price}; ${edit.type}</h1>
                    <form action="/editsave" method="post">
                        <p><input hidden type="text" name="id" value="${edit.id}"></p>
                        <p><input type="text" name="name" value="${edit.name}"></p>
                        <p><input type="text" name="description" value="${edit.description}"></p>
                        <p><input type="text" name="number" value="${edit.number}"></p>
                        <p><input type="text" name="price" value="${edit.price}"></p>
                        <p>
                            <select name="type">
                                <option value="phone">Телефон</option>
                                <option value="console">Консоль</option>
                                <option value="laptop">Ноутбук</option>
                                <option value="pc">Комп'ютер</option>
                            </select>
                        </p>
                        <p><button type="submit" class="btn btn-warning">Save</button></p>
                    </form>
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
                    <li><a href="#verh"> Наверх.</a></li>
                </ul>
            </div>
        </footer>
    </body>
</html>
