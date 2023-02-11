package lk.ijse.supermarket.view.tm;

public class POSDetailsTM {
    private String productId;
    private String brandName;
    private String productName;
    private int qty;
    private double price;

    public POSDetailsTM() {
    }

    public POSDetailsTM(String productId, String brandName, String productName, int qty, double price) {
        this.productId = productId;
        this.brandName = brandName;
        this.productName = productName;
        this.qty = qty;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
