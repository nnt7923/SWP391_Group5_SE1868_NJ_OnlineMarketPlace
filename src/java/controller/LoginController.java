package controller;

import dao.AccountDAO;
import model.Account;
import model.GoogleAccount;
import model.Role;
import controller.GoogleLogin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
    response.setContentType("text/html;charset=UTF-8");

    String code = request.getParameter("code");
    String error = request.getParameter("error");

    if (error != null) {
        request.setAttribute("errorMessage", "Authorization failed or cancelled.");
        request.getRequestDispatcher("login.jsp").forward(request, response);
        return;
    }

    if (code != null) {
        GoogleLogin googleLogin = new GoogleLogin();
        try {
            String accessToken = googleLogin.getToken(code);
            GoogleAccount googleAccount = googleLogin.getUserInfo(accessToken);
            AccountDAO accountDAO = new AccountDAO();

            Account account = accountDAO.getAccountByEmail(googleAccount.getEmail());
            if (account == null) {
                // Tài khoản không tồn tại, tạo mới
                account = new Account();
                account.setUsername(googleAccount.getEmail().split("@")[0]);
                account.setEmail(googleAccount.getEmail());

                // Tạo mật khẩu ngẫu nhiên cho tài khoản Google
                String randomPassword = accountDAO.generateRandomPassword();
                account.setPassword(randomPassword);

                account.setRoleID(3);
                account.setStatus("active");
                accountDAO.addAccount(account);

                // Lấy lại thông tin account mới thêm từ DB để có ID chính xác
                account = accountDAO.getAccountByEmail(googleAccount.getEmail());
            }

            HttpSession session = request.getSession();
            session.setAttribute("account", account);

            // Lấy vai trò của tài khoản từ cơ sở dữ liệu và lưu vào session
            Role role = accountDAO.getRoleByAccountId(account.getId());
            
            if (role == null) {
                // Xử lý trường hợp role không tìm thấy, tránh NullPointerException
                request.setAttribute("errorMessage", "Role not found for the account.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            session.setAttribute("role", role);

            // Điều hướng dựa trên vai trò
            if (role.getRoleName().equals("admin")) {
                response.sendRedirect("admin/dashboard.jsp");
            } else if (role.getRoleName().equals("seller")) {
                response.sendRedirect("seller/dashboard.jsp");
            } else if (role.getRoleName().equals("customer")) {
                response.sendRedirect("./index.jsp");
            } else if (role.getRoleName().equals("shipper")) {
                response.sendRedirect("shipper/dashboard.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to retrieve user information from Google.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    } else {
        response.sendRedirect("login.jsp");
    }
}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AccountDAO dao = new AccountDAO();
        Account account = dao.login(email, password);

        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("account", account);

            Role role = dao.getRoleByAccountId(account.getId());
            session.setAttribute("role", role);

            // Điều hướng dựa trên vai trò
            if (role.getRoleName().equals("admin")) {
                response.sendRedirect("admin/dashboard.jsp");
            } else if (role.getRoleName().equals("seller")) {
                response.sendRedirect("seller/dashboard.jsp");
            } else if (role.getRoleName().equals("customer")) {
                response.sendRedirect("./index.jsp");
            } else if (role.getRoleName().equals("shipper")) {
                response.sendRedirect("shipper/dashboard.jsp");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
