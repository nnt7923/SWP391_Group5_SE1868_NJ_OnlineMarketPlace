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
import validation.PasswordValidator;

@WebServlet(name = "ChangePasswordController", urlPatterns = {"/change-password"})
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // L?y thông tin ng??i dùng t? session
        HttpSession session = request.getSession(false);
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            // N?u ch?a ??ng nh?p, chuy?n v? trang ??ng nh?p
            response.sendRedirect("login.jsp");
            return;
        }

        // L?y d? li?u t? form
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // So sánh tr?c ti?p m?t kh?u hi?n t?i v?i m?t kh?u trong tài kho?n
        if (!account.getPassword().equals(currentPassword)) {
            request.setAttribute("errorMessage", "Current password is incorrect!");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }

        // Ki?m tra m?t kh?u m?i và xác nh?n m?t kh?u có trùng kh?p không
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "New password and confirm password do not match!");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            return;
        }

        // C?p nh?t m?t kh?u m?i
        AccountDAO dao = new AccountDAO();
        int result = dao.updatePassword(newPassword, account.getEmail());

        if (!PasswordValidator.isValidPassword(newPassword)) {
            request.setAttribute("errorMessage", "Mật khẩu không hợp lệ. Mật khẩu phải có ít nhất 8 ký tự, bao gồm ít nhất 1 chữ hoa, 1 chữ thường và 1 số.");
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } else {
            request.setAttribute("successMessage", "Password changed successfully!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            
        }
    }
}