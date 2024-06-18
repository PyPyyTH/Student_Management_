import Student.Student;
import java.util.Scanner;

public class Main {
    private Student[] students;
    private int count;

    public Main(int size) {
        students = new Student[size];
        count = 0;
    }

    public void addStudent(String id, String name, double marks) {
        if (count < students.length) {
            students[count++] = new Student(id, name, marks);
        } else {
            System.out.println("The list is full. Cannot add more students.");
        }
    }

    public void editStudent(String id, String newName, double newMarks) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equals(id)) {
                students[i].setName(newName);
                students[i].setMarks(newMarks);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudent(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equals(id)) {
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[--count] = null;
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void sortStudentsBySelectionSort() {
        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < count; j++) {
                if (students[j].getMarks() < students[minIndex].getMarks()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Student temp = students[i];
                students[i] = students[minIndex];
                students[minIndex] = temp;
            }
        }
    }

    public void sortStudentsByQuickSort() {
        quickSort(0, count - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        double pivot = students[high].getMarks();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (students[j].getMarks() <= pivot) {
                i++;
                Student temp = students[i];
                students[i] = students[j];
                students[j] = temp;
            }
        }
        Student temp = students[i + 1];
        students[i + 1] = students[high];
        students[high] = temp;
        return i + 1;
    }

    public void searchStudent(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equals(id)) {
                System.out.println(students[i]);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayAllStudents() {
        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int size = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Main sm = new Main(size);

        while (true) {
            System.out.println("=== Students Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students by Selection Sort");
            System.out.println("5. Sort Students by Quick Sort");
            System.out.println("6. Search Student");
            System.out.println("7. Display All Students");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Marks: ");
                    double marks = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    sm.addStudent(id, name, marks);
                    break;
                case 2:
                    System.out.print("Enter Student ID to Edit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Enter New Student Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Student Marks: ");
                    double newMarks = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    sm.editStudent(editId, newName, newMarks);
                    break;
                case 3:
                    System.out.print("Enter Student ID to Delete: ");
                    String deleteId = scanner.nextLine();
                    sm.deleteStudent(deleteId);
                    break;
                case 4:
                    sm.sortStudentsBySelectionSort();
                    System.out.println("Students sorted by Selection Sort.");
                    break;
                case 5:
                    sm.sortStudentsByQuickSort();
                    System.out.println("Students sorted by Quick Sort.");
                    break;
                case 6:
                    System.out.print("Enter Student ID to Search: ");
                    String searchId = scanner.nextLine();
                    sm.searchStudent(searchId);
                    break;
                case 7:
                    sm.displayAllStudents();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

