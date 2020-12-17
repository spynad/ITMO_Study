package creatures;

import enums.PlaceOfLiving;

public final class Narrator extends Human{
    public Narrator(String name) {
        super(name);
    }

    public void thinkAboutElderOrigin(Elder elder, UnicellularOrganism org1) {
        if (elder.getEvolutionStage() < org1.getEvolutionStage()) {
            System.out.println("creatures.Narrator is confused! Elders is on evolution stage "
                    + elder.getEvolutionStage() + " while unicellular organism is on"
                    + " evolution stage " + org1.getEvolutionStage());
        }
        else {
            System.out.println("creatures.Narrator thinks that everything is fine.");
        }

    }

    public Elder[] compareAncientWithElders(Elder[] elders) {
        for (int i = 0; i < elders.length; i++) {
            if (elders[i].isCorruptState()) {
                System.out.println("Beast " + elders[i].getName() + " is corrupted. creatures.Narrator cannot compare it.");
            }
            else {
                System.out.println("creatures.Narrator have found an uncorrupted beast. " +
                        "The narrator assumes that they once lived outside Antarctica.");
                for (int j = 0; j < elders.length; j++) {
                    elders[j].changePol(PlaceOfLiving.NON_ANTARCTICA);
                }
                break;
            }

        }
        return elders;
    }

    public void dissect(Organism organism) {
        System.out.println("The narrator is dissecting " + organism.getName());
    }

    public void talkAbout(Object obj) {
        System.out.println("creatures.Narrator talks about " + obj.toString());
    }

    public void say(String str) {
        System.out.println("creatures.Narrator: " + str);
    }

    public void remember(String str) {
        System.out.println("creatures.Narrator remembers " + str);
    }

    @Override
    public String toString() {
        return "creatures.Narrator.";
    }
}
