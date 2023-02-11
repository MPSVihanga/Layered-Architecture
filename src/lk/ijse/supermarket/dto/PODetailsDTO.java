package lk.ijse.supermarket.dto;

public class PODetailsDTO extends PODTO {
    private String poDpoID;
    private String poDprdctID;


    public PODetailsDTO() {
    }

    public PODetailsDTO(String poDpoID, String poDprdctID) {
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
