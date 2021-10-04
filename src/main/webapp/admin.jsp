<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserDao: Huddson
  Date: 27.12.2018
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<head>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</head>


<c:if test="${not empty error}">
    <div class="container text-center text-danger">
        <h1> ${error} </h1>
        <p>
            <a href="/cabinet" class="btn btn-info btn-lg">
                <span class="glyphicon glyphicon-arrow-right"></span> Back
            </a>
        </p>
    </div>
</c:if>


<div class="container-fluid" style="font-size: 20px">
    <div class="container">
        <h1>Hello, Admin</h1>
        <a href="/logout" class="btn btn-danger btn-lg">
            <span class="glyphicon glyphicon-log-out"></span> Log Out
        </a>
    </div>
</div>
<div class="container-fluid">
    <div class="container">
        <table class="table table-bordered">
            <tr>
                <th>User Login</th>
                <th>User Name</th>
                <th>User Surname</th>
                <th>User Balance</th>
            </tr>
            <c:forEach items="${usersPag}" var="users">
                <tr>
                    <td>
                        <c:url value="admin" var="URL">
                            <c:param name="id" value="${users.id}"/>
                        </c:url>
                        <a href="${URL}"><c:out value="${users.login}"></c:out> </a>
                    </td>
                    <td><c:out value="${users.name}"></c:out></td>
                    <td><c:out value="${users.surname}"></c:out></td>
                    <td><c:out value="${users.balance}"></c:out></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="row">
        <form action="/admin" method="get">
            <div class="col-md-2 col-md-offset-5">
                <input type="hidden" name="currentPage" value="1">
                <div class="form-group" style="float: left;padding: 5px">
                    <select class="form-control" id="records" name="recordsPerPage">
                        <option value="1">1</option>
                        <option value="3" selected>3</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <div style="float: left; padding: 5px">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </form>

    </div>
    <div class="col-md-2 col-md-offset-5">
        <nav aria-label="Navigation for countries">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="admin?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="/admin?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item"><a class="page-link"
                                             href="/admin?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>


<form method="post" action="/admin">
    <div class="container">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
            </thead>
            <c:forEach items="${bonuses}" var="bonuses">
                <tr>
                    <td><c:out value="${bonuses.name}"></c:out></td>
                    <td><c:out value="${bonuses.description}"></c:out></td>
                    <td><p><input type="checkbox" name="bonuses" value="${bonuses.id}"></p></td>

                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="col-lg-10">
            <div class="col-lg-2">
                <label>
                    <input type="radio" name="roomType" id="1" value="PRESIDENT">
                    President
                </label>
            </div>
            <div class="col-lg-2">
                <label>
                    <input type="radio" name="roomType" id="2" value="COMFORT">
                    Comfort
                </label>
            </div>
            <div class="col-lg-2">
                <label>
                    <input type="radio" name="roomType" id="3" value="STANDART">
                    Standart
                </label>
            </div>
            <div class="col-lg-2">
                <input class="btn btn-warning btn-padding-size" type="submit" name="bonusAction" value="Add Bonuses">
            </div>
            <div class="col-lg-2">
                <input class="btn btn-danger btn-padding-size" type="submit" name="bonusAction" value="Remove Bonuses">
            </div>
        </div>

    </div>
</form>


<form name="registration" method="post" action="/createBonus">
    <div class="container">
        <hr>
        <div class="col-md-12">
            <div class="col-md-offset-4">
                <div class="row">
                    <div class="col-xs-2">
                        <label>Bonus Name :</label></div>
                    <div class="col-xs-3">
                        <input type="text" name="name" required placeholder="Enter Name">
                    </div>
                </div>
            </div>
            <div class="col-md-offset-4">
                <div class="row">
                    <div class="col-xs-2">
                        <label class="mail">Description :</label></div>
                    <div class="col-xs-3">
                        <input type="text" name="description" placeholder="Enter Description">
                    </div>
                </div>
                <div class="col-sm-offset-1 margin-top-5">
                    <input class="btn btn-lg btn-success" type="submit" value="Add">
                </div>
            </div>
        </div>
    </div>
</form>

</body>
</html>
