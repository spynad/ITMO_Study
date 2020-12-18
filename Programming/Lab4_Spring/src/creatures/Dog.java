package creatures;

import enums.Family;

public class Dog extends Animal {
    private int barkCount;

    Dog(String name) {
        super(name);
        setFamily(Family.CANIDAE);
    }

    public void bark() {
        if (barkCount < 10) {
            System.out.println(getName() + ": bark! bark!");
        }
        else {
            System.out.println(getName() + ": bark! bark! This dog has already begun to wheeze from barking.");
        }
    }


}
