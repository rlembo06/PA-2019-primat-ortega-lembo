package weapons;

import annotations.weapons.Powerful;
import annotations.weapons.Weapon;

@Weapon
public class Bazooka {

    public int shot() {
        return 10;
    }

    @Powerful
    public int recharge(int bullets) { return bullets * 10; }

    public String getBrand (String brand) { return "Its brand : " + brand; }
}
