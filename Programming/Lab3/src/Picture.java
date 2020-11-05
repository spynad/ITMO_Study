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
}
