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
    public boolean insertOrder(Order order) {
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

//import beans.Order;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.ResultSet;
//
//public class OrderDAO {
//    private Connection conn;
//
//    public OrderDAO(Connection conn) {
//        this.conn = conn;
//    }
//
//    // Phương thức insertOrder sẽ chèn đơn hàng vào bảng "donhang"
//    public int insertOrder(Order order) throws SQLException {
//        String sql = "INSERT INTO donhang (idKhachHang, ngayDat, tongTien) VALUES (?, ?, ?)";
//        // Lấy id sinh tự động
//        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            stmt.setInt(1, order.getIdKhachHang());
//            // Sử dụng java.sql.Timestamp để lưu giá trị ngày giờ
//            stmt.setTimestamp(2, new java.sql.Timestamp(order.getNgayDat().getTime()));
//            stmt.setDouble(3, order.getTongTien());
//            int affectedRows = stmt.executeUpdate();
//            if (affectedRows == 0) {
//                throw new SQLException("Chèn đơn hàng thất bại, không có dòng nào được ảnh hưởng.");
//            }
//            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    order.setId(generatedKeys.getInt(1));
//                } else {
//                    throw new SQLException("Chèn đơn hàng thất bại, không lấy được ID.");
//                }
//            }
//            return order.getId();
//        }
//    }
//
//}
