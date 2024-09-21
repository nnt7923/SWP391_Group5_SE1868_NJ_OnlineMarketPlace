/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dao.AccountDAO;
import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Role;

/**
 *
 * @author phamd
 */
@WebServlet({
    "/login",
    "/register"// List roles
})
public class AuthController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private AccountDAO accountDAO;

    private RoleDAO roleDAO;

    @Override
    public void init() throws ServletException {
        roleDAO = new RoleDAO();
        accountDAO = new AccountDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getServletPath();
            switch (path) {
                case "/login" ->
                    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                case "/register" ->
                    showStoreForm(request, response);
                default ->
                    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showStoreForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        List<Role> roles = roleDAO.getAll();
        request.setAttribute("roles", roles);

        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        try {
            switch (path) {
                case "/register":
                    register(request, response);
                    break;
            }
        } catch (SQLServerException e) {
            throw new ServletException(e);
        } catch (SQLException ex) {
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        AccountDAO dao = new AccountDAO();
//        Account account = dao.login(username, password);
//
//        if (account != null) {
//            HttpSession session = request.getSession();
//            session.setAttribute("account", account);
//            response.sendRedirect("index.html");  // Chuyển hướng khi đăng nhập thành công
//        } else {
//            request.setAttribute("errorMessage", "Invalid username or password");
//            request.getRequestDispatcher("login.jsp").forward(request, response);  // Trả về trang login nếu thất bại
//        }
//    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();

        String routeContext = "/register";

        String username = request.getParameter("username");

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String re_password = request.getParameter("re_password");

        String role = request.getParameter("role");

        if (role == null || email == null || password == null || username == null) {
            session.setAttribute("error", "Invalid data.");
        } else if (!password.equals(re_password)) {
            session.setAttribute("error", "Invalid password.");
        } else {
            Account account = new Account(username, email, password, new Role(Integer.parseInt(role)));

            if (accountDAO.register(account)) {
                session.setAttribute("message", "Tạo tài khoản thành công.");
            } else {
                session.setAttribute("error", "Failed to save account.");
            }
        }
        response.sendRedirect(request.getContextPath() + routeContext);
    }
}
