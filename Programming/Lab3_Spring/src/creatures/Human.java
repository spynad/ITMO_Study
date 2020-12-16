package creatures;

import interfaces.*;
import items.*;

public class Human extends Animal implements ableToRead, LookAt {

    private int lvl = 1;
    private int currentExperience = 0;
    private int totalExperience = 0;
    private int expToNextLvl = 100;

    public static final int MAX_LVL = 100;

    public Human(String name) {
        super(name);
        setPartsLocation(true);
        setEvolutionStage(5);
    }

    @Override
    public void read(Book book) {
        System.out.println(getName() + " is reading a book: " + book.read());
    }

    @Override
    public void lookAt(Picture pic) {
        System.out.println(getName() + " is looking at picture: " + pic.lookAt());
    }

    //TODO: это нужно как-то переделать, тупая функция
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
