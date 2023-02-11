package lk.ijse.supermarket.view.tm;

public class PODetailsTM extends POTM {
    private String poDpoID;
    private String poDprdctID;


    public PODetailsTM() {
    }

    public PODetailsTM(String poDpoID, String poDprdctID) {
        this.poDpoID = poDpoID;
        this.poDprdctID = poDprdctID;

    }

    public String getPoDpoID() {
        return poDpoID;
    }

    public void setPoDpoID(String poDpoID) {
        this.poDpoID = poDpoID;
    }

    public String getPoDprdctID() {
        return poDprdctID;
    }

    public void setPoDprdctID(String poDprdctID) {
        this.poDprdctID = poDprdctID;
    }

}
