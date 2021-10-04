<%--
  Created by IntelliJ IDEA.
  User: Huddson
  Date: 31.12.2018
  Time: 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</head>
<body>

<div class="login">
    <div class="container text-center">
        <div class="container margin-top">
            <h3>Enter Login and Password</h3><br>
            <form method="post" action="/login">

                <input type="text" required placeholder="login" name="login"><br>
                <input type="password" required placeholder="password" name="password"><br><br>
                <button class="btn btn-lg btn-primary" type="submit">Login</button>
                <a href="/registration" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-log-in"></span> Create
                </a>
            </form>

        </div>
    </div>
</div>


</body>
</html>
