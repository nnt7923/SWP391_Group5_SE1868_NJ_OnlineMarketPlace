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
    private Role role;

    public Account(int id, Role role, String username, String password, String email, String phone, String address, int roleID, String status) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleID = roleID;
        this.status = status;
    }

    public Account(String username ,String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
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
