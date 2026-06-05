package main;

public class Admin extends Person {
    private String adminId;
    private String password;

    public Admin(String adminId,String name,String email,String phno,String gender,int age,String password) {
        super(age, gender, phno, email, name);
        this.adminId = adminId;
        this.password=password;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void displayInfo() {
        System.out.println("--------------------------------------------------------");
        System.out.println("AdminId: " + this.adminId);
        System.out.println("Admin Name: " + this.getName());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Role: " + this.password);
        System.out.println("--------------------------------------------------------");
    }
}
