package Data.Prio;

public class Priority {

    private int id;
    private String name;
    private int level;
    private String description;
    private int colorId;

    public Priority(int id, String name, int level, String description, int colorId)
    {
        this.id = id;
        this.name = name;
        this.level = level;
        this.description = description;
        this.colorId = colorId;
    }
    public Priority(String name, int level, String description, int colorId)
    {
        this.name = name;
        this.level = level;
        this.description = description;
        this.colorId = colorId;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", colorId=" + colorId +
                '}';
    }
}
