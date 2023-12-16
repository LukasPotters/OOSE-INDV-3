public class Result {
    private int studentId;
    private int componentId;
    private String date;
    private float result;

    public Result(int studentId, int componentId, String date, float result) {
        this.studentId = studentId;
        this.componentId = componentId;
        this.date = date;
        this.result = result;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getComponentId() {
        return componentId;
    }

    public String getDate() {
        return date;
    }

    public float getResult() {
        return result;
    }
    
    public void setResult(float result) {
        this.result = result;
    }
}
