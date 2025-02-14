package dao;

import beans.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    // Lấy danh sách tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTenSanPham(rs.getString("tenSanPham"));
                product.setGia(rs.getDouble("gia"));
                product.setMoTa(rs.getString("moTa"));
                list.add(product);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy thông tin sản phẩm theo ID
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM sanpham WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTenSanPham(rs.getString("tenSanPham"));
                product.setGia(rs.getDouble("gia"));
                product.setMoTa(rs.getString("moTa"));
                return product;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
