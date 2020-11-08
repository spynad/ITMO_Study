public class Human extends Animal implements Readable, Showable {
    Human(String name) {
        super(name);
        setPartsLocation(true);
        setEvolutionStage(5);
    }

    public void read(Book book) {
        System.out.println(getName() + " is reading a book: " + book.read());
    }

    public void lookAt(Picture pic) {
        System.out.println(getName() + " is looking at picture: " + pic.lookAt());
    }

    @Override
    public String toString() {
        return "Human. " + getName() + ". "
                + " Origin: " + getOrigin().toString()
                + " Place of living: " + getPol().toString()
                + " Direction: " + getDirection().toString();
    }

}