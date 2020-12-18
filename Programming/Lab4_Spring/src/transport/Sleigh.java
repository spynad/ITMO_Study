package transport;

import java.util.ArrayList;
import creatures.Human;
import enums.Location;
import interfaces.Wind;
import interfaces.canBeAdded;

public class Sleigh {
    private final String name;
    private final ArrayList<Human> helpers = new ArrayList<>();
    private Location loc = Location.SOMEWHERE;
    private Location prevLoc;
    private canBeAdded cargo;

    public Sleigh(String name) {
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

    public void addCargo(canBeAdded object) {
        if (cargo == null) {
            cargo = object;
            System.out.println("Груз " + object.getName() + " погружен в сани");
        }
    }

    public void sendToBay() {
        Wind wind = new Wind() {
            private int speed;
            @Override
            public void blow(int speed) {
                System.out.println("The wind blows at a speed " + speed);
                this.speed = speed;
            }
            public int getSpeed() {
                return speed;
            }
        };

        String windStr;
        if(wind.getSpeed() > 30) {
            windStr = "The wind is strong";
        }
        else if (wind.getSpeed() > 15) {
            windStr = "The wind is okay";
        }
        else {
            windStr = "Almost no wind.";
        }
        if (helpers.size() == 3) {
            if (cargo == null) {
                System.out.println("Sl is now at the bay without cargo. " + windStr);
            }
            else {
                System.out.println("Sl is went to the bay with cargo " + cargo.getName() + " " + windStr);
            }
            prevLoc = loc;
            loc = Location.BAY;
        }
    }

    public void unloadCargo() {
        //TODO: реализовать выгрузку груза в локации Location
    }
}
