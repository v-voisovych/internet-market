<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
    </head>

    <body>
        <h1>My Market</h1>
        <ol>
            <c:forEach var="el" items="${list}">
                <li>
                    ${el}
                    <button type="button"><a href="/edit/${el.id}">edit</a></button>
                    <button type="button"><a href="/delete/${el.id}">delete</a></button>
                </li>
            </c:forEach>
        </ol>

        <button type="button"><a href="/itemform">Add</a></button>

    </body>
</html>