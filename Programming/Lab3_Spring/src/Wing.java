public final class Wing extends Part implements IPart {
    Wing(String name) {
        super(name);
    }

    @Override
    public void use(String action) {
        if (action == "swim")
            System.out.println("The beast is swimming!");
        else if (action == "fly")
            System.out.println("The beast is flying!");
    }

    @Override
    public String toString() {
        return "Wing. " + getName() + ".";
    }
}