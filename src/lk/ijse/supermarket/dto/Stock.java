package lk.ijse.supermarket.dto;

import java.time.LocalDate;

public class Stock {

    private String pbName;
    private String pname;
    private String pcategory;
    private LocalDate mfdDate;
    private LocalDate expDate;
    private int qty;
    private String qtyType;
    private double discount;
    private double unitPrice;



    public Stock() {
    }

    public Stock(String pname, double discount, double unitPrice) {
        this.setPname(pname);
        this.setDiscount(discount);
        this.setUnitPrice(unitPrice);
    }

    public Stock(String pbName, String pname, String pcategory, LocalDate mfdDate, LocalDate expDate, int qty, String qtyType, double discount, double unitPrice) {
        this.pbName = pbName;
        this.pname = pname;
        this.pcategory = pcategory;
        this.mfdDate = mfdDate;
        this.expDate = expDate;
        this.qty = qty;
        this.qtyType = qtyType;
        this.discount = discount;
        this.unitPrice = unitPrice;
    }

    public String getPbName() {
        return pbName;
    }

    public void setPbName(String pbName) {
        this.pbName = pbName;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcategory() {
        return pcategory;
    }

    public void setPcategory(String pcategory) {
        this.pcategory = pcategory;
    }

    public LocalDate getMfdDate() {
        return mfdDate;
    }

    public void setMfdDate(LocalDate mfdDate) {
        this.mfdDate = mfdDate;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getQtyType() {
        return qtyType;
    }

    public void setQtyType(String qtyType) {
        this.qtyType = qtyType;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
