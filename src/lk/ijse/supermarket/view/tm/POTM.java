package lk.ijse.supermarket.view.tm;

import java.time.LocalDate;

public class POTM {
    private String poID;
    private String poSupID;
    private LocalDate date;

    public POTM() {
    }

    public POTM(String poID, String poSupID, LocalDate date) {
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
