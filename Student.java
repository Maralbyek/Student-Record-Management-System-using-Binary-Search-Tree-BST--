package model;

public class Student {
    private String name;
    private String matricNumber;
    private double cgpa;

    public Student(String name, String matricNumber, double cgpa) {
        this.name = name;
        this.matricNumber = matricNumber;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Matric: " + matricNumber + ", CGPA: " + cgpa;
    }
}
