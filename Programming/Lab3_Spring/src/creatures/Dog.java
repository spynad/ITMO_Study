package creatures;

public class Dog extends Animal {
    private int barkCount;

    Dog(String name) {
        super(name);
    }

    public void bark() {
        if (barkCount < 50) {
            System.out.println(getName() + ": bark! bark!");
        }
        else {
            System.out.println(getName() + ": bark! bark! This dog has already begun to wheeze from barking.");
        }
    }


}
