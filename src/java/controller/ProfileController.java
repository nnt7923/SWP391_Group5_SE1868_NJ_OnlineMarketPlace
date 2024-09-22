package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;

@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy session
        HttpSession session = request.getSession(false);  // Không tạo session mới nếu chưa có

        // Kiểm tra session và account
        Account account = null;
        if (session != null) {
            account = (Account) session.getAttribute("account");
        }

        if (account == null) {
            // Nếu không có thông tin account, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
        } else {
            // Đặt thông tin account vào request và chuyển tiếp đến trang profile.jsp
            request.setAttribute("account", account);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }
}
