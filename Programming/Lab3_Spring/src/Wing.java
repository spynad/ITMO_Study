public final class Wing extends Part implements Parts {
    Wing(String name) {
        super(name);
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
        return "Wing. " + getName() + ".";
    }
}