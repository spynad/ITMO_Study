package creatures;

import java.util.Objects;
import creatures.parts.*;
import enums.*;
import interfaces.canBeAdded;

public final class Elder extends MulticellularOrganism implements canBeAdded {
    private boolean corruptState;
    private boolean isUnderwater;
    private final Parts wing = new Wing("wing", PartLocation.SPINE);
    private final Parts tentacle = new Tentacle("wing", PartLocation.SPINE);

    public Elder(String name) {
        super(name);
        setPartsLocation(true);
        setEvolutionStage(0);
        changePol(PlaceOfLiving.ANTARCTICA);
    }

    public boolean isCorruptState() {
        return corruptState;
    }

    public boolean isUnderwater() {
        return isUnderwater;
    }

    public void setCorruptState(boolean corrupt) {
        corruptState = corrupt;
        System.out.println(getName() + " is now corrupted.");
    }

    @Override
    public void setLocation(Location loc) {
        setLoc(loc);
    }

    public void setUnderwater(boolean underwater) {
        isUnderwater = underwater;
    }

    public void fly() {
        wing.use("fly");
    }

    public void swim(){
        wing.use("swim");
    }

    public void moveUnderWater() {
        tentacle.use("move");
    }

    private final class Wing extends Part implements Parts {
        public Wing(String name, PartLocation partLocation) {
            super(name);
            setPartLocation(partLocation);
        }

        @Override
        public void use(String action) {
            if (action.equals("swim"))
                System.out.println("The beast is swimming!");
            else if (action.equals("fly"))
                System.out.println("The beast is flying!");
        }

        @Override
        public String toString() {
            return "creatures.parts.Wing. " + getName() + ".";
        }
    }

    private final class Tentacle extends Part implements Parts {
        private final int size = 4;
        private Muscularity muscularity = Muscularity.HIGH_MUSCLES;

        Tentacle(String name, PartLocation partLocation) {
            super(name);
            setPartLocation(partLocation);

        }

        public Muscularity getMuscularity() {
            return muscularity;
        }

        public void setMuscularity(Muscularity muscularity) {
            this.muscularity = muscularity;
        }

        @Override
        public void use(String action) {
            class Paws extends Part implements Parts {
                Paws(String name) {
                    super(name);
                }

                @Override
                public void use(String action) {
                    if(action.equals("move")) {
                        System.out.println("The beast is moving underwater");
                    }
                }

            }
            if (action.equals("move")) {
                if (isUnderwater) {
                    System.out.println("The beast is moving");
                }
                else {
                    Paws paws = new Paws("paws");
                    paws.use("move");
                }
            }
        }
    }

    public static String description() {
        return "Ancients, those who allegedly gave birth to life on Earth, either for fun or by mistake.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elder elder = (Elder) o;
        return corruptState == elder.corruptState &&
                Objects.equals(wing, elder.wing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corruptState, wing);
    }

    @Override
    public String toString() {
        String corrupt;
        if(isCorruptState())
            corrupt = "corrupted";
        else
            corrupt = "uncorrupted";
        return getName() + " is " + corrupt + " "
                + " Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString()
                + " Direction: " + getDirection().toString();
    }
}
