import java.sql.*;
import java.util.ArrayList;

public class SemesterService extends DatabaseService<Semester> {
    public SemesterService(DatabaseConnection db) {
        super(db);
    }

    @Override
    public void create(Semester obj) {
        DatabaseConnection db = getDb();
        String query = "INSERT INTO semester (id, name) VALUES (?, ?)";
        db.executeUpdate(query, obj.getId(), obj.getName());

    }

    @Override
    public void update(String id, Semester model) {
        DatabaseConnection db = getDb();
        String query = "UPDATE semester SET name = ? WHERE id = ?";
        db.executeUpdate(query, model.getName(), id);
    }

    @Override
    Table getTable() {
        return Table.SEMESTER;
    }

    @Override
    public ArrayList<Semester> getArray(ResultSet rs) {
        ArrayList<Semester> semesters = new ArrayList<>();
        try {
            while (rs.next()) {
                Semester semester = new Semester(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("name")
                );
                semesters.add(semester);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return semesters;
    }
}
