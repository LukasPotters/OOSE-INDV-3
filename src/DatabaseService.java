import java.sql.*;
import java.util.ArrayList;

abstract class DatabaseService<T> {
    private DatabaseConnection db;

    enum Table {
        STUDENT,
        SEMESTER,
        STUDY_COMPONENT,
        RESULT,
        STUDENT_SEMESTER
    }

    abstract Table getTable();

    public DatabaseService(DatabaseConnection db) {
        this.db = db;
    }

    abstract public void create(T obj);

    abstract public void update(String id, T model);

    public ResultSet getById(String id) {
        return db.queryItemFilter(getTable(), "id", id);
    }

    public ResultSet getAll() {
        return db.queryItems(getTable());
    }

    public ResultSet getByField(String field, String value) {
        return db.queryItemFilter(getTable(), field, value);
    }

    public DatabaseConnection getDb() {
        return db;
    }

    abstract public ArrayList<T> getArray(ResultSet rs);
}
