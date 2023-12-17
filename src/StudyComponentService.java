public class StudyComponentService extends DatabaseService<StudyComponent> {
    public StudyComponentService(DatabaseConnection db) {
        super(db);
    }

    @Override
    public void create(StudyComponent obj) {
        DatabaseConnection db = getDb();
        String query = "INSERT INTO studyComponent (id, name, semesterId) VALUES (?, ?, ?)";
        db.executeUpdate(query, obj.getId(), obj.getName(), obj.getSemesterId());

    }

    @Override
    public void update(String id, StudyComponent model) {
        DatabaseConnection db = getDb();
        String query = "UPDATE studyComponent SET name = ?, semesterId = ? WHERE id = ?";
        db.executeUpdate(query, model.getName(), model.getSemesterId(), id);
    }

    @Override
    Table getTable() {
        return Table.STUDY_COMPONENT;
    }
}