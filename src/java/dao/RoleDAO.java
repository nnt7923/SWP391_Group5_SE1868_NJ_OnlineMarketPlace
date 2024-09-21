/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;

/**
 *
 * @author phamd
 */
public class RoleDAO extends DBContext {

    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        String sql = "select * from Role";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt("id"),
                        rs.getString("roleName")
                );

                roles.add(role);
                System.out.println(1);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return roles;
    }
 public static void main(String[] args) throws SQLException {
        RoleDAO roleDAO = new RoleDAO();
        List<Role> roles = roleDAO.getAll();
        for (var role : roles) {
            System.out.println(role.toString());
        }

    }
}
