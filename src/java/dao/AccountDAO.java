package dao;

import context.DBContext;
import model.Account;
import model.GoogleAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;

public class AccountDAO {

    public Account login(String username, String password) {
        String query = "SELECT * FROM Account WHERE username = ? AND password = ?";
        try (Connection conn = new DBContext().conn; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                            rs.getString("password"), rs.getString("phone"), rs.getString("address"),
                            rs.getInt("roleID"), rs.getString("status"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Role getRoleByAccountId(int accountId) {
        String query = "SELECT Role.ID, Role.roleName FROM Role "
                + "JOIN Account ON Role.ID = Account.roleID WHERE Account.id = ?";
        try (Connection conn = new DBContext().conn; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Role(rs.getInt("ID"), rs.getString("roleName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm người dùng mới vào cơ sở dữ liệu
    public void addAccount(Account account) throws SQLException {
        String query = "INSERT INTO Account (username, email, password, roleID, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().conn; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPassword());
            ps.setInt(4, account.getRoleID());
            ps.setString(5, account.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tạo mật khẩu ngẫu nhiên
    public String generateRandomPassword() {
        int length = 10;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

    // Xử lý đăng nhập Google
    public void handleGoogleLogin(GoogleAccount googleAccount) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        Account existingAccount = accountDAO.getAccountByEmail(googleAccount.getEmail());

        if (existingAccount != null) {
            // Người dùng đã có trong hệ thống
            System.out.println("User already exists: " + existingAccount.getEmail());
        } else {
            System.out.println("New Google user, creating account: " + googleAccount.getEmail());
            Account newAccount = new Account();
            newAccount.setUsername(googleAccount.getEmail().split("@")[0]);
            newAccount.setEmail(googleAccount.getEmail());
            newAccount.setPassword("");
            newAccount.setRoleID(3);
            newAccount.setStatus("active");

            accountDAO.addAccount(newAccount);
            System.out.println("New Google account added to database.");
        }
    }

    // Lấy tài khoản theo email
    public Account getAccountByEmail(String email) throws SQLException {
        String query = "SELECT * FROM Account WHERE email = ?";
        try (Connection conn = new DBContext().conn; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setId(rs.getInt("id"));
                    account.setUsername(rs.getString("username"));
                    account.setEmail(rs.getString("email"));
                    account.setRoleID(rs.getInt("roleID"));
                    account.setStatus(rs.getString("status"));
                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getUserById(int id) {
        String sql = "SELECT * FROM Account WHERE id = ?";
        try (Connection conn = new DBContext().conn; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {  // Chỉ cần lấy một kết quả với id
                    Account account = new Account();
                    account.setId(rs.getInt("id"));
                    account.setUsername(rs.getString("username"));
                    account.setPassword(rs.getString("password"));
                    account.setEmail(rs.getString("email"));
                    account.setPhone(rs.getString("phone"));
                    account.setAddress(rs.getString("address"));
                    account.setRoleID(rs.getInt("roleID"));
                    account.setStatus(rs.getString("status"));
                    return account;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;  // Trả về null nếu không tìm thấy người dùng
    }

    public static void main(String[] args) {
        AccountDAO db = new AccountDAO();
        Account d = db.getUserById(3);
        System.out.println(d);

    }
}
