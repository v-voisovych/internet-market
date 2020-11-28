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
            <h1>Login</h1>
            <form name='f' action="/login" method='POST'>
                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='username'></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password' /></td>
                    </tr>
                    <tr>
                        <td><button type="submit">Sing In</button></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="container bgcont center-block">
            <form:form method="POST" action="/registration" modelAttribute="userForm">
            <h1>Sing Up</h1>
            <div>
                <form:input type="text" path="username" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
                    ${usernameError}
            </div>
            <div>
                <form:input type="password" path="password" placeholder="Password"></form:input>
            </div>
            <div>
                <form:input type="password" path="passwordConfirm"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="password"></form:errors>
                    ${passwordError}
            </div>
            <div>
                <input hidden type="text" value="user" name="role">
            </div>
            <button type="submit">Sing Up</button>
            </form:form>
        </div>
    </body>
</html>