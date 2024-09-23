package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import dao.AccountDAO;
import model.Account;

@WebServlet(name = "ChangePasswordController", urlPatterns = {"/change-password"})
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession(false);
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            // Nếu chưa đăng nhập, chuyển về trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy dữ liệu từ form
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // So sánh trực tiếp mật khẩu hiện tại với mật khẩu trong tài khoản
        if (!account.getPassword().equals(currentPassword)) {
            request.setAttribute("errorMessage", "Current password is incorrect!");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu có trùng khớp không
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "New password and confirm password do not match!");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }

        // Cập nhật mật khẩu mới
        AccountDAO dao = new AccountDAO();
        int result = dao.updatePassword(newPassword, account.getEmail());

        if (result > 0) {
            request.setAttribute("successMessage", "Password changed successfully!");
            request.getRequestDispatcher("profiles.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Error while changing password. Please try again.");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        }
    }
}

