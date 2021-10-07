<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%--<div class="container right-panel-active">--%>
<%--    <!-- Sign Up -->--%>

<%--    <div class="container__form container--signup">--%>
<%--        <form name="registration" action="/registration" method="post" class="form" id="form1" >--%>
<%--            <h2 class="form__title">Sign Up</h2>--%>
<%--            <input type="text" name="login" placeholder="Login" required class="input"/>--%>
<%--            <input type="password" name="password" placeholder="Password" required class="input"/>--%>
<%--            <input type="text" name="name" placeholder="First Name" required class="input"/>--%>
<%--            <input type="text" name="surname" placeholder="Second Name" required class="input"/>--%>
<%--            <button class="btn" type="submit" value="Register">Sign Up</button>--%>
<%--        </form>--%>
<%--    </div>--%>

<%--    <!-- Sign In -->--%>
<%--    <div class="container__form container--signin">--%>
<%--        <form action="/login" method="post" class="form" id="form2">--%>
<%--            <h2 class="form__title">Sign In</h2>--%>
<%--            <input type="text" name="login" required placeholder="Login" class="input"/>--%>
<%--            <input type="password" name="password" required placeholder="Password" class="input"/>--%>
<%--            <a href="#" class="link">Forgot your password?</a>--%>
<%--            <button class="btn" type="submit">Sign In</button>--%>
<%--        </form>--%>
<%--    </div>--%>

<%--    <!-- Overlay -->--%>
<%--    <div class="container__overlay">--%>
<%--        <div class="overlay">--%>
<%--            <div class="overlay__panel overlay--left">--%>
<%--                <button class="btn" id="signIn">Sign In</button>--%>
<%--            </div>--%>
<%--            <div class="overlay__panel overlay--right">--%>
<%--                <button class="btn" id="signUp">Sign Up</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<script>--%>
<%--    const signInBtn = document.getElementById("signIn");--%>
<%--    const signUpBtn = document.getElementById("signUp");--%>
<%--    const container = document.querySelector(".container");--%>

<%--    signInBtn.addEventListener("click", () => {--%>
<%--        container.classList.remove("right-panel-active");--%>
<%--    });--%>

<%--    signUpBtn.addEventListener("click", () => {--%>
<%--        container.classList.add("right-panel-active");--%>
<%--    });--%>

<%--</script>--%>

</body>
</html>
