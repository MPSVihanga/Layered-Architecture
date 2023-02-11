package lk.ijse.supermarket.dto;

import java.time.LocalDate;


public class SupplierDTO {

    private String Id;
    private String companyName;
    private String companyTel;
    private String supplierName;
    private String supplierTel;
    private LocalDate date;
    private String adderss;
    private String companyEmail;

    public SupplierDTO(String id, String supplierName) {
        Id = id;
        this.supplierName = supplierName;
    }

    public SupplierDTO(String id, String companyName, String companyTel, String supplierName, String supplierTel, LocalDate date, String adderss, String companyEmail) {
        Id = id;
        this.companyName = companyName;
        this.companyTel = companyTel;
        this.supplierName = supplierName;
        this.supplierTel = supplierTel;
        this.date = date;
        this.adderss = adderss;
        this.companyEmail = companyEmail;
    }

    public SupplierDTO() {
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierTel() {
        return supplierTel;
    }

    public void setSupplierTel(String supplierTel) {
        this.supplierTel = supplierTel;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAdderss() {
        return adderss;
    }

    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String toString() {
        return Id + "\t" + supplierName;
    }
}
