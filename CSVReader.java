package utils;

import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Student> readStudentsFromCSV(String filePath) {
        List<Student> students = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(cvsSplitBy);
                String name = studentData[1].trim();
                String matricNumber = studentData[0].trim();
                double cgpa = Double.parseDouble(studentData[2].trim());
                students.add(new Student(name, matricNumber, cgpa));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
