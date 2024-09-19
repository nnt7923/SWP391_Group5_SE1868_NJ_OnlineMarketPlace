/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import model.Account;
import java.sql.*;

public class AccountDAO extends DBContext {



    public Account login(String username, String password) {
        String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private boolean register(Account account) throws SQLException {
       String sql = "INSERT INTO Account (role_id, department_id, code, full_name, gender, date_of_birth, phone_number, email, address, status) VALUES (?, ?, ?, ?, ?,?, ?,?, ?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, employee.getRole().getId());
            statement.setInt(2, employee.getDepartment().getId());
            statement.setString(3, employee.getCode());
            statement.setString(4, employee.getFullName());
            statement.setBoolean(5, employee.getGender().getId());
             statement.setDate(6, employee.getDateOfBirth());
             statement.setString(7, employee.getPhoneNumber());
             statement.setString(8, employee.getEmail());
             statement.setString(9, employee.getAddress());
             statement.setBoolean(10, employee.getStatus().getId());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }catch(Exception e){
            return false;
        }
    }
    
}
