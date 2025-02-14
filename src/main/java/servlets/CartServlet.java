package servlets;

import beans.Product;
import dao.ProductDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.DBConnection;

public class CartServlet extends HttpServlet {
    // Xử lý thêm sản phẩm vào giỏ hàng
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if("add".equals(action)) {
            String idParam = request.getParameter("id");
            if(idParam != null) {
                try {
                    int productId = Integer.parseInt(idParam);
                    try (Connection conn = DBConnection.getConnection()) {
                        ProductDAO productDAO = new ProductDAO(conn);
                        Product product = productDAO.getProductById(productId);
                        if(product != null) {
                            HttpSession session = request.getSession();
                            List<Product> cart = (List<Product>) session.getAttribute("cart");
                            if(cart == null) {
                                cart = new ArrayList<>();
                            }
                            cart.add(product);
                            session.setAttribute("cart", cart);
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response.sendRedirect("CartServlet");
    }

    // Hiển thị danh sách sản phẩm trong giỏ hàng
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if(cart == null) {
            cart = new ArrayList<>();
        }
        request.setAttribute("cart", cart);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
        dispatcher.forward(request, response);
    }
}
