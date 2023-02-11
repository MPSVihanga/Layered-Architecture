package lk.ijse.supermarket.dto;

public class PoAndDetailsDTO {
    private String poId;
    private String supId;
    private String productId;
    private String productBName;
    private String productName;
    private int qty;
    private String qtyType;

    public PoAndDetailsDTO() {
    }

    public PoAndDetailsDTO(String poId, String supId, String productId, String productBName, String productName, int qty, String qtyType) {
        this.poId = poId;
        this.supId = supId;
        this.productId = productId;
        this.productBName = productBName;
        this.productName = productName;
        this.qty = qty;
        this.qtyType = qtyType;
    }

    public String getPoId() {
        return poId;
    }

    public void setPoId(String poId) {
        this.poId = poId;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductBName() {
        return productBName;
    }

    public void setProductBName(String productBName) {
        this.productBName = productBName;
    }

    public String getProductName() {return productName;}

    public void setProductName(String productName) {
        this.productName = productName;
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
}
