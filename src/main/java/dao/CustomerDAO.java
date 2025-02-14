package dao;

import beans.Customer;
import java.sql.*;

public class CustomerDAO {
    private Connection conn;

    // Khởi tạo kết nối từ file cấu hình, có thể lấy thông tin từ properties
    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean register(Customer customer) {
        String sql = "INSERT INTO khachhang(username, password, hoTen, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getPassword());
            stmt.setString(3, customer.getHoTen());
            stmt.setString(4, customer.getEmail());
            return stmt.executeUpdate() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer login(String username, String password) {
        String sql = "SELECT * FROM khachhang WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setHoTen(rs.getString("hoTen"));
                customer.setEmail(rs.getString("email"));
                return customer;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
