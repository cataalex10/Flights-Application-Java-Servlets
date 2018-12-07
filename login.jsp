<%--
  Created by IntelliJ IDEA.
  User: Cata
  Date: 28-Oct-18
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>LOGIN</title>
    <link rel="stylesheet" type="text/css" href="/resources/resources/css/loginStyle.css">
</head>
<body class="logincata">
<section class="login">
    <div class="titulo">AIRPORT FLIGHTS LOGIN</div>
    <form action="LoginServlet" method="post">
        <input name="username" type="text" required title="Username required" placeholder="username" data-icon="U"/>
        <input name="password" type="password" required title="Password required" placeholder="password" data-icon="x"/>
        <br><br>
        <center>
            <input type="submit" value="Login">
        </center>
    </form>
</section>
</body>
</html>