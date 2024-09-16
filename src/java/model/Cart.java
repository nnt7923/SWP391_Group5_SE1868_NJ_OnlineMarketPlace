package model;

public class Cart {
    private int accountId;
    private int productId;
    private int amount;
    private int orderId;

    public Cart() {
    }

    public Cart(int accountId, int productId, int amount, int orderId) {
        this.accountId = accountId;
        this.productId = productId;
        this.amount = amount;
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}
