import java.sql.*;
import java.util.ArrayList;

public class Controller {
    DatabaseConnection connection;
    StudentService studentService;
    SemesterService semesterService;
    StudyComponentService studyComponentService;
    ResultService resultService;
    StudentSemesterService studentSemesterService;
    Menu menu;

    public static void main(String[] args) {
        Controller controller = new Controller();
        LogHandler.log("Program started.");
        controller.mainLoop();
    }

    public Controller() {
        connection = new DatabaseConnection();
        studentService = new StudentService(connection);
        semesterService = new SemesterService(connection);
        studyComponentService = new StudyComponentService(connection);
        resultService = new ResultService(connection);
        studentSemesterService = new StudentSemesterService(connection);
        menu = new Menu();
    }

    private void mainLoop() {
        while (true) {
            menu.printMainMenu();
            String choice = menu.getInput();
            switch (choice) {
                case "0":
                    connection.closeConnection();
                    LogHandler.log("Program ended.");
                    System.exit(0);
                    break;
                case "a1":
                    Student student = menu.inputStudent(-1);
                    studentService.create(student);
                    break;
                case "a2":
                    ResultSet students = studentService.getAll();
                    ArrayList<Student> studentList = studentService.getArray(students);
                    for (Student s : studentList) {
                        System.out.println(s.getId() + " | " + s.getName());
                    }
                    break;
                case "a3":
                    System.out.println("Enter student id: ");
                    String id = menu.getInput();
                    ResultSet idStudents = studentService.getById(id);
                    ArrayList<Student> idStudentList = studentService.getArray(idStudents);
                    if (idStudentList.isEmpty()) {
                        System.out.println("Student not found.");
                        break;
                    }
                    for (Student s : idStudentList) {
                        System.out.println(s.getId() + " | " + s.getName());
                    }
                    break;
                case "a4":
                    System.out.println("Enter student last name: ");
                    String lastName = menu.getInput();
                    ResultSet lastNameStudents = studentService.getByLastName(lastName);
                    ArrayList<Student> lastNameStudentsList = studentService.getArray(lastNameStudents);
                    if (lastNameStudentsList.isEmpty()) {
                        System.out.println("Student not found.");
                        break;
                    }
                    for (Student s : lastNameStudentsList) {
                        System.out.println(s.getId() + " | " + s.getName());
                    }
                    break;
                case "a5":
                    System.out.println("Enter student id: ");
                    String studentId = menu.getInput();
                    ResultSet updateStudent = studentService.getById(studentId);
                    ArrayList<Student> updateStudentList = studentService.getArray(updateStudent);
                    if (updateStudentList.isEmpty()) {
                        System.out.println("Student not found.");
                        break;
                    }
                    Student studentModel = updateStudentList.get(0);
                    Student updatedStudent = menu.inputStudent(studentModel.getId());
                    studentService.update(studentId, updatedStudent);
                    break;
                case "a6":
                    System.out.println("Enter student id: ");
                    String signupStudentId = menu.getInput();
                    System.out.println("Enter semester id: ");
                    String semesterId = menu.getInput();
                    ResultSet signupStudent = studentService.getById(signupStudentId);
                    ArrayList<Student> signupStudentList = studentService.getArray(signupStudent);
                    if (signupStudentList.isEmpty()) {
                        System.out.println("Student not found.");
                        break;
                    }
                    ResultSet signupSemester = semesterService.getById(semesterId);
                    ArrayList<Semester> signupSemesterList = semesterService.getArray(signupSemester);
                    if (signupSemesterList.isEmpty()) {
                        System.out.println("Semester not found.");
                        break;
                    }
                    StudentSemester studentSemester = new StudentSemester(
                            signupStudentList.get(0).getId(),
                            signupSemesterList.get(0).getId()
                    );
                    studentSemesterService.create(studentSemester);
                    break;
                case "a7":
                    System.out.println("Enter student id: ");
                    String signoutStudentId = menu.getInput();
                    System.out.println("Enter semester id: ");
                    String signoutSemesterId = menu.getInput();
                    ResultSet signoutStudent = studentService.getById(signoutStudentId);
                    ArrayList<Student> signoutStudentList = studentService.getArray(signoutStudent);
                    if (signoutStudentList.isEmpty()) {
                        System.out.println("Student not found.");
                        break;
                    }
                    ResultSet signoutSemester = semesterService.getById(signoutSemesterId);
                    ArrayList<Semester> signoutSemesterList = semesterService.getArray(signoutSemester);
                    if (signoutSemesterList.isEmpty()) {
                        System.out.println("Semester not found.");
                        break;
                    }
                    studentSemesterService.delete(signoutStudentId, signoutSemesterId);
                    break;
                case "a8":
                    System.out.println("Enter student id: ");
                    String semesterStudentId = menu.getInput();
                    ResultSet semesterStudent = studentService.getById(semesterStudentId);
                    ArrayList<Student> semesterStudentList = studentService.getArray(semesterStudent);
                    if (semesterStudentList.isEmpty()) {
                        System.out.println("Student not found.");
                        break;
                    }
                    ResultSet semestersStudent = studentSemesterService.getByField("studentId", semesterStudentId);
                    ArrayList<StudentSemester> semestersStudentList = studentSemesterService.getArray(semestersStudent);
                    if (semestersStudentList.isEmpty()) {
                        System.out.println("No semesters found.");
                        break;
                    }
                    for (StudentSemester ss : semestersStudentList) {
                        ResultSet semester = semesterService.getById(Integer.toString(ss.getSemesterId()));
                        ArrayList<Semester> semesterList = semesterService.getArray(semester);
                        System.out.println(semesterList.get(0).getId() + " | " + semesterList.get(0).getName());
                    }
                    break;
                
                case "b1":
                    System.out.println("Enter semester name: ");
                    String semesterName = menu.getInput();
                    Semester semester = new Semester(-1, semesterName);
                    semesterService.create(semester);
                    break;
                case "b2":
                    ResultSet semesters = semesterService.getAll();
                    ArrayList<Semester> semesterList = semesterService.getArray(semesters);
                    for (Semester s : semesterList) {
                        System.out.println(s.getId() + " | " + s.getName());
                    }
                    break;
                case "b3":
                    System.out.println("Enter semester id: ");
                    String getSemesterId = menu.getInput();
                    ResultSet semesterById = semesterService.getById(getSemesterId);
                    ArrayList<Semester> semesterByIdList = semesterService.getArray(semesterById);
                    if (semesterByIdList.isEmpty()) {
                        System.out.println("Semester not found.");
                        break;
                    }
                    for (Semester s : semesterByIdList) {
                        System.out.println(s.getId() + " | " + s.getName());
                    }
                    break;
                case "b4":
                    System.out.println("Enter semester name: ");
                    String getSemesterName = menu.getInput();
                    ResultSet semesterByName = semesterService.getByName(getSemesterName);
                    ArrayList<Semester> semesterByNameList = semesterService.getArray(semesterByName);
                    if (semesterByNameList.isEmpty()) {
                        System.out.println("Semester not found.");
                        break;
                    }
                    for (Semester s : semesterByNameList) {
                        System.out.println(s.getId() + " | " + s.getName());
                    }
                    break;
                case "b5":
                    System.out.println("Enter semester id: ");
                    String semesterIdToGetStudents = menu.getInput();
                    ResultSet semesterToGetStudents = semesterService.getById(semesterIdToGetStudents);
                    ArrayList<Semester> semesterToGetStudentsList = semesterService.getArray(semesterToGetStudents);
                    if (semesterToGetStudentsList.isEmpty()) {
                        System.out.println("Semester not found.");
                        break;
                    }
                    ResultSet studentsForSemester = studentSemesterService.getByField("semesterId", semesterIdToGetStudents);
                    ArrayList<StudentSemester> studentsForSemesterList = studentSemesterService.getArray(studentsForSemester);
                    if (studentsForSemesterList.isEmpty()) {
                        System.out.println("No students found.");
                        break;
                    }
                    for (StudentSemester ss : studentsForSemesterList) {
                        ResultSet studentOnSemester = studentService.getById(Integer.toString(ss.getStudentId()));
                        ArrayList<Student> studentOnSemesterList = studentService.getArray(studentOnSemester);
                        System.out.println(studentOnSemesterList.get(0).getId() + " | " + studentOnSemesterList.get(0).getName());
                    }
                    break;
                
                case "c1":
                    System.out.println("Enter study component name: ");
                    String studyComponentName = menu.getInput();
                    System.out.println("Enter semester id: ");
                    String StudyComponentSemesterId = menu.getInput();
                    ResultSet studyComponentSemester = semesterService.getById(StudyComponentSemesterId);
                    ArrayList<Semester> studyComponentSemesterList = semesterService.getArray(studyComponentSemester);
                    if (studyComponentSemesterList.isEmpty()) {
                        System.out.println("Semester not found.");
                        break;
                    }
                    StudyComponent studyComponent = new StudyComponent(-1, studyComponentName, studyComponentSemesterList.get(0).getId());
                    studyComponentService.create(studyComponent);
                    break;
                case "c2":
                    ResultSet studyComponents = studyComponentService.getAll();
                    ArrayList<StudyComponent> studyComponentList = studyComponentService.getArray(studyComponents);
                    for (StudyComponent sc : studyComponentList) {
                        System.out.println(sc.getId() + " | " + sc.getName());
                    }
                    break;
                case "c3":
                    System.out.println("Enter study component id: ");
                    String studyComponentId = menu.getInput();
                    ResultSet studyComponentById = studyComponentService.getById(studyComponentId);
                    ArrayList<StudyComponent> studyComponentByIdList = studyComponentService.getArray(studyComponentById);
                    if (studyComponentByIdList.isEmpty()) {
                        System.out.println("Study component not found.");
                        break;
                    }
                    for (StudyComponent sc : studyComponentByIdList) {
                        System.out.println(sc.getId() + " | " + sc.getName());
                    }
                    break;
                case "c4":
                    System.out.println("Enter study component name: ");
                    String getStudyComponentName = menu.getInput();
                    ResultSet studyComponentByName = studyComponentService.getByName(getStudyComponentName);
                    ArrayList<StudyComponent> studyComponentNameList = studyComponentService.getArray(studyComponentByName);
                    if (studyComponentNameList.isEmpty()) {
                        System.out.println("Study component not found.");
                        break;
                    }
                    for (StudyComponent sc : studyComponentNameList) {
                        System.out.println(sc.getId() + " | " + sc.getName());
                    }
                    break;

                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }
}