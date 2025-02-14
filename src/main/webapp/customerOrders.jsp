<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đơn hàng của tôi</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h2>Danh sách đơn hàng</h2>
  <c:if test="${empty orders}">
    <div class="alert alert-info">Bạn chưa có đơn hàng nào!</div>
  </c:if>
  <c:if test="${not empty orders}">
    <c:forEach var="order" items="${orders}">
      <div class="card mb-3">
        <div class="card-header">
          <strong>Đơn hàng #${order.id}</strong> - Ngày: ${order.ngayDat} - Tổng tiền: ${order.tongTien}
        </div>
        <div class="card-body">
          <h5>Chi tiết đơn hàng</h5>
          <table class="table table-bordered">
            <thead>
            <tr>
              <th>ID Sản phẩm</th>
              <th>Số lượng</th>
              <th>Đơn giá</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="detail" items="${requestScope['details_' + order.id]}">
              <tr>
                <td>${detail.idSanPham}</td>
                <td>${detail.soLuong}</td>
                <td>${detail.donGia}</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </c:forEach>
  </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
