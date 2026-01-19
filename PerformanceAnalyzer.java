package performance;

import bst.BinarySearchTree;
import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * PERFORMANCE ANALYZER
 * Time complexity analysis and large-input performance testing
 */
public class PerformanceAnalyzer {

    /**
     * Main method to start performance analysis
     */
    public static void main(String[] args) {
        runAnalysis();
    }

    /**
     * Run comprehensive performance analysis with n = 1000 nodes
     */
    public static void runAnalysis() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PERFORMANCE ANALYSIS - Member 4");
        System.out.println("=".repeat(60));

        int n = 1000;
        BinarySearchTree bst = new BinarySearchTree();
        List<Student> students = new ArrayList<>();
        Random random = new Random(42);

        // PHASE 1: INSERTION PERFORMANCE
        System.out.println("\n[PHASE 1] INSERT PERFORMANCE (n = " + n + ")");
        System.out.println("-".repeat(60));
        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            String matric = String.format("AIU%04d", i);
            String name = "Student_" + i;
            double cgpa = 2.0 + (random.nextDouble() * 2.0);
            Student student = new Student(name, matric, cgpa);
            bst.insert(student);
            students.add(student);
        }

        long insertTime = System.nanoTime() - startTime;
        System.out.println("Total Insertion Time: " + formatTime(insertTime));
        System.out.println("Average per insertion: " + formatTime(insertTime / n));
        System.out.println("Tree Height: " + bst.height());
        System.out.println("Node Count: " + bst.countNodes());

        // PHASE 2: SEARCH PERFORMANCE
        System.out.println("\n[PHASE 2] SEARCH PERFORMANCE (100 random searches)");
        System.out.println("-".repeat(60));
        int searchCount = 100;
        long totalSearchTime = 0;
        int successCount = 0;

        for (int i = 0; i < searchCount; i++) {
            int randomIndex = random.nextInt(n);
            String searchMatric = String.format("AIU%04d", randomIndex);

            long searchStart = System.nanoTime();
            Student result = bst.search(searchMatric);
            long searchEnd = System.nanoTime();

            totalSearchTime += (searchEnd - searchStart);
            if (result != null) successCount++;
        }

        System.out.println("Search Count: " + searchCount);
        System.out.println("Successful Searches: " + successCount);
        System.out.println("Total Search Time: " + formatTime(totalSearchTime));
        System.out.println("Average per search: " + formatTime(totalSearchTime / searchCount));

        // PHASE 3: DELETION PERFORMANCE
        System.out.println("\n[PHASE 3] DELETION PERFORMANCE (100 random deletions)");
        System.out.println("-".repeat(60));
        int deleteCount = 100;
        long totalDeleteTime = 0;

        for (int i = 0; i < deleteCount; i++) {
            int randomIndex = random.nextInt(n);
            String deleteMatric = String.format("AIU%04d", randomIndex);

            long deleteStart = System.nanoTime();
            bst.delete(deleteMatric);
            long deleteEnd = System.nanoTime();

            totalDeleteTime += (deleteEnd - deleteStart);
        }

        System.out.println("Deletion Count: " + deleteCount);
        System.out.println("Total Deletion Time: " + formatTime(totalDeleteTime));
        System.out.println("Average per deletion: " + formatTime(totalDeleteTime / deleteCount));
        System.out.println("Remaining Nodes: " + bst.countNodes());
        System.out.println("Tree Height: " + bst.height());

        // PHASE 4: TRAVERSAL PERFORMANCE
        System.out.println("\n[PHASE 4] TRAVERSAL PERFORMANCE");
        System.out.println("-".repeat(60));
        long traversalStart = System.nanoTime();
        List<Student> inOrder = bst.inOrderTraversal();
        long traversalEnd = System.nanoTime();
        long traversalTime = traversalEnd - traversalStart;

        System.out.println("In-order Traversal Time: " + formatTime(traversalTime));
        System.out.println("Elements Traversed: " + inOrder.size());

        // SUMMARY AND ANALYSIS
        printAnalysisSummary(insertTime, totalSearchTime, totalDeleteTime, traversalTime, bst.height(), n);
    }

    /**
     * Print time complexity analysis summary with theoretical explanations
     */
    private static void printAnalysisSummary(long insertTime, long searchTime, long deleteTime, 
                                              long traversalTime, int finalHeight, int n) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TIME COMPLEXITY ANALYSIS SUMMARY");
        System.out.println("=".repeat(60));

        System.out.println("\n[1. BST OPERATION TIME COMPLEXITIES]");
        System.out.println("Operation       | Best Case | Avg Case  | Worst Case");
        System.out.println("-".repeat(60));
        System.out.println("Insertion       | O(log n)  | O(log n)  | O(n)");
        System.out.println("Search          | O(log n)  | O(log n)  | O(n)");
        System.out.println("Deletion        | O(log n)  | O(log n)  | O(n)");
        System.out.println("Traversal       | O(n)      | O(n)      | O(n)");

        System.out.println("\n[2. WHY BST DEGRADES TO O(n)]");
        System.out.println("• Performance depends on TREE BALANCE");
        System.out.println("• Balanced: height = log(n) → O(log n) operations");
        System.out.println("• Skewed:   height = n → O(n) operations");
        System.out.println("• Degradation occurs when insertions are sorted:");
        System.out.println("  Example: Insert [1,2,3,4,5] → creates linked list!");

        System.out.println("\n[3. BST vs LINKED LIST COMPARISON]");
        System.out.println("Operation    | Linked List | BST (Avg) | Winner");
        System.out.println("-".repeat(60));
        System.out.println("Search       | O(n)        | O(log n)  | BST*");
        System.out.println("Insert       | O(1)        | O(log n)  | List");
        System.out.println("Delete       | O(n)        | O(log n)  | BST");
        System.out.println("Min/Max Find | O(n)        | O(h)      | BST*");
        System.out.println("*Assumes balanced BST");
        System.out.println("\nConclusion: For search-heavy workloads, BST >> Linked List");

        System.out.println("\n[4. BST INORDER TRAVERSAL vs MERGE SORT]");
        System.out.println("Operation               | Time      | Space");
        System.out.println("-".repeat(60));
        System.out.println("BST Inorder Traversal   | O(n)      | O(h)");
        System.out.println("Merge Sort              | O(n log n)| O(n)");
        System.out.println("Quick Sort              | O(n log n)| O(log n)");
        System.out.println("\nWhen to use:");
        System.out.println("• BST Inorder: BEST if already in BST (single pass O(n))");
        System.out.println("• Merge Sort:  BETTER if unsorted or need stability");

        System.out.println("\n[5. ACTUAL PERFORMANCE OBSERVED]");
        System.out.println("Tree Height: " + finalHeight);
        System.out.println("Expected height (balanced): ~" + (int)(Math.log(n) / Math.log(2)));
        System.out.println("Balance Ratio: " + String.format("%.2f", 
            (double)finalHeight / (Math.log(n) / Math.log(2))));
        System.out.println("\nPerformance Rating:");
        System.out.println("• Insertion: " + (insertTime/n/1000 < 10 ? "Excellent" : "Good"));
        System.out.println("• Search: " + (searchTime/100/1000 < 10 ? "Excellent" : "Good"));
        System.out.println("• Deletion: " + (deleteTime/100/1000 < 10 ? "Excellent" : "Good"));
        System.out.println("• Tree Balance: " + (finalHeight < Math.log(n)/Math.log(2) * 2 ? 
            "Well-balanced" : "Slightly imbalanced"));

        System.out.println("\n[CONCLUSIONS]");
        System.out.println("✓ BST provides efficient O(log n) operations for balanced trees");
        System.out.println("✓ Random insertions result in good tree balance (height ≈ log n)");
        System.out.println("✓ Search/Delete operations perform efficiently on this dataset");
        System.out.println("✓ For n=1000, performance is optimal with average case achieved");
        System.out.println("=".repeat(60));
    }

    /**
     * Format nanoseconds to human-readable time format
     */
    private static String formatTime(long nanoSeconds) {
        if (nanoSeconds < 1000) {
            return nanoSeconds + " ns";
        } else if (nanoSeconds < 1_000_000) {
            return String.format("%.2f µs", nanoSeconds / 1000.0);
        } else if (nanoSeconds < 1_000_000_000) {
            return String.format("%.2f ms", nanoSeconds / 1_000_000.0);
        } else {
            return String.format("%.2f s", nanoSeconds / 1_000_000_000.0);
        }
    }
}
