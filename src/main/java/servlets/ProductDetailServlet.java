package servlets;

import dao.ProductDAO;
import beans.Product;
import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import util.DBConnection;

public class ProductDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if(idParam == null) {
            response.sendRedirect("ProductListServlet");
            return;
        }
        try {
            int productId = Integer.parseInt(idParam);
            try (Connection conn = DBConnection.getConnection()) {
                ProductDAO productDAO = new ProductDAO(conn);
                Product product = productDAO.getProductById(productId);
                if(product != null) {
                    request.setAttribute("product", product);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("productDetail.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("ProductListServlet");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Product detail retrieval error");
        }
    }
}
