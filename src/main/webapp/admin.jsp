<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<%--    <div class="row">--%>
<%--        <form action="/admin" method="get">--%>
<%--            <div class="col-md-2 col-md-offset-5">--%>
<%--                <input type="hidden" name="currentPage" value="1">--%>
<%--                <div class="form-group" style="float: left;padding: 5px">--%>
<%--                    <select class="form-control" id="records" name="recordsPerPage">--%>
<%--                        <option value="1">1</option>--%>
<%--                        <option value="3" selected>3</option>--%>
<%--                        <option value="5">5</option>--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--                <div style="float: left; padding: 5px">--%>
<%--                    <button type="submit" class="btn btn-primary">Submit</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </form>--%>

<%--    </div>--%>
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

</body>
</html>
