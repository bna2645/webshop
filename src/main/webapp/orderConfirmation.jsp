<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Xác nhận đơn hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Đơn hàng của bạn đã được đặt thành công!</h2>
    <c:if test="${not empty sessionScope.currentOrder}">
        <p><strong>Mã đơn hàng:</strong> ${sessionScope.currentOrder.id}</p>
        <p><strong>Ngày đặt:</strong> ${sessionScope.currentOrder.ngayDat}</p>
        <p><strong>Tổng tiền:</strong> ${sessionScope.currentOrder.tongTien}</p>
    </c:if>
    <a href="ProductListServlet" class="btn btn-primary">Tiếp tục mua sắm</a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
