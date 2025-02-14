package servlets;

import beans.Order;
import dao.OrderDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBConnection;

public class OrderServlet extends HttpServlet {
    // Xử lý đặt hàng khi người dùng xác nhận thanh toán
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        // Giả sử id khách hàng được lưu trong session sau khi đăng nhập
        Integer idKhachHang = (Integer) session.getAttribute("customerId");
        if (idKhachHang == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy tổng tiền đơn hàng từ session hoặc tính lại từ giỏ hàng
        Double tongTien = (Double) session.getAttribute("totalPrice");
        if (tongTien == null) {
            tongTien = 0.0;
        }

        // Tạo đơn hàng với thời gian hiện tại
        Order order = new Order();
        order.setIdKhachHang(idKhachHang);
        order.setNgayDat(new Date());
        order.setTongTien(tongTien);

        try (Connection conn = DBConnection.getConnection()) {
            OrderDAO orderDAO = new OrderDAO(conn);
            boolean orderId = orderDAO.insertOrder(order);
            // Lưu thông tin đơn hàng vào session nếu cần hiển thị sau này
            session.setAttribute("currentOrder", order);
            // Sau khi đặt hàng thành công, chuyển hướng đến trang xác nhận đơn hàng
            response.sendRedirect("orderConfirmation.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Order failed due to system error");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Nếu cần hiển thị trang đặt hàng bằng doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderConfirmation.jsp");
        dispatcher.forward(request, response);
    }
}
