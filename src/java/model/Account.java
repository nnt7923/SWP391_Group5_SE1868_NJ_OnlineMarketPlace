package model;

public class Account {
<<<<<<< HEAD
    private int accountId;
=======

    private int id;
>>>>>>> 3770e6f1151f1d1993992ffe77616807a569a4d7
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private int roleId;
    private String status;
    private Role role;

<<<<<<< HEAD
    // Getters và Setters

    public Account() {
    }

    public Account(int accountId, String username, String password, String email, String phone, String address, int roleId, String status) {
        this.accountId = accountId;
=======
    public Account(int id, Role role, String username, String password, String email, String phone, String address, int roleID, String status) {
        this.id = id;
        this.role = role;
>>>>>>> 3770e6f1151f1d1993992ffe77616807a569a4d7
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
        this.status = status;
    }

<<<<<<< HEAD
    public Account(String username, String password, String email, String phone, String address, int roleId, String status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
        this.status = status;
    }
    
    

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
=======
    public Account(String username ,String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
>>>>>>> 3770e6f1151f1d1993992ffe77616807a569a4d7
    }
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
