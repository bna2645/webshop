package dao;

import beans.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    // Thêm một đơn hàng mới
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO donhang(idKhachHang, ngayDat, tongTien) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, order.getIdKhachHang());
            // Chuyển đổi java.util.Date thành java.sql.Timestamp
            Timestamp timestamp = new Timestamp(order.getNgayDat().getTime());
            stmt.setTimestamp(2, timestamp);
            stmt.setDouble(3, order.getTongTien());
            return stmt.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách đơn hàng của khách hàng theo id
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM donhang WHERE idKhachHang = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setIdKhachHang(rs.getInt("idKhachHang"));
                order.setNgayDat(rs.getTimestamp("ngayDat"));
                order.setTongTien(rs.getDouble("tongTien"));
                orders.add(order);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
