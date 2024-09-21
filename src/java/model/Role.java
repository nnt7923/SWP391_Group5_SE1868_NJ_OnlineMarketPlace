/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Role {

<<<<<<< HEAD
    private int roleId;
=======
    private int id;
>>>>>>> 3770e6f1151f1d1993992ffe77616807a569a4d7
    private String roleName;

    public Role() {
    }

<<<<<<< HEAD
    public Role(int roleId, String roleName) {
        this.roleId = roleId;
=======
    public Role(int id) {
        this.id = id;
    }

    public Role(int id, String roleName) {
        this.id = id;
>>>>>>> 3770e6f1151f1d1993992ffe77616807a569a4d7
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
