public class Wing extends Part {
    Wing(String name) {
        super(name);
    }

    public static void use(String action) {
        if (action == "swim")
            System.out.println("The beast is swimming!");
        else if (action == "fly")
            System.out.println("The beast is flying!");
    }

}
//usable