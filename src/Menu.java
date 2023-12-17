import java.util.Scanner;
public class Menu {
    Scanner scanner;
    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void printMainMenu() {
        System.out.println("-- Options --");
        System.out.println("0. Exit");
        System.out.println("Students:");
        System.out.println("a1. Create student");
        System.out.println("a2. Get student list");
        System.out.println("a3. Get student by id");
        System.out.println("a4. Get student by last name");
        System.out.println("a5. Update student");
        System.out.println("a6. Sign up for semester");
        System.out.println("a7. Sigh out for semester");
        System.out.println("a8. Get semesters for student");
        System.out.println("Semesters:");
        System.out.println("b1. Create semester");
        System.out.println("b2. Get semester list");
        System.out.println("b3. Get semester by id");
        System.out.println("b4. Get semester by name");
        System.out.println("b5. Get students for semester");
        System.out.println("Study components:");
        System.out.println("c1. Create study component");
        System.out.println("c2. Get study component list");
        System.out.println("c3. Get study component by id");
        System.out.println("c4. Get study component by name");
    }

    public String getInput() {
        String input = scanner.nextLine();
        return input;
    }

    public Student inputStudent(int id) {
        System.out.println("Enter student first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter student last name: ");
        String lastName = scanner.nextLine();
        return new Student(id, firstName, lastName);
    }
}
