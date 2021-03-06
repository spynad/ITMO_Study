import java.util.Objects;

public class Picture {
    private String name;
    private String author;

    Picture(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String lookAt() {
        return "Picture " + name + " by " + author + " has been reviewed.";
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
