package servlets;

import beans.Order;
import beans.OrderDetail;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBConnection;

public class CustomerOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Giả sử id khách hàng lưu trong session
        HttpSession session = request.getSession();
        Integer idKhachHang = (Integer) session.getAttribute("customerId");
        if(idKhachHang == null){
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Lấy thông tin đơn hàng của khách hàng (ví dụ, bạn có thể tạo thêm một phương thức getOrdersByCustomerId trong OrderDAO)
            OrderDAO orderDAO = new OrderDAO(conn);
            List<Order> orders = orderDAO.getOrdersByCustomerId(idKhachHang);

            // Với mỗi đơn hàng, lấy các chi tiết đơn hàng
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO(conn);
            for(Order order : orders){
                List<OrderDetail> details = orderDetailDAO.getOrderDetailsByOrderId(order.getId());
                // Lưu thông tin chi tiết (bạn có thể set vào Order nếu mở rộng model hoặc map riêng trong request)
                request.setAttribute("details_" + order.getId(), details);
            }

            request.setAttribute("orders", orders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("customerOrders.jsp");
            dispatcher.forward(request, response);
        } catch(Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi lấy thông tin đơn hàng");
        }
    }
}
