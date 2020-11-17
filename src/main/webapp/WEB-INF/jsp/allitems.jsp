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
                            <tr><th>Номер</th><th>Найменування</th><th>Опис</th><th>Кількість</th><th>Ціна</th><th>Категорія товару</th><th>Дата створення</th><th>Редагувати</th><th>Видалити</th></tr>
                            <c:forEach var="el" items="${list}">
                                <tr>
                                    <td>${el.count}</td>
                                    <td>${el.name}</td>
                                    <td>${el.description}</td>
                                    <td>${el.number}</td>
                                    <td>${el.price}</td>
                                    <td>${el.type}</td>
                                    <td>${el.creationDate}</td>
                                    <td><button type="button"><a href="/edit?id=${el.id}">edit</a></button></td>
                                    <td><button type="button"><a href="/delete?id=${el.id}">delete</a></button></td>
                                </tr>
                            </c:forEach>


                            <tr>
                                <form action="/search" method="post">
                                    <td><input type="text" name="count" placeholder="Сount"></td>
                                    <td> <input type="text" name="name" placeholder="Name"></td>
                                    <td> <input type="text" name="description" placeholder="Description"></td>
                                    <td><input type="text" name="number" placeholder="Number"></td>
                                    <td><input type="text" name="price" placeholder="Price"></td>
                                    <td><input type="text" name="type" placeholder="Type"></td>
                                    <td><input type="text" name="creationDate" placeholder="CreationDate"></td>
                                    <td> <button type="submit" class="btn btn-warning">find</button></td>
                                    <td> <button type="submit" class="btn btn-warning">find</button></td>
                                </form>
                            </tr>
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
                    <li><a href="/itemform">Додати товар.</a></li>
                    <li><a href="#verh"> Вгору.</a></li>
                </ul>
            </div>
        </footer>

    </body>
</html>