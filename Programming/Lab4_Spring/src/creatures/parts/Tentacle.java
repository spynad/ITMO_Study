package creatures.parts;

import enums.Muscularity;
import enums.PartLocation;

public class Tentacle extends Part {
    private int size;
    private Muscularity muscularity = Muscularity.HIGH_MUSCLES;


    //TODO: implement “lapki”
    Tentacle(String name, int size, PartLocation partLocation) {
        super(name);
        this.size = size;
        setPartLocation(partLocation);

    }

    public Muscularity getMuscularity() {
        return muscularity;
    }

    public void setMuscularity(Muscularity muscularity) {
        this.muscularity = muscularity;
    }
}
