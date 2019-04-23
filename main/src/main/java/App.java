import plugins.Movement;
import plugins.Weapon;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.List;

public class App {

    public static void main(String args[])  throws IOException, MalformedURLException, ClassNotFoundException {

        /* PluginLoader pluginLoader = new PluginLoader();
        pluginLoader.loadPlugin();
        List<Class<?>> classes = pluginLoader.getClasses();

        System.out.println(" All classes : " + classes); */

        Movement movement = new Movement();
        List<Class<?>> movementClasses = movement.getClasses();
        System.out.println("Movement : " + movementClasses);
        for (Class<?> cl : movementClasses) {
            System.out.println("- Movement class : " + cl.getName());
        }

        Weapon weapon = new Weapon();
        List<Class<?>> weaponClasses = weapon.getClasses();
        System.out.println("Weapon : " + weaponClasses);
        for (Class<?> cl : weaponClasses) {
            System.out.println("- Weapon class : " + cl.getName());
        }

        Class<?> bazooka = weapon.getClassByName("weapons.Bazooka");
        List<Method> methodsPowerful = weapon.getMethodsByAnnotation(bazooka, annotations.Powerful.class);
        for (Method method : methodsPowerful) {
            System.out.println("- method Powerful : " + method.getName());
        }

        List<Class<?>> classesWeaponAnnoted = weapon.getClassesByAnnotation(weaponClasses, annotations.Weapon.class);
        for (Class<?> cl : classesWeaponAnnoted) {
            System.out.println("- method Weapon Annoted : " + cl.getName());
        }
    }
}
