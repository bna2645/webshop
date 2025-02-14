<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Product list</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h2 class="mb-4">Product list</h2>
  <div class="row">
    <c:forEach var="product" items="${products}">
      <div class="col-md-4 mb-4">
        <div class="card h-100">
          <div class="card-body">
            <h5 class="card-title">${product.tenSanPham}</h5>
            <p class="card-text">Price: ${product.gia}</p>
            <a href="ProductDetailServlet?id=${product.id}" class="btn btn-primary">View details</a>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
  <a href="CartServlet" class="btn btn-success mt-3">View Cart</a>
  <a href="LogoutServlet" class="btn btn-secondary mt-3 float-right">Logout</a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
