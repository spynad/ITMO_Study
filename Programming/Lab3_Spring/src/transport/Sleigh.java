package transport;

import java.util.ArrayList;
import creatures.Human;

public class Sleigh {
    private final String name;
    private ArrayList<Human> helpers = new ArrayList<>();
    private Object cargo;

    Sleigh(String name) {
        this.name = name;
    }


    public void addHelper(Human human) {
        if (helpers.size() < 3) {
            helpers.add(human);
            System.out.println(human.getName() + " now belongs to " + name);
        } else {
            System.out.println("Can't attach" + name + "to " + human.getName());
        }
    }

    public void addCargo(Object object) {
        if (cargo == null) {
            cargo = object;
            System.out.println("Груз " + object.toString() + " погружен в сани");
        }
    }

    public void sendToBase() {

    }
}
