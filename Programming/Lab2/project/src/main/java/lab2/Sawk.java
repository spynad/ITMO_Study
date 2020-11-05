package lab2;

import ru.ifmo.se.pokemon.*;

public class Sawk extends Pokemon {
    Sawk(String name, int lvl) {
        super(name, lvl);
        setType(Type.FIGHTING);
        setStats(75, 125, 75, 30, 75, 85);
        setMove(new BulkUp(), new Leer(), new DoubleKick(), new Facade());
    }
}

class BulkUp extends StatusMove{
     BulkUp() {
         super(Type.FIGHTING, 0, 0);
     }

     protected void applySelfEffects(Pokemon p) { ;
         p.setMod(Stat.ATTACK, 1);
         p.setMod(Stat.DEFENSE, 1);
     }
	 
	 protected String describe() {
		 return "использует Bulk Up";
	 }

}

class Leer extends StatusMove {
    Leer() {
        super(Type.NORMAL, 0, 1.0);
    }

    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.DEFENSE, -1);
    }
	
	protected String describe() {
		 return "использует Leer";
	 }
}

class DoubleKick extends PhysicalMove {
    DoubleKick() {
        super(Type.FIGHTING, 30, 1.0, 0, 2);
    }
	
	protected String describe() {
		 return "использует Double Kick";
	 }
}

class Facade extends PhysicalMove {
    Facade() {
        super(Type.NORMAL, 70, 1.0);
    }

    protected void applyOppEffects(Pokemon p) {
        Status stat = p.getCondition();
        if (stat == Status.BURN || stat == Status.POISON || stat == Status.PARALYZE) {
            power = 140;
        } else {
            power = 70;
        }
    }
	
	protected String describe() {
		 return "использует Facade";
	 }
}