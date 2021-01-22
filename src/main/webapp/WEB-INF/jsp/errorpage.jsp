<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    </head>

    <body>
        <div class="container bgcont center-block">
            <h1>
                ${error403} ${error403text}
                ${error404}
                ${error405}
                ${error500}
            </h1>
            <img src="/img/404.gif">
            <h1><a href="/">Home.</a></h1>
        </div>
    </body>
</html>