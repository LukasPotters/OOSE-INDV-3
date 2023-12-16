public class StudentSemester {
    private int studentId;
    private int semesterId;

    public StudentSemester(int studentId, int semesterId) {
        this.studentId = studentId;
        this.semesterId = semesterId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public int getStudentId() {
        return studentId;
    }
}
