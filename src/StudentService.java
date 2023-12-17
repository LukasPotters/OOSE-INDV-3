public class StudentService extends DatabaseService<Student> {
    public StudentService(DatabaseConnection db) {
        super(db);
    }

    @Override
    public void create(Student obj) {
        DatabaseConnection db = getDb();
        String query = "INSERT INTO student (id, firstName, lastName) VALUES (?, ?, ?)";
        db.executeUpdate(query, obj.getId(), obj.getFirstName(), obj.getLastName());

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
}