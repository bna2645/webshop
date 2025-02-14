<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Product detail</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h2>${product.tenSanPham}</h2>
  <p>Price: ${product.gia}</p>
  <p>${product.moTa}</p>
  <form action="CartServlet" method="post">
    <input type="hidden" name="id" value="${product.id}" />
    <input type="hidden" name="action" value="add" />
    <button type="submit" class="btn btn-primary">Add to cart</button>
  </form>
  <a href="ProductListServlet" class="btn btn-secondary mt-3">Back to product list</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
