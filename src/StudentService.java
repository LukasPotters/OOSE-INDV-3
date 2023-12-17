import java.sql.*;
import java.util.ArrayList;

public class StudentService extends DatabaseService<Student> {
    public StudentService(DatabaseConnection db) {
        super(db);
    }

    @Override
    public void create(Student obj) {
        DatabaseConnection db = getDb();
        String query = "INSERT INTO student (firstName, lastName) VALUES (?, ?)";
        db.executeUpdate(query, obj.getFirstName(), obj.getLastName());

    }

    @Override
    public void update(String id, Student model) {
        DatabaseConnection db = getDb();
        String query = "UPDATE student SET firstName = ?, lastName = ? WHERE id = ?";
        db.executeUpdate(query, model.getFirstName(), model.getLastName(), id);
    }

    @Override
    Table getTable() {
        return Table.STUDENT;
    }

    @Override
    public ArrayList<Student> getArray(ResultSet rs) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            while (rs.next()) {
                Student student = new Student(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public ResultSet getByLastName(String lastName) {
        return getByField("lastName", lastName);
    }
}