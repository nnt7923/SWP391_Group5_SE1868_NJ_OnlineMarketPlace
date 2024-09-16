package model;

public class Feedback {
    private int id;
    private int accountId;
    private int productId;
    private int rating;
    private String createDate;

    public Feedback() {
    }

    public Feedback(int id, int accountId, int productId, int rating, String createDate) {
        this.id = id;
        this.accountId = accountId;
        this.productId = productId;
        this.rating = rating;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    
}
