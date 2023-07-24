package model;

public class notifyemployee {
    // Attributes
    private int empId;
    private String email;
    private String profession;

    // Constructor
    public notifyemployee(int empId, String email, String profession) {
        this.empId = empId;
        this.email = email;
        this.profession = profession;
    }

    // Getters
    public int getEmpId() {
        return empId;
    }

    public String getEmail() {
        return email;
    }

    public String getProfession() {
        return profession;
    }

    // Setters
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
