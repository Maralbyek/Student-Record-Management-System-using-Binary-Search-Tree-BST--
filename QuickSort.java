package sorting;

import model.Student;

import java.util.List;

public class QuickSort {

    public void quickSort(List<Student> students, boolean ascending) {
        quickSort(students, 0, students.size() - 1, ascending);
    }

    private void quickSort(List<Student> students, int low, int high, boolean ascending) {
        if (low < high) {
            int pi = partition(students, low, high, ascending);
            quickSort(students, low, pi - 1, ascending);
            quickSort(students, pi + 1, high, ascending);
        }
    }

    private int partition(List<Student> students, int low, int high, boolean ascending) {
        double pivot = students.get(high).getCgpa();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            boolean condition = ascending ? students.get(j).getCgpa() < pivot : students.get(j).getCgpa() > pivot;
            if (condition) {
                i++;
                swap(students, i, j);
            }
        }

        swap(students, i + 1, high);
        return i + 1;
    }

    private void swap(List<Student> students, int i, int j) {
        Student temp = students.get(i);
        students.set(i, students.get(j));
        students.set(j, temp);
    }
}
