<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Result of search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div>
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
                           href="${contextPath}/category">Category</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${contextPath}/cart/open">Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/login">Logout</a>
                    </li>
                    <%--                    <li class="nav-item dropdown">--%>
                    <%--                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop"--%>
                    <%--                           data-toggle="dropdown">--%>
                    <%--                            Category--%>
                    <%--                        </a>--%>
                    <%--                        <div class="dropdown-menu">--%>
                    <%--                            <c:if test="${not empty category}">--%>
                    <%--                                <c:forEach items="${category}" var="category">--%>
                    <%--                                    <a class="dropdown-item"--%>
                    <%--                                       href="${contextPath}/category/${category.getId()}">${category.getName()}--%>
                    <%--                                        <img class="card-img" style="width:150px;height:120px"--%>
                    <%--                                             src="${contextPath}/images/${category.getImagePath()}"--%>
                    <%--                                             alt="Card image">--%>
                    <%--                                    </a>--%>
                    <%--                                </c:forEach>--%>
                    <%--                            </c:if>--%>
                    <%--                        </div>--%>
                    <%--                    </li>--%>
                </ul>
                <form action="${contextPath}/search" class="d-flex">
                    <input class="form-control me-2" type="text" id="searchField" name="searchField"
                           placeholder="Search">
                    <button class="btn btn-primary" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>

    <div id="list-product" class="container-fluid mt-3">
        <div class="container">
            <h1>List devices</h1>
            <table class="table">
                <tr>
                    <th><h3>Photo</h3></th>
                    <th><h3>Brand</h3></th>
                    <th><h3>Model</h3></th>
                    <th><h3>Price</h3></th>
                    <th><h3>Available</h3></th>
                    <th><h3></h3></th>
                </tr>
                <c:if test="${not empty devices}">
                    <c:forEach items="${devices}" var="product">
                        <tr>
                            <td>
                                <a href="${contextPath}/product?product_id=${product.getId()}">
                                    <img class="card-img" style="width:150px;height:120px"
                                         src="${contextPath}${product.getImagePath()}"
                                         alt="Card image">
                                </a>
                            </td>
                            <td style="font-style: italic">
                                <a href="${contextPath}/product?product_id=${product.getId()}">
                                    <strong>${product.getBrand()}</strong>
                                </a>
                            </td>
                            <td style="font-style: italic">
                                <a href="${contextPath}/product?product_id=${product.getId()}">
                                    <strong>${product.getModel()}</strong>
                                </a>
                            </td>
                            <td style="font-style: italic">
                                <a href="${contextPath}/product?product_id=${product.getId()}">
                                    <strong>${product.getPrice()}$</strong>
                                </a>
                            </td>
                            <td style="font-style: italic">
                                <a href="${contextPath}/product?product_id=${product.getId()}">
                                    <strong>${product.getQuantity()}</strong>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty devices}">
                    <tr>
                        <td>
                            <p>Список пуст</p>
                        </td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</div>
</body>
</html>
