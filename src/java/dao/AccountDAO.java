<<<<<<< HEAD
=======
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
>>>>>>> 3770e6f1151f1d1993992ffe77616807a569a4d7
package dao;

import context.DBContext;
import model.Account;
import java.sql.*;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
=======
public class AccountDAO extends DBContext {

    public Account login(String username, String password) {
        String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

>>>>>>> 3770e6f1151f1d1993992ffe77616807a569a4d7
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

<<<<<<< HEAD
    
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
        try ( PreparedStatement ps = conn.prepareStatement(query)) {
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
        try ( PreparedStatement ps = conn.prepareStatement(query)) {
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
        try ( PreparedStatement ps = conn.prepareStatement(query)) {
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
        try ( PreparedStatement ps = conn.prepareStatement(query)) {
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
        boolean  list = dao.add(new Account("wjbunhi", "12345", "wjbunhi@gmail.com", "123456789", "ha noi", 1, "active"));
        System.out.println(list);
    }
=======
    public boolean register(Account account) throws SQLException {
        String sql = "INSERT INTO Account (username,email, password, roleID) VALUES (?,?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, account.getUsername());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getPassword());
            statement.setInt(4, account.getRole().getId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

>>>>>>> 3770e6f1151f1d1993992ffe77616807a569a4d7
}
