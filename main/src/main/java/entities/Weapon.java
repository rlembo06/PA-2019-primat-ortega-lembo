package entities;

public class Weapon {
    private String id;
    private String label;
    private int bulletCapacity;

    public Weapon(String id, String label, int bulletCapacity) {
        this.id = id;
        this.label = label;
        this.bulletCapacity = bulletCapacity;
    }

    public Weapon(String label) {
        this.label = label;
    }

    public int getBullets(){ return bulletCapacity;}

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
