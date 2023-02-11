package lk.ijse.supermarket.view.tm;

import java.time.LocalDate;

public class WastageTM {
    private String wastageId;
    private String productId;
    private LocalDate date;
    private int qty;
    private String reason;

    public WastageTM() {
    }

    public WastageTM(String wastageId, String productId, LocalDate date, int qty, String reason) {
        this.wastageId = wastageId;
        this.productId = productId;
        this.date = date;
        this.qty = qty;
        this.reason = reason;
    }

    public String getWastageId() {
        return wastageId;
    }

    public void setWastageId(String wastageId) {
        this.wastageId = wastageId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
