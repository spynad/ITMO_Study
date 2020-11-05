package lab2;

import ru.ifmo.se.pokemon.*;

public class Porygon2 extends Porygon {
    Porygon2(String name, int lvl) {
        super(name, lvl);
        setStats(85, 80, 90, 105, 95, 60);
        setType(Type.NORMAL);
        setMove(new TriAttack(), new ShadowBall(), new DefenseCurl());
    }
}

class DefenseCurl extends StatusMove {
    DefenseCurl() {
        super(Type.NORMAL, 0, 0);
    }

    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.DEFENSE, 1);
    }
	
	protected String describe() {
		 return "использует Defense Curl";
	 }
}
