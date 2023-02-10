package lk.ijse.supermarket.dto;

import java.time.LocalDate;

public class PO {
    private String poID;
    private String poSupID;
    private LocalDate date;

    public PO() {
    }

    public PO(String poID, String poSupID, LocalDate date) {
        this.poID = poID;
        this.poSupID = poSupID;
        this.date = date;
    }

    public String getPoID() {
        return poID;
    }

    public void setPoID(String poID) {
        this.poID = poID;
    }

    public String getPoSupID() {
        return poSupID;
    }

    public void setPoSupID(String poSupID) {
        this.poSupID = poSupID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
