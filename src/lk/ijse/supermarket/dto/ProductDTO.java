package lk.ijse.supermarket.dto;

import java.time.LocalDate;

public class ProductDTO {
    private String pid;
    private String pbName;
    private String pname;
    private String pcategory;
    private LocalDate mfdDate;
    private LocalDate expDate;
    private int qty;
    private String qtyType;
    private double discount;
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(String pid, int qty, String qtyType) {
        this.pid = pid;
        this.qty = qty;
        this.qtyType = qtyType;
    }

    public ProductDTO(String pid, String pbName, String pname) {
        this.setPid(pid);
        this.setPbName(pbName);
        this.setPname(pname);
    }


    public ProductDTO(String pid, String pbName, String pname, String pcategory, LocalDate mfdDate, LocalDate expDate, int qty, String qtyType, double discount, double price) {
        this.pid = pid;
        this.pbName = pbName;
        this.pname = pname;
        this.pcategory = pcategory;
        this.mfdDate = mfdDate;
        this.expDate = expDate;
        this.qty = qty;
        this.qtyType = qtyType;
        this.discount = discount;
        this.price = price;
    }

    public ProductDTO(String pid, int qty) {
        this.pid = pid;
        this.qty = qty;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return pid + "  " + pbName + "  " + pname;
    }


}
