package enums;

import interfaces.canBeAdded;

import java.util.ArrayList;

public enum Location {
    SOMEWHERE(),
    BAY(),
    SHIP(),
    BASE();

    public final ArrayList<canBeAdded> storage = new ArrayList<>();

    public void addInStorage(canBeAdded object) {
        storage.add(object);
        System.out.println(object.getName() + " successfully added in " + toString());
    }

    public ArrayList<canBeAdded> getStorage() {
        return storage;
    }

    //TODO: реализовать систему хранения грузов, которые переданы при помощи Sleigh
}
