<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<head>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<div class="container-fluid" style="font-size: 20px">
    <div class="container">
        <h1>Hello, ${user.name} ${user.surname}</h1>

        <a href="/logout" class="btn btn-danger btn-lg">
            <span class="glyphicon glyphicon-log-out"></span> Log Out
        </a>
        <a style="float: right">Balance : ${user.balance} $</a>
    </div>
</div>
<div class="container">
    <div class="text-info text-center">
        <h2>Available cruises : </h2>
    </div>
    <form action="/cabinet" method="post">
        <table class="table table-bordered">
            <tr>
                <th>Name</th>
                <th>Passenger Amount</th>
                <th>Route to</th>
                <th>Route from</th>
                <th>Date of start trip</th>
                <th>Date of end trip</th>
            </tr>

            <c:forEach items="${ships}" var="ships">
                <tbody>
                <tr>
                    <td><c:out value="${ships.name}"></c:out></td>
                    <td><c:out value="${ships.passengerAmount}"></c:out></td>
                    <td><c:out value="${ships.routeTo}"></c:out></td>
                    <td><c:out value="${ships.routeFrom}"></c:out></td>
                    <td><c:out value="${ships.travelStart}"></c:out></td>
                    <td><c:out value="${ships.travelEnd}"></c:out></td>
                    <td><p><input type="checkbox" name="${"shipId"}" value="${ships.id}"></p></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>

        <div class="text-center">
            <input class="btn btn-warning btn-padding-size" type="submit" value="Order">
        </div>
    </form>

    <c:if test="${fn:length(cruisesInfo) > 0}">
    <div class="text-info text-center">
        <h2>Your cruises : </h2>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Name</th>
            <th>Passenger Amount</th>
            <th>Route to</th>
            <th>Route from</th>
            <th>Date of start trip</th>
            <th>Date of end trip</th>
            <th>Type of room</th>
            <th>Price</th>
            <th>Order number</th>
        </tr>
        </thead>
        <c:forEach items="${userShips}" var="amount" varStatus="generalIndex">
            <c:set var="index" value="${generalIndex.index}"/>
            <c:forEach items="${userShips}" var="userShips" varStatus="userShipsIndex">
                <c:if test="${userShipsIndex.index == index}">
                    <tr>
                    <td><c:out value="${userShips.name}"></c:out></td>
                    <td><c:out value="${userShips.passengerAmount}"></c:out></td>
                    <td><c:out value="${userShips.routeTo}"></c:out></td>
                    <td><c:out value="${userShips.routeFrom}"></c:out></td>
                    <td><c:out value="${userShips.travelStart}"></c:out></td>
                    <td><c:out value="${userShips.travelEnd}"></c:out></td>
                </c:if>
            </c:forEach>
            <c:forEach items="${cruisesInfo}" var="cruisesInfo" varStatus="cruise">
                <c:if test="${cruise.index == index}">
                    <td><c:out value="${cruisesInfo.roomType}"></c:out></td>
                    <td><c:out value="${cruisesInfo.totalPrice}"></c:out></td>
                    <td><c:out value="${cruisesInfo.id}"></c:out></td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:forEach>
    </table>
</div>
</c:if>
</body>
</html>
