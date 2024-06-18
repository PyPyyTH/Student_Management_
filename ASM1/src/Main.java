import Student.Student;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Student> students = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students");
            System.out.println("5. Search Student");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    editStudent(scanner);
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    sortStudents(scanner);
                    break;
                case 5:
                    searchStudent(scanner);
                    break;
                case 6:
                    displayAllStudents();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }




    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        students.add(new Student(id, name, marks));
    }

    private static void editStudent(Scanner scanner) {
        System.out.print("Enter student ID to edit: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter new student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new marks: ");
        double marks = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        student.setName(name);
        student.setMarks(marks);
        System.out.println("Student marks updated.");
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        students.remove(student);
        System.out.println("Student deleted.");
    }

    private static void sortStudents(Scanner scanner) {
        System.out.println("Choose sorting method:");
        System.out.println("1. Selection Sort");
        System.out.println("2. Quick Sort");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                selectionSort(students);
                System.out.println("Students sorted by Selection Sort.");
                break;
            case 2:
                quickSort(students, 0, students.size() - 1);
                System.out.println("Students sorted by Quick Sort.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void selectionSort(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).getMarks() > students.get(minIndex).getMarks()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Student temp = students.get(i);
                students.set(i, students.get(minIndex));
                students.set(minIndex, temp);
            }
        }
    }

    private static int partition(List<Student> students, int low, int high) {
        Student pivot = students.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (students.get(j).getMarks() > pivot.getMarks()) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        return i + 1;
    }
    
    private static void quickSort(List<Student> students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);
            quickSort(students, low, pi - 1);
            quickSort(students, pi + 1, high);
        }
    }



    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter student ID to search: ");
        String id = scanner.nextLine();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println(student);
    }

    private static void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}
