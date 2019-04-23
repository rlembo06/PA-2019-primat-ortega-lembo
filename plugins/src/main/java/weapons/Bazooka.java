package weapons;

import annotations.Powerful;
import annotations.Weapon;

@Weapon
public class Bazooka {

    @Powerful
    public int shot() {
        return 10;
    }

}
