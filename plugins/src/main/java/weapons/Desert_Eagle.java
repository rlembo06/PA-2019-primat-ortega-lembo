package weapons;

import annotations.Powerful;
import annotations.Weapon;



@Weapon
public class Desert_Eagle {

    public int damage = 100;
    public int maxAmmunition = 3;

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
