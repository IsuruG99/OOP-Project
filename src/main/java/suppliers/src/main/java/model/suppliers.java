package model;


public class suppliers {
    // initialize Attributes for suppliers (spId, name, profession, contact, email)
    private int spId;
    private String name;
    private String profession;
    private String contact;
    private String email;

    // Constructor for suppliers
    public suppliers(int spId, String name, String profession, String contact, String email) {
        this.spId = spId;
        this.name = name;
        this.profession = profession;
        this.contact = contact;
        this.email = email;
    }
    // Getters and setters for the suppliers attributes

    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Object getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
