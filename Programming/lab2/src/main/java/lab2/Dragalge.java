package lab2;

import ru.ifmo.se.pokemon.*;

public class Dragalge extends Skrelp {
    Dragalge(String name, int lvl) {
        super(name, lvl);
        setStats(65, 75, 90, 97, 123, 44);
        setType(Type.POISON, Type.DRAGON);
        setMove(new WaterPulse(), new Tackle(), new DragonPulse(), new FocusBlast());
    }
}

class FocusBlast extends SpecialMove {
    FocusBlast() {
        super(Type.FIGHTING, 120, 0.7);
    }

    protected void applyOppEffects(Pokemon pokemon) {
        if(Math.random() < 0.1) {
            pokemon.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }
}
