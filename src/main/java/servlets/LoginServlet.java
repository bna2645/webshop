package servlets;

import dao.CustomerDAO;
import beans.Customer;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Connection;
import util.DBConnection;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            CustomerDAO customerDAO = new CustomerDAO(conn);
            Customer customer = customerDAO.login(username, password);
            if(customer != null) {
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                response.sendRedirect("ProductListServlet");
            } else {
                request.setAttribute("error", "Wrong login information.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "System errors.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
