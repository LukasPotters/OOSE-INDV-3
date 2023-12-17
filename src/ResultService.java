public class ResultService extends DatabaseService<Result> {
    public ResultService(DatabaseConnection db) {
        super(db);
    }

    @Override
    public void create(Result obj) {
        DatabaseConnection db = getDb();
        String query = "INSERT INTO result (studentId, componentId, date, result) VALUES (?, ?, ?, ?)";
        db.executeUpdate(query, obj.getStudentId(), obj.getComponentId(), obj.getDate(), obj.getResult());

    }

    @Override
    public void update(String id, Result model) {
        throw new UnsupportedOperationException("This method cannot be used.");
    }

    @Override
    Table getTable() {
        return Table.RESULT;
    }
}
