package main;

public abstract class Person {
    private String name;
    private String email;
    private String phno;
    private String gender;
    private int age;

    public Person(int age, String gender, String phno, String email, String name) {
        this.age = age;
        this.gender = gender;
        this.phno = phno;
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhno() {
        return phno;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public abstract void displayInfo();
}
