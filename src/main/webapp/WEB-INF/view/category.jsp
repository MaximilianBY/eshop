<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>My Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${contextPath}/account">My account</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link"
                       href="${contextPath}/cart/open">Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/login">Logout</a>
                </li>
            </ul>
            <form action="${contextPath}/search" class="d-flex">
                <input class="form-control me-2" type="text" id="searchField" name="searchField"
                       placeholder="Search">
                <button class="btn btn-primary" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<div>
    <h2>Popular categories</h2>

    <div class="container-fluid">
        <c:if test="${not empty categories}">
            <div class="row">
                <c:forEach items="${categories}" var="category">
                    <div class="card w-25 m-1" type="category">
                        <div class="card-body">
                            <a href="${contextPath}/devices?category_id=${category.getId()}">${category.getName()}
                                <img class="card-img" style="width:150px;height:120px"
                                     src="${contextPath}${category.getImagePath()}"
                                     alt="Card image">
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
