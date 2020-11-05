package lab2;

import ru.ifmo.se.pokemon.*;

public class PorygonZ extends Porygon2{
    PorygonZ(String name, int lvl) {
        super(name, lvl);
        setStats(85, 80, 70, 135, 75, 90);
        setType(Type.NORMAL);
    }
}

class NastyPlot extends StatusMove {
    NastyPlot() {
        super(Type.DARK, 0, 0);
    }

    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPECIAL_ATTACK, 2);
    }
	
	protected String describe() {
		 return "использует Nasty Plot";
	 }
}
