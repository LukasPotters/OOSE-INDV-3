import java.sql.*;
import java.util.ArrayList;

public class StudyComponentService extends DatabaseService<StudyComponent> {
    public StudyComponentService(DatabaseConnection db) {
        super(db);
    }

    @Override
    public void create(StudyComponent obj) {
        DatabaseConnection db = getDb();
        String query = "INSERT INTO study_component (name, semesterId) VALUES (?, ?)";
        db.executeUpdate(query, obj.getName(), obj.getSemesterId());
    }

    @Override
    public void update(String id, StudyComponent model) {
        DatabaseConnection db = getDb();
        String query = "UPDATE study_component SET name = ?, semesterId = ? WHERE id = ?";
        db.executeUpdate(query, model.getName(), model.getSemesterId(), id);
    }

    @Override
    public void delete(Object... params) {
        throw new UnsupportedOperationException("This method cannot be used.");
    }

    @Override
    Table getTable() {
        return Table.STUDY_COMPONENT;
    }

    @Override
    public ArrayList<StudyComponent> getArray(ResultSet rs) {
        ArrayList<StudyComponent> studyComponents = new ArrayList<>();
        try {
            while (rs.next()) {
                StudyComponent studyComponent = new StudyComponent(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("name"),
                        Integer.parseInt(rs.getString("semesterId"))
                );
                studyComponents.add(studyComponent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studyComponents;
    }

    public ResultSet getByName(String name) {
        return getByField("name", name);
    }
}