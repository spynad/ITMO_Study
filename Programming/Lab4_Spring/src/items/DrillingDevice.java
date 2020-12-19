package items;

import creatures.Human;
import places.Cave;

import java.util.Objects;

public class DrillingDevice {
    private final String author = "Pebody";

    public void drill(Human human, Cave cave) {
        if (cave.isCanBeEntered()) {
            System.out.println("There is no need to drill a hole in this cave");
        }
        else {
            System.out.println("The hole has been successfully drilled. Humans now can go there");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(author);
    }

    @Override
    public String toString() {
        return "DrillingDevice{" +
                "author='" + author + '\'' +
                '}';
    }
}
