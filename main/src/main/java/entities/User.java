package entities;

public class User {
    private int id;
    private String name = "Joueur";
    private Weapon weapon = new Weapon();
    private Movement movement = new Movement();

    public User(int id) {
        this.id = id;
    }

    public User(int id, Weapon weapon, Movement movement) {
        this.id = id;
        this.weapon = weapon;
        this.movement = movement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name + " " + id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    @Override
    public String toString(){
        return name + " " + id;
    }
}
