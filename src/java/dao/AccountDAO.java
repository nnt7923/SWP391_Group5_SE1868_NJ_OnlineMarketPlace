package dao;

import context.DBContext;
import model.Account;
import model.GoogleAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;

public class AccountDAO extends DBContext {

    public Account login(String email, String password) {
        String query = "SELECT * FROM Account WHERE email = ? AND password = ?";
        try (Connection conn = new DBContext().conn; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"),
                            rs.getString("email"), rs.getString("phone"), rs.getString("address"),
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
                + "JOIN Account ON Role.ID = Account.roleID WHERE Account.account_id = ?";
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

    // Thêm ng??i dùng m?i vào c? s? d? li?u
    public void addAccount(Account account) throws SQLException {
        String query = "INSERT INTO Account (username, email, password, roleID, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().conn; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPassword());
            ps.setInt(4, account.getRoleId());
            ps.setString(5, account.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // T?o m?t kh?u ng?u nhiên
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

    // X? lý ??ng nh?p Google
    public void handleGoogleLogin(GoogleAccount googleAccount) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        Account existingAccount = accountDAO.getAccountByEmail(googleAccount.getEmail());

        if (existingAccount != null) {
            // Ng??i dùng ?ã có trong h? th?ng
            System.out.println("User already exists: " + existingAccount.getEmail());
        } else {
            System.out.println("New Google user, creating account: " + googleAccount.getEmail());
            Account newAccount = new Account();
            newAccount.setUsername(googleAccount.getEmail().split("@")[0]);
            newAccount.setEmail(googleAccount.getEmail());
            newAccount.setPassword("");
            newAccount.setRoleId(3);
            newAccount.setStatus("active");

            accountDAO.addAccount(newAccount);
            System.out.println("New Google account added to database.");
        }
    }

    // L?y tài kho?n theo email
    public Account getAccountByEmail(String email) throws SQLException {
        String query = "SELECT * FROM Account WHERE email = ?";
        try (Connection conn = new DBContext().conn; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setAccountId(rs.getInt("account_id"));
                    account.setUsername(rs.getString("username"));
                    account.setEmail(rs.getString("email"));
                    account.setRoleId(rs.getInt("roleID"));
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
                if (rs.next()) {  // Ch? c?n l?y m?t k?t qu? v?i id
                    Account account = new Account();
                    account.setAccountId(rs.getInt("account_id"));
                    account.setUsername(rs.getString("username"));
                    account.setPassword(rs.getString("password"));
                    account.setEmail(rs.getString("email"));
                    account.setPhone(rs.getString("phone"));
                    account.setAddress(rs.getString("address"));
                    account.setRoleId(rs.getInt("roleID"));
                    account.setStatus(rs.getString("status"));
                    return account;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;  // Tr? v? null n?u không tìm th?y ng??i dùng
    }

    public int resetPassword(String newPassword, String email) {

        String sql = "update Account set password = ?\n"
                + "  where email = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, newPassword);
            pre.setString(2, email);

            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    // Li?t kê t?t c? tài kho?n
    public List<Account> listAll() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM Account";
        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getInt("roleID"),
                        rs.getString("status"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Thêm tài kho?n m?i
    public boolean add(Account account) {
        String query = "INSERT INTO Account (username, password, email, phone, address, roleId, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getPhone());
            ps.setString(5, account.getAddress());
            ps.setInt(6, account.getRoleId());
            ps.setString(7, account.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa tài kho?n theo id
    public boolean delete(int id) {
        String query = "DELETE FROM Account WHERE account_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // C?p nh?t tài kho?n
    public boolean update(Account account) {
        String query = "UPDATE Account SET username = ?, password = ?, email = ?, phone = ?, address = ?, roleID = ?, status = ? WHERE account_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getPhone());
            ps.setString(5, account.getAddress());
            ps.setInt(6, account.getRoleId());
            ps.setString(7, account.getStatus());
            ps.setInt(8, account.getAccountId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm ki?m tài kho?n theo tên ng??i dùng
    public List<Account> search(String keyword) {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM Account WHERE username LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getInt("roleID"),
                        rs.getString("status"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // L?y tài kho?n theo id
    public Account getAccountById(int id) {
        String query = "SELECT * FROM Account WHERE account_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getInt("roleID"),
                        rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updatePassword(String newPassword, String email) {
        String sql = "UPDATE Account SET password = ? WHERE email = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, newPassword);
            pre.setString(2, email);

            return pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static void main(String[] args) {
        AccountDAO db = new AccountDAO();
        Account d = db.login("customer@gmail.com", "123");
        System.out.println(d);

    }
}
