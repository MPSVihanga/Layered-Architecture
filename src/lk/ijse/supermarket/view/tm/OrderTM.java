package lk.ijse.supermarket.view.tm;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrderTM {
    private String orderId;
    private LocalDate date;
    private LocalTime time;
    private double totalPrice;
    private String productId;

    public OrderTM() {
    }

    public OrderTM(String orderId, LocalDate date, LocalTime time, double totalPrice) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.totalPrice = totalPrice;
    }

    public OrderTM(String orderId, String productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
