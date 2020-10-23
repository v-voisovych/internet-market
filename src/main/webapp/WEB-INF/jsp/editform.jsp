<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <meta charset="utf-8">
    </head>

    <body>
    <h1>Edit Item</h1>

        <form action="/editsave" method="post">
            <input type="text" value="" name="name" placeholder="Name">
            <input type="text" name="description" placeholder="Description">
            <input type="text" name="number" placeholder="Number">
            <input type="text" name="price" placeholder="Price">
            <button type="submit">edit</button>
        </form>
    </body>
</html>