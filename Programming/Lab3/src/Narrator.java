public class Narrator extends Human{
    Narrator(String name) {
        super(name);
    }

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
            if (beasts[i].isCorruptState() == true) {
                System.out.println("Beast" + i + " is corrupted. Narrator cannot compare it.");
            }
            else {
                System.out.println("Narrator have found an uncorrupted beast." +
                        "The narrator assumes that they once lived outside Antarctica.");
                for (int j = 0; j < beasts.length; j++) {
                    beasts[i].changePol(PlaceOfLiving.NON_ANTARCTICA);
                }

                break;
            }
        }
        return beasts;
    }
}
