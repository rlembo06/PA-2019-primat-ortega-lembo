package weapons;

import annotations.Powerful;
import annotations.Weapon;

@Weapon
public class Bazooka {

    public int damage = 500;
    public int maxAmmunition = 1;

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

