package weapons;

import annotations.weapons.Powerful;
import annotations.weapons.Weapon;


@Weapon
public class Mamitraillette {

    public int damage = 25;
    public int maxAmmunition = 10;

    @Powerful
    public int shot(int bullets) {
        if(bullets == 0){
            reload(bullets);
        }else {
            System.out.println("Shot!!!");
            return damage;
        }
        return 1;
    }

    public int reload(int bullets){
        System.out.println("Reloading....");
        return bullets = maxAmmunition;
    }
}
