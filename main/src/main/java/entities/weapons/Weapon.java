package entities;

public class Weapon {
    private String id;
    private String label;

    public Weapon(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public Weapon(String label) {
        this.label = label;
    }

    public Weapon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}
