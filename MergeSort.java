package sorting;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public void mergeSort(List<Student> students) {
        if (students.size() <= 1) {
            return;
        }

        int mid = students.size() / 2;
        List<Student> left = new ArrayList<>(students.subList(0, mid));
        List<Student> right = new ArrayList<>(students.subList(mid, students.size()));

        mergeSort(left);
        mergeSort(right);
        merge(students, left, right);
    }

    private void merge(List<Student> students, List<Student> left, List<Student> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getName().compareTo(right.get(j).getName()) <= 0) {
                students.set(k++, left.get(i++));
            } else {
                students.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            students.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            students.set(k++, right.get(j++));
        }
    }
}
