package servlets;

import dao.CustomerDAO;
import beans.Customer;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.Connection;
import util.DBConnection;
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setHoTen(hoTen);
        customer.setEmail(email);

        try (Connection conn = DBConnection.getConnection()) {
            CustomerDAO customerDAO = new CustomerDAO(conn);
            if(customerDAO.register(customer)) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("error", "Registration failed!");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "System errors.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }
}
