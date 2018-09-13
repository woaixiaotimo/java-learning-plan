package reflectTest;

public class Subject  extends Test implements SubjectChild{
    private int id;
    private String name;

    public Subject() {
        System.out.println("prepare...");
    }

    public Subject(int id, String name) {
        System.out.println("instance:" + id + name);
    }

    public void describe() {
        System.out.println("describe the world");
    }

    public void describe(int id) {
        System.out.println("describe the world" + id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
