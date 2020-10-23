<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
</head>

<body>
<h1>Edit Item: ${edit} </h1>

<form action="/editsave" method="post">
    <input hidden type="text" name="id" value="${edit.id}">
    <input type="text" name="name" value="${edit.name}">
    <input type="text" name="description" value="${edit.description}">
    <input type="text" name="number" value="${edit.number}">
    <input type="text" name="price" value="${edit.price}">
    <button type="submit">edit</button>
</form>
</body>
