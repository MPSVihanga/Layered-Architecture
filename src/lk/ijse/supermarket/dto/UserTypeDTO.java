package lk.ijse.supermarket.dto;

public class UserTypeDTO {
    private String usrTypeId;
    private String usrRole;
    private String usrEmpId;

    public UserTypeDTO() {
    }

    public UserTypeDTO(String usrTypeId, String usrRole, String usrEmpId) {
        this.usrTypeId = usrTypeId;
        this.usrRole = usrRole;
        this.usrEmpId = usrEmpId;
    }

    public String getUsrTypeId() {
        return usrTypeId;
    }

    public void setUsrTypeId(String usrTypeId) {
        this.usrTypeId = usrTypeId;
    }

    public String getUsrRole() {
        return usrRole;
    }

    public void setUsrRole(String usrRole) {
        this.usrRole = usrRole;
    }

    public String getUsrEmpId() {
        return usrEmpId;
    }

    public void setUsrEmpId(String usrEmpId) {
        this.usrEmpId = usrEmpId;
    }
}
