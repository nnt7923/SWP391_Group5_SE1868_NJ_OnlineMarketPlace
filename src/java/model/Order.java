package model;

public class Order {
    private int id;
    private int accountId;
    private double totalPrice;
    private String note;
    private String createDate;
    private int shippingId;
    private int customerId;

    public Order(int id, int accountId, double totalPrice, String note, String createDate, int shippingId, int customerId) {
        this.id = id;
        this.accountId = accountId;
        this.totalPrice = totalPrice;
        this.note = note;
        this.createDate = createDate;
        this.shippingId = shippingId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getNote() {
        return note;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getShippingId() {
        return shippingId;
    }

    public int getCustomerId() {
        return customerId;
    }
}
