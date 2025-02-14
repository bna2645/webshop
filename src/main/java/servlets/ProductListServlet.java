package servlets;

import dao.ProductDAO;
import beans.Product;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.DBConnection;

public class ProductListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            ProductDAO productDAO = new ProductDAO(conn);
            List<Product> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("productList.jsp");
            dispatcher.forward(request, response);
        } catch(Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Product listing errors");
        }
    }
}
