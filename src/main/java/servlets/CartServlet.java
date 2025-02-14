package servlets;

import beans.Product;
import dao.ProductDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBConnection;

public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if(cart == null){
            cart = new ArrayList<>();
        }
        if("add".equals(action)) {
            String idParam = request.getParameter("id");
            if(idParam != null){
                try {
                    int productId = Integer.parseInt(idParam);
                    try (Connection conn = DBConnection.getConnection()) {
                        ProductDAO productDAO = new ProductDAO(conn);
                        Product product = productDAO.getProductById(productId);
                        if(product != null) {
                            cart.add(product);
                            session.setAttribute("cart", cart);
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        } else if("remove".equals(action)) {
            String idParam = request.getParameter("id");
            if(idParam != null){
                try {
                    int productId = Integer.parseInt(idParam);
                    // Xóa lần xuất hiện đầu tiên của sản phẩm có id trùng với productId
                    Iterator<Product> iterator = cart.iterator();
                    while(iterator.hasNext()){
                        Product p = iterator.next();
                        if(p.getId() == productId){
                            iterator.remove();
                            break;
                        }
                    }
                    session.setAttribute("cart", cart);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response.sendRedirect("CartServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if(cart == null) {
            cart = new ArrayList<>();
        }

        // Tính tổng tiền của các sản phẩm trong giỏ hàng
        double total = 0;
        for(Product p : cart) {
            total += p.getGia();
        }
        request.setAttribute("totalPrice", total);
        request.setAttribute("cart", cart);

        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
        dispatcher.forward(request, response);
    }
}