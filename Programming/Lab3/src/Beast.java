public class Beast extends MulticellularOrganism{
    private boolean corruptState;
    private Wing wing = new Wing("Wing");

    Beast(String name) {
        super(name);
        setPartsLocation(true);
    }


    public void setCorruptState(boolean corrupt) {
        corruptState = corrupt;
        System.out.println("The beast is now corrupted.");
    }

    public void fly() {
        wing.use("fly");
    }

    public void swim(){
        wing.use("swim");
    }
}
