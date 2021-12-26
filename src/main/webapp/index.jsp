<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : \"en\"}"
       scope="session"/>
<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="text" />

<!DOCTYPE html>
<html lang="${param.lang}">
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
        <h3 class="login-tab"><a class="active" href="#login-tab-content"><fmt:message key="login.login"/></a></h3>
        <h3 class="signup-tab"><a href="#signup-tab-content">Sign Up</a></h3>
    </div>

    <div class="tabs-content">
        <div id="signup-tab-content" >
            <form class="signup-form" name="registration" action="/registration" method="post">
                <input type="text" name="login" class="input" required placeholder="Username">
                <input type="password" name="password" class="input" required placeholder="Password">
                <input type="text" name="name" class="input" required placeholder="First Name">
                <input type="text" name="surname" class="input" required placeholder="Second Name">
                <input type="submit" class="button" value="Sign Up">
            </form>
        </div>

        <div id="login-tab-content" class="active">
            <form class="login-form" action="/login" method="post">
                <input type="text" name="login" class="input" required placeholder="<fmt:message key="login.username"/>">
                <input type="password" name="password" class="input" required placeholder="<fmt:message key="login.password"/>">

                <input type="submit" class="button" value="<fmt:message key="login.button"/>">
            </form>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}?language=en">
                <fmt:message key="EN"/>
            </a>
            <a href="${pageContext.request.contextPath}?language=ru">
                <fmt:message key="RU"/>
            </a>
        </div>
    </div>
</div>

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
