<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        <%@include file="/styles/index.css" %>
    </style>
<body>
<div class="form-wrap">
    <div class="tabs">
        <h3 class="login-tab"><a class="active" href="#login-tab-content">Login</a></h3>
        <h3 class="signup-tab"><a href="#signup-tab-content">Sign Up</a></h3>
    </div><!--.tabs-->

    <div class="tabs-content">
        <div id="signup-tab-content" >
            <form class="signup-form" name="registration" action="/registration" method="post">
                <input type="text" name="login" class="input" required placeholder="Username">
                <input type="password" name="password" class="input" required placeholder="Password">
                <input type="text" name="name" class="input" required placeholder="First Name">
                <input type="text" name="surname" class="input" required placeholder="Second Name">
                <input type="submit" class="button" value="Sign Up">
            </form><!--.login-form-->
            <div class="help-text">
                <p>By signing up, you agree to our</p>
                <p><a href="#">Terms of service</a></p>
            </div><!--.help-text-->
        </div><!--.signup-tab-content-->

        <div id="login-tab-content" class="active">
            <form class="login-form" action="/login" method="post">
                <input type="text" name="login" class="input" required placeholder="Username">
                <input type="password" name="password" class="input" required placeholder="Password">
                <input type="checkbox" class="checkbox" id="remember_me">
                <label for="remember_me">Remember me</label>

                <input type="submit" class="button" value="Login">
            </form><!--.login-form-->
            <div class="help-text">
                <p><a href="#">Forget your password?</a></p>
            </div><!--.help-text-->
        </div><!--.login-tab-content-->
    </div><!--.tabs-content-->
</div><!--.form-wrap-->

<script>
    jQuery(document).ready(function ($) {
        const tab = $('.tabs h3 a');

        tab.on('click', function (event) {
            event.preventDefault();
            tab.removeClass('active');
            $(this).addClass('active');

            const tab_content = $(this).attr('href');
            $('div[id$="tab-content"]').removeClass('active');
            $(tab_content).addClass('active');
        });
    });
</script>

</body>
</html>
