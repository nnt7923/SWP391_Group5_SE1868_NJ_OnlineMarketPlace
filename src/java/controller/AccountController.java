package controller;

import dao.AccountDAO;
import model.Account;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountController", urlPatterns = {"/account"})
public class AccountController extends HttpServlet {

    private AccountDAO accountDAO;

    @Override
    public void init() {
        accountDAO = new AccountDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        switch (service) {
            case "listAll":
                // Lấy danh sách tất cả các Account
                List<Account> accounts = accountDAO.listAll();

                // Đặt danh sách vào request attribute
                request.setAttribute("accounts", accounts);

                // Chuyển tiếp tới JSP để hiển thị danh sách
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/manageAccount.jsp");
                dispatcher.forward(request, response);
                break;

            case "addAccount":
                addAccount(request, response);
                break;

            case "updateAccount":
                updateAccount(request, response);
                break;

            case "deleteAccount":
                deleteAccount(request, response);
                break;

            case "searchAccount":
                searchAccount(request, response);
                break;

            case "addAccountForm":
                showAddAccountForm(request, response);
                break;

            case "updateAccountForm":
                showUpdateAccountForm(request, response);
                break;

            default:
                response.sendRedirect("account?service=listAll");
                break;
        }
    }

   

    private void addAccount(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int roleID = Integer.parseInt(request.getParameter("roleID"));
        String status = request.getParameter("status");

        Account newAccount = new Account(0, username, password, email, phone, address, roleID, status);
        accountDAO.add(newAccount);
        response.sendRedirect("account?service=listAll");
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        String status = request.getParameter("status");

        Account updatedAccount = new Account(accountId, username, password, email, phone, address, roleId, status);
        accountDAO.update(updatedAccount);
        response.sendRedirect("account?service=listAll");
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int accountIdToDelete = Integer.parseInt(request.getParameter("accountId"));
        accountDAO.delete(accountIdToDelete);
        response.sendRedirect("account?service=listAll");
    }

    private void searchAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Account> searchedAccounts = accountDAO.search(keyword);
        request.setAttribute("accounts", searchedAccounts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/manageAccount.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddAccountForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/addAccount.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateAccountForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int accountIdToUpdate = Integer.parseInt(request.getParameter("accountId"));
        Account accountToUpdate = accountDAO.getAccountById(accountIdToUpdate);
        request.setAttribute("account", accountToUpdate);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/editAccount.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "AccountController";
    }
}
