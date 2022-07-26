<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Shop</title>
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
                        <a class="nav-link" href="${contextPath}/category">Category</a>
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
            <h1>Cart</h1>
            <table class="table">
                <tr>
                    <th><h3>Photo</h3></th>
                    <th><h3>Brand</h3></th>
                    <th><h3>Model</h3></th>
                    <th><h3>Price</h3></th>
                    <th><h3>Quantity</h3></th>
                    <th><h3></h3></th>
                </tr>
                <c:if test="${not empty user_cart}">
                    <c:forEach items="${user_cart}" var="product">
                        <tr>
                            <td>
                                <img class="card-img" style="width:150px;height:120px"
                                     src="${contextPath}/images/${product.getImagePath()}"
                                     alt="Card image">
                            </td>
                            <td style="font-style: italic">
                                <strong>${product.getBrand()}</strong>
                            </td>
                            <td style="font-style: italic">
                                <strong>${product.getModel()}</strong>
                            </td>
                            <td style="font-style: italic">
                                <strong>${product.getPrice()}$</strong>
                            </td>
                            <td>
                                <strong>${product.getQuantity()}</strong>
                            </td>
                            <td>
                                <a href="${contextPath}/cart/delete?product_id=${product.getId()}">
                                    <button type="submit" class="btn btn-primary">Delete
                                    </button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty user_cart}">
                    <tr>
                        <td>
                            <p>Список пуст</p>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        <a href="${contextPath}/cart/reset">
                            <button type="submit" class="btn btn-primary">Clear your cart
                            </button>
                        </a>
                    </td>
                    <td>

                    </td>
                    <td>

                    </td>
                    <td>

                    </td>
                    <td>
                        <a href="${contextPath}/cart/confirmOrder">
                            <button class="btn btn-primary" type="submit">Confirm order</button>
                        </a>
                    </td>
                    <td>
                        <h3><strong>Total price: ${total_price}$</strong></h3>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
