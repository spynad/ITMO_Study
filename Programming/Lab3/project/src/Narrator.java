public class Narrator extends Human{
    Narrator(String name) {
        super(name);
    }

    //TODO: срочно пересмотреть этот метод, мне он не нравится
    public void thinkAboutBeastOrigin (Beast beast,
                                       UnicellularOrganism org1,
                                       MulticellularOrganism org2,
                                       Plant plant,
                                       Animal animal) {
        if (beast.getEvolutionStage() < org1.getEvolutionStage()) {
            System.out.println("Narrator is confused! Beasts is on evolution stage "
                    + beast.getEvolutionStage() + " while unicellular organism is on"
                    + " evolution stage " + org1.getEvolutionStage());
        }
        else {
            System.out.println("Narrator thinks that everything is fine.");
        }

    }

    public Beast[] compareAncientWithBeasts(Beast[] beasts) {
        for (int i = 0; i < beasts.length; i++) {
            if (beasts[i].isCorruptState()) {
                System.out.println("Beast" + i + " is corrupted. Narrator cannot compare it.");
            }
            else {
                System.out.println("Narrator have found an uncorrupted beast." +
                        "The narrator assumes that they once lived outside Antarctica.");
                for (int j = 0; j < beasts.length; j++) {
                    beasts[j].changePol(PlaceOfLiving.NON_ANTARCTICA);
                }
                break;
            }

        }
        return beasts;
    }

    public void talkAbout(Object obj) {
        System.out.println("Narrator talks about " + obj.toString());
    }

    public void remember(String str) {
        System.out.println("Narrator remembers " + str);
    }

    @Override
    public String toString() {
        return "Narrator.";
    }
}
