package model;

public class employee {
    // Attributes
    private int empId;
    private String name;
    private String profession;
    private String contact;
    private String email;
    private String workStatus;

    // Constructor
    public employee(int empId, String name, String profession, String contact, String email, String workStatus) {
        this.empId = empId;
        this.name = name;
        this.profession = profession;
        this.contact = contact;
        this.email = email;
        this.workStatus = workStatus;
    }

    // Getters
    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    // Setters
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
}
