package com.intelliplace.model;

public class Student {

    private int studentId;
    private String name;
    private String department;
    private int year;
    private double cgpa;
    private String email;
    private String phone;
    private String skills;

    // Default Constructor
    public Student() {
    }

    // Parameterized Constructor
    public Student(
            int studentId,
            String name,
            String department,
            int year,
            double cgpa,
            String email,
            String phone,
            String skills) {

        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.year = year;
        this.cgpa = cgpa;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
    }

    // Getters and Setters

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}