package model;

public class Account {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private int roleID;
    private String status;

    public Account(int id, String username, String password, String email, String phone, String address, int roleID, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleID = roleID;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getStatus() {
        return status;
    }
}
