<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Your Shopping Cart</h2>
    <c:if test="${empty cart}">
        <div class="alert alert-info">The cart is empty.</div>
    </c:if>
    <c:if test="${not empty cart}">
        <table class="table table-bordered mt-3">
            <thead>
            <tr>
                <th>STT</th>
                <th>Product Name</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${cart}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${product.tenSanPham}</td>
                    <td>${product.gia}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <a href="ProductListServlet" class="btn btn-primary">Continue to purchase</a>
    <a href="LogoutServlet" class="btn btn-secondary float-right">Logout</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
