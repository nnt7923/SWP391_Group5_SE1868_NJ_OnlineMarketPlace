package dao;

import context.DBContext;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDAO extends DBContext {

//     Đăng nhập
    public Account login(String email, String password) {
        String query = "SELECT * FROM Account WHERE email = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
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

    // Liệt kê tất cả tài khoản
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

    // Thêm tài khoản mới
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

    // Xóa tài khoản theo id
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

    // Cập nhật tài khoản
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

    // Tìm kiếm tài khoản theo tên người dùng
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

    // Lấy tài khoản theo id
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

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        Account account = dao.login("quocphongoccho3@gmail.com", "123456789");
        System.out.println(account.getAddress());
    }
}
