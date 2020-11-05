public class Book{
    String name;

    Book(String name) {
        this.name = name;
    }

    public String read() {
        return "Book " + name + " is completed.";
    }
}
