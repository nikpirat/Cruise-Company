<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
</head>
<style>
    a {
        color: black;
        font-size: 30px;
        border: 4px black solid;
        padding: 15px;
        margin: 10px;
    }

    h1 {
        font-size: 50px;
    }
</style>
<body>
<div class="index">
    <div class="container text-center">
        <h1>Cruise Company</h1>
        <div class="container margin-top">
            <a href="/registration" class="btn btn-info btn-lg">
                <span class="glyphicon glyphicon-registration-mark"></span> Registration
            </a>
            <a href="/login" class="btn btn-primary btn-lg">
                <span class="glyphicon glyphicon-log-in"></span> Log In
            </a>
        </div>
    </div>
</div>

</body>
</html>
