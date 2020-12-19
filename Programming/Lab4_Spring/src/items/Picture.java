package items;

import java.util.Objects;

public final class Picture {
    private final String name;
    private final String author;
    private final String bookDesc;

    public Picture(String name, String author, String bookDesc) {
        this.name = name;
        this.author = author;
        this.bookDesc = bookDesc;
    }

    public String lookAt() {
        return "items.Picture " + name + " by " + author + " has been reviewed.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return Objects.equals(name, picture.name) &&
                Objects.equals(author, picture.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author);
    }

    @Override
    public String toString() {
        return "Name: " + name + "Author: " + author;
    }
}
