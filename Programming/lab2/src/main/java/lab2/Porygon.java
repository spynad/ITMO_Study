package lab2;

import ru.ifmo.se.pokemon.*;

public class Porygon extends Pokemon{
    Porygon(String name, int lvl) {
        super(name, lvl);
        setStats(65, 60, 70, 85, 75, 40);
        setType(Type.NORMAL);
        setMove(new TriAttack(), new ShadowBall());
    }
}

class TriAttack extends SpecialMove {
    TriAttack() {
        super(Type.NORMAL, 80, 1.0);
    }

    protected void applyOppEffects(Pokemon pokemon) {
        double res = 100 * Math.random();
        if (res < 20) {
            if (res < 6.67) {
                Effect.paralyze(pokemon);
            }
            else if (res < 13.3) {
                Effect.burn(pokemon);
            }
            else {
                Effect.freeze(pokemon);
            }
        }
    }
}

class ShadowBall extends SpecialMove {
    ShadowBall() {
        super(Type.GHOST, 80, 1);
    }

    protected void applyOppEffects(Pokemon pokemon) {
        if(Math.random() < 0.20) {
            pokemon.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }
}
