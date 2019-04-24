import entities.Weapons;
import plugins.MovementPlugin;
import plugins.WeaponPlugin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;

public class Test {

    public static void main(String args[]) throws IOException, MalformedURLException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

        /*MovementPlugin movement = new MovementPlugin();
        List<Class<?>> movementClasses = movement.getClasses();
        System.out.println("Movement : " + movementClasses);
        for (Class<?> cl : movementClasses) {
            System.out.println("- Movement class : " + cl.getName());
        }

        WeaponPlugin weapon = new WeaponPlugin();
        List<Class<?>> weaponClasses = weapon.getClasses();
        System.out.println("Weapon : " + weaponClasses);
        for (Class<?> cl : weaponClasses) {
            System.out.println("- Weapon class : " + cl.getName());
        }

        Class<?> bazooka = weapon.getClassByName("weapons.Bazooka");
        Object methodsPowerful = weapon.getMethodsByAnnotation(bazooka, annotations.Powerful.class, 5);
        System.out.println("Method Powerful : " + methodsPowerful);

        List<Class<?>> classesWeaponAnnoted = weapon.getClassesByAnnotation(weaponClasses, annotations.Weapon.class);
        for (Class<?> cl : classesWeaponAnnoted) {
            System.out.println("- method Weapon Annoted : " + cl.getName());
        }*/

        Weapons weapons = new Weapons();
        System.out.println("WEAPONS: " + weapons.getList());
    }
}
