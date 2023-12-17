import java.sql.*;
import java.util.ArrayList;

public class StudentSemesterService extends DatabaseService<StudentSemester> {
    public StudentSemesterService(DatabaseConnection db) {
        super(db);
    }

    @Override
    public void create(StudentSemester obj) {
        DatabaseConnection db = getDb();
        String query = "INSERT INTO studentSemester (studentId, semesterId) VALUES (?, ?)";
        db.executeUpdate(query, obj.getStudentId(), obj.getSemesterId());
    }

    @Override
    public void update(String id, StudentSemester model) {
        throw new UnsupportedOperationException("This method cannot be used.");
    }

    @Override
    public void delete(Object... params) {
        String query = "DELETE FROM studentSemester WHERE studentId = ? AND semesterId = ?";
        getDb().executeUpdate(query, params);
    }

    @Override
    Table getTable() {
        return Table.STUDENT_SEMESTER;
    }

    @Override
    public ArrayList<StudentSemester> getArray(ResultSet rs) {
        ArrayList<StudentSemester> studentSemesters = new ArrayList<>();
        try {
            while (rs.next()) {
                StudentSemester studentSemester = new StudentSemester(
                        Integer.parseInt(rs.getString("studentId")),
                        Integer.parseInt(rs.getString("semesterId"))
                );
                studentSemesters.add(studentSemester);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentSemesters;
    }
}
