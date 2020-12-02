public final class Narrator extends Human{
    Narrator(String name) {
        super(name);
    }

    public void thinkAboutElderOrigin(Elder elder, UnicellularOrganism org1) {
        if (elder.getEvolutionStage() < org1.getEvolutionStage()) {
            System.out.println("Narrator is confused! Elders is on evolution stage "
                    + elder.getEvolutionStage() + " while unicellular organism is on"
                    + " evolution stage " + org1.getEvolutionStage());
        }
        else {
            System.out.println("Narrator thinks that everything is fine.");
        }

    }

    public Elder[] compareAncientWithElders(Elder[] elders) {
        for (int i = 0; i < elders.length; i++) {
            if (elders[i].isCorruptState()) {
                System.out.println("Beast " + elders[i].getName() + " is corrupted. Narrator cannot compare it.");
            }
            else {
                System.out.println("Narrator have found an uncorrupted beast. " +
                        "The narrator assumes that they once lived outside Antarctica.");
                for (int j = 0; j < elders.length; j++) {
                    elders[j].changePol(PlaceOfLiving.NON_ANTARCTICA);
                }
                break;
            }

        }
        return elders;
    }

    public void talkAbout(Object obj) {
        System.out.println("Narrator talks about " + obj.toString());
    }

    public void say(String str) {
        System.out.println("Narrator: " + str);
    }

    public void remember(String str) {
        System.out.println("Narrator remembers " + str);
    }

    @Override
    public String toString() {
        return "Narrator.";
    }
}
