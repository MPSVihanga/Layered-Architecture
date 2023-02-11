package lk.ijse.supermarket.dto;

import java.time.LocalDate;

public class EmployeeDTO {
    private String id;
    private String name;
    private int age;
    private String email;
    private String nic;
    private LocalDate date;
    private String address;
    private String tel;
    private String jobRole;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, String name, int age, String email, String nic, LocalDate date, String address, String tel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.nic = nic;
        this.date = date;
        this.address = address;
        this.tel = tel;
    }


    public EmployeeDTO(String id, String name, int age, String email, String nic, LocalDate date, String address, String tel, String jobRole) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.nic = nic;
        this.date = date;
        this.address = address;
        this.tel = tel;
        this.jobRole = jobRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }
}
