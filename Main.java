import bst.BinarySearchTree;
import model.Student;
import performance.PerformanceAnalyzer;
import sorting.MergeSort;
import sorting.QuickSort;
import utils.CSVReader;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        List<Student> studentList = CSVReader.readStudentsFromCSV("students.csv");
        if (studentList.isEmpty()) {
            System.out.println("[ERROR] No students were loaded from students.csv. Please ensure the file exists and is not empty.");
        }
        for (Student student : studentList) {
            bst.insert(student);
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Student Record Management System ---");
            System.out.println("1. Insert a new student (ID is auto-generated)");
            System.out.println("2. Search for a student");
            System.out.println("3. Delete a student");
            System.out.println("4. Display all students (In-order Traversal)");
            System.out.println("5. Display all students (Pre-order Traversal)");
            System.out.println("6. Display all students (Post-order Traversal)");
            System.out.println("7. Display tree structure");
            System.out.println("8. Sort students by name (Merge Sort)");
            System.out.println("9. Sort students by CGPA (Quick Sort)");
            System.out.println("10. Run Full Performance Analysis");
            System.out.println("11. Run Basic Test Cases");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
                continue; // Skip the rest of the loop
            }
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    double cgpa = getGpaInput(scanner);
                    
                    String matric = generateNewMatricNumber(bst);
                    bst.insert(new Student(name, matric, cgpa));
                    System.out.println("Successfully added new student with Matric Number: " + matric);
                    break;
                case 2:
                    System.out.print("Enter Matric Number to search: ");
                    String searchMatric = scanner.nextLine();
                    Student foundStudent = bst.search(searchMatric);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Matric Number to delete: ");
                    String deleteMatric = scanner.nextLine();
                    bst.delete(deleteMatric);
                    System.out.println("Student deleted if existed.");
                    break;
                case 4:
                    System.out.println("\n--- In-order Traversal ---");
                    printStudents(bst.inOrderTraversal());
                    break;
                case 5:
                    System.out.println("\n--- Pre-order Traversal ---");
                    printStudents(bst.preOrderTraversal());
                    break;
                case 6:
                    System.out.println("\n--- Post-order Traversal ---");
                    printStudents(bst.postOrderTraversal());
                    break;
                case 7:
                    System.out.println("\n--- BST Diagram ---");
                    bst.printTree();
                    break;
                case 8:
                    System.out.println("\n--- Merge Sort (by Name) ---");
                    List<Student> forMergeSort = bst.inOrderTraversal();
                    new MergeSort().mergeSort(forMergeSort);
                    printStudents(forMergeSort);
                    break;
                case 9:
                    System.out.println("\n--- Quick Sort (by CGPA) ---");
                    List<Student> forQuickSort = bst.inOrderTraversal();
                    System.out.print("Sort in (A)scending or (D)escending order? ");
                    char order = scanner.next().charAt(0);
                    new QuickSort().quickSort(forQuickSort, Character.toUpperCase(order) == 'A');
                    printStudents(forQuickSort);
                    break;
                case 10:
                    PerformanceAnalyzer.runAnalysis();
                    break;
                case 11:
                    runTestCases();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void runTestCases() {
        System.out.println("\n--- Running Test Cases ---");

        // Test Case 1: Functional Testing
        System.out.println("\n--- Test Case 1: Functional Testing ---");
        BinarySearchTree testBst = new BinarySearchTree();
        List<Student> students = CSVReader.readStudentsFromCSV("students.csv");
        // Insert >= 15 students
        for (int i=0; i < 15; i++) {
            testBst.insert(students.get(i));
        }
        System.out.println("Inserted 15 students.");
        System.out.println("\nIn-order Traversal:");
        printStudents(testBst.inOrderTraversal());
        System.out.println("\nTree Structure:");
        testBst.printTree();


        // Search 3 existing + 2 non-existing
        System.out.println("\nSearching for students...");
        System.out.println("Search AIU201: " + (testBst.search("AIU201") != null ? "Found" : "Not Found"));
        System.out.println("Search AIU208: " + (testBst.search("AIU208") != null ? "Found" : "Not Found"));
        System.out.println("Search AIU215: " + (testBst.search("AIU215") != null ? "Found" : "Not Found"));
        System.out.println("Search AIU999: " + (testBst.search("AIU999") != null ? "Found" : "Not Found"));
        System.out.println("Search AIU000: " + (testBst.search("AIU000") != null ? "Found" : "Not Found"));


        // Delete: leaf, one-child, two-child
        System.out.println("\nDeleting nodes...");
        // Leaf node (e.g., AIU215)
        System.out.println("Deleting leaf node (AIU215)...");
        testBst.delete("AIU215");
        testBst.printTree();

        // Node with one child (e.g., AIU214)
        System.out.println("Deleting node with one child (AIU214)...");
        testBst.delete("AIU214");
        testBst.printTree();

        // Node with two children (e.g., AIU209)
        System.out.println("Deleting node with two children (AIU209)...");
        testBst.delete("AIU209");
        testBst.printTree();


        // Test Case 2: Edge Cases
        System.out.println("\n--- Test Case 2: Edge Cases ---");
        System.out.println("Inserting duplicate matric (AIU201):");
        testBst.insert(new Student("Test Duplicate", "AIU201", 3.0));
        printStudents(testBst.inOrderTraversal()); // Should not have changed

        System.out.println("\nDeleting non-existing matric (AIU998):");
        testBst.delete("AIU998"); // Should not crash

        System.out.println("\nOperations on empty tree:");
        BinarySearchTree emptyBst = new BinarySearchTree();
        System.out.println("Search on empty: " + emptyBst.search("AIU101"));
        emptyBst.delete("AIU101");

        System.out.println("\nSingle-node tree behavior:");
        BinarySearchTree singleNodeBst = new BinarySearchTree();
        singleNodeBst.insert(new Student("Single", "SINGLE01", 4.0));
        singleNodeBst.printTree();
        singleNodeBst.delete("SINGLE01");
        System.out.println("After deletion:");
        singleNodeBst.printTree();


        // Test Case 3: Performance Evaluation
        System.out.println("\n--- Test Case 3: Performance Evaluation (n=1000) ---");
        BinarySearchTree perfBst = new BinarySearchTree();
        List<Student> perfStudents = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            perfStudents.add(new Student("Student" + i, "PERF" + i, 2.0 + (i % 200) / 100.0));
        }
        Collections.shuffle(perfStudents);

        long startTime = System.nanoTime();
        for (Student s : perfStudents) {
            perfBst.insert(s);
        }
        long endTime = System.nanoTime();
        System.out.println("Insertion of 1000 nodes took: " + (endTime - startTime) / 1_000_000 + " ms");

        startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            perfBst.search("PERF" + (i * 10));
        }
        endTime = System.nanoTime();
        System.out.println("Search of 100 random keys took: " + (endTime - startTime) / 1_000_000 + " ms");

        startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            perfBst.delete("PERF" + (i * 10));
        }
        endTime = System.nanoTime();
        System.out.println("Deletion of 100 random keys took: " + (endTime - startTime) / 1_000_000 + " ms");

        startTime = System.nanoTime();
        perfBst.inOrderTraversal();
        endTime = System.nanoTime();
        System.out.println("In-order traversal of remaining nodes took: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    private static double getGpaInput(Scanner scanner) {
        while (true) {
            System.out.print("Enter CGPA (0.0 - 4.0): ");
            try {
                double cgpa = scanner.nextDouble();
                if (cgpa >= 0.0 && cgpa <= 4.0) {
                    scanner.nextLine(); // consume newline
                    return cgpa;
                } else {
                    System.out.println("[ERROR] CGPA must be between 0.0 and 4.0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] Invalid input. Please enter a number for the CGPA.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    private static String generateNewMatricNumber(BinarySearchTree bst) {
        Random random = new Random();
        String matricNumber;
        do {
            int num = 10000000 + random.nextInt(90000000); // Generate 8-digit number
            matricNumber = "AIU" + num;
        } while (bst.search(matricNumber) != null); // Ensure uniqueness
        return matricNumber;
    }
}
