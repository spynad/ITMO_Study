package lab2;

import ru.ifmo.se.pokemon.*;

public class Lab2 {
    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Sawk("Reimu", 20));
        b.addAlly(new Skrelp("Marisa", 20));
        b.addAlly(new Dragalge("Sanae", 20));
        b.addFoe(new Porygon("Sakuya", 20));
        b.addFoe(new Porygon2("Remilia", 20));
        b.addFoe(new PorygonZ("Flandre", 20));
        b.go();


    }
}
