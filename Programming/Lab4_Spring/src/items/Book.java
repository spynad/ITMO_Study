package items;

import java.util.Objects;

public final class Book{
    String name;

    public Book(String name) {
        this.name = name;
    }

    public String read() {
        return "items.Book " + name + " is completed.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Name of the book: " + name;
    }
}
