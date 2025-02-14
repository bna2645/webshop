package dao;

import beans.OrderDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {
    private Connection conn;

    public OrderDetailDAO(Connection conn) {
        this.conn = conn;
    }

    // Chèn bản ghi chi tiết đơn hàng mới
    public void insertOrderDetail(OrderDetail orderDetail) throws SQLException {
        String sql = "INSERT INTO chitietdonhang (idDonHang, idSanPham, soLuong, donGia) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDetail.getIdDonHang());
            stmt.setInt(2, orderDetail.getIdSanPham());
            stmt.setInt(3, orderDetail.getSoLuong());
            stmt.setDouble(4, orderDetail.getDonGia());
            stmt.executeUpdate();
        }
    }

    // Lấy danh sách các chi tiết đơn hàng theo id đơn hàng
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietdonhang WHERE idDonHang = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    OrderDetail detail = new OrderDetail();
                    detail.setId(rs.getInt("id"));
                    detail.setIdDonHang(rs.getInt("idDonHang"));
                    detail.setIdSanPham(rs.getInt("idSanPham"));
                    detail.setSoLuong(rs.getInt("soLuong"));
                    detail.setDonGia(rs.getDouble("donGia"));
                    list.add(detail);
                }
            }
        }
        return list;
    }
}
