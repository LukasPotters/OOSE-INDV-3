import java.sql.*;
import java.util.ArrayList;

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
    public void delete(Object... params) {
        throw new UnsupportedOperationException("This method cannot be used.");
    }

    @Override
    Table getTable() {
        return Table.RESULT;
    }

    @Override
    public ArrayList<Result> getArray(ResultSet rs) {
        ArrayList<Result> results = new ArrayList<>();
        try {
            while (rs.next()) {
                Result result = new Result(
                        Integer.parseInt(rs.getString("studentId")),
                        Integer.parseInt(rs.getString("componentId")),
                        rs.getString("date"),
                        Float.parseFloat(rs.getString("result"))
                );
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
