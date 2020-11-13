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
        <h1><c:forEach var="el" items="${list}"><p>${el}</p></c:forEach> </h1>


    </body>
</html>