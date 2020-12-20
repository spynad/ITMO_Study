package creatures;

import entities.Stratum;
import enums.Family;
import places.Base;
import enums.Location;
import interfaces.*;
import items.*;

public class Human extends Animal implements ableToRead, ableToLookAt{

    private int lvl = 1;
    private int currentExperience = 0;
    private int totalExperience = 0;
    private int expToNextLvl = 100;

    private final boolean isSearching = false;

    public static final int MAX_LVL = 100;

    public Human(String name) {
        super(name);
        setPartsLocation(true);
        setEvolutionStage(5);
        setFamily(Family.HOMINIDAE);
    }

    public boolean isSearching() {
        return isSearching;
    }

    public int getLvl() {
        return lvl;
    }

    public int getExpToNextLvl() {
        return expToNextLvl;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    @Override
    public void read(Book book) {
        System.out.println(getName() + " is reading a book: " + book.read());
    }

    @Override
    public void lookAt(Picture pic) {
        System.out.println(getName() + " is looking at picture: " + pic.lookAt());
    }

    public void chipOffStalagmite(Stratum.Stalagmite stalagmite) {
        int damage = (int) (Math.random()*600);
        while (!stalagmite.isChippedOff()){
            System.out.println(getName() + " tried to damage the stalagmite");
            stalagmite.takeDamage(damage);
        }

    }

    public void surprise(String text) {
        System.out.println(getName() + " is surprised: " + text);
    }

    public void watchOverADog(Dog dog) {
        System.out.println(getName() + " is watching over " + dog.getName());
    }

    public void searchForSomething() {
        if(!isSearching) {
            System.out.println(getName() + " has started to search for something.");
        } else {
            System.out.println(getName() + " stopped to search for something.");
        }
    }

    public void transport(Location loc, canBeMoved obj) {
        Location selfLoc = getLoc();
        if (loc == obj.getLocation()) {
            System.out.println(obj.toString() + " is already at desired destination");
        }
        else {
            System.out.println("Moving " + obj.toString() + " from " + obj.getLocation() + " to " +
                    loc + " by " + getName());
            obj.move(loc);
        }
    }

    public void sendMessageToBase(String message, Base base) {
        if (base.isConnectionEstablished()) {
            base.acceptMessage(message);
        }
    }

    public void shame(String reason) {
        System.out.println(getName() + " is ashamed: " + reason);
    }

    public void makeADiscovery(String discovery) {
        System.out.println(getName() + " made a discovery: " + discovery);
    }

    public void giveThanks(Human human, String reason){
        System.out.println(getName() + " thanks " + human.getName() + " for: " + reason);
    }


    public void studyMaterial(StudyMaterial sm) {
        int smExp = sm.getExpAmount();

        System.out.println(getName() + " gets " + smExp + " EXP from [" + sm.getType() + "].");

        currentExperience += smExp;
        totalExperience += smExp;
        updateLevel();

    }

    public void updateLevel() {
        int lvl = this.lvl - 1;
        int nextLevel = 0;
        int t = expToNextLvl;

        if (this.lvl == Human.MAX_LVL)
        {
            //Количество опыта при 100 уровне
            currentExperience = 0;
            totalExperience = 495000;

            System.out.println(getName() + " is maxed out.");
            return;
        }
        else if (currentExperience >= expToNextLvl) {
            for(;;) {
                if (currentExperience < t) {
                    nextLevel = lvl + 1;
                    break;
                }

                lvl++;
                t = t + (100 + lvl * 100);
            }
        }

        if (nextLevel > 0) {

            expToNextLvl = t;
            currentExperience -= t - (100 + lvl*100);
            this.lvl = nextLevel;

            if (nextLevel >= 100) {
                currentExperience = 0;
                this.lvl = 100;
            }

            System.out.println("LVL UP! " + getName() + " is now on Level " + this.lvl + ".");
        }

    }

    @Override
    public String toString() {
        return "creatures.Human. " + getName() + ". "
                + " enums.Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString()
                + " enums.Direction: " + getDirection().toString();
    }

    public void writeAbout(Object object) {
        System.out.println(getName() + " is writing about " + object.toString());
    }

}
