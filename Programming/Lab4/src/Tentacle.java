import enums.Muscularity;
import enums.PartLocation;

public class Tentacle extends Part implements Parts {
    private int size;
    private Muscularity muscularity = Muscularity.HIGH_MUSCLES;


    //TODO: implement “lapki”
    Tentacle(String name, PartLocation partLocation) {
        super(name, partLocation);

    }

    public Muscularity getMuscularity() {
        return muscularity;
    }

    public void setMuscularity(Muscularity muscularity) {
        this.muscularity = muscularity;
    }

    public void use(String name, String action) {
        System.out.println(name + " uses tentacle!");
    }
    public void use(String name) {
        System.out.println(name + " uses tentacle!");
    }
}
