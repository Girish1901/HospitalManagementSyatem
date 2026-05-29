package main;

public class Admin extends Person {
    private String adminId;
    private String role;

    public Admin(int age, String gender, String phno, String email, String name, String adminId, String role) {
        super(age, gender, phno, email, name);
        this.adminId = adminId;
        this.role = role;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void displayInfo() {
        System.out.println("--------------------------------------------------------");
        System.out.println("AdminId: " + this.adminId);
        System.out.println("Admin Name: " + this.getName());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Role: " + this.role);
        System.out.println("--------------------------------------------------------");
    }
}
