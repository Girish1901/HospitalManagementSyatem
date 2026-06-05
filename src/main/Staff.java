package main;

public class Staff extends Person {
    private String staffId;
    private String role;
    private String department;
    private double salary;

    public Staff(String staffId,String name,String email,String phno,String gender,int age,String role,String department,double salary) {
        super(age, gender, phno, email, name);
        this.staffId = staffId;
        this.role = role;
        this.department = department;
        this.salary = salary;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println("--------------------------------------------------------");
        System.out.println("StaffId: " + this.staffId);
        System.out.println("Staff Name: " + this.getName());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Role: " + this.role);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + this.salary);
        System.out.println("--------------------------------------------------------");
    }
}
