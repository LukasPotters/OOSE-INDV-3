public class StudyComponent {
    private int id;
    private String name;
    private int semesterId;

    public StudyComponent(int id, String name, int semesterId) {
        this.id = id;
        this.name = name;
        this.semesterId = semesterId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemesterId() {
        return semesterId;
    }
}
