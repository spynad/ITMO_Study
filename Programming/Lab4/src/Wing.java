import enums.PartLocation;

public final class Wing extends Part implements Parts {
    Wing(String name, PartLocation partLocation) {
        super(name, partLocation);
    }

    @Override
    public void use(String name, String action) {
        if (action.equals("swim"))
            System.out.println(name + " is swimming!");
        else if (action.equals("fly"))
            System.out.println(name + " is flying!");
    }

    public void use(String name) {
        System.out.println(name + " uses wing!");
    }

    @Override
    public String toString() {
        return "Wing. " + getName() + ".";
    }
}