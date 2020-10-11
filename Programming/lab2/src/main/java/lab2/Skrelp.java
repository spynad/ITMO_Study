package lab2;

import ru.ifmo.se.pokemon.*;

public class Skrelp extends Pokemon {
    Skrelp(String name, int lvl) {
        super(name, lvl);
        setStats(50, 60, 60, 60, 60, 30);
        setType(Type.POISON, Type.WATER);
        setMove(new WaterPulse(), new Tackle(), new DragonPulse());
    }
}

class WaterPulse extends SpecialMove {
    WaterPulse() {
        super(Type.WATER, 60, 1.0);
    }

    protected void applyOppEffects(Pokemon pokemon) {
        if (Math.random() < 0.2) {
            pokemon.confuse();
        }
    }
	
	protected String describe() {
		 return "использует Water Pulse";
	 }
}

class Tackle extends PhysicalMove {
    Tackle() {
        super(Type.NORMAL, 30, 1.0);
    }
	
	protected String describe() {
		 return "использует Tackle";
	 }
}

class DragonPulse extends SpecialMove {
    DragonPulse() {
        super(Type.DRAGON, 85, 1.0);
    }
	
	protected String describe() {
		 return "использует Dragon Pulse";
	 }
}